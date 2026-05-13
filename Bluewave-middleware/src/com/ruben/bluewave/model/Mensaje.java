package com.ruben.bluewave.model;

import java.util.Date;

public class Mensaje {
	private Long id;
	private String numeroMensaje;
	private String asunto;
	private String cuerpo;
	private Long tipoMensajeId;
	private Long incidenciaId;
	private String tipoRemitente;
	private Long clienteRemitenteId;
	private Long empleadoRemitenteId;
	private String tipoDestinatario;
	private Long clienteDestinatarioId;
	private Long empleadoDestinatarioId;
	private Long mensajePadreId;
	private Boolean leido;
	private Boolean importante;
	private Date fechaEnvio;
	private Date fechaLectura;

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

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Long getTipoMensajeId() {
		return tipoMensajeId;
	}

	public void setTipoMensajeId(Long tipoMensajeId) {
		this.tipoMensajeId = tipoMensajeId;
	}

	public Long getIncidenciaId() {
		return incidenciaId;
	}

	public void setIncidenciaId(Long incidenciaId) {
		this.incidenciaId = incidenciaId;
	}

	public String getTipoRemitente() {
		return tipoRemitente;
	}

	public void setTipoRemitente(String tipoRemitente) {
		this.tipoRemitente = tipoRemitente;
	}

	public Long getClienteRemitenteId() {
		return clienteRemitenteId;
	}

	public void setClienteRemitenteId(Long clienteRemitenteId) {
		this.clienteRemitenteId = clienteRemitenteId;
	}

	public Long getEmpleadoRemitenteId() {
		return empleadoRemitenteId;
	}

	public void setEmpleadoRemitenteId(Long empleadoRemitenteId) {
		this.empleadoRemitenteId = empleadoRemitenteId;
	}

	public String getTipoDestinatario() {
		return tipoDestinatario;
	}

	public void setTipoDestinatario(String tipoDestinatario) {
		this.tipoDestinatario = tipoDestinatario;
	}

	public Long getClienteDestinatarioId() {
		return clienteDestinatarioId;
	}

	public void setClienteDestinatarioId(Long clienteDestinatarioId) {
		this.clienteDestinatarioId = clienteDestinatarioId;
	}

	public Long getEmpleadoDestinatarioId() {
		return empleadoDestinatarioId;
	}

	public void setEmpleadoDestinatarioId(Long empleadoDestinatarioId) {
		this.empleadoDestinatarioId = empleadoDestinatarioId;
	}

	public Long getMensajePadreId() {
		return mensajePadreId;
	}

	public void setMensajePadreId(Long mensajePadreId) {
		this.mensajePadreId = mensajePadreId;
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

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Date getFechaLectura() {
		return fechaLectura;
	}

	public void setFechaLectura(Date fechaLectura) {
		this.fechaLectura = fechaLectura;
	}

}