//package com.ruben.bluewave.dao;
//
//import java.util.List;
//
//import com.ruben.bluewave.dao.criteria.PlanCriteria;
//import com.ruben.bluewave.model.Plan;
//
//public class PlanDAOTest {
//
//	public static final void testFindById() {
//		try {
//			PlanDAO dao = new PlanDAO();
//			Plan p = dao.findById(2L);
//
//			if (p != null) {
//				System.out.println("Plan encontrado:");
//				System.out.println("ID: " + p.getId());
//				System.out.println("Nombre: " + p.getNombre());
//				System.out.println("Descripción: " + p.getDescripcion());
//				System.out.println("Precio desde: " + p.getPrecioDesde());
//				System.out.println("Precio hasta: " + p.getPrecioHasta());
//				System.out.println("Duración meses: " + p.getDuracionMeses());
//				System.out.println("Descuento: " + p.getDescuento());
//				System.out.println("Tipo Plan ID: " + p.getTipoPlanId());
//			} else {
//				System.out.println("No se encontró el plan con ID 2");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static final void testFindBy() {
//		try {
//			PlanDAO dao = new PlanDAO();
//
//			// List<Plan> resultados = dao.findBy(null, "ad", "a", 20d, 2l);
//
//			PlanCriteria criteria = new PlanCriteria();
//			criteria.setNombre("a");
//			criteria.setPrecioDesde(10d);
//			criteria.setPrecioHasta(50d);
//
//			// criteria.setDuracionMesesDesde(1);
//			// criteria.setDuracionMesesHasta(12);
//			criteria.setActivo(true);
//
//			List<Plan> resultados = dao.findBy(criteria);
//
//			System.out.println("Resultados de búsqueda (" + resultados.size() + " planes encontrados):");
//			for (Plan p : resultados) {
//				System.out.println("[" + p.getId() + "] [" + p.getNombre() + "] [" + p.getDescripcion() + "] ["
//						+ p.getPrecioDesde() + "€ - " + p.getPrecioHasta() + "€] [" + p.getDuracionMeses() + " meses] ["
//						+ (p.getActivo() ? "Activo" : "Inactivo") + "]");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static final void testCreate() {
//		try {
//			PlanDAO dao = new PlanDAO();
//
//			Plan p = new Plan();
//			p.setNombre("Plan de prueba");
//			p.setDescripcion("Descripción del plan de prueba creado desde PlanDAOTest");
//			p.setPrecioDesde(30.23d);
//			p.setPrecioHasta(39.99d);
//			p.setDescuento(1d);
//			p.setDuracionMeses(3);
//			p.setFechaCreacion(new java.util.Date());
//			p.setTipoPlanId(1L);
//			p.setActivo(true);
//
//			p = dao.create(p);
//			System.out.println("Plan creado exitosamente con ID: " + p.getId());
//			System.out.println("Detalles del plan creado:");
//			System.out.println("Nombre: " + p.getNombre());
//			System.out.println("Precio: " + p.getPrecioDesde() + "€ - " + p.getPrecioHasta() + "€");
//			System.out.println("Duración: " + p.getDuracionMeses() + " meses");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static final void testUpdate() {
//		try {
//			PlanDAO dao = new PlanDAO();
//
//			Plan p = dao.findById(2L);
//
//			if (p != null) {
//				System.out.println("Plan antes de actualizar:");
//				System.out.println("Nombre: " + p.getNombre());
//				System.out.println("Descripción: " + p.getDescripcion());
//
//				p.setNombre(p.getNombre() + " - MODIFICADO");
//				p.setDescripcion(p.getDescripcion() + " (Actualizado el " + new java.util.Date() + ")");
//				p.setDescuento(5d);
//
//				Plan updated = dao.update(p);
//				if (updated != null) {
//					System.out.println("Plan actualizado exitosamente");
//					System.out.println("Nombre después: " + updated.getNombre());
//				} else {
//					System.out.println("Error al actualizar el plan");
//				}
//			} else {
//				System.out.println("No se encontró el plan para actualizar");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static final void testDelete() {
//		try {
//			PlanDAO dao = new PlanDAO();
//
//			Plan tempPlan = new Plan();
//			tempPlan.setNombre("Plan temporal para eliminar");
//			tempPlan.setDescripcion("Este plan será eliminado en la prueba");
//			tempPlan.setPrecioDesde(10d);
//			tempPlan.setPrecioHasta(20d);
//			tempPlan.setDuracionMeses(1);
//			tempPlan.setDescuento(0d);
//			tempPlan.setFechaCreacion(new java.util.Date());
//			tempPlan.setTipoPlanId(1L);
//			tempPlan.setActivo(true);
//
//			tempPlan = dao.create(tempPlan);
//			System.out.println("Plan temporal creado con ID: " + tempPlan.getId());
//
//			boolean deleted = dao.delete(tempPlan.getId());
//			if (deleted) {
//				System.out.println("Plan eliminado exitosamente");
//
//				Plan verify = dao.findById(tempPlan.getId());
//				if (verify == null) {
//					System.out.println("Verificación: El plan fue eliminado correctamente");
//				} else {
//					System.out.println("ERROR: El plan no fue eliminado");
//				}
//			} else {
//				System.out.println("Error al eliminar el plan");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args) {
//
//		testFindById();
//		// testFindBy();
//		// testCreate();
//		// testUpdate();
//		// testDelete();
//
//	}
//}
