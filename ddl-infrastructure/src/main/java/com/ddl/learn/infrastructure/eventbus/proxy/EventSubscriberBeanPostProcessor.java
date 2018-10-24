package com.ddl.learn.infrastructure.eventbus.proxy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.stream.Stream;

/**
 * description:<事件订阅后置处理器>
 * @author dongdongliu
 * @date 2018/10/24 16:33
 */
public class EventSubscriberBeanPostProcessor implements BeanDefinitionRegistryPostProcessor, BeanFactoryAware {

    private static final Logger logger = LoggerFactory.getLogger(EventSubscriberBeanPostProcessor.class);

    private ConfigurableListableBeanFactory beanFactory;

    private final SubscriberScanner subscriberScanner;

    /**
     * 订阅代理
     */
    private final SubscriberProxyClassGenerator proxyClassGenerator;


    public EventSubscriberBeanPostProcessor() {
        this(new ReflectiveSubscriberScanner(), new CglibSubscriberProxyClassGenerator());
    }


    EventSubscriberBeanPostProcessor(SubscriberScanner subscriberScanner,
                                     SubscriberProxyClassGenerator proxyClassGenerator) {
        this.subscriberScanner = subscriberScanner;
        this.proxyClassGenerator = proxyClassGenerator;
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        if (!(beanFactory instanceof ConfigurableListableBeanFactory)) {
            throw new IllegalArgumentException(EventSubscriberBeanPostProcessor.class.getSimpleName()
                    + " requires a ConfigurableListableBeanFactory");
        }
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    /**
     * description:<注册到bean的声明>
     * @return <void>
     * @author dongdongliu
     * @date 2018/10/24 16:13
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        Stream.of(beanFactory.getBeanDefinitionNames())
                .map(name -> new BeanNameAndType(name, beanFactory.getType(name)))
                .peek(bean -> logger.trace(
                        "Inspecting bean {} of type {} for event subscriber methods",
                        bean.getName(), bean.getType()))
                .flatMap(bean ->
                        subscriberScanner.scanForSubscriberMethods(bean.getName(), bean.getType()))
                .map(this::makeProxyBeanDefinition)
                .forEach(bean -> registry.registerBeanDefinition(bean.getName(), bean.getDefinition()));
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }


    private BeanNameAndDefinition makeProxyBeanDefinition(EventSubscriberInfo subscriberInfo) {
        return new BeanNameAndDefinition(
                makeProxyBeanName(subscriberInfo),
                createProxyBeanDefinition(subscriberInfo));
    }


    private String makeProxyBeanName(EventSubscriberInfo subscriberInfo) {
        return subscriberInfo.getBeanName() + "##eventSubscriber_"
                + subscriberInfo.getSubscriberMethodName() + "_"
                + subscriberInfo.getEventType().getSimpleName();
    }


    private BeanDefinition createProxyBeanDefinition(EventSubscriberInfo subscriberInfo) {

        Class<?> proxyClass = proxyClassGenerator.generate(subscriberInfo, beanFactory.getBeanClassLoader());

        return BeanDefinitionBuilder.genericBeanDefinition(proxyClass)
                .addConstructorArgReference(subscriberInfo.getBeanName())
                .getBeanDefinition();
    }

    /**
     * description:<bean的名称和class类型>
     * @author dongdongliu
     * @date 2018/10/24 16:30
     */
    private static class BeanNameAndType {

        private final String name;

        private final Class<?> type;

        private BeanNameAndType(String name, Class<?> type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public Class<?> getType() {
            return type;
        }
    }


    private static class BeanNameAndDefinition {

        private final String name;

        private final BeanDefinition definition;

        private BeanNameAndDefinition(String name, BeanDefinition definition) {
            this.name = name;
            this.definition = definition;
        }


        public String getName() {
            return name;
        }

        public BeanDefinition getDefinition() {
            return definition;
        }
    }
}
