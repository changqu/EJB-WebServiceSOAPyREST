package com.sdi.business.impl.classes.registrado;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

public class FindTrip {
	
	public Trip find(Long id){
		return Factories.persistence.createTripDao().findById(id);
	}

}
