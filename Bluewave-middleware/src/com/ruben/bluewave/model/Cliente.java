package com.ruben.bluewave.model;

import java.util.Date;

public class Cliente {
    private Long id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
    private String telefono;
    private String telefono2;
    private String telefono3;
    private String email;
    private String contrasena;
    private Date fechaNacimiento;

 
    private Date fechaAlta;
    private Date fechaBaja;

    private Long estadoClienteId;
    private Long direccionId;
    private Long empleadoAsignadoId;
    private Long generoId;
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
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
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
	public Long getDireccionId() {
		return direccionId;
	}
	public void setDireccionId(Long direccionId) {
		this.direccionId = direccionId;
	}
	public Long getEmpleadoAsignadoId() {
		return empleadoAsignadoId;
	}
	public void setEmpleadoAsignadoId(Long empleadoAsignadoId) {
		this.empleadoAsignadoId = empleadoAsignadoId;
	}
	public Long getGeneroId() {
		return generoId;
	}
	public void setGeneroId(Long generoId) {
		this.generoId = generoId;
	}
    
    



}