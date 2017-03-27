package com.sdi.client.accion;

import java.util.List;

import com.sdi.business.AdminService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.User;

public class ListarUsuarios{

	private AdminService adminService = new RemoteEjbServicesLocator().getAdminService();

	public void execute(){
		List<User> users = adminService.getUsuarios();
		System.out.println("ID - Nombre - Apellidos - Login - Password - Email - Estado - Viaje promovidos - Viajes participado");
		int viajePromovido = 0;
		int viajeParticipado = 0;
		for (User u: users) {
			viajePromovido = adminService.getViajesPromovido(u);
			viajeParticipado = adminService.getViajesParticipado(u);
			System.out.printf("%d - %s - %s - %s - %s - %s - %s - %d - %d\n", 
					u.getId(), u.getName(), u.getSurname(), 
					u.getLogin(), u.getPassword(), 
					u.getEmail(), u.getStatus().toString(),
					viajePromovido, viajeParticipado);			
		}	
		
	}

}
