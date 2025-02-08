package com.autenticacion.login.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;



@Entity
@Table(name = "roles")

public class Roles implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idrol")
	private int idrol;

	private String descripcion;

	/*@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "roles_modulos", joinColumns = {
			@JoinColumn(name = "idrol", referencedColumnName = "idrol") }, inverseJoinColumns = {
			@JoinColumn(name = "idmodulo", referencedColumnName = "idmodulo") })
	private Set<Modulos> modulos;*/

	public Roles() {
		//modulos = new HashSet<Modulos>();
	}

	/*public Set<Modulos> getModulos() {
		return modulos;
	}

	public void setModulos(Set<Modulos> modulos) {
		this.modulos = modulos;
	}*/

	public int getIdrol() {
		return idrol;
	}

	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}