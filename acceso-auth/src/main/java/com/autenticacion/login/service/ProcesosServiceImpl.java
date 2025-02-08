package com.autenticacion.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.autenticacion.login.dao.ProcesosDao;
import com.autenticacion.login.modelo.AdministracionLog;
import com.autenticacion.login.modelo.EstadoProceso;
import com.google.gson.Gson;



@Service
public class ProcesosServiceImpl implements ProcesosService {

	@Autowired
	ProcesosDao service;
	
	@Override
	public ResponseEntity<String> agregarEjecucionProceso(String log) {
		try {
			AdministracionLog request = new Gson().fromJson(log, AdministracionLog.class);
			service.agregarEjecucionProceso(request);
			return new ResponseEntity<String>("Datos guardados correctamente.",HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().getMessage(),HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> listadoEstadoProceso() {
		try {
			List<EstadoProceso> lstEstado= service.listadoEstadoProceso();
			if(!lstEstado.isEmpty()) {
				return new ResponseEntity<String>(new Gson().toJson(lstEstado),HttpStatus.OK);

			}else {
				return new ResponseEntity<String>("No hay estados de procesos en la base de datos.",HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().getMessage(),HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
