package com.autenticacion.login.service;

import com.autenticacion.login.dao.ParametrosUsuariosDao;
import com.autenticacion.login.modelo.EstadoUsuarios;
import com.autenticacion.login.modelo.Moneda;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParametrosUsuariosServiceImpl implements ParametrosUsuariosService {

    @Autowired
    ParametrosUsuariosDao service;
    @Override
    public ResponseEntity<String> listaEstadoUsuarios() {
        try {
            List<EstadoUsuarios> lstEstados=service.listaEstadosUsuarios();
            if(!lstEstados.isEmpty()) {
                return new ResponseEntity<String>(new Gson().toJson(lstEstados), HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("No existen estados para los usuarios en la base de datos.",
                        HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
	@Override
	public ResponseEntity<String> obtenerMonedas() {
		try {
			List<Moneda> lstMoneda=service.obtenerMoneda();
			if(!lstMoneda.isEmpty()) {
				return new ResponseEntity<String>(new Gson().toJson(lstMoneda), HttpStatus.OK);
			}else{
				return new ResponseEntity<String>("No existen monedas en la base de datos.",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().getCause().getMessage(),
					HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
}
