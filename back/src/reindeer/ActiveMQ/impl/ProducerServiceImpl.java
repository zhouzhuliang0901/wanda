package reindeer.ActiveMQ.impl;

import javax.annotation.Resource;
import javax.jms.ObjectMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reindeer.ActiveMQ.ProducerService;

@Service
@Transactional
public class ProducerServiceImpl implements ProducerService{
	
	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;
    
	@Override
	public void sendOceanQueue(String queueName, String message) {
		jmsMessagingTemplate.convertAndSend(new ActiveMQQueue(queueName), message);
	}

	@Override
	public void sendOceanTopic(String queueName, String message) {
		jmsMessagingTemplate.convertAndSend(new ActiveMQTopic(queueName), message);
	}

	@Override
	public void sendOceanQueue(String queueName, ObjectMessage obj) {
		jmsMessagingTemplate.convertAndSend(new ActiveMQQueue(queueName), obj);
	}
	
	@Override
	public void sendOceanTopic(String queueName, ObjectMessage obj) {
		jmsMessagingTemplate.convertAndSend(new ActiveMQQueue(queueName), obj);
	}
}
