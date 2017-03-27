package com.sdi.business.impl.classes.admin;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Rating;

public class ListadoComentario {
	
	public List<Rating> listado(){
		return Factories.persistence.createRatingDao().findAll();
	}
}
