package com.sdi.client.accion;

import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

import com.sdi.business.RegistradoService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.client.util.Jndi;
import com.sdi.model.User;
import com.sdi.model.Trip;

//Recibir mensajes
public class RecibirMensajes {

	private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String TOPIC = "jms/topic/MessageValido"; //usa misma canal topic para recibir mensaje como para messageValido
											//igual que nombre de exported.			
	private RegistradoService registradoService = new RemoteEjbServicesLocator().getRegistradoService();
	private static Scanner scanner;
	
	private static TopicConnection con;

	public void run(User user) throws JMSException {
		scanner = new Scanner(System.in);
		do{
			System.out.println("Introducir \"0\" para salir de esta opción");
			System.out.println("Introducir Id del viaje para recibir menasje:");
			System.out.println(">");
			String line = scanner.nextLine();//esperar recibir un input
			if (line.equals("0"))
				return;
			Long tripId = Long.parseLong(line);
			if(!viajePermitido(tripId, user)){
				System.err.println("No tienes permiso para ver mensaje de este viaje");
				return;
			}
			initialize(user, tripId);
			do{
				System.out.println("Introducir \"0\" para salir de esta opción");
				System.out.println(">");
				line = scanner.nextLine();
				if (line.equals("0"))
					break;
			}while(true);
			con.close();
		}while(true);
	}
	
	//comprobar si el usuario tiene permiso para ver mensaje de este viaje
	private boolean viajePermitido(Long tripId, User user){
		for(Trip t : registradoService.misViajes(user))
			if(t.getId().equals(tripId))
				return true;
		for(Trip t : registradoService.viajesComoParticiapante(user))
			if(t.getId().equals(tripId))
				return true;
		return false;
	}

	private void initialize(User user, Long tripId) throws JMSException {
		TopicConnectionFactory factory = (TopicConnectionFactory) Jndi.find(JMS_CONNECTION_FACTORY);
		Topic topic = (Topic) Jndi.find(TOPIC);
		con = factory.createTopicConnection("sdi", "password");
		con.setClientID(user.getId() + "-" + tripId);
		TopicSession session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		//crea el subscriber sobre marcha
		MessageConsumer consumer = session.createDurableSubscriber(topic, "trip");
		consumer.setMessageListener(new TripMessage(tripId));
		con.start();
	}

	public class TripMessage implements MessageListener {
		
		private Long tripId;
		
		public TripMessage(Long tripId){
			this.tripId = tripId;
		}
		
		@Override
		public void onMessage(Message msg) {
			try {
				if(comprobarMensaje(msg))
					processMessage(msg);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
		//Encontrar mensajes para el viaje seleccionado
		private boolean comprobarMensaje(Message msg) throws JMSException{
			return ((MapMessage)msg).getLong("tripId") == tripId;
		}

		private void processMessage(Message msg) throws JMSException {
			if (!(msg instanceof MapMessage)) {
				System.out.println("Message not of expected type");
				return;
			}
			MapMessage mmsg = (MapMessage) msg;
			System.out.println(mmsg.getString("userLogin") + ": " + mmsg.getString("mensajeEnviado"));
		}
	}

}
