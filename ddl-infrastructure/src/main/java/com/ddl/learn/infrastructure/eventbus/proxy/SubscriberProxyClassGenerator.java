package com.ddl.learn.infrastructure.eventbus.proxy;


import com.ddl.learn.infrastructure.eventbus.EventSubscriber;

interface SubscriberProxyClassGenerator {

    Class<? extends EventSubscriber> generate(EventSubscriberInfo subscriberInfo, ClassLoader classLoader);
}
