package com.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.AdminService;
import com.sdi.business.PublicoService;
import com.sdi.business.RegistradoService;
import com.sdi.business.ServicesFactory;

public class RemoteEjbServicesLocator implements ServicesFactory {

	private static final String PUBLICO_SERVICE_JNDI_KEY = 
			 "sdi3-180/" + "sdi3-180.EJB/" + "EjbPublicoService!"
			+ "com.sdi.business.impl.RemotePublicoService";

	private static final String REGISTRADO_SERVICE_JNDI_KEY = 
			 "sdi3-180/" + "sdi3-180.EJB/" + "EjbRegistradoService!"
			+ "com.sdi.business.impl.RemoteRegistradoService";
	
	private static final String ADMIN_SERVICE_JNDI_KEY = 
			 "sdi3-180/" + "sdi3-180.EJB/" + "EjbAdminService!"
			+ "com.sdi.business.impl.RemoteAdminService"; 

	@Override
	public PublicoService getPublicoService() {
		System.out.println("Using remote services locator");
		try {
			Context ctx = new InitialContext();
			return (PublicoService) ctx.lookup(PUBLICO_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public RegistradoService getRegistradoService() {
		System.out.println("Using remote services locator");
		try {
			Context ctx = new InitialContext();
			return (RegistradoService) ctx.lookup(REGISTRADO_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}
	
	@Override
	public AdminService getAdminService() {
		System.out.println("Using remote services locator");
		try {
			Context ctx = new InitialContext();
			return (AdminService) ctx.lookup(ADMIN_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

}
