package com.sdi.business.impl.classes.publico;

import alb.util.log.Log;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.persistence.UserDao;

public class RegistrarUsuario {

	public void registrar(User nuevoUsuario) throws EntityAlreadyExistsException{
		UserDao uDao = Factories.persistence.createUserDao();
		if(uDao.findByLogin(nuevoUsuario.getLogin())!=null){
			throw new EntityAlreadyExistsException();
		}else{
			uDao.save(nuevoUsuario);
			Log.info("Nuevo usuario [%s] registrado", nuevoUsuario.getLogin());
		}
	}

}
