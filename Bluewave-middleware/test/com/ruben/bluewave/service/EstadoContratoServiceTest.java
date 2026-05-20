package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.model.EstadoContrato;
import com.ruben.bluewave.service.impl.EstadoContratoServiceImpl;

public class EstadoContratoServiceTest {

	private static EstadoContratoService estadoContratoService = new EstadoContratoServiceImpl();

	public static void testFindAll() {
		try {
			List<EstadoContrato> estados = estadoContratoService.findAll();
			if (estados != null && !estados.isEmpty()) {
				System.out.println("Estados de contrato encontrados: " + estados.size());
				for (EstadoContrato ec : estados) {
					System.out.println(
							"  - ID: " + ec.getId() + ", Nombre: " + ec.getNombre() + ", Activo: " + ec.getActivo());
				}
			} else {
				System.out.println("No se encontraron estados de contrato");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("=== TEST ESTADO CONTRATO SERVICE ===");
		testFindAll();
	}
}