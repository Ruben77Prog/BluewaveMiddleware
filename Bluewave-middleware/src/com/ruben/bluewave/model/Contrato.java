package com.ruben.bluewave.model;

import java.util.Date;

public class Contrato {
    private Long id;
    private String numeroContrato;
    private Date fechaInicio;
    private Date fechaFin;
    private Double costeInstalacion;
    private Double costeMensual;
    private Long metodoPagoId;
    private Long clienteId;
    private Long planId;
    private Long estadoContratoId;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Double getCosteInstalacion() {
		return costeInstalacion;
	}
	public void setCosteInstalacion(Double costeInstalacion) {
		this.costeInstalacion = costeInstalacion;
	}
	public Double getCosteMensual() {
		return costeMensual;
	}
	public void setCosteMensual(Double costeMensual) {
		this.costeMensual = costeMensual;
	}
	public Long getMetodoPagoId() {
		return metodoPagoId;
	}
	public void setMetodoPagoId(Long metodoPagoId) {
		this.metodoPagoId = metodoPagoId;
	}
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public Long getEstadoContratoId() {
		return estadoContratoId;
	}
	public void setEstadoContratoId(Long estadoContratoId) {
		this.estadoContratoId = estadoContratoId;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

    
    
}