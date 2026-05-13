package com.ruben.bluewave.model;

public class NivelIncidencia {
	private Long id;
	private String nombre;
	private String descripcion;
	private Integer prioridad;
	private Integer horasRespuesta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public Integer getHorasRespuesta() {
		return horasRespuesta;
	}

	public void setHorasRespuesta(Integer horasRespuesta) {
		this.horasRespuesta = horasRespuesta;
	}
}