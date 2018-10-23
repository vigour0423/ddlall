package com.ddl.learn.eventbus.components;

import com.ddl.egg.exception.BusinessException;
import com.ddl.learn.eventbus.EventPublisher;

import com.ddl.learn.eventbus.IgnoreForceConsume;
import com.ddl.learn.log.datachange.DataChangeTracer;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;

public class ChannelEventPublisher implements EventPublisher {

	private final MessageChannel channel;

	public ChannelEventPublisher(MessageChannel channel) {
		this.channel = channel;
	}

	@Override
	public void publish(Object event) {
		Message<?> message = MessageBuilder.withPayload(event).build();
		boolean isTracing = DataChangeTracer.isTracing();
		if (!isTracing) {
			initTrace(event);
		}
		try {
			channel.send(message);
		} catch (MessageDeliveryException e) {
			if (e.getCause() instanceof BusinessException) {
				throw (BusinessException) e.getCause();
			}
			String desc = e.getMessage();
			if (StringUtils.contains(desc, "No channel resolved by router")) {
				IgnoreForceConsume i = event.getClass().getAnnotation(IgnoreForceConsume.class);
				if (i == null) {
					throw e;
				}
			}
			throw e;
		} finally {
			if (!isTracing) {
				DataChangeTracer.stopTrace();
			}
		}
	}

	private void initTrace(Object event) {
		try {
			Map<String, Object> map = (Map<String, Object>) BeanUtils.describe(event);
			String transactionId = map.containsKey("transactionId") ? String.valueOf(map.get("transactionId")) : "";
			String module = map.containsKey("module") ? String.valueOf(map.get("module")) : "";
			String function = map.containsKey("functionName") ? String.valueOf(map.get("functionName")) : "";
			String changeType = map.containsKey("changeType") ? String.valueOf(map.get("changeType")) : "";
			String reason = map.containsKey("reason") ? String.valueOf(map.get("reason")) : "";
			String operatorName = map.containsKey("operatorName") ? String.valueOf(map.get("operatorName")) : "";
			String operatorAccount = map.containsKey("operatorAccount") ? String.valueOf(map.get("operatorAccount")) : "";
			DataChangeTracer.startTrace(transactionId, module, function, changeType, reason, operatorName, operatorAccount);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}
