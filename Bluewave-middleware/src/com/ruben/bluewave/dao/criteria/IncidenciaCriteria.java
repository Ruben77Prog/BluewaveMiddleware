package com.ruben.bluewave.dao.criteria;

import java.util.Date;

public class IncidenciaCriteria {
	public final String ORDER_BY_NUMERO = " i.numero_incidencia ";
	public final String ORDER_BY_TITULO = " i.titulo ";
	public final String ORDER_BY_FECHA_INCIDENCIA = " i.fecha_incidencia ";
	public final String ORDER_BY_ESTADO = " estado_incidencia_nombre ";

	private Long id = null;
	private String numeroIncidencia = null;
	private String titulo = null;
	private Date fechaIncidenciaDesde = null;
	private Date fechaIncidenciaHasta = null;
	private Long tipoIncidenciaId = null;
	private Long contratoId = null;
	private Long estadoIncidenciaId = null;
	private Long empleadoAsignadoId = null;
	private Long nivelIncidenciaId = null;
	private Long empleadoCreadorId = null;
	private String orderBy = ORDER_BY_FECHA_INCIDENCIA;
	private Boolean ascDesc = false;

	public IncidenciaCriteria() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroIncidencia() {
		return numeroIncidencia;
	}

	public void setNumeroIncidencia(String numeroIncidencia) {
		this.numeroIncidencia = numeroIncidencia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaIncidenciaDesde() {
		return fechaIncidenciaDesde;
	}

	public void setFechaIncidenciaDesde(Date fechaIncidenciaDesde) {
		this.fechaIncidenciaDesde = fechaIncidenciaDesde;
	}

	public Date getFechaIncidenciaHasta() {
		return fechaIncidenciaHasta;
	}

	public void setFechaIncidenciaHasta(Date fechaIncidenciaHasta) {
		this.fechaIncidenciaHasta = fechaIncidenciaHasta;
	}

	public Long getTipoIncidenciaId() {
		return tipoIncidenciaId;
	}

	public void setTipoIncidenciaId(Long tipoIncidenciaId) {
		this.tipoIncidenciaId = tipoIncidenciaId;
	}

	public Long getContratoId() {
		return contratoId;
	}

	public void setContratoId(Long contratoId) {
		this.contratoId = contratoId;
	}

	public Long getEstadoIncidenciaId() {
		return estadoIncidenciaId;
	}

	public void setEstadoIncidenciaId(Long estadoIncidenciaId) {
		this.estadoIncidenciaId = estadoIncidenciaId;
	}

	public Long getEmpleadoAsignadoId() {
		return empleadoAsignadoId;
	}

	public void setEmpleadoAsignadoId(Long empleadoAsignadoId) {
		this.empleadoAsignadoId = empleadoAsignadoId;
	}

	public Long getNivelIncidenciaId() {
		return nivelIncidenciaId;
	}

	public void setNivelIncidenciaId(Long nivelIncidenciaId) {
		this.nivelIncidenciaId = nivelIncidenciaId;
	}

	public Long getEmpleadoCreadorId() {
		return empleadoCreadorId;
	}

	public void setEmpleadoCreadorId(Long empleadoCreadorId) {
		this.empleadoCreadorId = empleadoCreadorId;
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
