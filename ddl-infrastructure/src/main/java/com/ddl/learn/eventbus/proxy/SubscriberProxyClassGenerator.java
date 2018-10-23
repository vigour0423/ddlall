package com.ddl.learn.eventbus.proxy;


import com.ddl.learn.eventbus.EventSubscriber;

interface SubscriberProxyClassGenerator {

    Class<? extends EventSubscriber> generate(EventSubscriberInfo subscriberInfo, ClassLoader classLoader);
}
