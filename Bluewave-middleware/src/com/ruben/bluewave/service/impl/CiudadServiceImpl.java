package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

			List<Ciudad> result = ciudadDAO.findBy(c, criteria);

			commit = true;
			return result;

		} catch (Exception e) {
			logger.error("Buscando {}: {}", provinciaId, e.getMessage(), e);
			throw e;

		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<Ciudad> findByPaisId(Long paisId) throws Exception {

		Connection c = null;
		boolean commit = false;

		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);

			ProvinciaCriteria provinciaCriteria = new ProvinciaCriteria();
			provinciaCriteria.setPaisId(paisId);

			List<Provincia> provincias = provinciaDAO.findByCriteria(c, provinciaCriteria);

			List<Ciudad> todas = new ArrayList<>();

			for (Provincia p : provincias) {
				CiudadCriteria criteria = new CiudadCriteria();
				criteria.setProvinciaId(p.getId());

				todas.addAll(ciudadDAO.findBy(c, criteria));
			}

			commit = true;
			return todas;

		} catch (Exception e) {
			logger.error("Error buscando ciudades por país {}: {}", paisId, e.getMessage(), e);
			throw e;

		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public Ciudad findById(Long id) throws Exception {

		Connection c = null;
		boolean commit = false;

		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);

			Ciudad result = ciudadDAO.findById(c, id);

			commit = true;
			return result;

		} catch (Exception e) {
			logger.error("Error buscando ciudad con id {}: {}", id, e.getMessage(), e);
			throw e;

		} finally {
			JDBCUtils.close(c, commit);
		}
	}
}