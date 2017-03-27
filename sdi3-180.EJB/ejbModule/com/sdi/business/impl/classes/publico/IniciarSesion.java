package com.sdi.business.impl.classes.publico;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.persistence.UserDao;

public class IniciarSesion {

	public User validar(String identificador, String contrasena) {
		UserDao uDao = Factories.persistence.createUserDao();
		User u = uDao.findByLogin(identificador);
		if(u!=null && u.getPassword().equals(contrasena)){
			Log.info("El usuario [%s] se ha validado correctamente", u.getLogin());
			return u;//usuario encontrado
		}
		return null;//usuario no encontrado
	}

}
