package com.ruben.bluewave.model;

import java.util.Date;

public class PlanDTO extends AbstractValueObject{
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Double precioDesde;
    private Double precioHasta;
    private Integer duracionMeses;
    private Double descuento;
    private Date fechaCreacion;
    private Date fechaDesde;
    private Date fechaHasta;
    private Boolean activo;
    
    // FK 
    private Long tipoPlanId;
    private String tipoPlanNombre;
    private String velocidadNombre;
    private String subidaNombre;
    private String limiteDatosNombre;
    
    public PlanDTO() {
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

    public Double getPrecioDesde() {
        return precioDesde;
    }

    public void setPrecioDesde(Double precioDesde) {
        this.precioDesde = precioDesde;
    }

    public Double getPrecioHasta() {
        return precioHasta;
    }

    public void setPrecioHasta(Double precioHasta) {
        this.precioHasta = precioHasta;
    }

    public Integer getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(Integer duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
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

    public String getTipoPlanNombre() {
        return tipoPlanNombre;
    }

    public void setTipoPlanNombre(String tipoPlanNombre) {
        this.tipoPlanNombre = tipoPlanNombre;
    }

    public String getVelocidadNombre() {
        return velocidadNombre;
    }

    public void setVelocidadNombre(String velocidadNombre) {
        this.velocidadNombre = velocidadNombre;
    }

    public String getSubidaNombre() {
        return subidaNombre;
    }

    public void setSubidaNombre(String subidaNombre) {
        this.subidaNombre = subidaNombre;
    }

    public String getLimiteDatosNombre() {
        return limiteDatosNombre;
    }

    public void setLimiteDatosNombre(String limiteDatosNombre) {
        this.limiteDatosNombre = limiteDatosNombre;
    }
}