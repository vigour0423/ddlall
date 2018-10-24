package com.ddl.learn.infrastructure.eventbus.proxy;


import com.ddl.learn.infrastructure.eventbus.EventSubscriber;

/**
 * description:<订阅通用代理>
 * @author dongdongliu
 * @date 2018/10/24 16:26
 */
interface SubscriberProxyClassGenerator {

    Class<? extends EventSubscriber> generate(EventSubscriberInfo subscriberInfo, ClassLoader classLoader);
}
