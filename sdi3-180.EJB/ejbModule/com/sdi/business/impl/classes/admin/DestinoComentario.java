package com.sdi.business.impl.classes.admin;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;

public class DestinoComentario {
	
	public String destino(Long id){
		Trip t = Factories.persistence.createTripDao().findById(id);
		return t.getDestination().getCity();
	}
}
