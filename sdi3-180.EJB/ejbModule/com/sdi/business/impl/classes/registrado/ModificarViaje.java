package com.sdi.business.impl.classes.registrado;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.persistence.TripDao;

public class ModificarViaje {
	
	public void modificarViaje(Trip t) throws EntityAlreadyExistsException{
		TripDao tDao = Factories.persistence.createTripDao();
		tDao.update(t);
	}

}
