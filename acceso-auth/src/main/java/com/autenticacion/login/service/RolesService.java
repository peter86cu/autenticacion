package com.autenticacion.login.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.autenticacion.login.modelo.Modulos;
import com.autenticacion.login.modelo.Roles;
import com.autenticacion.login.vo.ObjectPermisos;



public interface RolesService {
	ResponseEntity<String> agregarRol(String rol);
	List<Modulos> listaModulosUsuarios(String usuario);

	List<Roles> listarRoles();
	void actualizarRol(Roles rol);
	boolean eliminarRol(int idRol);	
	Roles buscarRol(int idRol);
	String obtenerDescripcionRol(int id);
	//List<Gestiones> listadoGestionesPorModulos(int idmodulo);
	Roles obtenerRolPorUsuario(String idusuario);
	List<ObjectPermisos> obtenerPermisosPorModulos(int idmodulo);
}
