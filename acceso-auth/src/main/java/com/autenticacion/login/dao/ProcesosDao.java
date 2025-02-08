package com.autenticacion.login.dao;

import java.util.List;

import com.autenticacion.login.modelo.AdministracionLog;
import com.autenticacion.login.modelo.EstadoProceso;




public interface ProcesosDao {
	
	List<EstadoProceso> listadoEstadoProceso();
	
	void agregarEjecucionProceso(AdministracionLog log);

}
