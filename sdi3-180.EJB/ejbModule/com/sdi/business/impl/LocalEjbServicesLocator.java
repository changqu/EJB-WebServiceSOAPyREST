package com.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.AdminService;
import com.sdi.business.PublicoService;
import com.sdi.business.RegistradoService;
import com.sdi.business.ServicesFactory;

public class LocalEjbServicesLocator implements ServicesFactory {

	private static final String PUBLICO_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi3-180/" + "sdi3-180.EJB/" + "EjbPublicoService!"
			+ "com.sdi.business.impl.LocalPublicoService";
 
	private static final String REGISTRADO_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi3-180/" + "sdi3-180.EJB/" + "EjbRegistradoService!"
			+ "com.sdi.business.impl.LocalRegistradoService";
	
	private static final String ADMIN_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi3-180/" + "sdi3-180.EJB/" + "EjbAdminService!"
			+ "com.sdi.business.impl.LocalAdminService";

	@Override
	public PublicoService getPublicoService() {
		try {
			Context ctx = new InitialContext();
			return (PublicoService) ctx.lookup(PUBLICO_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public RegistradoService getRegistradoService() {
		try {
			Context ctx = new InitialContext();
			return (RegistradoService) ctx.lookup(REGISTRADO_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public AdminService getAdminService() {
		try {
			Context ctx = new InitialContext();
			return (AdminService) ctx.lookup(ADMIN_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

}
