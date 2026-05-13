package com.ruben.bluewave.model;

public class Direccion {
    private Long id;
    private String calle;
    private String numero;
    private String piso;
    private String puerta;
    private String codigoPostal;
    private Long ciudadId;
    private String ciudadNombre;        
    private String provinciaNombre;     
    private String paisNombre;         
    
  
    public Direccion() {}
    

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCalle() {
        return calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getPiso() {
        return piso;
    }
    
    public void setPiso(String piso) {
        this.piso = piso;
    }
    
    public String getPuerta() {
        return puerta;
    }
    
    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }
    
    public String getCodigoPostal() {
        return codigoPostal;
    }
    
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
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
    
    public String getProvinciaNombre() {
        return provinciaNombre;
    }
    
    public void setProvinciaNombre(String provinciaNombre) {
        this.provinciaNombre = provinciaNombre;
    }
    
    public String getPaisNombre() {
        return paisNombre;
    }
    
    public void setPaisNombre(String paisNombre) {
        this.paisNombre = paisNombre;
    }
}