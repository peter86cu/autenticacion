package com.autenticacion.login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.autenticacion.login.dao.RolesDao;
import com.autenticacion.login.modelo.AccionesGestion;
import com.autenticacion.login.modelo.Gestiones;
import com.autenticacion.login.modelo.Modulos;
import com.autenticacion.login.modelo.Roles;
import com.autenticacion.login.repositorio.GestionesJPASpring;
import com.autenticacion.login.repositorio.ModulosJPASpring;
import com.autenticacion.login.vo.ObjectPermisos;
import com.autenticacion.login.vo.RequestAddRoles;
import com.google.gson.Gson;



@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	RolesDao dao;
	
	@Autowired
	ModulosJPASpring daoModulos;
	
	@Autowired
	GestionesJPASpring daoGestiones;
	
	
	@Override
	public ResponseEntity<String> agregarRol(String datos) {
		RequestAddRoles request = new Gson().fromJson(datos, RequestAddRoles.class);
		
		if(dao.recuperarRolPorId(request.getRoles().getIdrol())==null) {
			dao.agregarRol(request.getRoles());
			return new ResponseEntity<String>(com.autenticacion.login.utils.Constants.RESULTADO_OK,HttpStatus.ACCEPTED);			}
		return new ResponseEntity<String>(com.autenticacion.login.utils.Constants.RESULTADO_NOK,HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public List<Roles> listarRoles() {
		return dao.listarRoles();
	}

	@Override
	public void actualizarRol(Roles rol) {
		if(dao.recuperarRolPorId(rol.getIdrol())!=null) {
			dao.actualizarRol(rol);
		}

	}

	@Override
	public boolean eliminarRol(int idRol) {
		if(dao.recuperarRolPorId(idRol)!=null) {
			dao.eliminarRol(idRol);
			return true;
		}
		return false;
	}

	
	@Override
	public Roles buscarRol(int rol) {
		return dao.recuperarRolPorId(rol);
	}

	@Override
	public String obtenerDescripcionRol(int id) {		
		return  dao.descripcionRol(id);
	}

	@Override
	public List<Modulos> listaModulosUsuarios(String usuario) {

		List<Modulos> list=daoModulos.obtenerListaModulosPorUsuario(usuario);
		return list;
	}

	/*@Override
	public List<Gestiones> listadoGestionesPorModulos(int idmodulo) {
		return daoGestiones.obtenerGestionesPorModulos(idmodulo);
	}*/

	@Override
	public Roles obtenerRolPorUsuario(String idusuario) {
		return dao.obtenerRolPorUsuario(idusuario);
	}

	@Override
	public List<ObjectPermisos> obtenerPermisosPorModulos(int idmodulo) {
		List<ObjectPermisos> permisos = new ArrayList<ObjectPermisos>();
		List<Gestiones> gestiones = daoGestiones.obtenerGestionPorModulo(idmodulo);
		if(gestiones!=null) {
			for(Gestiones gest: gestiones) {
				ObjectPermisos obj= new ObjectPermisos();
				obj.setGestion(gest);
				List<AccionesGestion> acciones= daoGestiones.obtenerAccionesPorGestion(gest.getIdgestion());
				if (acciones!=null) {
					obj.setAccion(acciones);
				}
				permisos.add(obj);
			}
		}
		return permisos;
	}

}
