package com.sdi.business.impl.classes.admin;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;

public class UserLogin {
	
	public String obtenerLogin(Long id){
		User u = Factories.persistence.createUserDao().findById(id);
		return u.getLogin();
	}

}
