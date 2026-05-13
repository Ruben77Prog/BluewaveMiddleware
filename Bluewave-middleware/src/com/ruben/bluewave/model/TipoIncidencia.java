package com.ruben.bluewave.model;

public class TipoIncidencia  extends AbstractValueObject{
    private Long id;
    private String nombre;
    private String descripcion;
    private String prioridadDefault; 
    private Long nivelIncidenciaDefaultId;
    private Boolean activo;

    public TipoIncidencia() {
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

    public String getPrioridadDefault() {
        return prioridadDefault;
    }

    public void setPrioridadDefault(String prioridadDefault) {
        this.prioridadDefault = prioridadDefault;
    }

    public Long getNivelIncidenciaDefaultId() {
        return nivelIncidenciaDefaultId;
    }

    public void setNivelIncidenciaDefaultId(Long nivelIncidenciaDefaultId) {
        this.nivelIncidenciaDefaultId = nivelIncidenciaDefaultId;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}