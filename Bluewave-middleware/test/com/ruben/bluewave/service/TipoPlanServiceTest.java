package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.model.TipoPlan;
import com.ruben.bluewave.service.impl.TipoPlanServiceImpl;

public class TipoPlanServiceTest {

    private static TipoPlanService tipoPlanService = new TipoPlanServiceImpl();

    public static void testFindAll() {
        try {
            List<TipoPlan> tipos = tipoPlanService.findAll();
            if (tipos != null && !tipos.isEmpty()) {
                System.out.println("Tipos de plan encontrados: " + tipos.size());
                for (TipoPlan tp : tipos) {
                    System.out.println("  - ID: " + tp.getId() + ", Nombre: " + tp.getNombre() + ", Velocidad: " + tp.getVelocidadMbps() + " Mbps");
                }
            } else {
                System.out.println("No se encontraron tipos de plan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== TEST TIPO PLAN SERVICE ===");
        testFindAll();
    }
}