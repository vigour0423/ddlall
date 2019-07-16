package com.ddl.infrastructure.eventbus.proxy;


import com.ddl.infrastructure.eventbus.Subscribe;

import java.lang.reflect.Method;

/**
 * description:<用来存放含有@Subscribe注解bean的相关信息>
 * @author dongdongliu
 * @date 2018/10/25 9:32
 */
final class ReflectiveEventSubscriberInfo implements EventSubscriberInfo {

    private final String beanName;

    private final Class<?> beanClass;

    private final Method subscriberMethod;

    private final Subscribe annotation;


    public ReflectiveEventSubscriberInfo(String beanName, Class<?> beanClass, Method subscriberMethod, Subscribe annotation) {
        this.beanName = beanName;
        this.beanClass = beanClass;
        this.subscriberMethod = subscriberMethod;
        this.annotation = annotation;
    }


    @Override
    public String getBeanName() {
        return beanName;
    }


    @Override
    public Class<?> getBeanClass() {
        return beanClass;
    }


    @Override
    public String getSubscriberMethodName() {
        return subscriberMethod.getName();
    }


    @Override
    public Class<?> getEventType() {
        return subscriberMethod.getParameterTypes()[0];
    }


    @Override
    public boolean isAsync() {
        return annotation.async();
    }
}
