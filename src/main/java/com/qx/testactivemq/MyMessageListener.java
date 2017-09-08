package com.qx.testactivemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 采用spring去配置ActiveMQ时候需要用到，因为<bean>标签中要指明实现类
 * @author dell
 *
 */
public class MyMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage textMessage=(TextMessage) message;
		try {
			System.out.println("---->"+textMessage.getText());//文本消息的文本内容
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
