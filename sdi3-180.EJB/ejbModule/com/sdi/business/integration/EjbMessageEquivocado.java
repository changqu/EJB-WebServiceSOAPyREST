package com.sdi.business.integration;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import alb.util.log.Log;

@Stateless
public class EjbMessageEquivocado implements TripMessage{

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory factory;
	
	@Resource(mappedName = "java:/queue/MessageEquivocado")//redirecciona a un cola de mensaje equivocado
	private Destination queue;	//canal de cola destino de mapped anterior
	
	@Override
	public void enviar(Message msg) throws JMSException { //con throws, puede quitar try catch
		Connection con = factory.createConnection("sdi", "password");
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer sender = session.createProducer(queue);
		con.start();
		sender.send(msg);
		con.close();
		
		//mostrar mensaje erreneo enviado a modo log por consola
		MapMessage m = (MapMessage) msg;
		Log.info("Mensaje equivocado del %s para viaje %d, se a redirecciona a la cola de mensajes erroneos.",
				m.getString("userLogin"), m.getLong("tripId"));
	}

}
