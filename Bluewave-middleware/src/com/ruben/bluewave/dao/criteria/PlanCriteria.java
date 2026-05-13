package com.ruben.bluewave.dao.criteria;

public class PlanCriteria {
	private Long id = null;
	private String nombre = null;
	private String descripcion = null;
	private Double precio = null;
	private Boolean activo = null;
	private Long tipoPlanId = null;

	public PlanCriteria() {
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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Long getTipoPlanId() {
		return tipoPlanId;
	}

	public void setTipoPlanId(Long tipoPlanId) {
		this.tipoPlanId = tipoPlanId;
	}
}