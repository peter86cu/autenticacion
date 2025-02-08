package com.autenticacion.login.dao;

import java.util.List;

import com.autenticacion.login.modelo.DireccionUsuario;
import com.autenticacion.login.modelo.DptoPais;
import com.autenticacion.login.modelo.ShoppingUsuarios;



public interface ShoppingUsuariosDao {

	void crearUsuarioNuevo(ShoppingUsuarios usuario);

	ShoppingUsuarios recuperarUsuarioPorId(String idUsuario);

	ShoppingUsuarios recuperarUsuario(String mail);

	void actualizarUsuario(ShoppingUsuarios Usuario);

	void cambiarPassword(String idUsuario, String password);

	void eliminarUsuarioPorId(String id);
	
	List<ShoppingUsuarios> listadoUsuarios();
	
	//DIRECCION
	
	void guardarDireccion(DireccionUsuario dire);
	
	List<DireccionUsuario> recuperarDreccionUsuarioPorId(String idUsuario);
	
	DireccionUsuario recuperarDireccionUsuarioCompra(String orderID);
	
	DireccionUsuario recuperarDireccionID(int id);
	
	void eliminarDreccionUsuarioPorId(int id);
	
	List<DptoPais> listaDptoPais(int pais);

}
