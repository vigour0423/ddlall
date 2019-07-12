package com.ddl.infrastructure.eventbus.proxy;


import com.ddl.infrastructure.eventbus.EventSubscriber;

/**
 * description:<订阅通用代理>
 * @author dongdongliu
 * @date 2018/10/24 16:26
 */
interface SubscriberProxyClassGenerator {

    Class<? extends EventSubscriber> generate(EventSubscriberInfo subscriberInfo, ClassLoader classLoader);
}
