package com.ruben.bluewave.dao.criteria;

public class TipoPlanCriteria {
    private Long id = null;
    private String nombre = null;
    private String descripcion = null;
    private Long velocidadId = null;
    private String velocidadNombre = null;
    private Long subidaId = null;
    private String subidaNombre = null;
    private Long limiteDatosId = null;
    private String limiteDatosNombre = null;

    public TipoPlanCriteria() {
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

    public Long getVelocidadId() {
        return velocidadId;
    }

    public void setVelocidadId(Long velocidadId) {
        this.velocidadId = velocidadId;
    }

    public String getVelocidadNombre() {
        return velocidadNombre;
    }

    public void setVelocidadNombre(String velocidadNombre) {
        this.velocidadNombre = velocidadNombre;
    }

    public Long getSubidaId() {
        return subidaId;
    }

    public void setSubidaId(Long subidaId) {
        this.subidaId = subidaId;
    }

    public String getSubidaNombre() {
        return subidaNombre;
    }

    public void setSubidaNombre(String subidaNombre) {
        this.subidaNombre = subidaNombre;
    }

    public Long getLimiteDatosId() {
        return limiteDatosId;
    }

    public void setLimiteDatosId(Long limiteDatosId) {
        this.limiteDatosId = limiteDatosId;
    }

    public String getLimiteDatosNombre() {
        return limiteDatosNombre;
    }

    public void setLimiteDatosNombre(String limiteDatosNombre) {
        this.limiteDatosNombre = limiteDatosNombre;
    }
}