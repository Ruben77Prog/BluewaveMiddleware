package com.ruben.bluewave.dao.criteria;

import java.util.Date;

public class ContratoCriteria {

	public final String ORDER_BY_NUMERO = " con.numero_contrato ";
	public final String ORDER_BY_FECHA_INICIO = " con.fecha_inicio ";
	public final String ORDER_BY_FECHA_ID = " con.id ";
	public final String ORDER_BY_CLIENTE = " cliente_nombre ";
	public final String ORDER_BY_PLAN = " plan_nombre ";

	private Long id = null;
	private String numeroContrato = null;
	private Date fechaInicioDesde = null;
	private Date fechaInicioHasta = null;
	private Long metodoPagoId = null;
	private Long clienteId = null;
	private Long planId = null;
	private Long estadoContratoId = null;

	private String orderBy = ORDER_BY_FECHA_ID;

	private Boolean ascDesc = false;

	public ContratoCriteria() {
	}

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

	public Date getFechaInicioDesde() {
		return fechaInicioDesde;
	}

	public void setFechaInicioDesde(Date fechaInicioDesde) {
		this.fechaInicioDesde = fechaInicioDesde;
	}

	public Date getFechaInicioHasta() {
		return fechaInicioHasta;
	}

	public void setFechaInicioHasta(Date fechaInicioHasta) {
		this.fechaInicioHasta = fechaInicioHasta;
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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getAscDesc() {
		return ascDesc;
	}

	public void setAscDesc(Boolean ascDesc) {
		this.ascDesc = ascDesc;
	}
}
