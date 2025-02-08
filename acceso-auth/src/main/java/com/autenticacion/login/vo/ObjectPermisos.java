package com.autenticacion.login.vo;

import java.util.List;

import com.autenticacion.login.modelo.AccionesGestion;
import com.autenticacion.login.modelo.Gestiones;


public class ObjectPermisos {
	
	private Gestiones gestion;
	private List<AccionesGestion> accion;

	public List<AccionesGestion> getAccion() {
		return accion;
	}

	public void setAccion(List<AccionesGestion> accion) {
		this.accion = accion;
	}

	public Gestiones getGestion() {
		return gestion;
	}

	public void setGestion(Gestiones gestion) {
		this.gestion = gestion;
	}
	
	
	

}
