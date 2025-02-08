package com.autenticacion.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.autenticacion.login.service.ProcesosService;

@RestController
@RequestMapping("/api/v1/process")
public class ProcesosController {

	@Autowired
	ProcesosService service;
	
	@PostMapping(value="log/add",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> guardarLogAdmin(@RequestBody String datos) {
		
		return service.agregarEjecucionProceso(datos);
	}	
	
	
	 @GetMapping(value="proceso/estados-procesos",produces= MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.CREATED)
	    public ResponseEntity<String> listadoEstadoUsuario() throws Exception {
	        return service.listadoEstadoProceso();


	    }
}
