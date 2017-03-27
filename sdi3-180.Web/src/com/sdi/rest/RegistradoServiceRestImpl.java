package com.sdi.rest;

import java.util.List;

import com.sdi.business.RegistradoService;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.exception.EntityNotFoundException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.User;

public class RegistradoServiceRestImpl implements RegistradoServiceRest{

	RegistradoService service = Factories.services.getRegistradoService();
	
	@Override
	public User getUser(String login) throws EntityNotFoundException {
		return service.findUserByLogin(login);
	}
	
	@Override
	public List<Trip> getViajes(String login) throws EntityNotFoundException {
		User u = service.findUserByLogin(login);
		return service.misViajes(u);
	}

	@Override
	public List<User> getSolicitantes(Long tripId) throws EntityNotFoundException {
		Trip t = service.findTripById(tripId);
		return service.obtenerSolicitantes(t);
	}

	@Override
	public void saveSeat(Seat seat) throws EntityNotFoundException, EntityAlreadyExistsException {
		Trip t = service.findTripById(seat.getTripId());
		User u = service.findUserById(seat.getUserId());
		service.confirmarPasajero(t, u);
	}

}
