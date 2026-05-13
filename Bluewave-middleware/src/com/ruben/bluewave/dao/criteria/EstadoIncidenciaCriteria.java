package com.ruben.bluewave.dao.criteria;

public class EstadoIncidenciaCriteria {
    private Long id = null;
    private String nombre = null;
    private String descripcion = null;
    private Boolean esFinal = null;

    public EstadoIncidenciaCriteria() {
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

    public Boolean getEsFinal() {
        return esFinal;
    }

    public void setEsFinal(Boolean esFinal) {
        this.esFinal = esFinal;
    }
}