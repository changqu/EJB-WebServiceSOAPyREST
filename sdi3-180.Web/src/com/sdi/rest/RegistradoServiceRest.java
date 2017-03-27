package com.sdi.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;

@Path("/RegistradoServiceRs") //el rest delante esta en web.xml, cli-rest aprovechan metodos creados de aqui 
public interface RegistradoServiceRest {
	
	//Obtener usuario apartir del login para posteriormente usarse en autentificacion
	@GET
	@Path("{login}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	User getUser(@PathParam("login") String login) throws EntityNotFoundException;
	
	//listar viajes promovidos y aun abiertos del usuario
	@GET
	@Path("promotor/{login}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<Trip> getViajes(@PathParam("login") String login) throws EntityNotFoundException;
	
	//obtener participantes de un viaje
	@GET
	@Path("tripSolicitantes/{tripId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	List<User> getSolicitantes(@PathParam("tripId") Long tripId) throws EntityNotFoundException;
	
	//confirmar pasajero, con Seat, encontramos solicitud y viaje, solo permite un parametro
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	void saveSeat(Seat seat) throws EntityNotFoundException, EntityAlreadyExistsException;
	
	//@POST with consumes, no Patch, igual que @PUT
	//@DELETE with Path, no Consumes ni Produces
	
}
