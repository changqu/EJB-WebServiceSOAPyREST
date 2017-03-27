package com.sdi.client.accion;

import com.sdi.business.AdminService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.Rating;

public class EliminarComentario{

	private AdminService adminService = new RemoteEjbServicesLocator().getAdminService();
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
