package com.ddl.infrastructure.eventbus.config;

import com.ddl.infrastructure.eventbus.components.ChannelEventPublisher;
import com.ddl.infrastructure.eventbus.components.EventBusBuilderImpl;
import com.ddl.infrastructure.eventbus.EventPublisher;
import com.ddl.infrastructure.eventbus.proxy.AnnotationSubscriberConfig;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;

import java.util.List;

/**
 * description:<spring bean配置>
 * @author dongdongliu
 * @date 2018/10/25 10:05
 */
@Configuration
@EnableIntegration
@Import({AnnotationSubscriberConfig.class, EventBusTransactionConfig.class})
public class EventBusConfig {

    private List<EventBusConfigurer> eventBusConfigurers;

    @Autowired(required = false)
    public EventBusConfig(List<EventBusConfigurer> eventBusConfigurers) {
        this.eventBusConfigurers = eventBusConfigurers;
    }

    @Bean
    public MessageChannel eventBusInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public EventPublisher eventPublisher() {
        return new ChannelEventPublisher(eventBusInputChannel());
    }

    @Bean
    public IntegrationFlow eventBus() {
        EventBusBuilderImpl builder = new EventBusBuilderImpl(eventBusInputChannel());

        //反转列表，使具有最高优先级的配置器调用最后一个。
        Lists.reverse(eventBusConfigurers)
                .forEach(c -> c.configureEventBus(builder));

        return builder.build();
    }
}
