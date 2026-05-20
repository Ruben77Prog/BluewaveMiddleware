package com.ruben.bluewave.service;

import java.util.List;

import com.ruben.bluewave.dao.criteria.PlanCriteria;
import com.ruben.bluewave.model.PlanDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.impl.PlanServiceImpl;

public class PlanServiceTest {

	private static PlanService planService = new PlanServiceImpl();

	private static PlanDTO createTestPlan() {
		long suffix = System.currentTimeMillis() % 100000;
		PlanDTO plan = new PlanDTO();
		plan.setNombre("Plan Test " + suffix);
		plan.setDescripcion("Descripción del plan de prueba");
		plan.setPrecio(49.99);
		plan.setDuracionMeses(12);
		plan.setDescuento(10.0);
		plan.setActivo(true);
		plan.setTipoPlanId(1L);
		return plan;
	}

	public static void testCreate() {
		try {
			Long planId = planService.create(createTestPlan());
			if (planId != null) {
				System.out.println("Plan creado con ID: " + planId);
			} else {
				System.out.println("Error al crear el plan");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testFindById() {
		try {
			Long planId = planService.create(createTestPlan());
			if (planId == null) {
				System.out.println("No se pudo crear el plan para buscar por ID");
				return;
			}
			PlanDTO plan = planService.findById(planId);
			if (plan != null) {
				System.out.println("Plan encontrado por ID:");
				System.out.println("  ID: " + plan.getId());
				System.out.println("  Nombre: " + plan.getNombre());
				System.out.println("  Precio: " + plan.getPrecio());
				System.out.println("  Duración: " + plan.getDuracionMeses() + " meses");
			} else {
				System.out.println("No se encontró el plan con ID " + planId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testFindAll() {
		try {
			List<PlanDTO> planes = planService.findAll();
			if (planes != null && !planes.isEmpty()) {
				System.out.println("Planes encontrados: " + planes.size());
				for (PlanDTO p : planes) {
					System.out.println(
							"  - ID: " + p.getId() + ", Nombre: " + p.getNombre() + ", Precio: " + p.getPrecio() + "€");
				}
			} else {
				System.out.println("No se encontraron planes");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testFindActivos() {
		try {
			List<PlanDTO> planes = planService.findActivos();
			if (planes != null && !planes.isEmpty()) {
				System.out.println("Planes activos encontrados: " + planes.size());
				for (PlanDTO p : planes) {
					System.out.println(
							"  - ID: " + p.getId() + ", Nombre: " + p.getNombre() + ", Activo: " + p.getActivo());
				}
			} else {
				System.out.println("No se encontraron planes activos");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testFindByCriteria() {
		try {
			PlanCriteria criteria = new PlanCriteria();
			criteria.setActivo(true);
			Results<PlanDTO> results = planService.findByCriteria(criteria, 0, Integer.MAX_VALUE);
			if (results != null) {
				List<PlanDTO> planes = results.getPage();
				if (planes != null && !planes.isEmpty()) {
					System.out.println("Planes por criterio (activos): " + planes.size());
					for (PlanDTO p : planes) {
						System.out.println("  - ID: " + p.getId() + ", Nombre: " + p.getNombre());
					}
				} else {
					System.out.println("No se encontraron planes con el criterio especificado");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() {
		try {
			PlanDTO plan = createTestPlan();
			Long planId = planService.create(plan);
			if (planId == null) {
				System.out.println("No se pudo crear el plan para actualizar");
				return;
			}
			plan.setId(planId);
			plan.setPrecio(59.99);
			plan.setDescuento(15.0);
			boolean actualizado = planService.update(plan);
			System.out.println("Actualización del plan: " + (actualizado ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() {
		try {
			PlanDTO plan = createTestPlan();
			Long planId = planService.create(plan);
			if (planId == null) {
				System.out.println("No se pudo crear el plan para eliminar");
				return;
			}
			boolean eliminado = planService.delete(planId);
			System.out.println("Eliminación del plan: " + (eliminado ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("=== TEST PLAN SERVICE ===");
		testCreate();
		testFindById();
		testFindAll();
		testFindActivos();
		testFindByCriteria();
		testUpdate();
		testDelete();
	}
}