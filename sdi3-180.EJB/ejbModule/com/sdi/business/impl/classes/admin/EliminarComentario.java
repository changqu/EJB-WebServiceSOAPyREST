package com.sdi.business.impl.classes.admin;

import com.sdi.infrastructure.Factories;
import com.sdi.persistence.RatingDao;

public class EliminarComentario {
	
	public void eliminar(Long id){
		RatingDao rDao = Factories.persistence.createRatingDao();
		rDao.delete(id);
	}
}
