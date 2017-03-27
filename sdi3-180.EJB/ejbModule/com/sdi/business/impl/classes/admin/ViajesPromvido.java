package com.sdi.business.impl.classes.admin;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.model.User;

public class ViajesPromvido {
	
	public int contar(User u){
		List<Trip> viajes = Factories.persistence.createTripDao().findByPromoter(u.getId());
		return viajes.size();
	}
}
