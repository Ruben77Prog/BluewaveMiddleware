package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.service.impl.CocheServiceImpl;
import com.ruben.bluewave.dao.CiudadDAO;
import com.ruben.bluewave.dao.ProvinciaDAO;
import com.ruben.bluewave.dao.criteria.CiudadCriteria;
import com.ruben.bluewave.dao.criteria.ProvinciaCriteria;
import com.ruben.bluewave.model.Ciudad;
import com.ruben.bluewave.model.Provincia;
import com.ruben.bluewave.service.CiudadService;
import com.ruben.bluewave.util.JDBCUtils;

public class CiudadServiceImpl implements CiudadService {
	private Logger logger = LogManager.getLogger(CiudadServiceImpl.class.getName());
	private CiudadDAO ciudadDAO;
	private ProvinciaDAO provinciaDAO;

	public CiudadServiceImpl() {
		this.ciudadDAO = new CiudadDAO();
		this.provinciaDAO = new ProvinciaDAO();
	}

	@Override
	public List<Ciudad> findByProvinciaId(Long provinciaId) throws Exception {
		Connection c = null;
		boolean commit = false;
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			CiudadCriteria criteria = new CiudadCriteria();
			criteria.setProvinciaId(provinciaId);
			commit = true;
			return ciudadDAO.findBy(c, criteria);

		} catch (Exception e) {
			logger.error("Buscando {}: {}", provinciaId, e.getMessage(), e);
			throw e;

		} finally {
			JDBCUtils.getConnection();
		}

	}

	@Override
	public List<Ciudad> findByPaisId(Long paisId) {
		if (paisId == null) {
			return new ArrayList<>();
		}
		ProvinciaCriteria provinciaCriteria = new ProvinciaCriteria();
		provinciaCriteria.setPaisId(paisId);
		List<Provincia> provincias = provinciaDAO.findBy(provinciaCriteria);
		List<Ciudad> todas = new ArrayList<>();
		for (Provincia p : provincias) {
			CiudadCriteria criteria = new CiudadCriteria();
			criteria.setProvinciaId(p.getId());
			todas.addAll(ciudadDAO.findBy(criteria));
		}
		return todas;
	}

	@Override
	public Ciudad findById(Long id) {
		return ciudadDAO.findById(id);
	}
}