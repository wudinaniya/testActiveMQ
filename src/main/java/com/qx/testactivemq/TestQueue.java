package com.qx.testactivemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class TestQueue {
	public static void main(String[] args) {
		
	}
	
	/**
	 * 测试生产者
	 * @throws Exception 
	 */
	@Test
	public void testQueueproducer() throws Exception{
		//创建连接工厂，目的和服务端建立连接(连接工厂选择apche包下的，而非spring包下的)
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://10.0.111.120:61616");//参数是服务器地址  tcp/ip协议
		//创建连接
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		//创建Session
		/*参1： boolean transacted 是否开启事务，默认都是false, true是开启*/
		/*参2：int acknowledgeMode 应答模式  参2必须在 参数1是false的情况下才有效*/
		/**
		 * 参2 应答模式，代表响应回执的感觉，因为我需要知道你是否收到，没有收到再给你发一次
		 */
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); //自动应答模式
		//创建消息对象. 参数是消息类型的名字，相当于约束，只有另外一方是它的情况下，才可以收到消息，类似于发短信的时候填写的手机号，对方必须是这个手机号才行
		Queue queue = session.createQueue("pointtopoint");
		//创建生产者
		MessageProducer producer = session.createProducer(queue); //public interface Queue extends Destination
		//发送消息
		TextMessage textMessage = session.createTextMessage("明年今日（十年），在富士山下（爱情转移），黑鸟手拿白玫瑰（红玫瑰）对 他说了一句好久不见，其实不如不见");
		producer.send(textMessage);
		//释放资源
		producer.close();
		session.close();
		connection.close();
	}
	
	/**
	 * 接收者/消费者
	 * @throws Exception 
	 */
	@Test
	public void testConsumer() throws Exception{
		//创建连接工厂
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://10.0.111.120:61616");
		//获取连接
		Connection connection = connectionFactory.createConnection();
		//启动连接
		connection.start();
		//创建session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//创建一个消息类型对象
		Queue queue = session.createQueue("pointtopoint");
		//创建消费者
		MessageConsumer consumer = session.createConsumer(queue);
		//。设置监听，接收消息
		consumer.setMessageListener(new MessageListener() { //消息监听是异步的
			/**
			 * 收到消息的时候
			 */
			@Override
			public void onMessage(Message message) {
				// TODO Auto-generated method stub
				TextMessage textMessage = (TextMessage) message;
				String text=null;
				try {
					text = textMessage.getText();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("--------->"+text);
			}
		});
		//尽量不要使用while true,因为这个while true要执行的，会抢占cpu,最起码得用个睡眠，或者是wait()
		System.in.read();//阻塞
		//到这里，java代码结束了，jvm就退出了
		consumer.close();
		session.close();
		connection.close();
	}
}
