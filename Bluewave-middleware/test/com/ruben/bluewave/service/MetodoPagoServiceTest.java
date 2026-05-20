package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.model.MetodoPago;
import com.ruben.bluewave.service.impl.MetodoPagoServiceImpl;

public class MetodoPagoServiceTest {

    private static MetodoPagoService metodoPagoService = new MetodoPagoServiceImpl();

    public static void testFindAll() {
        try {
            List<MetodoPago> metodos = metodoPagoService.findAll();
            if (metodos != null && !metodos.isEmpty()) {
                System.out.println("Métodos de pago encontrados: " + metodos.size());
                for (MetodoPago mp : metodos) {
                    System.out.println("  - ID: " + mp.getId() + ", Nombre: " + mp.getNombre() + ", Activo: " + mp.getActivo());
                }
            } else {
                System.out.println("No se encontraron métodos de pago");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== TEST METODO PAGO SERVICE ===");
        testFindAll();
    }
}