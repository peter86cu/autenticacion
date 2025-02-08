package com.autenticacion.login.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estados")
public class EstadoUsuarios implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String estado;
	private String idmodulo;
	


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public String getIdmodulo() {
		return idmodulo;
	}



	public void setIdmodulo(String idmodulo) {
		this.idmodulo = idmodulo;
	}



	public EstadoUsuarios() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
