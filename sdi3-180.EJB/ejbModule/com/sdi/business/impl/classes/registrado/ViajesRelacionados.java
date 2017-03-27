package com.sdi.business.impl.classes.registrado;

import java.util.ArrayList;
import java.util.List;

import com.sdi.model.TripStatus;
import com.sdi.model.Application;
import com.sdi.model.Seat;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.persistence.ApplicationDao;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;

public class ViajesRelacionados {
	
	private TripDao tDao = Factories.persistence.createTripDao();
	private SeatDao sDao = Factories.persistence.createSeatDao();
	private ApplicationDao aDao = Factories.persistence.createApplicationDao();
	
	public List<Trip> viajesRelacionados(User u){
		List<Trip> viajesRelacionados = new ArrayList<Trip>();
		for(Application a: aDao.findByUserId(u.getId()))
			viajesRelacionados.add(tDao.findById(a.getTripId()));	
		for(Seat s: sDao.findByUser(u.getId())){
			Trip t = tDao.findById(s.getTripId());
			if(t.getStatus()!=TripStatus.DONE && !t.getPromoterId().equals(u.getId()))
				viajesRelacionados.add(t);
		}
		Log.info("Obteniendo viajes relacionado del usuario [%s]", u.getLogin());
		return viajesRelacionados;
	}
	
	public List<Trip> viajesComoParticipante(User u){
		List<Trip> viajesRelacionados = new ArrayList<Trip>();
		for(Seat s: sDao.findByUser(u.getId())){
			Trip t = tDao.findById(s.getTripId());
			if(t.getStatus()!=TripStatus.DONE && !t.getPromoterId().equals(u.getId()))
				viajesRelacionados.add(t);
		}
		Log.info("Obteniendo viajes como participantes del usuario [%s]", u.getLogin());
		return viajesRelacionados;
	}
	
}
