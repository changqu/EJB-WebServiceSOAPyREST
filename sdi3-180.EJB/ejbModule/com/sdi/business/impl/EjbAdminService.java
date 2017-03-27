package com.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.classes.admin.DeshabilitarUsuario;
import com.sdi.business.impl.classes.admin.DestinoComentario;
import com.sdi.business.impl.classes.admin.EliminarComentario;
import com.sdi.business.impl.classes.admin.ListadoComentario;
import com.sdi.business.impl.classes.admin.ListadoUsuarios;
import com.sdi.business.impl.classes.admin.UserLogin;
import com.sdi.business.impl.classes.admin.ViajesParticipado;
import com.sdi.business.impl.classes.admin.ViajesPromvido;
import com.sdi.model.Rating;
import com.sdi.model.User;

@Stateless  
@WebService(name="AdminService") 
public class EjbAdminService implements LocalAdminService, RemoteAdminService{

	@Override
	public List<User> getUsuarios() {
		return new ListadoUsuarios().listado();
	}

	@Override
	public int getViajesPromovido(User u) {
		return new ViajesPromvido().contar(u);
	}

	@Override
	public int getViajesParticipado(User u) {
		return new ViajesParticipado().contar(u);
	}

	@Override
	public void deshabilitarUsuario(Long id) {
		new DeshabilitarUsuario().deshabilitar(id);
	}

	@Override
	public List<Rating> getComentarios() {
		return new ListadoComentario().listado();
	}
	
	@Override
	public String getDestino(Long id){
		return new DestinoComentario().destino(id);
	}
	
	@Override
	public String getUserLogin(Long id){
		return new UserLogin().obtenerLogin(id);
	}

	@Override
	public void eliminarComentario(Long id) {
		new EliminarComentario().eliminar(id);
	}

}
