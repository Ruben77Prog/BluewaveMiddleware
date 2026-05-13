package com.ruben.bluewave.service.impl;

import java.util.List;

import com.ruben.bluewave.dao.MensajeDAO;
import com.ruben.bluewave.dao.criteria.MensajeCriteria;
import com.ruben.bluewave.model.Mensaje;
import com.ruben.bluewave.service.MensajeService;

public class MensajeServiceImpl implements MensajeService {

	private MensajeDAO dao = null;

	public MensajeServiceImpl() {
		dao = new MensajeDAO();
	}

	@Override
	public Mensaje findById(Long id) {
		if (id == null) {
			return null;
		}
		return dao.findById(id);
	}

	@Override
	public List<Mensaje> findBy(MensajeCriteria criteria) {
		try {
			if (dao == null) {
				
				dao = new MensajeDAO();
			}
			List<Mensaje> resultados = dao.findBy(criteria);
			return resultados;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Mensaje> findAll() {
		try {
			if (dao == null) {
				System.err.println("Error: MensajeDAO no está inicializado");
				dao = new MensajeDAO();
			}
			List<Mensaje> resultados = dao.findAll();
			return resultados;
		} catch (Exception e) {
		
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Long create(Mensaje mensaje) {
		if (mensaje == null) {
			return null;
		}
		Mensaje created = dao.create(mensaje);
		if (created == null) {
			return null;
		}
		return created.getId();
	}

	@Override
	public boolean update(Mensaje mensaje) {
		return false;
		
	}

	@Override
	public boolean delete(Long id) {
		if (id == null) {
			return false;
		}
		return dao.delete(id);
	}
}