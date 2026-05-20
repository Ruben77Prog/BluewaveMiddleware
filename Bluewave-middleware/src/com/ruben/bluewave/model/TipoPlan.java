package com.ruben.bluewave.model;

public class TipoPlan extends AbstractValueObject {
	private Long id;
	private String nombre;
	private String descripcion;
	private Integer velocidadMbps;

	public TipoPlan() {
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

	public Integer getVelocidadMbps() {
		return velocidadMbps;
	}

	public void setVelocidadMbps(Integer velocidadMbps) {
		this.velocidadMbps = velocidadMbps;
	}
}