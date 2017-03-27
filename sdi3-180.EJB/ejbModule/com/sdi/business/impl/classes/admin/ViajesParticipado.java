package com.sdi.business.impl.classes.admin;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Seat;
import com.sdi.model.User;

public class ViajesParticipado {
	
	public int contar(User u){
		List<Application> applications = Factories.persistence.createApplicationDao().findByUserId(u.getId());
		List<Seat> seats = Factories.persistence.createSeatDao().findByUser(u.getId());
		return applications.size()+seats.size();
	}
}
