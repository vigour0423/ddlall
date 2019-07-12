package com.ddl.infrastructure.eventbus;

/**
 * description:<事件发布>
 * @author dongdongliu
 * @date 2018/10/25 9:52
 */
public interface EventPublisher {

    /**
     * description:<发布>
     * @author dongdongliu
     * @date 2018/10/25 11:20
     * @parame vent
     */
    void publish(Object event);
}
