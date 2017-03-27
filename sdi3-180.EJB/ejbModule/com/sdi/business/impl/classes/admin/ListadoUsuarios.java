package com.sdi.business.impl.classes.admin;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;

import java.util.List;

public class ListadoUsuarios {

	public List<User> listado(){
		return Factories.persistence.createUserDao().findAll();
	}
}
