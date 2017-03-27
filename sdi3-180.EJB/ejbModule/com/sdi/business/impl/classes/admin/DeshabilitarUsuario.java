package com.sdi.business.impl.classes.admin;

import java.util.List;

import com.sdi.infrastructure.Factories;
import com.sdi.model.SeatStatus;
import com.sdi.model.User;
import com.sdi.model.Seat;
import com.sdi.model.UserStatus;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.UserDao;

public class DeshabilitarUsuario {
	
	public void deshabilitar(Long id){
		UserDao uDao = Factories.persistence.createUserDao();
		SeatDao sDao = Factories.persistence.createSeatDao();
		User u = uDao.findById(id);
		u.setStatus(UserStatus.CANCELLED);
		uDao.update(u);
		List<Seat> seats = sDao.findByUser(id);
		for(Seat s: seats){
			s.setStatus(SeatStatus.EXCLUDED);
			sDao.update(s);
		}
	}
}
