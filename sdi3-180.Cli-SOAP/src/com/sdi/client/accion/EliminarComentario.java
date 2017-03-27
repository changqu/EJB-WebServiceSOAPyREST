package com.sdi.client.accion;

import com.sdi.ws.AdminService;
import com.sdi.ws.EjbAdminServiceService;
import com.sdi.ws.Rating;

public class EliminarComentario{

	private AdminService adminService = new EjbAdminServiceService().getAdminServicePort();
	private Rating rating = null;
	
	public void execute(Long id) {
		comprobarId(id);
		if (rating == null){ 
			System.err.println("Comentario inexistente, no puede eliminar");
			return;
		}
		adminService.eliminarComentario(id);
		System.out.println("Comentario eliminado correctamente");
	}
	
	public void comprobarId(Long id){
		for(Rating r : adminService.getComentarios()){
			if(id.equals(r.getId()))
				rating=r;
		}
	}

}
