package com.autenticacion.login.service;

import org.springframework.http.ResponseEntity;


public interface ProcesosService {

	ResponseEntity<String> agregarEjecucionProceso(String log);
	
	ResponseEntity<String> listadoEstadoProceso();

}
