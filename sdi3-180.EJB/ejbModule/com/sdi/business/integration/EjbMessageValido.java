package com.sdi.business.integration;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

@Stateless
public class EjbMessageValido implements TripMessage {

	@Resource(mappedName = "java:/ConnectionFactory")
	private TopicConnectionFactory factory;

	@Resource(mappedName = "java:/topic/MessageValido")//redirecciona la mensaje a un cola nuevo
	private Topic topic; //canal topic destino de mapped anterior

	@Override
	public void enviar(Message msg) throws JMSException {
		TopicConnection con = factory.createTopicConnection("sdi", "password");
		TopicSession session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer sender = session.createPublisher(topic);
		con.start();
		sender.send(msg);
		con.close();		
	}

}
