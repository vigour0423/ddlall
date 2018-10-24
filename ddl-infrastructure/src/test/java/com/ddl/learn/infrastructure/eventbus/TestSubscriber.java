package com.ddl.learn.infrastructure.eventbus;

import org.springframework.beans.factory.annotation.Autowired;

public class TestSubscriber {

	@Autowired
	EventPublisher eventPublisher;

	@Subscribe
	public void handleTestEvent(TestEvent event) {
		System.out.println("This is sync event subscriber" + ".ThreadId:" + Thread.currentThread().getId());
		AsyncTestEvent event2 = new AsyncTestEvent();
		eventPublisher.publish(event2);
	}

	public EventPublisher getEventPublisher() {
		return eventPublisher;
	}

	public void setEventPublisher(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}
}
