package com.sdi.business.timer;

import java.util.Date;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import alb.util.log.Log;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.persistence.TripDao;

@Singleton
public class ViajeMantenimiento {
	
	@Schedule(minute="*/3", hour="*")//se ejecuta cada 3 minutos
	private void updateTrips() {
		TripDao tDao = Factories.persistence.createTripDao();
		List<Trip> viajes = tDao.findAll();
		for (Trip t: viajes) 
			if (cambiarEstado(t))
				tDao.update(t);
	}
	
	private boolean cambiarEstado(Trip t) {
		if (t.getClosingDate().before(new Date()) && t.getArrivalDate().after(new Date()) &&
				t.getStatus() != TripStatus.CLOSED) {
			t.setStatus(TripStatus.CLOSED);
			Log.info("El viaje [%s] ha cambiado su estado al CLOSED", t.getId());
			return true;
		}
		if (t.getArrivalDate().before(new Date()) && t.getStatus() != TripStatus.DONE) {
			t.setStatus(TripStatus.DONE);
			Log.info("El viaje [%s] ha cambiado su estado al DONE", t.getId());
			return true;
		}
		return false;
	}

}
