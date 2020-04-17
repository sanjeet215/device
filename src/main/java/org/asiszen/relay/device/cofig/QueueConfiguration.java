package org.asiszen.relay.device.cofig;

import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
public class QueueConfiguration {

	@Value("${activemq.broker-url}")
	private String brokerUrl;

	@Value("${destination.order}")
	private String orderDestination;

	@Bean
	public ActiveMQConnectionFactory senderConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);
		activeMQConnectionFactory.setAlwaysSessionAsync(true);
		activeMQConnectionFactory.setMaxThreadPoolSize(50);
		activeMQConnectionFactory.setWatchTopicAdvisories(false);
		return activeMQConnectionFactory;
	}

	@Bean
	public CachingConnectionFactory cachingConnectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(senderConnectionFactory());
		cachingConnectionFactory.setSessionCacheSize(10);
		return cachingConnectionFactory;
	}

	@Bean
	public Destination orderDestination() {
		return new ActiveMQQueue(orderDestination);
	}

	// Serialize message content to json using TextMessage
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}


	@Bean
	public JmsTemplate orderJmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory());
		jmsTemplate.setDefaultDestination(orderDestination());

		return jmsTemplate;
	}

	
	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {

		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(senderConnectionFactory());
		factory.setConcurrency("1-1");

		return factory;

	}
}
