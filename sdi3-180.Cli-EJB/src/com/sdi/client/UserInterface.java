package com.sdi.client;

import java.io.*;

import com.sdi.client.accion.DeshabilitarUsuario;
import com.sdi.client.accion.EliminarComentario;
import com.sdi.client.accion.ListarComentarios;
import com.sdi.client.accion.ListarUsuarios;

public class UserInterface {
	
	private static BufferedReader in;

	public void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		comandos();
		do {
			opciones();
			String[] line = in.readLine().split(" ");
			if (line[0].equals("0")){
				System.out.println("\nHasta pronto!");
				return;
			}
			else if (line[0].equals("1"))
				new ListarUsuarios().execute();
			else if (line[0].equals("2"))
				opcionDeshabilitarUsuario();
			else if (line[0].equals("3"))
				new ListarComentarios().execute();
			else if (line[0].equals("4"))
				opcionEliminarComentario();
			else
				System.err.println("Comando desconocido-_-");
		} while (true);
	}
	
	public void comandos(){
		System.out.println("Comandos:");
		System.out.println("0, 1, 2, 3, 4");
	}
	
	public void opciones(){
		System.out.println("\nOpción: ");
		System.out.println("0.Salir");
		System.out.println("1.Listar usuarios del sistema");
		System.out.println("2.Deshabilitar un usuario del sistema");
		System.out.println("3.Listar comentarios y puntuaciones");
		System.out.println("4.Eliminar comentarios y puntuaciones");
		System.out.print(">");
	}
	
	public void opcionDeshabilitarUsuario() throws IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		do{
			System.out.println("\nIntroducir \"0\" para salir de esta opcion");
			System.out.println("Intriducir ID del usuario: ");
			System.out.print(">");
			String[] line = in.readLine().split(" ");
			if(line[0].equals("0"))
				break;
			try{
				new DeshabilitarUsuario().execute(Long.parseLong(line[0]));
			}catch(Exception e){
				System.err.println("Id del usuario invalido, tiene que ser numeros");
			}
		}while(true);	
	}
	
	public void opcionEliminarComentario() throws IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		do{
			System.out.println("\nIntroducir \"0\" para salir de esta opcion");
			System.out.println("Intriducir ID del comentario: ");
			System.out.print(">");
			String[] line = in.readLine().split(" ");
			if(line[0].equals("0"))
				break;
			try{
				new EliminarComentario().execute(Long.parseLong(line[0]));
			}catch(Exception e){
				System.err.println("Id del comentario invalido, tiene que ser numeros");
			}
		}while(true);
	}
	
}

