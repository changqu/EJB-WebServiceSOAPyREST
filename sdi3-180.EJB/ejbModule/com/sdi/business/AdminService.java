package com.sdi.business;

import java.util.List;

import com.sdi.model.Rating;
import com.sdi.model.User;

public interface AdminService {
	 
	List<User> getUsuarios();    
	int getViajesPromovido(User u);
	int getViajesParticipado(User u);
	void deshabilitarUsuario(Long id);
	
	List<Rating> getComentarios();
	String getDestino(Long id);
	String getUserLogin(Long id);   
	void eliminarComentario(Long id);
}
