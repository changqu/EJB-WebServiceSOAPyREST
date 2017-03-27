package com.sdi.client.accion;

import com.sdi.business.PublicoService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.User;

public class IdentificarUsuario {
	
	private PublicoService publicoService = new RemoteEjbServicesLocator().getPublicoService();
	
	public User identificar(String login, String password){
		return publicoService.iniciarSesion(login, password);
	}
}
