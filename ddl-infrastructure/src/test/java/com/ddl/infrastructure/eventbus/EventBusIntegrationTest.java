package com.ddl.infrastructure.eventbus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:eventbus-test.xml" })
public class EventBusIntegrationTest {

	@Autowired
	EventPublisher eventPublisher;

	@Autowired
	TestSubscriber testSubscriber;

	@Autowired
	AsyncTestSubscriber asyncTestSubscriber;

	@Test
	public void testSyncEvent() {
		System.out.println("This is async event Test start." + ".ThreadId:" + Thread.currentThread().getId());

		TestEvent event = new TestEvent();
		eventPublisher.publish(event);
		System.out.println("This is async event Test finish." + ".ThreadId:" + Thread.currentThread().getId());


	}


	@Test
	public void testAsyncEvent() {
		System.out.println("This async event Test start." + ".ThreadId:" + Thread.currentThread().getId());

		AsyncTestEvent event = new AsyncTestEvent();
		eventPublisher.publish(event);
		System.out.println("This async event Test finish." + ".ThreadId:" + Thread.currentThread().getId());

	}
}
