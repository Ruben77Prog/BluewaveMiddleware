package com.ruben.bluewave.service;

import java.util.Date;

import com.ruben.bluewave.model.Incidencia;
import com.ruben.bluewave.model.IncidenciaDTO;
import com.ruben.bluewave.service.impl.IncidenciaServiceImpl;

public class IncidenciaServiceTest {

    private IncidenciaService incidenciaService;

    public IncidenciaServiceTest() {
        incidenciaService = new IncidenciaServiceImpl();
    }

    public void testCreateIncidencia() {
        System.out.println("\n=== CREAR INCIDENCIA ===");
        try {
            Incidencia inc = new Incidencia();
            inc.setNumeroIncidencia("TEST-" + System.currentTimeMillis());
            inc.setTitulo("Incidencia de prueba");
            inc.setDescripcion("Descripción automática");
            inc.setFechaIncidencia(new Date());
            inc.setHorasEstimadas(1.5);
            inc.setTipoIncidenciaId(1L);     
            inc.setContratoId(1L);           
            inc.setEstadoIncidenciaId(1L);    
            inc.setCreadorEmpleadoId(1L);      

            Long id = incidenciaService.create(inc);
            if (id != null) {
                System.out.println(" Incidencia creada con ID: " + id);
            } else {
                System.out.println(" Error: no se pudo crear (servicio devolvió null)");
            }
        } catch (Exception e) {
            System.err.println(" Excepción: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void testFindById() {
        System.out.println("\n=== BUSCAR INCIDENCIA POR ID ===");
        try {
            Long id = 1L; 
            IncidenciaDTO inc = incidenciaService.findById(id);  
            if (inc != null) {
                System.out.println("Encontrada: " + inc.getNumeroIncidencia() + " - " + inc.getTitulo());
            } else {
                System.out.println("No existe incidencia con ID " + id);
            }
        } catch (Exception e) {
            System.err.println(" Excepción: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        IncidenciaServiceTest test = new IncidenciaServiceTest();
        test.testCreateIncidencia();
//        test.testFindById();
    }
}