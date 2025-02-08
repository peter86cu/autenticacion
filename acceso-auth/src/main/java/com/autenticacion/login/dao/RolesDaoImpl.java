package com.autenticacion.login.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.autenticacion.login.modelo.Roles;
import com.autenticacion.login.repositorio.RolesJpaSpring;



@Repository
public class RolesDaoImpl implements RolesDao {

	@Autowired
	RolesJpaSpring roles;


	@Override
	public void agregarRol(Roles rol) {
		roles.save(rol);
	}

	@Override
	public List<Roles> listarRoles() {
		return roles.findAll();
	}
	
	@Override
	public void eliminarRol(int idRol) {
		roles.deleteById(idRol);
		
	}

	@Override
	public Roles recuperarRolPorId(int idRol) {	
		return roles.findById(idRol).orElse(null);
	}

	@Override
	public void actualizarRol(Roles rol) {
		roles.save(rol);
		
	}

	@Override
	public String descripcionRol(int id) {
		return roles.findById(id).orElse(null).toString();
	}

	@Override
	public Roles obtenerRolPorUsuario(String usuario) {
		return roles.findRolesByUser(usuario);
	}

	

	

	
	

}
