package com.ruben.bluewave.model;

import java.util.Date;

public class ContratoDTO extends AbstractValueObject{
    private Long id;
    private String numeroContrato;
    private Date fechaInicio;
    private Date fechaFin;
    private Double costoInstalacion;
    private Double costoMensual;

    private Long metodoPagoId;
    private String metodoPagoNombre;

    private Long clienteId;
    private String clienteNombre;
    private String clienteApellido;

    private Long planId;
    private String planNombre;

    private Long estadoContratoId;
    private String estadoContratoNombre;

    private Date creadoEn;
    private Date actualizadoEn;

    public ContratoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getCostoInstalacion() {
        return costoInstalacion;
    }

    public void setCostoInstalacion(Double costoInstalacion) {
        this.costoInstalacion = costoInstalacion;
    }

    public Double getCostoMensual() {
        return costoMensual;
    }

    public void setCostoMensual(Double costoMensual) {
        this.costoMensual = costoMensual;
    }

    public Long getMetodoPagoId() {
        return metodoPagoId;
    }

    public void setMetodoPagoId(Long metodoPagoId) {
        this.metodoPagoId = metodoPagoId;
    }

    public String getMetodoPagoNombre() {
        return metodoPagoNombre;
    }

    public void setMetodoPagoNombre(String metodoPagoNombre) {
        this.metodoPagoNombre = metodoPagoNombre;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteApellido() {
        return clienteApellido;
    }

    public void setClienteApellido(String clienteApellido) {
        this.clienteApellido = clienteApellido;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getPlanNombre() {
        return planNombre;
    }

    public void setPlanNombre(String planNombre) {
        this.planNombre = planNombre;
    }

    public Long getEstadoContratoId() {
        return estadoContratoId;
    }

    public void setEstadoContratoId(Long estadoContratoId) {
        this.estadoContratoId = estadoContratoId;
    }

    public String getEstadoContratoNombre() {
        return estadoContratoNombre;
    }

    public void setEstadoContratoNombre(String estadoContratoNombre) {
        this.estadoContratoNombre = estadoContratoNombre;
    }

    public Date getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }

    public Date getActualizadoEn() {
        return actualizadoEn;
    }

    public void setActualizadoEn(Date actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }
}