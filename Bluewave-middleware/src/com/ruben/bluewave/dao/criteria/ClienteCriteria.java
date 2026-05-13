package com.ruben.bluewave.dao.criteria;

import java.util.Date;

import com.ruben.bluewave.model.AbstractValueObject;

public class ClienteCriteria extends AbstractValueObject{
	
	public final String ORDER_BY_NOMBRE = " c.nombre ";
	public final String ORDER_BY_APELLIDO1 = " c.apellido1 ";
	public final String ORDER_BY_EMAIL = " c.email ";
	public final String ORDER_BY_FECHA_CREACION = " c.fecha_creacion ";
	
	
    private Long id = null;
    private String nombre = null;
    private String apellido1 = null;
    private String apellido2 = null;
    private String dni = null;
    private String telefono = null;
    private String email = null;
    private Date fechaNacimientoDesde = null;
    private Date fechaNacimientoHasta = null;
    private Date fechaCreacionDesde = null;   
    private Date fechaCreacionHasta = null;   
    private Long estadoClienteId = null;
    private Long direccionId = null;
    private Long empleadoAsignadoId = null;
    private Long generoId = null;
    
    // para paginacion en el service
    private String orderBy = ORDER_BY_NOMBRE;
    private Boolean ascDesc = true;
    
    

    public ClienteCriteria() {
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

    public Date getFechaNacimientoDesde() {
        return fechaNacimientoDesde;
    }

    public void setFechaNacimientoDesde(Date fechaNacimientoDesde) {
        this.fechaNacimientoDesde = fechaNacimientoDesde;
    }

    public Date getFechaNacimientoHasta() {
        return fechaNacimientoHasta;
    }

    public void setFechaNacimientoHasta(Date fechaNacimientoHasta) {
        this.fechaNacimientoHasta = fechaNacimientoHasta;
    }

    public Date getFechaCreacionDesde() {
        return fechaCreacionDesde;
    }

    public void setFechaCreacionDesde(Date fechaCreacionDesde) {
        this.fechaCreacionDesde = fechaCreacionDesde;
    }

    public Date getFechaCreacionHasta() {
        return fechaCreacionHasta;
    }

    public void setFechaCreacionHasta(Date fechaCreacionHasta) {
        this.fechaCreacionHasta = fechaCreacionHasta;
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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getAscDesc() {
		return ascDesc;
	}

	public boolean isAscDesc() {
		return Boolean.TRUE.equals(ascDesc);
	}

	public void setAscDesc(Boolean ascDesc) {
		this.ascDesc = ascDesc;
	}
}
