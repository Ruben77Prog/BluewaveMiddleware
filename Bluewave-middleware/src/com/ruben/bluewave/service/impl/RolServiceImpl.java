package com.ruben.bluewave.service.impl;

import java.util.List;

import com.ruben.bluewave.dao.RolDAO;
import com.ruben.bluewave.dao.criteria.RolCriteria;
import com.ruben.bluewave.model.Rol;
import com.ruben.bluewave.service.RolService;

public class RolServiceImpl implements RolService {

	private RolDAO rolDAO = null;

	public RolServiceImpl() {
		rolDAO = new RolDAO();
	}

	@Override
	public Rol findById(Long id) {
		if (id == null) {
			return null;
		}
		return rolDAO.findById(id);
	}

	@Override
	public List<Rol> findBy(RolCriteria criteria) {
		try {
			if (rolDAO == null) {
				
				rolDAO = new RolDAO();
			}
			List<Rol> resultados = rolDAO.findBy(criteria);
			return resultados;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Rol> findAll() {
		return null;

	}

	@Override
	public Long create(Rol rol) {
		if (rol == null) {
			return null;
		}
		Rol rolCreado = rolDAO.create(rol);
		if (rolCreado == null) {
			return null;
		}
		return rolCreado.getId();
	}

	@Override
	public boolean update(Rol rol) {
		return false;
		
	}

	@Override
	public boolean delete(Long id) {
		if (id == null) {
			return false;
		}
		return rolDAO.delete(id);
	}
}