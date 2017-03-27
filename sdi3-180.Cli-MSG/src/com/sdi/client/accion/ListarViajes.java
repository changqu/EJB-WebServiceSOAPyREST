package com.sdi.client.accion;

import java.util.List;

import com.sdi.business.RegistradoService;
import com.sdi.business.impl.RemoteEjbServicesLocator;
import com.sdi.model.Trip;
import com.sdi.model.User;

public class ListarViajes {

	private RegistradoService registradoService = new RemoteEjbServicesLocator().getRegistradoService();
	
	public void mostrarViajes(User u){
		System.out.println("Viajes promovidos");
		imprimirViaje(registradoService.misViajes(u));
		
		System.out.println("Viajes como participante");
		imprimirViaje(registradoService.viajesComoParticiapante(u));
	}
	
	private void imprimirViaje(List<Trip> trips) {
		System.out.println("ID - Origen - destino - plazaLibre");
		for (Trip t: trips) 
			System.out.printf("%d - %s - %s - %d\n", 
					t.getId(), t.getDeparture().getCity(), 
					t.getDestination().getCity(), t.getAvailablePax());			
	}
	
}
