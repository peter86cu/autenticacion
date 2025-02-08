package com.autenticacion.login.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "gestion")
public class Gestiones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idgestion;
	private String nombre;
	
	@ManyToMany(mappedBy = "gestiones")
	private transient Set<Modulos> modulos= new HashSet<Modulos>();
	
	/*@ManyToOne(fetch=FetchType.LAZY)
    @JoinTable(name = "modulos_gestion")
    private Modulos modulos;*/
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "gestion_accion", joinColumns = {
			@JoinColumn(name = "idgestion", referencedColumnName = "idgestion") }, inverseJoinColumns = {
			@JoinColumn(name = "idaccion", referencedColumnName = "idaccion") })
	private Set<AccionesGestion> accion= new HashSet<AccionesGestion>();
	
	/*@OneToMany(fetch=FetchType.LAZY,mappedBy = "gestion")    
    private List<AccionesGestion> accion=  new ArrayList<AccionesGestion>();*/

	public int getIdgestion() {
		return idgestion;
	}

	public void setIdgestion(int idgestion) {
		this.idgestion = idgestion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Modulos> getModulos() {
		return modulos;
	}

	public void setModulos(Set<Modulos> modulos) {
		this.modulos = modulos;
	}

	public Set<AccionesGestion> getAccion() {
		return accion;
	}

	public void setAccion(Set<AccionesGestion> accion) {
		this.accion = accion;
	}

	
	


}
