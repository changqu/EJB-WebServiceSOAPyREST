package com.sdi.business.integration;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.sdi.business.impl.LocalRegistradoService;
import com.sdi.business.RegistradoService;
import com.sdi.model.Trip;
import com.sdi.model.User;

@MessageDriven(
	activationConfig = { 
		@ActivationConfigProperty(
			propertyName = "destination", 
			propertyValue = "queue/TripQueue") 
	}
)//indicación del canal de cola que escucha, la misma que enviarMensaje del cli-Msg
public class TripMessageListener implements MessageListener{
	
	@EJB(beanInterface = LocalRegistradoService.class)
	private RegistradoService registradoService;
	
	@EJB (lookup = "java:global/sdi3-180/sdi3-180.EJB/"
			+ "EjbMessageValido!" //la clase que busca
			+ "com.sdi.business.integration.TripMessage")//la interfaz del clase que busca
	private TripMessage mensajeValido;			// = new EjbMessageValido();
	
	@EJB (lookup = "java:global/sdi3-180/sdi3-180.EJB/"
			+ "EjbMessageEquivocado!" //la clase que busca
			+ "com.sdi.business.integration.TripMessage") //la interfaz del clase que busca
	private TripMessage mensajeEquivocado;		// = new EjbMessageEquivocado();
	
	@Override
	public void onMessage(Message msg) {
//		System.out.println("TripMessageListener: Msg received");
		try {
			seleccionarCanal(msg).enviar(msg);
		} catch (JMSException jex) {
			// here we should log the exception
			jex.printStackTrace();
		}
	}
	
	//Filtrar mensajes enviados y elegir canal para estos mensajes
	private TripMessage seleccionarCanal(Message msg) throws JMSException{
		MapMessage m = (MapMessage) msg;
		Trip t = registradoService.findTripById(m.getLong("tripId"));
		User u = registradoService.findUserById(m.getLong("userId"));
		//viaje que envio mensaje no existe
		if(t==null)
			return mensajeEquivocado;
		//usuario es promotor del viaje
		for(Trip trip: registradoService.misViajes(u))
			if(trip.getId().equals(t.getId()))
				return mensajeValido;
		//usuario es participante del viaje
		for(Trip trip: registradoService.viajesComoParticiapante(u))
			if(trip.getId().equals(t.getId()))
				return mensajeValido;
		//viaje valido, pero el usuario no es ni promotor ni participantes del viaje
		return mensajeEquivocado;	
	}

}
