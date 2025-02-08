package com.autenticacion.login.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.autenticacion.login.modelo.AdministracionLog;
import com.autenticacion.login.modelo.EstadoProceso;
import com.autenticacion.login.repositorio.AdminLogJpaSpring;
import com.autenticacion.login.repositorio.EstadoProcesoJpaSpring;



@Repository
public class ProcesosDaoImpl implements ProcesosDao {

	@Autowired
	AdminLogJpaSpring daoProcesos;
	
	@Autowired
	EstadoProcesoJpaSpring daoEstadoProceso;
	
	@Override
	public void agregarEjecucionProceso(AdministracionLog log) {
			daoProcesos.save(log);
	}

	@Override
	public List<EstadoProceso> listadoEstadoProceso() {
		
		return daoEstadoProceso.findAll();
	}

}
