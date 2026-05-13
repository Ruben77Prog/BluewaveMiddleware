package com.ruben.bluewave.model;

public class TipoPlan {
    private Long id;
    private String nombre;
    private String descripcion;
    private Long velocidadId;
    private Long subidaId;
    private Long limiteDatosId;

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

    public Long getVelocidadId() {
        return velocidadId;
    }

    public void setVelocidadId(Long velocidadId) {
        this.velocidadId = velocidadId;
    }

    public Long getSubidaId() {
        return subidaId;
    }

    public void setSubidaId(Long subidaId) {
        this.subidaId = subidaId;
    }

    public Long getLimiteDatosId() {
        return limiteDatosId;
    }

    public void setLimiteDatosId(Long limiteDatosId) {
        this.limiteDatosId = limiteDatosId;
    }
}