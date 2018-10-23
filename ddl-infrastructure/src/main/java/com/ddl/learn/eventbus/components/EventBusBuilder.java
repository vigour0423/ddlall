package com.ddl.learn.eventbus.components;

import com.ddl.learn.eventbus.EventSubscriber;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.core.PollerSpec;
import org.springframework.integration.store.ChannelMessageStore;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Collections;

public interface EventBusBuilder {

    EventBusBuilder setName(String name);

    EventBusBuilder setMessageStore(ChannelMessageStore messageStore);

    EventBusBuilder setTransactionManager(PlatformTransactionManager transactionManager);

    EventBusBuilder addSubscribers(Iterable<? extends EventSubscriber> subscribers);

    default EventBusBuilder addSubscriber(EventSubscriber subscriber) {
        return addSubscribers(Collections.singleton(subscriber));
    }

    EventBusBuilder setEventPoller(PollerSpec eventPoller);

    IntegrationFlow build();
}
