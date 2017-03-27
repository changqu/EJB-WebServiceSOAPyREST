package com.sdi.client.accion;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.sdi.client.util.Jndi;
import com.sdi.model.User;

//Enviar mensajes
public class EnviarMensajes {
	
	private static final String JMS_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String QUEUE = "jms/queue/TripQueue";//usan las mismas canal de cola con TripMessageListener
																//por lo tanto se escuchan las cosas que hace aqui
	private static Scanner scanner;
	private static Long tripId;
	
	private static MessageProducer sender;
	private static Connection con;
	private static Session session;
	
	public void run(User user) throws JMSException{
		scanner = new Scanner(System.in);
		do{
			System.out.println("Introducir \"0\" para salir de esta opción");
			System.out.println("Introducir Id del viaje para enviar menasje:");
			System.out.println(">");
			String line = scanner.nextLine();//esperar recibir un input
			if (line.equals("0"))
				return;
			tripId = Long.parseLong(line);
			initialize();
			do{
				System.out.println("Introducir \"0\" para salir de esta opción");
				System.out.println("Introducir menasje:");
				System.out.println(">");
				line = scanner.nextLine();//esperar recibir un input
				if (line.equals("0"))
					break;//si aqui usa return acaba ejecucion del metodo, hay que meter un break
				sender.send(createMessage(user, line));
			}while(true);
			con.close();
		}while(true);
	}

	private void initialize() throws JMSException {
		ConnectionFactory factory = (ConnectionFactory) Jndi.find(JMS_CONNECTION_FACTORY);
		Destination queue = (Destination) Jndi.find(QUEUE);
		con = factory.createConnection("sdi", "password");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		sender = session.createProducer(queue);
		con.start();
	}

	private MapMessage createMessage(User user, String mensaje) throws JMSException {
		MapMessage msg = session.createMapMessage();
		msg.setLong("tripId", tripId);
		msg.setLong("userId", user.getId());
		msg.setString("userLogin", user.getLogin());
		msg.setString("mensajeEnviado", mensaje);
		System.out.println("MensajeEnviado: " + mensaje); //mostrar mensaje enviado, showMessage()
		return msg;
	}
	
}
