package com.sdi.rest;

import java.util.HashSet;
import java.util.Set;

import com.sdi.rest.AuthFilter;

public class Application extends javax.ws.rs.core.Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> res = new HashSet<>();
		res.add(RegistradoServiceRestImpl.class);
		res.add(AuthFilter.class);//Metemos un filtro de autentificacion para que solamente los
		return res;				//usuarios registrado en el sistema y proporcionando correctamente
	}							//su login y contraseña puede acceder al servicio de rest.

}
