package com.ddl.learn.infrastructure.eventbus.proxy;

import com.ddl.learn.infrastructure.eventbus.Subscribe;
import com.ddl.learn.infrastructure.eventbus.util.MethodSignature;
import com.ddl.learn.infrastructure.eventbus.util.Reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * description:<反射式用户扫描，用来扫面有@Subscribe的对象>
 * @author dongdongliu
 * @date 2018/10/24 16:46
 */
final class ReflectiveSubscriberScanner implements SubscriberScanner {

    private final Logger logger = LoggerFactory.getLogger(ReflectiveSubscriberScanner.class);

    /**
     * description:<将含有订阅注解的bean存入信息返回>
     * @param beanName, beanType
     * @author dongdongliu
     * @date 2018/10/26 15:26
     */
    @Override
    public Stream<EventSubscriberInfo> scanForSubscriberMethods(String beanName, Class<?> beanType) {

        return Reflection.allMethods(beanType)
                .filter(m -> m.isAnnotationPresent(Subscribe.class))
                .filter(this::hasExactlyOneParameter)
                .filter(this::hasVoidReturnType)
                .collect(Collectors.groupingBy(MethodSignature::of))
                .entrySet().stream()
                .map(e -> makeSubscriberInfo(beanName, beanType, e.getKey(), e.getValue()));
    }

    private EventSubscriberInfo makeSubscriberInfo(String beanName, Class<?> beanType,
                                                   MethodSignature signature, List<Method> annotatedMethods) {

        Method methodToCall = Reflection.allMethodsMatching(beanType, signature)
                .min(Reflection.methodOverridesComparator())
                .get();

        Subscribe annotationToUse = Collections.max(annotatedMethods, Reflection.methodOverridesComparator())
                .getAnnotation(Subscribe.class);

        return new ReflectiveEventSubscriberInfo(beanName, methodToCall.getDeclaringClass(),
                methodToCall, annotationToUse);
    }

    private boolean hasExactlyOneParameter(Method method) {
        if (method.getParameterCount() != 1) {
            logger.error("Method \"{}\" is annotated with @Subscribe but does not qualify as a subscriber "
                    + "because it has {} parameters (must have exactly 1)", method, method.getParameterCount());
            return false;
        }

        return true;
    }

    private boolean hasVoidReturnType(Method method) {
        if (method.getReturnType() != Void.TYPE) {
            logger.error("Method \"{}\" is annotated with @Subscribe but does not qualify as a subscriber "
                    + "because it has a non-void return type", method);
            return false;
        }

        return true;
    }
}
