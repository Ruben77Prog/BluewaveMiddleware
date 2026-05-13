package com.ruben.bluewave.model;

import java.util.Date;

public class Incidencia {
	private Long id;
	private String numeroIncidencia;
	private String titulo;
	private String descripcion;
	private Date fechaIncidencia;
	private Date fechaResolucion;
	private Double horasEstimadas;
	private Double horasReales;
	private Double costeReparacion;
	private Long tipoIncidenciaId;
	private Long contratoId;
	private Long estadoIncidenciaId;
	private Long empleadoAsignadoId;
	private Long creadorEmpleadoId;
	private Date fechaCreacion;
	private Date fechaActualizacion;

	public Incidencia() {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaIncidencia() {
		return fechaIncidencia;
	}

	public void setFechaIncidencia(Date fechaIncidencia) {
		this.fechaIncidencia = fechaIncidencia;
	}

	public Date getFechaResolucion() {
		return fechaResolucion;
	}

	public void setFechaResolucion(Date fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}

	public Double getHorasEstimadas() {
		return horasEstimadas;
	}

	public void setHorasEstimadas(Double horasEstimadas) {
		this.horasEstimadas = horasEstimadas;
	}

	public Double getHorasReales() {
		return horasReales;
	}

	public void setHorasReales(Double horasReales) {
		this.horasReales = horasReales;
	}

	public Double getCosteReparacion() {
		return costeReparacion;
	}

	public void setCosteReparacion(Double costeReparacion) {
		this.costeReparacion = costeReparacion;
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

	public Long getCreadorEmpleadoId() {
		return creadorEmpleadoId;
	}

	public Long getEmpleadoAsignadoId() {
		return empleadoAsignadoId;
	}

	public void setEmpleadoAsignadoId(Long empleadoAsignadoId) {
		this.empleadoAsignadoId = empleadoAsignadoId;
	}

	public void setCreadorEmpleadoId(Long creadorEmpleadoId) {
		this.creadorEmpleadoId = creadorEmpleadoId;
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
