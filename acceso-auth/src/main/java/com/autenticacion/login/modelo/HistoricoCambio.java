package com.autenticacion.login.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "historico_cambio")
public class HistoricoCambio implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String idmoneda;
	private double valorcompra;
	private double valorventa;
	private String idapertura;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdmoneda() {
		return idmoneda;
	}
	public void setIdmoneda(String idmoneda) {
		this.idmoneda = idmoneda;
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
	public String getIdapertura() {
		return idapertura;
	}
	public void setIdapertura(String idapertura) {
		this.idapertura = idapertura;
	}
	

}
