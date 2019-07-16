package com.ddl.infrastructure.eventbus;

;
import com.ddl.infrastructure.eventbus.components.EventBusBuilder;
import com.ddl.infrastructure.eventbus.config.EnableEventBus;
import com.ddl.infrastructure.eventbus.config.EventBusConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableEventBus
public class TestConfig implements EventBusConfigurer {

    @Bean
    TestSubscriber testSubscriber() {
        return new TestSubscriber();
    }

    @Bean
    AsyncTestSubscriber asyncTestSubscriber() {
        return new AsyncTestSubscriber();
    }

    @Override
    public void configureEventBus(EventBusBuilder eventBusBuilder) {
        //            eventBusBuilder.eventPoller = Pollers.fixedDelay(100)
    }
}
