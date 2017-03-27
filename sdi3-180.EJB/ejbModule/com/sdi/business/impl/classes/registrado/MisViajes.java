package com.sdi.business.impl.classes.registrado;

import java.util.ArrayList;
import java.util.List;

import com.sdi.infrastructure.Factories;

import alb.util.log.Log;

import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.User;
import com.sdi.persistence.TripDao;

public class MisViajes {
	
	public List<Trip> misViajes(User u){
		TripDao tDao = Factories.persistence.createTripDao();
		List<Trip> viajes = tDao.findAll();
		List<Trip> misViajes = new ArrayList<Trip>();
		for(Trip t: viajes){//mis viajes en estado no Done
			if(t.getPromoterId().equals(u.getId()) && t.getStatus()!=TripStatus.DONE)
				misViajes.add(t);
		}
		Log.info("Obteniendo viajes del usuario [%s]", u.getLogin());
		return misViajes;
	}
}
