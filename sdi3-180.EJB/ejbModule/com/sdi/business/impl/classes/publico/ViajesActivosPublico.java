package com.sdi.business.impl.classes.publico;

import java.util.ArrayList;
import java.util.List;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.persistence.TripDao;

public class ViajesActivosPublico {

	public List<Trip> obtenerViajes(){
		List<Trip> viajesActivo = new ArrayList<Trip>();
		TripDao tDao = Factories.persistence.createTripDao();
		viajesActivo = tDao.findOpen();
		Log.info("obteniendo lista de viajes activos");
		return viajesActivo;
	}
	
}
