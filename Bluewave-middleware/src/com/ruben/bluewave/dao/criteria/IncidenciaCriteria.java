package com.ruben.bluewave.dao.criteria;

import java.util.Date;

public class IncidenciaCriteria {

    public static final String ORDER_BY_ID = " i.id ";
    public static final String ORDER_BY_NUMERO = " i.numero_incidencia ";
    public static final String ORDER_BY_FECHA = " i.fecha_incidencia ";

    private Long id;
    private String numeroIncidencia;
    private String titulo;
    private Long tipoIncidenciaId;
    private Long contratoId;
    private Long estadoIncidenciaId;
    
   
    private String contratoNumero;
    private String clienteNombre;
    private String empleadoAsignadoNombre;
    
  
    private Date fechaDesde;
    private Date fechaHasta;
    
    private String orderBy = ORDER_BY_FECHA;
    private Boolean ascDesc = false;   

 
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroIncidencia() { return numeroIncidencia; }
    public void setNumeroIncidencia(String numeroIncidencia) { this.numeroIncidencia = numeroIncidencia; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Long getTipoIncidenciaId() { return tipoIncidenciaId; }
    public void setTipoIncidenciaId(Long tipoIncidenciaId) { this.tipoIncidenciaId = tipoIncidenciaId; }

    public Long getContratoId() { return contratoId; }
    public void setContratoId(Long contratoId) { this.contratoId = contratoId; }

    public Long getEstadoIncidenciaId() { return estadoIncidenciaId; }
    public void setEstadoIncidenciaId(Long estadoIncidenciaId) { this.estadoIncidenciaId = estadoIncidenciaId; }

    public String getContratoNumero() { return contratoNumero; }
    public void setContratoNumero(String contratoNumero) { this.contratoNumero = contratoNumero; }

    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }

    public String getEmpleadoAsignadoNombre() { return empleadoAsignadoNombre; }
    public void setEmpleadoAsignadoNombre(String empleadoAsignadoNombre) { this.empleadoAsignadoNombre = empleadoAsignadoNombre; }

    public Date getFechaDesde() { return fechaDesde; }
    public void setFechaDesde(Date fechaDesde) { this.fechaDesde = fechaDesde; }

    public Date getFechaHasta() { return fechaHasta; }
    public void setFechaHasta(Date fechaHasta) { this.fechaHasta = fechaHasta; }

    public String getOrderBy() { return orderBy; }
    public void setOrderBy(String orderBy) { this.orderBy = orderBy; }

    public Boolean getAscDesc() { return ascDesc; }
    public void setAscDesc(Boolean ascDesc) { this.ascDesc = ascDesc; }
}