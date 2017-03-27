package com.sdi.client.accion;

import java.util.List;

import com.sdi.ws.AdminService;
import com.sdi.ws.EjbAdminServiceService;
import com.sdi.ws.Rating;

public class ListarComentarios{

	private AdminService adminService = new EjbAdminServiceService().getAdminServicePort();
	
	public void execute() {
		System.out.println("ID - ViajeId - DestinoViaje - ComentadoPor - Sobre - Valoracion - Comentario");
		List<Rating> comentarios = adminService.getComentarios();
		//ordenar de lo mas reciente a menos.
		for (int i=comentarios.size()-1; i >= 0; i--) {
			System.out.printf("%d - %d - %s - %s - %s - %d - %s\n", 
					comentarios.get(i).getId(), comentarios.get(i).getSeatAboutTripId(), adminService.getDestino(comentarios.get(i).getSeatAboutTripId()), 
					adminService.getUserLogin(comentarios.get(i).getSeatFromUserId()), adminService.getUserLogin(comentarios.get(i).getSeatAboutUserId()), 
					comentarios.get(i).getValue(), comentarios.get(i).getComment());			
		}
		
	}

}
