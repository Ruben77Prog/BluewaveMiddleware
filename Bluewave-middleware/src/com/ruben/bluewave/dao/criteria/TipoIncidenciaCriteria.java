package com.ruben.bluewave.dao.criteria;

public class TipoIncidenciaCriteria {
    private Long id = null;
    private String nombre = null;
    private String descripcion = null;
    private String prioridadDefecto = null;
    private Long nivelDefectoId = null;
    private Boolean activo = null;

    public TipoIncidenciaCriteria() {
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

    public String getPrioridadDefecto() {
        return prioridadDefecto;
    }

    public void setPrioridadDefecto(String prioridadDefecto) {
        this.prioridadDefecto = prioridadDefecto;
    }

    public Long getNivelDefectoId() {
        return nivelDefectoId;
    }

    public void setNivelDefectoId(Long nivelDefectoId) {
        this.nivelDefectoId = nivelDefectoId;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}