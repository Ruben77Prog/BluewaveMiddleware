package com.ruben.bluewave.service;

import java.util.Date;
import java.util.List;

import com.ruben.bluewave.dao.criteria.ContratoCriteria;
import com.ruben.bluewave.model.ContratoDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.impl.ContratoServiceImpl;

public class ContratoServiceTest {

	private static ContratoService contratoService = new ContratoServiceImpl();

	private static ContratoDTO createTestContrato() {
		long suffix = System.currentTimeMillis() % 100000;
		ContratoDTO contrato = new ContratoDTO();
		contrato.setNumeroContrato("CON-TEST-" + suffix);
		contrato.setFechaInicio(new Date());
		contrato.setFechaFin(new Date(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000));
		contrato.setCostoInstalacion(50.0);
		contrato.setCostoMensual(29.99);
		contrato.setMetodoPagoId(1L);
		contrato.setClienteId(1L);
		contrato.setPlanId(1L);
		contrato.setEstadoContratoId(1L);
		return contrato;
	}

	public static void testCreate() {
		try {
			Long contratoId = contratoService.create(createTestContrato());
			if (contratoId != null) {
				System.out.println("Contrato creado con ID: " + contratoId);
			} else {
				System.out.println("Error al crear el contrato");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testFindById() {
		try {
			Long contratoId = contratoService.create(createTestContrato());
			if (contratoId == null) {
				System.out.println("No se pudo crear el contrato para buscar por ID");
				return;
			}
			ContratoDTO contrato = contratoService.findById(contratoId);
			if (contrato != null) {
				System.out.println("Contrato encontrado por ID:");
				System.out.println("  ID: " + contrato.getId());
				System.out.println("  Número: " + contrato.getNumeroContrato());
				System.out.println("  Cliente: " + contrato.getClienteNombre());
				System.out.println("  Plan: " + contrato.getPlanNombre());
			} else {
				System.out.println("No se encontró el contrato con ID " + contratoId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testFindByCriteria() {
		try {
			ContratoCriteria criteria = new ContratoCriteria();
			Results<ContratoDTO> results = contratoService.findByCriteria(criteria, 0, 10);
			if (results != null) {
				System.out.println("Total contratos: " + results.getTotal());
				List<ContratoDTO> contratos = results.getPage();
				if (contratos != null && !contratos.isEmpty()) {
					for (ContratoDTO c : contratos) {
						System.out.println("  - ID: " + c.getId() + ", Número: " + c.getNumeroContrato() + ", Cliente: "
								+ c.getClienteNombre());
					}
				} else {
					System.out.println("No hay contratos en esta página");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testFindByCliente() {
		try {
			List<ContratoDTO> contratos = contratoService.findByCliente(1L);
			if (contratos != null && !contratos.isEmpty()) {
				System.out.println("Contratos del cliente ID 1: " + contratos.size());
				for (ContratoDTO c : contratos) {
					System.out.println("  - ID: " + c.getId() + ", Número: " + c.getNumeroContrato());
				}
			} else {
				System.out.println("No se encontraron contratos para el cliente ID 1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testFindByNumeroContrato() {
		try {
			List<ContratoDTO> contratos = contratoService.findByNumeroContrato("CON-001");
			if (contratos != null && !contratos.isEmpty()) {
				System.out.println("Contratos con número CON-001: " + contratos.size());
				for (ContratoDTO c : contratos) {
					System.out.println("  - ID: " + c.getId() + ", Cliente: " + c.getClienteNombre());
				}
			} else {
				System.out.println("No se encontraron contratos con número CON-001");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testFindByEstado() {
		try {
			List<ContratoDTO> contratos = contratoService.findByEstado(1L);
			if (contratos != null && !contratos.isEmpty()) {
				System.out.println("Contratos con estado Activo: " + contratos.size());
				for (ContratoDTO c : contratos) {
					System.out.println("  - ID: " + c.getId() + ", Número: " + c.getNumeroContrato());
				}
			} else {
				System.out.println("No se encontraron contratos con estado Activo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() {
		try {
			ContratoDTO contrato = createTestContrato();
			Long contratoId = contratoService.create(contrato);
			if (contratoId == null) {
				System.out.println("No se pudo crear el contrato para actualizar");
				return;
			}
			contrato.setId(contratoId);
			contrato.setCostoMensual(39.99);
			boolean actualizado = contratoService.update(contrato);
			System.out.println("Actualización del contrato: " + (actualizado ? "OK" : "Fallo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testDelete() {
		try {
			ContratoDTO contrato = createTestContrato();
			Long contratoId = contratoService.create(contrato);
			if (contratoId == null) {
				System.out.println("No se pudo crear el contrato para eliminar");
				return;
			}
			contratoService.delete(contratoId);
			System.out.println("Contrato eliminado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("=== TEST CONTRATO SERVICE ===");
		testCreate();
		testFindById();
		testFindByCriteria();
		testFindByCliente();
		testFindByNumeroContrato();
		testFindByEstado();
		testUpdate();
		testDelete();
	}
}