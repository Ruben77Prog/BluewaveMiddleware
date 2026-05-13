package com.ruben.bluewave.service.impl;

import java.util.List;

import com.ruben.bluewave.dao.EmpleadoDAO;
import com.ruben.bluewave.dao.criteria.EmpleadoCriteria;
import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.EmpleadoService;
import com.ruben.bluewave.service.EncryptionService;

public class EmpleadoServiceImpl implements EmpleadoService {

	private EncryptionService encryptionService = null;
	private EmpleadoDAO empleadoDAO = null;

	public EmpleadoServiceImpl() {
		encryptionService = new EncryptionServiceBCCryptImpl();
		empleadoDAO = new EmpleadoDAO();
	}

	@Override
	public Long create(EmpleadoDTO empleado) {
		if (empleado == null) {
			return null;
		}

		
		empleado.setPassword(
				encryptionService.encrypt(empleado.getPassword())
		);

		EmpleadoDTO creado = empleadoDAO.create(empleado);

		return (creado != null) ? creado.getId() : null;
	}

	@Override
	public EmpleadoDTO findById(Long id) {
		if (id == null) {
			return null;
		}
		return empleadoDAO.findById(id);
	}

	
	public List<EmpleadoDTO> findByEmail(String email) {
		return empleadoDAO.findByEmail(email);
	}

	public List<EmpleadoDTO> findByDni(String dni) {
		return empleadoDAO.findByDni(dni);
	}

	@Override
	public List<EmpleadoDTO> findAll() {
		return empleadoDAO.findAll();
	}


	public List<EmpleadoDTO> findAllActivos() {
		return empleadoDAO.findAllActivos();
	}

	@Override
	public List<EmpleadoDTO> findByCriteria(EmpleadoCriteria criteria) {
		return empleadoDAO.findBy(criteria);
	}

	@Override
	public boolean update(EmpleadoDTO empleado) {
		if (empleado == null || empleado.getId() == null) {
			return false;
		}

		return empleadoDAO.update(empleado);
	}

	@Override
	public boolean delete(Long id) {
		if (id == null) {
			return false;
		}

		return empleadoDAO.delete(id);
	}

	@Override
	public EmpleadoDTO login(String correo, String contrasena) {
		List<EmpleadoDTO> empleados = empleadoDAO.findByEmail(correo);

		if (empleados == null || empleados.isEmpty()) {
			return null;
		}

		EmpleadoDTO empleado = empleados.get(0);

		if (encryptionService.checkEncription(contrasena, empleado.getPassword())) {
			return empleado;
		}

		return null;
	}

	@Override
	public boolean activar(Long empleadoId) {
		EmpleadoDTO empleado = findById(empleadoId);

		if (empleado == null) {
			return false;
		}

		empleado.setActivo(true);
		return update(empleado);
	}

	@Override
	public boolean desactivar(Long empleadoId) {
		EmpleadoDTO empleado = findById(empleadoId);

		if (empleado == null) {
			return false;
		}

		empleado.setActivo(false);
		return update(empleado);
	}

	@Override
	public boolean cambiarRol(Long empleadoId, Long nuevoRolId) {
		EmpleadoDTO empleado = findById(empleadoId);

		if (empleado == null) {
			return false;
		}

		empleado.setRolId(nuevoRolId);
		return update(empleado);
	}

	

	public Results<EmpleadoDTO> findByCriteria(EmpleadoCriteria criteria, int from, int pageSize) {
		Results<EmpleadoDTO> results = new Results<>();

		try {
		
			List<EmpleadoDTO> list = empleadoDAO.findBy(criteria);

			results.setPage(list);
			results.setTotal(list != null ? list.size() : 0);

			return results;

		} catch (Exception e) {
			e.printStackTrace();
			results.setPage(new java.util.ArrayList<>());
			results.setTotal(0);
			return results;
		}
	}

	@Override
	public List<EmpleadoDTO> findByCorreo(String correo) {
		
		return null;
	}

	@Override
	public List<EmpleadoDTO> findByRol(Long rolId) {
		
		return null;
	}

	@Override
	public List<EmpleadoDTO> findActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Results<EmpleadoDTO> findBy(EmpleadoCriteria criteria, int from, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
}