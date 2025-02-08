package com.autenticacion.login.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.autenticacion.login.modelo.Usuarios;
import com.autenticacion.login.repositorio.UsuarioJpaSpring;


@Repository
public class UsuarioDaoImpl implements UsuarioDao {

	@Autowired
	UsuarioJpaSpring usuario;


	@Override
	public void agregarUsuario(Usuarios Usuario) {
		usuario.save(Usuario);
	}

	@Override
	public List<Usuarios> devolverUsuarios() {
		return usuario.findAll();
	}
	


	@Override
	public Usuarios recuperarUsuarioPorId(String idUsuario) {	
		return usuario.findById(idUsuario).orElse(null);
	}

	@Override
	public void actualizarUsuario(Usuarios Usuario) {
		usuario.save(Usuario);
		
	}

	@Override
	public Usuarios recuperarUsuario(String user) {
		Usuarios userEnontrado=usuario.buscarPorUsuario(user);
		return userEnontrado;
	}

	@Override
	public List<Usuarios> getUsuariosPorIdEmpresa(String id) {		
		return usuario.findByIdEmpresa(id);
	}

	@Override
	public void eliminarUsuarioPorId(String id) {
		usuario.deleteById(id);
	}

	@Override
	public void cambiarPassword(String idUsuario, String password) {
		usuario.updatePasswordByUser(idUsuario, password);
		
	}

	

	

}
