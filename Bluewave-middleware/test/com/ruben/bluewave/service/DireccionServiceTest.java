package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.DireccionCriteria;
import com.ruben.bluewave.model.Direccion;
import com.ruben.bluewave.service.impl.DireccionServiceImpl;

public class DireccionServiceTest {

    private DireccionService direccionService = null;

    public DireccionServiceTest() {
        direccionService = new DireccionServiceImpl();
    }

    public void testCreateAndFindById() {
        System.out.println();
        System.out.println("- testCreateAndFindById -");

       
        Direccion direccion = new Direccion();
        direccion.setCalle("Calle de Prueba");
        direccion.setNumero("123");
        direccion.setPiso("2º");
        direccion.setPuerta("B");
        direccion.setCodigoPostal("28001");
        
        direccion.setCiudadId(1L);

        System.out.println("Creando dirección: " + direccion.getCalle() + ", " + direccion.getNumero());
        try {
            Direccion creada = direccionService.create(direccion);
            if (creada != null && creada.getId() != null) {
                System.out.println("Dirección creada con ID: " + creada.getId());

              
                Direccion encontrada = direccionService.findById(creada.getId());
                if (encontrada != null) {
                    System.out.println("Dirección recuperada:");
                    System.out.println("  ID: " + encontrada.getId());
                    System.out.println("  Calle: " + encontrada.getCalle());
                    System.out.println("  Número: " + encontrada.getNumero());
                    System.out.println("  Piso: " + encontrada.getPiso());
                    System.out.println("  Puerta: " + encontrada.getPuerta());
                    System.out.println("  CP: " + encontrada.getCodigoPostal());
                    System.out.println("  Ciudad ID: " + encontrada.getCiudadId());
                } else {
                    System.out.println("ERROR: No se pudo recuperar la dirección por ID");
                }
            } else {
                System.out.println("ERROR: No se pudo crear la dirección (DAO devolvió null)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testFindByCriteria() {
        System.out.println();
        System.out.println("- testFindByCriteria -");

        
        DireccionCriteria criteria = new DireccionCriteria();
        criteria.setCalle("Prueba");

        try {
            List<Direccion> direcciones = direccionService.findByCriteria(criteria);
            if (direcciones != null && !direcciones.isEmpty()) {
                System.out.println("Direcciones encontradas: " + direcciones.size());
                for (Direccion d : direcciones) {
                    System.out.println("  - ID: " + d.getId() + ", Calle: " + d.getCalle());
                }
            } else {
                System.out.println("No se encontraron direcciones con criterio 'calle=Prueba'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testUpdate() {
        System.out.println();
        System.out.println("- testUpdate -");

        
        Direccion direccion = new Direccion();
        direccion.setCalle("Calle Original");
        direccion.setNumero("0");
        direccion.setCiudadId(1L);
        try {
            Direccion creada = direccionService.create(direccion);
            if (creada == null || creada.getId() == null) {
                System.out.println("ERROR: No se pudo crear la dirección de prueba");
                return;
            }
            System.out.println("Dirección creada con ID: " + creada.getId() + " - Calle: " + creada.getCalle());

            
            creada.setCalle("Calle Modificada");
            boolean actualizado = direccionService.update(creada);
            if (actualizado) {
                System.out.println("Dirección actualizada correctamente");

                // Verificar cambios
                Direccion modificada = direccionService.findById(creada.getId());
                if (modificada != null && "Calle Modificada".equals(modificada.getCalle())) {
                    System.out.println("Verificación: calle actualizada a '" + modificada.getCalle() + "'");
                } else {
                    System.out.println("ERROR: La calle no se actualizó correctamente");
                }
            } else {
                System.out.println("ERROR: No se pudo actualizar la dirección");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testDelete() {
        System.out.println();
        System.out.println("- testDelete -");

      
        Direccion direccion = new Direccion();
        direccion.setCalle("Calle a Eliminar");
        direccion.setNumero("999");
        direccion.setCiudadId(1L);
        try {
            Direccion creada = direccionService.create(direccion);
            if (creada == null || creada.getId() == null) {
                System.out.println("ERROR: No se pudo crear la dirección de prueba");
                return;
            }
            Long id = creada.getId();
            System.out.println("Dirección creada con ID: " + id);

            
            boolean eliminado = direccionService.delete(id);
            if (eliminado) {
                System.out.println("Dirección eliminada correctamente");

             
                Direccion encontrada = direccionService.findById(id);
                if (encontrada == null) {
                    System.out.println("Verificación: la dirección ya no existe en BD");
                } else {
                    System.out.println("ERROR: La dirección sigue existiendo después de eliminar");
                }
            } else {
                System.out.println("ERROR: No se pudo eliminar la dirección");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DireccionServiceTest test = new DireccionServiceTest();

        System.out.println("===== DIRECCIÓN SERVICE TEST =====");
        test.testCreateAndFindById();
//        test.testFindByCriteria();
//        test.testUpdate();
//        test.testDelete();
    }
}