package com.ddl.infrastructure.eventbus.proxy;


import com.ddl.infrastructure.eventbus.EventSubscriber;
import com.google.common.hash.Hashing;
import org.apache.commons.codec.Charsets;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.Type;
import org.springframework.cglib.core.AbstractClassGenerator;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.cglib.core.CodeEmitter;
import org.springframework.cglib.core.Constants;
import org.springframework.cglib.core.Signature;
import org.springframework.messaging.Message;

/**
 * description:<订阅通用代理>
 * @author dongdongliu
 * @date 2018/10/26 15:24
 */
public class CglibSubscriberProxyClassGenerator implements SubscriberProxyClassGenerator {

    @Override
    public Class<? extends EventSubscriber> generate(EventSubscriberInfo subscriberInfo, ClassLoader classLoader) {
        return new Generator(subscriberInfo, classLoader)
                .create();
    }


    private static class Generator extends AbstractClassGenerator {

        private static final Source SOURCE = new Source(CglibSubscriberProxyClassGenerator.class.getName());

        private static final Type MESSAGE_TYPE = Type.getType(Message.class);

        private static final Signature GETPAYLOAD_SIGNATURE =
                new Signature("getPayload", Constants.TYPE_OBJECT, Constants.TYPES_EMPTY);

        private final EventSubscriberInfo subscriberInfo;

        private final ClassLoader classLoader;


        private Generator(EventSubscriberInfo subscriberInfo, ClassLoader classLoader) {
            super(SOURCE);
            this.subscriberInfo = subscriberInfo;
            this.classLoader = classLoader;
        }


        @Override
        protected ClassLoader getDefaultClassLoader() {
            return classLoader;
        }


        @Override
        protected Object firstInstance(Class type) throws Exception {
            return type;
        }


        @Override
        protected Object nextInstance(Object instance) throws Exception {
            return instance;
        }

        /**
         * description:<生成cglib代理类>
         * @author dongdongliu
         * @date 2018/10/26 15:48
         */
        @SuppressWarnings("unchecked")
        public Class<? extends EventSubscriber> create() {

            return (Class<? extends EventSubscriber>) super.create(generateKey());
        }


        private String generateKey() {
            return Hashing.goodFastHash(32).newHasher()
                    .putString(subscriberInfo.getBeanClass().getName(), Charsets.UTF_8)
                    .putString(subscriberInfo.getSubscriberMethodName(), Charsets.UTF_8)
                    .putString(subscriberInfo.getEventType().getName(), Charsets.UTF_8)
                    .hash()
                    .toString();
        }


        private String makeProxyClassName() {
            return subscriberInfo.getBeanClass().getName() + "$$EventSubscriber_" + generateKey();
        }


        @Override
        public void generateClass(ClassVisitor classVisitor) throws Exception {

            ClassEmitter classEmitter = new ClassEmitter(classVisitor);
            classEmitter.begin_class(Constants.V1_5,
                    Constants.ACC_PUBLIC,
                    makeProxyClassName(),
                    Constants.TYPE_OBJECT,
                    new Type[]{Type.getType(EventSubscriberProxy.class)},
                    null);

            //构建代理委托
            createDelegateField(classEmitter);
            createConstructor(classEmitter);
            createGetEventTypeMethod(classEmitter);

            createIsAsyncMethod(classEmitter);
            //创建事件订阅
            createHandleMessageMethod(classEmitter);

            classEmitter.end_class();
        }


        private void createDelegateField(ClassEmitter classEmitter) {
            classEmitter.declare_field(
                    Constants.ACC_PRIVATE | Constants.ACC_FINAL,
                    "delegate",
                    Type.getType(subscriberInfo.getBeanClass()),
                    null);
        }


        private void createConstructor(ClassEmitter classEmitter) {
            Signature signature = new Signature(
                    Constants.CONSTRUCTOR_NAME, Type.VOID_TYPE,
                    new Type[]{Type.getType(subscriberInfo.getBeanClass())});

            CodeEmitter emitter = classEmitter.begin_method(Constants.ACC_PUBLIC, signature, null);
            emitter.load_this();
            emitter.dup();
            emitter.super_invoke_constructor();
            emitter.load_arg(0);
            emitter.putfield("delegate");
            emitter.return_value();
            emitter.end_method();
        }


        private void createGetEventTypeMethod(ClassEmitter classEmitter) {
            Signature signature = new Signature(
                    "getEventType", Constants.TYPE_CLASS, Constants.TYPES_EMPTY);

            CodeEmitter emitter = classEmitter.begin_method(Constants.ACC_PUBLIC, signature, null);
            emitter.visitLdcInsn(Type.getType(subscriberInfo.getEventType()));
            emitter.return_value();
            emitter.end_method();
        }


        private void createIsAsyncMethod(ClassEmitter classEmitter) {
            Signature signature = new Signature(
                    "isAsync", Type.BOOLEAN_TYPE, Constants.TYPES_EMPTY);

            CodeEmitter emitter = classEmitter.begin_method(Constants.ACC_PUBLIC, signature, null);
            emitter.visitLdcInsn(subscriberInfo.isAsync() ? 1 : 0);
            emitter.return_value();
            emitter.end_method();
        }


        private void createHandleMessageMethod(ClassEmitter classEmitter) {
            Signature signature = new Signature(
                    "handleMessage", Type.VOID_TYPE, new Type[]{MESSAGE_TYPE});
            Signature subscriberMethodSignature = new Signature(
                    subscriberInfo.getSubscriberMethodName(),
                    Type.VOID_TYPE,
                    new Type[]{Type.getType(subscriberInfo.getEventType())});

            CodeEmitter emitter = classEmitter.begin_method(Constants.ACC_PUBLIC, signature, null);

            emitter.load_this();
            emitter.getfield("delegate");

            emitter.load_arg(0);
            emitter.invoke_interface(MESSAGE_TYPE, GETPAYLOAD_SIGNATURE);
            emitter.checkcast(Type.getType(subscriberInfo.getEventType()));
            emitter.invoke_virtual(Type.getType(subscriberInfo.getBeanClass()), subscriberMethodSignature);
            emitter.return_value();
            emitter.end_method();
        }
    }
}
