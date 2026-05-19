package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.model.Pais;
import com.ruben.bluewave.service.impl.PaisServiceImpl;

public class PaisServiceTest {

	private PaisService service = null;

	public PaisServiceTest() {
		service = new PaisServiceImpl();
	}

	public void testFindById() {
		try {
			Pais pais = service.findById(1L);
			if (pais != null) {
				System.out.println("Pais encontrado:");
				System.out.println("  ID: " + pais.getId());
				System.out.println("  Nombre: " + pais.getNombre());
				System.out.println("  Codigo ISO: " + pais.getCodigoIso());
			} else {
				System.out.println("Pais no encontrado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFindAll() {
		try {
			List<Pais> resultados = service.findAll();
			System.out.println("Paises encontrados: " + (resultados != null ? resultados.size() : 0));
			if (resultados != null) {
				for (Pais pais : resultados) {
					System.out.println("  - ID: " + pais.getId() + ", Nombre: " + pais.getNombre()
							+ ", Codigo ISO: " + pais.getCodigoIso());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PaisServiceTest test = new PaisServiceTest();
		test.testFindById();
		// test.testFindAll();
	}
}
