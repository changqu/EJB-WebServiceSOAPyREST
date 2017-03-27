package com.sdi.rest;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.util.Base64;

import com.sdi.infrastructure.Factories;

@Provider
public class AuthFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext arg0) throws IOException {		
		String auth = arg0.getHeaderString("Authorization");
		
		//inicialmente auth es null
		if (auth == null) {
			unauthorized(arg0);
			return;
		}
		
		String login = new String(Base64.decode(auth.split(" ")[1]));
		String[] credentials = login.split(":");
		
		String user = credentials[0];
		String password = credentials[1];
		
		if (Factories.services.getPublicoService().iniciarSesion(user, password) == null) {
			unauthorized(arg0);
		}
	}
	
	private void unauthorized(ContainerRequestContext crc) {
		Response response = Response.status(Response.Status.UNAUTHORIZED).build();
		response.getHeaders().putSingle("WWW-Authenticate", "Basic realm=\"Usuarios registrados\"");
		crc.abortWith(response);//Abort the filter chain with a response.
	}

}
