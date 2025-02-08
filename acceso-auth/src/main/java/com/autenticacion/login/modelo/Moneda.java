package com.autenticacion.login.modelo;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="moneda")
public class Moneda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private String id;	

	@Column(name="moneda")
	private String moneda;

	@Column(name="simbolo")
	private String simbolo;

	@Column(name="valorcompra")
	private double valorcompra;
	
	@Column(name="valorventa")
	private double valorventa;

	@Column(name="estado")
	private int estado;
	
	@Column(name="defecto")
	private int defecto;
	
	@Column(name="id_cambio_venta")
	private int idCambioVenta;
	
	@Column(name="codigo")
	private String codigo;


	
	
	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public int getIdCambioVenta() {
		return idCambioVenta;
	}



	public void setIdCambioVenta(int idCambioVenta) {
		this.idCambioVenta = idCambioVenta;
	}



	public int getDefecto() {
		return defecto;
	}



	public void setDefecto(int defecto) {
		this.defecto = defecto;
	}



	public Moneda() {
		super();
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getMoneda() {
		return moneda;
	}



	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}



	public String getSimbolo() {
		return simbolo;
	}



	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}



	public double getValorcompra() {
		return valorcompra;
	}



	public void setValorcompra(double valorcompra) {
		this.valorcompra = valorcompra;
	}



	public double getValorventa() {
		return valorventa;
	}



	public void setValorventa(double valorventa) {
		this.valorventa = valorventa;
	}



	public int getEstado() {
		return estado;
	}



	public void setEstado(int estado) {
		this.estado = estado;
	}


	

	
}