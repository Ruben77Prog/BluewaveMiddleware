//package com.ruben.bluewave.service;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import com.ruben.bluewave.dao.criteria.ContratoCriteria;
//import com.ruben.bluewave.model.ContratoDTO;
//import com.ruben.bluewave.service.impl.ContratoServiceImpl;
//
//public class ContratoServiceTest {
//
//	private ContratoService contratoService = null;
//
//	public ContratoServiceTest() {
//		contratoService = new ContratoServiceImpl();
//	}
//
//	private ContratoDTO createTestContrato() {
//
//		ContratoDTO testContrato = new ContratoDTO();
//		testContrato.setNumeroContrato("CNT-2024-001");
//
//		testContrato.setFechaInicio(new Date());
//
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.YEAR, 1);
//		testContrato.setFechaFin(cal.getTime());
//
//		testContrato.setCostoInstalacion(50.0);
//
//		testContrato.setCostoMensual(39.99);
//
//		testContrato.setMetodoPagoId(1L);
//		testContrato.setClienteId(1L);
//		testContrato.setPlanId(1L);
//		testContrato.setEstadoContratoId(1L);
//
//		return testContrato;
//	}
//
//	public void testCreate() {
//
//		ContratoDTO newContrato = createTestContrato();
//		Long contratoId = contratoService.create(newContrato);
//
//		if (contratoId != null) {
//			System.out.println("Contrato registrado con ID: " + contratoId);
//		} else {
//			System.out.println("El registro del Contrato falló.");
//		}
//	}
//
//	public void testFindById() {
//		Long idBuscar = 1L;
//		ContratoDTO contrato = contratoService.findById(idBuscar);
//
//		if (contrato != null) {
//			System.out.println("testFindById Ok");
//			System.out.println("  ID: " + contrato.getId());
//			System.out.println("  Número: " + contrato.getNumeroContrato());
//			System.out.println("  Cliente: " + contrato.getClienteNombre() + " " + contrato.getClienteApellido());
//			System.out.println("  Plan: " + contrato.getPlanNombre());
//			System.out.println("  Estado: " + contrato.getEstadoContratoNombre());
//
//			System.out.println("  Costo Mensual: " + contrato.getCostoMensual() + "€");
//		} else {
//			System.out.println("testFindById Fallo: No se encontró el contrato con ID " + idBuscar);
//		}
//	}
//
//	public void testFindByCliente() {
//		Long clienteId = 1L;
//		List<ContratoDTO> contratos = contratoService.findByCliente(clienteId);
//
//		System.out.println("Contratos encontrados para cliente ID " + clienteId + ": " + contratos.size());
//
//		if (contratos.isEmpty()) {
//			System.out.println("No se encontraron contratos para ese cliente");
//		} else {
//			for (ContratoDTO contrato : contratos) {
//				System.out.println("Contrato encontrado:");
//				System.out.println("  ID: " + contrato.getId());
//				System.out.println("  Número: " + contrato.getNumeroContrato());
//				System.out.println("  Plan: " + contrato.getPlanNombre());
//				System.out.println("  Estado: " + contrato.getEstadoContratoNombre());
//				System.out.println("  Fecha Inicio: " + contrato.getFechaInicio());
//				System.out.println("  Fecha Fin: " + contrato.getFechaFin());
//				System.out.println();
//			}
//		}
//	}
//
//	public void testFindByNumeroContrato() {
//		String numeroContrato = "CNT-2024-001";
//		List<ContratoDTO> contratos = contratoService.findByNumeroContrato(numeroContrato);
//
//		System.out.println("Resultados encontrados: " + contratos.size());
//
//		if (contratos.isEmpty()) {
//			System.out.println("No se encontró ningún contrato con ese número");
//		} else {
//			for (ContratoDTO contrato : contratos) {
//				System.out.println("Contrato encontrado:");
//				System.out.println("  ID: " + contrato.getId());
//				System.out.println("  Número: " + contrato.getNumeroContrato());
//				System.out.println("  Cliente: " + contrato.getClienteNombre() + " " + contrato.getClienteApellido());
//				System.out.println("  Estado: " + contrato.getEstadoContratoNombre());
//				System.out.println();
//			}
//		}
//	}
//
//	public void testFindByEstado() {
//		Long estadoId = 1L;
//		List<ContratoDTO> contratos = contratoService.findByEstado(estadoId);
//
//		System.out.println("Contratos en estado " + estadoId + ": " + contratos.size());
//
//		if (contratos.isEmpty()) {
//			System.out.println("No se encontraron contratos en ese estado");
//		} else {
//			for (ContratoDTO contrato : contratos) {
//				System.out.println("Contrato encontrado:");
//				System.out.println("  ID: " + contrato.getId());
//				System.out.println("  Número: " + contrato.getNumeroContrato());
//				System.out.println("  Cliente: " + contrato.getClienteNombre());
//				System.out.println("  Estado: " + contrato.getEstadoContratoNombre());
//				System.out.println();
//			}
//		}
//	}
//
//	public void testFindByCriteria() {
//		ContratoCriteria criteria = new ContratoCriteria();
//		criteria.setClienteId(1L);
//		criteria.setEstadoContratoId(1L);
//
//		List<ContratoDTO> resultados = contratoService.findBy(criteria);
//
//		System.out.println("Resultados encontrados: " + resultados.size());
//
//		if (resultados.isEmpty()) {
//			System.out.println("No se encontró ningún contrato con esos criterios");
//		} else {
//			for (ContratoDTO contrato : resultados) {
//				System.out.println("Contrato encontrado:");
//				System.out.println("  ID: " + contrato.getId());
//				System.out.println("  Número: " + contrato.getNumeroContrato());
//				System.out.println("  Cliente: " + contrato.getClienteNombre() + " " + contrato.getClienteApellido());
//				System.out.println("  Plan: " + contrato.getPlanNombre());
//				System.out.println("  Estado: " + contrato.getEstadoContratoNombre());
//				System.out.println("  Método Pago: " + contrato.getMetodoPagoNombre());
//
//				System.out.println("  Costo Mensual: " + contrato.getCostoMensual() + "€");
//				System.out.println();
//			}
//		}
//	}
//
//	public void testUpdate() {
//		Long contratoId = 1L;
//		ContratoDTO contrato = contratoService.findById(contratoId);
//
//		if (contrato != null) {
//
//			contrato.setCostoMensual(49.99);
//			contrato.setPlanId(2L);
//
//			boolean actualizado = contratoService.update(contrato);
//
//			if (actualizado) {
//				System.out.println("testUpdate Ok: Contrato actualizado correctamente");
//			} else {
//				System.out.println("testUpdate Fallo: No se pudo actualizar el contrato");
//			}
//		} else {
//			System.out.println("testUpdate Fallo: No se encontró el contrato");
//		}
//	}
//
//	public void testDelete() {
//		Long contratoId = 99L;
//
//		contratoService.delete(contratoId);
//		System.out.println("testDelete ejecutado para ID: " + contratoId);
//
//		ContratoDTO contrato = contratoService.findById(contratoId);
//		if (contrato == null) {
//			System.out.println("testDelete Ok: Contrato eliminado correctamente");
//		} else {
//			System.out.println("testDelete Fallo: El contrato aún existe");
//		}
//	}
//
//	public static void main(String[] args) {
//		ContratoServiceTest test = new ContratoServiceTest();
//
//		// test.testCreate();
//		test.testFindById();
//		// test.testFindByCliente();
//		// test.testFindByNumeroContrato();
//		// test.testFindByEstado();
//		// test.testFindByCriteria();
//		// test.testUpdate();
//
//		// test.testDelete();
//	}
//}