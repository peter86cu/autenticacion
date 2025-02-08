package com.autenticacion.login.modelo;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;





@Entity
@Table(name="modulos")
public class Modulos implements Serializable {
	private static final long serialVersionUID = 1L;

    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idmodulo")
    private int idmodulo;	
	@Column(name="modulo")
    private String modulo;
	@ManyToMany(mappedBy = "modulos")
	private transient Set<Roles> roles;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "modulos_gestion", joinColumns = {
			@JoinColumn(name = "idmodulo", referencedColumnName = "idmodulo") }, inverseJoinColumns = {
			@JoinColumn(name = "idgestion", referencedColumnName = "idgestion") })
	private Set<Gestiones> gestiones;
	
	/*@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="idrol", referencedColumnName = "idrol")
	private Roles rol;*/
	
	/*@OneToMany(mappedBy = "modulos")
    private List<Gestiones> gestiones = new ArrayList<Gestiones>();
	*/
/*	@ManyToOne
	@JoinColumns({	 
	  @JoinColumn( referencedColumnName = "idrol")
	})
    private Roles rol;
*/
	

	public Modulos() {
		super();
		roles=  new HashSet<Roles>();
		gestiones= new HashSet<Gestiones>();
	}

	public int getIdmodulo() {
		return idmodulo;
	}

	public void setIdmodulo(int idmodulo) {
		this.idmodulo = idmodulo;
	}
}
