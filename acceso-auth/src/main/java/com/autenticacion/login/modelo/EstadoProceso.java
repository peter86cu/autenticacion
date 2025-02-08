package com.autenticacion.login.modelo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "estados_proceso")
public class EstadoProceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String estado;

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

}