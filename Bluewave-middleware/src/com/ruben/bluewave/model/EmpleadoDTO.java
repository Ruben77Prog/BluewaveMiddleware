package com.ruben.bluewave.model;

import java.util.Date;

public class EmpleadoDTO extends AbstractValueObject {
	private Long id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String telefono;
	private String email;
	private String password;
	private Date fechaCreacion;
	private Date fechaBaja;
	private Date ultimoLogin;
	private Boolean activo;

	// FK
	private Long rolId;
	private String rolNombre;

	private Long generoId;
	private String generoNombre;
	private String generoCodigo;

	private Long direccionId;
	private String direccionCalle;
	private String direccionNumero;
	private String direccionPiso;
	private String direccionApartamento;
	private String direccionCodigoPostal;

	private Long ciudadId;
	private String ciudadNombre;

	private Long provinciaId;
	private String provinciaNombre;

	private Long paisId;
	private String paisNombre;
	private String paisCodigoIso;

	public EmpleadoDTO() {
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

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(Date ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Long getRolId() {
		return rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	public String getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}

	public Long getGeneroId() {
		return generoId;
	}

	public void setGeneroId(Long generoId) {
		this.generoId = generoId;
	}

	public String getGeneroNombre() {
		return generoNombre;
	}

	public void setGeneroNombre(String generoNombre) {
		this.generoNombre = generoNombre;
	}

	public String getGeneroCodigo() {
		return generoCodigo;
	}

	public void setGeneroCodigo(String generoCodigo) {
		this.generoCodigo = generoCodigo;
	}

	public Long getDireccionId() {
		return direccionId;
	}

	public void setDireccionId(Long direccionId) {
		this.direccionId = direccionId;
	}

	public String getDireccionCalle() {
		return direccionCalle;
	}

	public void setDireccionCalle(String direccionCalle) {
		this.direccionCalle = direccionCalle;
	}

	public String getDireccionNumero() {
		return direccionNumero;
	}

	public void setDireccionNumero(String direccionNumero) {
		this.direccionNumero = direccionNumero;
	}

	public String getDireccionPiso() {
		return direccionPiso;
	}

	public void setDireccionPiso(String direccionPiso) {
		this.direccionPiso = direccionPiso;
	}

	public String getDireccionApartamento() {
		return direccionApartamento;
	}

	public void setDireccionApartamento(String direccionApartamento) {
		this.direccionApartamento = direccionApartamento;
	}

	public String getDireccionCodigoPostal() {
		return direccionCodigoPostal;
	}

	public void setDireccionCodigoPostal(String direccionCodigoPostal) {
		this.direccionCodigoPostal = direccionCodigoPostal;
	}

	public Long getCiudadId() {
		return ciudadId;
	}

	public void setCiudadId(Long ciudadId) {
		this.ciudadId = ciudadId;
	}

	public String getCiudadNombre() {
		return ciudadNombre;
	}

	public void setCiudadNombre(String ciudadNombre) {
		this.ciudadNombre = ciudadNombre;
	}

	public Long getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(Long provinciaId) {
		this.provinciaId = provinciaId;
	}

	public String getProvinciaNombre() {
		return provinciaNombre;
	}

	public void setProvinciaNombre(String provinciaNombre) {
		this.provinciaNombre = provinciaNombre;
	}

	public Long getPaisId() {
		return paisId;
	}

	public void setPaisId(Long paisId) {
		this.paisId = paisId;
	}

	public String getPaisNombre() {
		return paisNombre;
	}

	public void setPaisNombre(String paisNombre) {
		this.paisNombre = paisNombre;
	}

	public String getPaisCodigoIso() {
		return paisCodigoIso;
	}

	public void setPaisCodigoIso(String paisCodigoIso) {
		this.paisCodigoIso = paisCodigoIso;
	}

	@Override
	public String toString() {
		return nombre + " " + apellido1;
	}
}