package com.qx.testactivemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class TestTopic {
	
	@Test
	public void testProducer() throws Exception{
		
		//创建连接工厂 ---选择apache包下的而不是spring包下的
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://10.0.111.120:61616");
		//获取连接
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		//获取session
		/*参1： boolean transacted 是否开启事务，默认都是false, true是开启*/
		/*参2：int acknowledgeMode 应答模式  参2必须在 参数1是false的情况下才有效*/
		/**
		 * 参2 应答模式，代表响应回执的感觉，因为我需要知道你是否收到，没有收到再给你发一次
		 */
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//创建消息类型对象
		Topic topic = session.createTopic("topicname");//public interface Topic extends Destination
		//创建生产者
		MessageProducer producer = session.createProducer(topic);
		//创建消息
		TextMessage textMessage = session.createTextMessage("这是群发的消息，我是一颗秋天的树");
		//发送消息
		producer.send(textMessage);
		producer.close();
		session.close();
		connection.close();
	}
	
	
	@Test
	public void TestConsumer() throws Exception{
		//创建连接工厂
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://10.0.111.120:61616");
		//获取连接
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		//获取session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//创建消息类型对象
		Topic topic = session.createTopic("topicname");
		//创建消费者
		MessageConsumer consumer = session.createConsumer(topic);
		consumer.setMessageListener(new MessageListener() {
			
			/*
			 * 发送的消息有什么用？
			 * 我给你带着消息过来了，你想怎么处理消息是你的事情(non-Javadoc)
			 * 发送者和接收者都是我们自己来写的，因此规则可以我们自己来定，比如消息里面放着某某某东西
			 * 这个东西分别代表什么意思，你去要做什么事情，我们同一规定好，规定好之后就可以做什么事情了。
			 * 比如说我们这样定义规则：
			 * messeage里面放一个json字符串，里面有个type字段，用于告诉你做什么事情，还有个message字段，
			 * 你做这个事情可能需要一些数据，比如1.假如我们定好规则，type字段的值0代表静态化详情页面，
			 * messeage字段的值1代表我要静态化1号商品的详情页面。
			 * 
			 * 然后在另外一边就可以将1号商品的详情页面给静态化掉。
			 */
			@Override
			public void onMessage(Message message) {//{"type":"0",message:"1"}
				// TODO Auto-generated method stub
				TextMessage textMessage=(TextMessage) message;
				try {
					System.out.println(textMessage.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		System.out.println("topicaaaaaaaaa");
		System.in.read();
		consumer.close();
		session.close();
		connection.close();
	}
	@Test
	public void TestConsumer2() throws Exception{
		//创建连接工厂
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://10.0.111.120:61616");
		//获取连接
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		//获取session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//创建消息类型对象
		Topic topic = session.createTopic("topicname");
		//创建消费者
		MessageConsumer consumer = session.createConsumer(topic);
		consumer.setMessageListener(new MessageListener() {
			
			/*
			 * 发送的消息有什么用？
			 * 我给你带着消息过来了，你想怎么处理消息是你的事情(non-Javadoc)
			 * 发送者和接收者都是我们自己来写的，因此规则可以我们自己来定，比如消息里面放着某某某东西
			 * 这个东西分别代表什么意思，你去要做什么事情，我们同一规定好，规定好之后就可以做什么事情了。
			 * 比如说我们这样定义规则：
			 * messeage里面放一个json字符串，里面有个type字段，用于告诉你做什么事情，还有个message字段，
			 * 你做这个事情可能需要一些数据，比如1.假如我们定好规则，type字段的值0代表静态化详情页面，
			 * messeage字段的值1代表我要静态化1号商品的详情页面。
			 * 
			 * 然后在另外一边就可以将1号商品的详情页面给静态化掉。
			 */
			@Override
			public void onMessage(Message message) {//{"type":"0",message:"1"}
				// TODO Auto-generated method stub
				TextMessage textMessage=(TextMessage) message;
				try {
					System.out.println(textMessage.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		System.out.println("topicaaaaaaaaa");
		System.in.read();
		consumer.close();
		session.close();
		connection.close();
	}
}
