package com.ruben.bluewave.model;

import java.util.Date;

public class ClienteDTO extends AbstractValueObject {
	private Long id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String telefono;
	private String telefono2;
	private String telefono3;
	private String email;
	private String password;
	private Date fechaNacimiento;
	private Date fechaCreacion;
	private Date fechaBaja;

	// FK
	private Long estadoClienteId;
	private String estadoClienteNombre;

	private Long direccionId;
	private String direccionCalle;
	private String direccionNumero;
	private String direccionPiso;
	private String direccionDepartamento;
	private String direccionCodigoPostal;

	private Long ciudadId;
	private String ciudadNombre;

	private Long empleadoAsignadoId;
	private String empleadoNombre;
	private String empleadoApellido;

	private Long generoId;
	private String generoNombre;
	private String generoCodigo;

	private Long paisId;
	private String paisNombre;
	private String paisCodigoIso;

	private Long provinciaId;
	private String provinciaNombre;
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
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getTelefono3() {
		return telefono3;
	}
	public void setTelefono3(String telefono3) {
		this.telefono3 = telefono3;
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
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	public Long getEstadoClienteId() {
		return estadoClienteId;
	}
	public void setEstadoClienteId(Long estadoClienteId) {
		this.estadoClienteId = estadoClienteId;
	}
	public String getEstadoClienteNombre() {
		return estadoClienteNombre;
	}
	public void setEstadoClienteNombre(String estadoClienteNombre) {
		this.estadoClienteNombre = estadoClienteNombre;
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
	public String getDireccionDepartamento() {
		return direccionDepartamento;
	}
	public void setDireccionDepartamento(String direccionDepartamento) {
		this.direccionDepartamento = direccionDepartamento;
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
	public Long getEmpleadoAsignadoId() {
		return empleadoAsignadoId;
	}
	public void setEmpleadoAsignadoId(Long empleadoAsignadoId) {
		this.empleadoAsignadoId = empleadoAsignadoId;
	}
	public String getEmpleadoNombre() {
		return empleadoNombre;
	}
	public void setEmpleadoNombre(String empleadoNombre) {
		this.empleadoNombre = empleadoNombre;
	}
	public String getEmpleadoApellido() {
		return empleadoApellido;
	}
	public void setEmpleadoApellido(String empleadoApellido) {
		this.empleadoApellido = empleadoApellido;
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

	


	
}