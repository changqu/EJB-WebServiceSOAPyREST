package com.sdi.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.JMSException;

import com.sdi.client.accion.EnviarMensajes;
import com.sdi.client.accion.IdentificarUsuario;
import com.sdi.client.accion.ListarViajes;
import com.sdi.client.accion.RecibirMensajes;
import com.sdi.model.User;

public class Main {

	private static User user;
	private static BufferedReader in;

	public static void main(String[] args) throws IOException, JMSException {
		do{
			if(new Main().identificarUsuario())
				break;
			System.err.println("\nUsuario inexistente");
		}while(true); 
		System.out.println("Usuario " + user.getLogin() + " ha identificado correctamente");
		new Main().run();
	}
	
	private boolean identificarUsuario() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Inicio");
		System.out.println("Login:");
		System.out.println(">");
		String[] line = in.readLine().split(" ");
		String login = line[0];
		System.out.println("Password:");
		System.out.println(">");
		line = in.readLine().split(" ");
		String password = line[0];
		user = new IdentificarUsuario().identificar(login, password);
		//si cumple eso devuelve true, caso contrario devuelve false
		return user!=null;
	}
	
	private void run() throws JMSException, IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Comandos: 0, 1, 2, 3");
		do{
			System.out.println("Opción: ");
			System.out.println("0.Salir");
			System.out.println("1.Ver viajes involucrado");
			System.out.println("2.Enviar mensaje al viaje");
			System.out.println("3.Recibir mensajes del viaje");
			System.out.println(">");
			String[] line = in.readLine().split(" ");
			if (line[0].equals("0")){
				System.out.println("\nHasta pronto!");
				return;
			}
			else if (line[0].equals("1"))
				new ListarViajes().mostrarViajes(user);
			else if (line[0].equals("2"))
				new EnviarMensajes().run(user);
			else if (line[0].equals("3"))
				new RecibirMensajes().run(user);
			else
				System.err.println("Comando desconocido-_-");
		}while(true);
	}

}
