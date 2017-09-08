package com.qx.testactivemq;


import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class TestSpring {
	
	@Test
	public void testSpringQueue(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		/**
		 * 两个方法可选：
		 * JmsTemplate bean = context.getBean(JmsTemplate.class);
		 * JmsTemplate template = (JmsTemplate) context.getBean("template");
		 */
		JmsTemplate template = (JmsTemplate) context.getBean("template");
		
		Queue queue=(Queue) context.getBean("queue");
		MessageCreator messageCreator=new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				TextMessage textMessage = session.createTextMessage("这是spring整合的点对点消息，再不分手就得买七夕礼物了");
				return textMessage;
			}
			
		};
		template.send(queue,messageCreator);
		
	}
	
	/**
	 * 接收消息
	 */
	/*实际项目中，生产者和消费者分别配置在不同的文件中，而不是配置在同一个spring配置文件中*/
	@Test
	public void testSpringConsumer(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSpingTopic(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		JmsTemplate template = context.getBean(JmsTemplate.class);
		Topic topic = context.getBean(Topic.class);
		MessageCreator messageCreator=new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				TextMessage textMessage=session.createTextMessage("这是spring整合的广播消息，再不买七夕礼物就分手了");
				return textMessage;
			}
		};
		template.send(topic, messageCreator);
		
	}
}
