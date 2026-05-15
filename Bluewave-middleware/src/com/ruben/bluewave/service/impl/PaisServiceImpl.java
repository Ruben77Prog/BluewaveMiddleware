package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ruben.bluewave.dao.PaisDAO;
import com.ruben.bluewave.model.Pais;
import com.ruben.bluewave.service.PaisService;
import com.ruben.bluewave.util.JDBCUtils;

public class PaisServiceImpl implements PaisService {

	private static Logger logger = LogManager.getLogger(PaisServiceImpl.class);

	private PaisDAO paisDAO;

	public PaisServiceImpl() {
		this.paisDAO = new PaisDAO();
	}

	@Override
	public List<Pais> findAll() throws Exception {

		Connection c = null;
		boolean commit = false;

		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);

			List<Pais> result = paisDAO.findAll(c);

			commit = true;
			return result;

		} catch (Exception e) {
			logger.error("Error buscando países: {}", e.getMessage(), e);
			throw e;

		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public Pais findById(Long id) throws Exception {

		Connection c = null;
		boolean commit = false;

		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);

			Pais result = paisDAO.findById(c, id);

			commit = true;
			return result;

		} catch (Exception e) {
			logger.error("Error buscando país con id {}: {}", id, e.getMessage(), e);
			throw e;

		} finally {
			JDBCUtils.close(c, commit);
		}
	}
}