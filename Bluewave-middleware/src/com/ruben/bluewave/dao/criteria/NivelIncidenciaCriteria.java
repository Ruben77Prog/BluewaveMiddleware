package com.ruben.bluewave.dao.criteria;

public class NivelIncidenciaCriteria {
	private Long id = null;
	private String nombre = null;
	private String descripcion = null;
	private Integer prioridad = null;
	private Integer horasRespuestaMin = null;
	private Integer horasRespuestaMax = null;

	public NivelIncidenciaCriteria() {
	}

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

	public Integer getHorasRespuestaMin() {
		return horasRespuestaMin;
	}

	public void setHorasRespuestaMin(Integer horasRespuestaMin) {
		this.horasRespuestaMin = horasRespuestaMin;
	}

	public Integer getHorasRespuestaMax() {
		return horasRespuestaMax;
	}

	public void setHorasRespuestaMax(Integer horasRespuestaMax) {
		this.horasRespuestaMax = horasRespuestaMax;
	}
}