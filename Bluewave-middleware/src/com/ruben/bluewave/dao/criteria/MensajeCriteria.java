package com.ruben.bluewave.dao.criteria;

import java.util.Date;

public class MensajeCriteria {
    public final String ORDER_BY_NUMERO = " m.numero_mensaje ";
    public final String ORDER_BY_ASUNTO = " m.asunto ";
    public final String ORDER_BY_FECHA_ENVIO = " m.fecha_envio ";
    public final String ORDER_BY_INCIDENCIA = " incidencia_titulo ";
    public final String ORDER_BY_CLIENTE_REMITENTE = " cliente_remitente_nombre ";
    public final String ORDER_BY_EMPLEADO_REMITENTE = " empleado_remitente_nombre ";

    private Long id = null;
    private String numeroMensaje = null;
    private String asunto = null;
    private Long incidenciaId = null;
    private String incidenciaTitulo = null;
    private Long clienteRemitenteId = null;
    private String clienteRemitenteNombre = null;
    private Long empleadoRemitenteId = null;
    private String empleadoRemitenteNombre = null;
    private Long clienteDestinatarioId = null;
    private String clienteDestinatarioNombre = null;
    private Long empleadoDestinatarioId = null;
    private String empleadoDestinatarioNombre = null;
    private Boolean leido = null;
    private Boolean importante = null;
    private Date fechaEnvioDesde = null;
    private Date fechaEnvioHasta = null;
    private String orderBy = ORDER_BY_FECHA_ENVIO;
    private Boolean ascDesc = false;

    public MensajeCriteria() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroMensaje() {
        return numeroMensaje;
    }

    public void setNumeroMensaje(String numeroMensaje) {
        this.numeroMensaje = numeroMensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Long getIncidenciaId() {
        return incidenciaId;
    }

    public void setIncidenciaId(Long incidenciaId) {
        this.incidenciaId = incidenciaId;
    }

    public String getIncidenciaTitulo() {
        return incidenciaTitulo;
    }

    public void setIncidenciaTitulo(String incidenciaTitulo) {
        this.incidenciaTitulo = incidenciaTitulo;
    }

    public Long getClienteRemitenteId() {
        return clienteRemitenteId;
    }

    public void setClienteRemitenteId(Long clienteRemitenteId) {
        this.clienteRemitenteId = clienteRemitenteId;
    }

    public String getClienteRemitenteNombre() {
        return clienteRemitenteNombre;
    }

    public void setClienteRemitenteNombre(String clienteRemitenteNombre) {
        this.clienteRemitenteNombre = clienteRemitenteNombre;
    }

    public Long getEmpleadoRemitenteId() {
        return empleadoRemitenteId;
    }

    public void setEmpleadoRemitenteId(Long empleadoRemitenteId) {
        this.empleadoRemitenteId = empleadoRemitenteId;
    }

    public String getEmpleadoRemitenteNombre() {
        return empleadoRemitenteNombre;
    }

    public void setEmpleadoRemitenteNombre(String empleadoRemitenteNombre) {
        this.empleadoRemitenteNombre = empleadoRemitenteNombre;
    }

    public Long getClienteDestinatarioId() {
        return clienteDestinatarioId;
    }

    public void setClienteDestinatarioId(Long clienteDestinatarioId) {
        this.clienteDestinatarioId = clienteDestinatarioId;
    }

    public String getClienteDestinatarioNombre() {
        return clienteDestinatarioNombre;
    }

    public void setClienteDestinatarioNombre(String clienteDestinatarioNombre) {
        this.clienteDestinatarioNombre = clienteDestinatarioNombre;
    }

    public Long getEmpleadoDestinatarioId() {
        return empleadoDestinatarioId;
    }

    public void setEmpleadoDestinatarioId(Long empleadoDestinatarioId) {
        this.empleadoDestinatarioId = empleadoDestinatarioId;
    }

    public String getEmpleadoDestinatarioNombre() {
        return empleadoDestinatarioNombre;
    }

    public void setEmpleadoDestinatarioNombre(String empleadoDestinatarioNombre) {
        this.empleadoDestinatarioNombre = empleadoDestinatarioNombre;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public Boolean getImportante() {
        return importante;
    }

    public void setImportante(Boolean importante) {
        this.importante = importante;
    }

    public Date getFechaEnvioDesde() {
        return fechaEnvioDesde;
    }

    public void setFechaEnvioDesde(Date fechaEnvioDesde) {
        this.fechaEnvioDesde = fechaEnvioDesde;
    }

    public Date getFechaEnvioHasta() {
        return fechaEnvioHasta;
    }

    public void setFechaEnvioHasta(Date fechaEnvioHasta) {
        this.fechaEnvioHasta = fechaEnvioHasta;
    }

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getAscDesc() {
		return ascDesc;
	}

	public void setAscDesc(Boolean ascDesc) {
		this.ascDesc = ascDesc;
	}
}
