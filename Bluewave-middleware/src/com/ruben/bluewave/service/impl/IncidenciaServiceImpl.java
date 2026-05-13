package com.ruben.bluewave.service.impl;

import java.util.List;

import com.ruben.bluewave.dao.IncidenciaDAO;
import com.ruben.bluewave.dao.criteria.IncidenciaCriteria;
import com.ruben.bluewave.model.Incidencia;
import com.ruben.bluewave.model.IncidenciaDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.IncidenciaService;

public class IncidenciaServiceImpl implements IncidenciaService {

	private IncidenciaDAO dao = null;

	public IncidenciaServiceImpl() {
		dao = new IncidenciaDAO();
	}

	@Override
	public IncidenciaDTO findById(Long id) {
		if (id == null) {
			return null;
		}
		return dao.findById(id);
	}

	@Override
	public Results<IncidenciaDTO> findByCriteria(IncidenciaCriteria criteria, int from, int pageSize) {
		return dao.findByCriteria(criteria, pageSize, pageSize);
	}

	
	@Override
	public List<IncidenciaDTO> findAll() {
		return dao.findAll();
	}

	@Override
	public Long create(Incidencia incidencia) {
		if (incidencia == null) {
			return null;
		}
		Incidencia created = dao.create(incidencia);
		return created != null ? created.getId() : null;
	}

	@Override
	public boolean update(Incidencia incidencia) {
		if (incidencia == null || incidencia.getId() == null) {
			return false;
		}
		return dao.update(incidencia);
	}

	@Override
	public boolean delete(Long id) {
		if (id == null) {
			return false;
		}
		return dao.delete(id);
	}

}
