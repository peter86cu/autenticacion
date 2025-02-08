package com.autenticacion.login.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.autenticacion.login.modelo.EstadoUsuarios;
import com.autenticacion.login.modelo.Moneda;
import com.autenticacion.login.repositorio.EstadoUsuarioJpaSpring;

import java.util.List;
@Repository
public class ParametrosUsuariosDaoImpl implements ParametrosUsuariosDao{

    @Autowired
    EstadoUsuarioJpaSpring daoEstadoUsuario;

    @Autowired
	MonedaJPASpring daoMoneda;
    
    @Override
    public List<EstadoUsuarios> listaEstadosUsuarios() {
        return daoEstadoUsuario.findAll();
    }



	@Override
	public List<Moneda> obtenerMoneda() {
		return daoMoneda.findAll();
	}

	
}
