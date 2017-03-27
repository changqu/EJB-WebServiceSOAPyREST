package com.sdi.client.accion;

import com.sdi.business.AdminService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.User;
import com.sdi.model.UserStatus;

public class DeshabilitarUsuario{

	private AdminService adminService = new RemoteEjbServicesLocator().getAdminService();
	private User user = null;
	
	
	public void execute(Long id) {
		comprobarId(id);
		if (user == null){
			System.err.println("Usuario inexistente, no puede deshabilitarle");
			return;
		}
		if (user.getStatus() == UserStatus.CANCELLED){ 
			System.err.println("El usuario ya ha sido deshabilitado");
			return;
		}
		adminService.deshabilitarUsuario(id);
		System.out.println("Usuario deshabilitado corectamente");
	}
	
	public void comprobarId(Long id){
		for(User u : adminService.getUsuarios()){
			if(id.equals(u.getId()))
				user=u;
		}
	}

}
