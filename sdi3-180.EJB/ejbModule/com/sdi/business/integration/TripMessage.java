package com.sdi.business.integration;

import javax.ejb.Local;
import javax.jms.JMSException;
import javax.jms.Message;

@Local
public interface TripMessage {
	void enviar(Message msg) throws JMSException;
}
