package com.sdi.business;

public interface ServicesFactory {
	
	PublicoService getPublicoService();
	RegistradoService getRegistradoService();
	
	AdminService getAdminService();
	
}
