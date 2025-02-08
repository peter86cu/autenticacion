package com.autenticacion.login.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;



@Entity
@Table(name = "accion")
public class AccionesGestion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idaccion")
	private int idaccion;

	private String accion;

	/*@ManyToMany(mappedBy = "accion")
	private transient Set<Gestiones> gestion= new HashSet<Gestiones>();*/
	
	
	public AccionesGestion() {
	}


	public int getIdaccion() {
		return idaccion;
	}


	public void setIdaccion(int idaccion) {
		this.idaccion = idaccion;
	}


	public String getAccion() {
		return accion;
	}


	public void setAccion(String accion) {
		this.accion = accion;
	}


	/*public Set<Gestiones> getGestion() {
		return gestion;
	}


	public void setGestion(Set<Gestiones> gestion) {
		this.gestion = gestion;
	}
*/

	
}