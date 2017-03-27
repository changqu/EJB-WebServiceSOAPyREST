package com.sdi.client.accion;

import com.sdi.ws.AdminService;
import com.sdi.ws.EjbAdminServiceService;
import com.sdi.ws.User;
import com.sdi.ws.UserStatus;

public class DeshabilitarUsuario{

	private AdminService adminService = new EjbAdminServiceService().getAdminServicePort();
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
