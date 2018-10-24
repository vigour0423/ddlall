package com.ddl.learn.infrastructure.eventbus.proxy;

import java.util.stream.Stream;

interface SubscriberScanner {

    Stream<EventSubscriberInfo> scanForSubscriberMethods(String beanName, Class<?> beanType);
}
