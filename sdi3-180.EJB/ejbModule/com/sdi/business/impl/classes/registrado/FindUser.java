package com.sdi.business.impl.classes.registrado;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;

public class FindUser {
	
	public User findLogin(String login){
		return Factories.persistence.createUserDao().findByLogin(login);
	}
	
	public User findId(Long id){
		return Factories.persistence.createUserDao().findById(id);
	}
	
}
