package com.autenticacion.login.service;

import java.util.List;

import org.springframework.http.ResponseEntity;


public interface UsuarioService {
	ResponseEntity<String> agregarUsuario(String datos);
	ResponseEntity<String> listadoUsuarios(String token);
	//ResponseEntity<String> actualizarUsuario(Usuarios Usuario);
	ResponseEntity<String> buscarUsuario(String user, String pwd);
	ResponseEntity<String> recuperarUsuarioPorUser(String token,String user);
	ResponseEntity<String> obtenerUsuarioPorIdEmpresa(String token,String id);
	ResponseEntity<String> buscarUsuarioPorId(String token,String id);
	ResponseEntity<String> eliminarUsuarioPorId(String datos);
	ResponseEntity<String> cambiarPassword(String token,String idUsuario, String pass);
}
