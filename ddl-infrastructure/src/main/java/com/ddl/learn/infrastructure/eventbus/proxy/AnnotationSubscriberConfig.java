package com.ddl.learn.infrastructure.eventbus.proxy;


import com.ddl.learn.infrastructure.eventbus.EventSubscriber;
import com.ddl.learn.infrastructure.eventbus.components.EventBusBuilder;
import com.ddl.learn.infrastructure.eventbus.config.EventBusConfigurer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.util.Collection;

/**
 * description:<有关@Subscribe注解的相关配置>
 * @author dongdongliu
 * @date 2018/10/24 16:37
 */
@Configuration
public class AnnotationSubscriberConfig implements EventBusConfigurer, Ordered, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Bean
    public EventSubscriberBeanPostProcessor eventSubscriberBeanFactoryPostProcessor() {
        return new EventSubscriberBeanPostProcessor();
    }

    @Override
    public void configureEventBus(EventBusBuilder eventBusBuilder) {
        Collection<? extends EventSubscriber> eventSubscribers =
                ((ListableBeanFactory) beanFactory).getBeansOfType(EventSubscriberProxy.class).values();

        eventBusBuilder.addSubscribers(eventSubscribers);
    }
}
