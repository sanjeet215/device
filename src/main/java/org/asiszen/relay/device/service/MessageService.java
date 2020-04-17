package org.asiszen.relay.device.service;

import javax.jms.Queue;

import org.asiszen.relay.device.model.DeviceData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MessageService {

	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	private Queue queue;

	public void publishMessagetoQueue(DeviceData data) {

		ObjectMapper Obj = new ObjectMapper();
		
		try {
			
			jmsMessagingTemplate.convertAndSend(this.queue, Obj.writeValueAsString(data));
		} catch (Exception ep) {
			logger.error("Error while publishing the messages");
			logger.error(ep.getLocalizedMessage());
		}
	}
}
