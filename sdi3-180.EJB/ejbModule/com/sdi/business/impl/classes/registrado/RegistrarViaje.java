package com.sdi.business.impl.classes.registrado;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.persistence.TripDao;

public class RegistrarViaje {
	
	public void registrarViaje(Trip t) throws EntityAlreadyExistsException{
		TripDao tDao = Factories.persistence.createTripDao();
		tDao.save(t);
	}
}
