package com.autenticacion.login.service;

import org.springframework.http.ResponseEntity;

public interface ParametrosUsuariosService {

    ResponseEntity<String> listaEstadoUsuarios();
    
	ResponseEntity<String> obtenerMonedas();


}
