package com.ddl.learn.eventbus.proxy;

import java.util.stream.Stream;

interface SubscriberScanner {

    Stream<EventSubscriberInfo> scanForSubscriberMethods(String beanName, Class<?> beanType);
}
