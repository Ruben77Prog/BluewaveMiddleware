package com.ruben.bluewave.dao.criteria;

public class TipoMensajeCriteria {
    private Long id = null;
    private String nombre = null;
    private String descripcion = null;

    public TipoMensajeCriteria() {
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
}