package com.ruben.bluewave.model;

import java.util.Date;

public class IncidenciaDTO extends AbstractValueObject {
	private Long id;
	private String numeroIncidencia;
	private String titulo;
	private String descripcion;
	private Date fechaIncidencia;
	private Date fechaResolucion;
	private Double horasEstimadas;
	private Double horasReales;
	private Double costeReparacion;

	// FK
	private Long tipoIncidenciaId;
	private String tipoIncidenciaNombre;

	private Long contratoId;
	private String contratoNumero;
	private String clienteNombre;

	private Long estadoIncidenciaId;
	private String estadoIncidenciaNombre;

	private Long empleadoCreadorId;
	private String empleadoCreadorNombre;

	private Date fechaCreacion;
	private Date fechaActualizacion;

	private Long nivelIncidenciaId;
	private String nivelIncidenciaNombre;

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

	public String getTipoIncidenciaNombre() {
		return tipoIncidenciaNombre;
	}

	public void setTipoIncidenciaNombre(String tipoIncidenciaNombre) {
		this.tipoIncidenciaNombre = tipoIncidenciaNombre;
	}

	public Long getContratoId() {
		return contratoId;
	}

	public void setContratoId(Long contratoId) {
		this.contratoId = contratoId;
	}

	public String getContratoNumero() {
		return contratoNumero;
	}

	public void setContratoNumero(String contratoNumero) {
		this.contratoNumero = contratoNumero;
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public Long getEstadoIncidenciaId() {
		return estadoIncidenciaId;
	}

	public void setEstadoIncidenciaId(Long estadoIncidenciaId) {
		this.estadoIncidenciaId = estadoIncidenciaId;
	}

	public String getEstadoIncidenciaNombre() {
		return estadoIncidenciaNombre;
	}

	public void setEstadoIncidenciaNombre(String estadoIncidenciaNombre) {
		this.estadoIncidenciaNombre = estadoIncidenciaNombre;
	}

	public Long getEmpleadoCreadorId() {
		return empleadoCreadorId;
	}

	public void setEmpleadoCreadorId(Long empleadoCreadorId) {
		this.empleadoCreadorId = empleadoCreadorId;
	}

	public String getEmpleadoCreadorNombre() {
		return empleadoCreadorNombre;
	}

	public void setEmpleadoCreadorNombre(String empleadoCreadorNombre) {
		this.empleadoCreadorNombre = empleadoCreadorNombre;
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

	public Long getNivelIncidenciaId() {
		return nivelIncidenciaId;
	}

	public void setNivelIncidenciaId(Long nivelIncidenciaId) {
		this.nivelIncidenciaId = nivelIncidenciaId;
	}

	public String getNivelIncidenciaNombre() {
		return nivelIncidenciaNombre;
	}

	public void setNivelIncidenciaNombre(String nivelIncidenciaNombre) {
		this.nivelIncidenciaNombre = nivelIncidenciaNombre;
	}
}