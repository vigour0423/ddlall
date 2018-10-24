package com.ddl.learn.infrastructure.eventbus.proxy;

import java.util.stream.Stream;

/**
 * description:<订阅扫描>
 * @author dongdongliu
 * @date 2018/10/24 16:27
 */
interface SubscriberScanner {

    Stream<EventSubscriberInfo> scanForSubscriberMethods(String beanName, Class<?> beanType);
}
