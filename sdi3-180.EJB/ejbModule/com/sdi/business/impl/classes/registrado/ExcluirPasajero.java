package com.sdi.business.impl.classes.registrado;

import java.util.Date;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.TripStatus;

import alb.util.log.Log;

import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;

public class ExcluirPasajero {

	public void excluirPasajero(Trip t, User u){
		SeatDao sDao = Factories.persistence.createSeatDao();
		TripDao tDao = Factories.persistence.createTripDao();
		//puede excluir usuario en open, closed y cancelled
		if(t.getClosingDate().after(new Date())){
			Seat s=sDao.findByUserAndTrip(u.getId(), t.getId());
			s.setStatus(SeatStatus.EXCLUDED);
			sDao.update(s);//guarda s al BD con status actualizado, importante
			if(t.getAvailablePax()<t.getMaxPax()-1)//plaza libre sea menor que (max-1)
				t.setAvailablePax(t.getAvailablePax()+1);
			if(t.getStatus()==TripStatus.CLOSED)//si este caso justo el viaje esta lleno y quitas pasajero
				t.setStatus(TripStatus.OPEN);	//el viaje cambia de estado al open
			tDao.update(t);
			Log.info("Se ha excluido correctamente el usuario con id [%s] de la viaje [%s]", u.getId(), t.getId());
		}else{
			Log.info("No se ha podido excluir pasajero dado que ya pasado el fecha de cierre");
		}
	}
}
