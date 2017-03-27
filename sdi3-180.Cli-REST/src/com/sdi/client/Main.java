package com.sdi.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sdi.client.model.Seat;
import com.sdi.client.model.SeatStatus;
import com.sdi.client.model.Trip;
import com.sdi.client.model.User;

public class Main {

	private static final String REST_SERVICE_URL = "http://localhost:8280/sdi3-180.Web/rest/RegistradoServiceRs";

	private static BufferedReader in;
	
	private static Authenticator authenticator;// = new Authenticator("sdi", "password");
	
	//esos valores tiene que ser statics 
	private static String login;
	private static String password;
	
	private static List<Trip> trips;
	private static List<User> users;
	
	public static void main(String[] args) throws IOException {
		do{
			if(new Main().identificarUsuario())
				break;
			System.err.println("Usuario inexistente\n");
		}while(true); 
		System.out.println("Usuario " + login + " ha identificado correctamente");
		new Main().run();
	}
	
	private boolean identificarUsuario() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Inicio");
		System.out.println("Login:");
		System.out.println(">");
		String[] line = in.readLine().split(" ");
		login = line[0];
		System.out.println("Password:");
		System.out.println(">");
		line = in.readLine().split(" ");
		password = line[0];
		//creamos authentificator para rellenar lo que pide en AuthFilter
		//quedamos con que este usuario que hace pasar filtro es el usuario que 
		//inicia el servicio de REST.
		authenticator = new Authenticator(login, password);
		//si cumple eso devuelve true, caso contrario devuelve false
		return getUser()!=null;
	}
	
	private User getUser() {
		Response response = ClientBuilder.newClient()
				.register(authenticator).target(REST_SERVICE_URL)
				.path(login).request()
				.accept(MediaType.APPLICATION_JSON).get();
		//si cumple eso devuelve User.class, caso contrario devuelve null
		return response.getStatus() != 401 ? 
				response.readEntity(User.class) : null;
	}

	private void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Comandos: 0, 1, 2, 3");
		do{
			System.out.println("\nOpción:");
			System.out.println("0.Salir");
			System.out.println("1.Listar viajes promovidos y aún abiertos");
			System.out.println("2.Ver solicitantes del viaje");
			System.out.println("3.Confirmar pasajero del viaje");
			System.out.println(">");
			String[] line = in.readLine().split(" ");
			if (line[0].equals("0")){
				System.out.println("\nHasta pronto!");
				return;
			}
			else if (line[0].equals("1"))
				mostrarTrips(restGetTrips());
			else if (line[0].equals("2"))
				opcionVerSolicitantes();
			else if (line[0].equals("3"))
				opcionConfirmarPasajero();
			else
				System.err.println("Comando desconocido-_-");
		}while(true);
	}

	private List<Trip> restGetTrips() {
		GenericType<List<Trip>> listm = new GenericType<List<Trip>>() {};
		return ClientBuilder.newClient()
				.register(authenticator).target(REST_SERVICE_URL)
				.path("promotor/"+ login).request()
				.accept(MediaType.APPLICATION_JSON).get()
				.readEntity(listm);
	}
	
	private void mostrarTrips(List<Trip> trips) {
		System.out.println("ID - Origen - destino - plazaLibre");
		for (Trip t: trips) {
			System.out.printf("%d - %s - %s - %d\n", 
					t.getId(), t.getDeparture().getCity(), 
					t.getDestination().getCity(), t.getAvailablePax());			
		}
		System.out.println("\nEND OF LIST TRIP");
	}
	
	private void opcionVerSolicitantes() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		do{
			System.out.println("\nIntroducir \"0\" para salir de esta opción");
			System.out.println("Introducir id del viaje para ver solicitantes");
			System.out.println(">");
			String[] line = in.readLine().split(" ");
			if (line[0].equals("0"))
				break;
			trips=restGetTrips();
			if(tripIdValido(Long.parseLong(line[0])))
				mostrarSolicitantes(restGetSolicitantes(Long.parseLong(line[0])));
			else
				System.err.println("El viaje seleccionado no es suyo o ya esta cerrado");
		}while(true);
	}

	private List<User> restGetSolicitantes(Long id) {
		GenericType<List<User>> listm = new GenericType<List<User>>() {};
		return ClientBuilder.newClient()
				.register(authenticator).target(REST_SERVICE_URL)
				.path("tripSolicitantes/" + id).request()
				.accept(MediaType.APPLICATION_JSON).get()
				.readEntity(listm);
	}
	
	private void mostrarSolicitantes(List<User> restGetSolicitantes) {
		System.out.println("ID - Nombre - Apellidos - Login - Email - Estado");
		for (User u: restGetSolicitantes) {
			System.out.printf("%d - %s - %s - %s - %s - %s\n", 
					u.getId(), u.getName(), u.getSurname(), 
					u.getLogin(), u.getEmail(), 
					u.getStatus().toString());			
		}	
		System.out.println("\nEND OF LIST USER");
	}
	
	private void opcionConfirmarPasajero() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		do{
			System.out.println("\nIntroducir \"0\" para salir de esta opción");
			System.out.println("Introducir id del viaje para confirmar pasajero");
			System.out.println(">");
			String[] line = in.readLine().split(" ");
			if (line[0].equals("0"))
				break;
			String tripId=line[0];
			trips=restGetTrips();
			if(!tripIdValido(Long.parseLong(tripId))){
				System.err.println("El viaje seleccionado no es suyo o ya esta cerrado, saliendo de la opción");
				return;
			}
			System.out.println("Introducir id del usuario para confirmar pasajero");
			System.out.println(">");
			line = in.readLine().split(" ");
			String userId=line[0];
			users=restGetSolicitantes(Long.parseLong(tripId));
			if(!userIdValido(Long.parseLong(userId))){
				System.err.println("Solicitante seleccionado no pertenece al viaje, saliendo de la opción");
				return;
			}
			confirmarPasajero(Long.parseLong(tripId), Long.parseLong(userId));
			System.out.println("Solicitante confirmado correctamente en el viaje");
		}while(true);	
	}

	//con esto vale ya que llama al metodo del web de 
	//put con el paramatro de tipo Seat que pasa
	private void confirmarPasajero(Long tripId, Long userId) {
		Seat seat = new Seat();
		seat.setComment(null);
		seat.setStatus(SeatStatus.ACCEPTED);
		seat.setTripId(tripId);
		seat.setUserId(userId);
		//hacer put
		ClientBuilder.newClient()
				.register(authenticator)
				.target(REST_SERVICE_URL).request()
				.put(Entity.entity(seat, MediaType.APPLICATION_JSON));
	}
	
	private boolean tripIdValido(Long id) {
		for(Trip t: trips)
			if(t.getId().equals(id))
				return true;
		return false;	
	}
	
	private boolean userIdValido(Long id) {
		for(User u: users)
			if(u.getId().equals(id))
				return true;
		return false;
	}

}
