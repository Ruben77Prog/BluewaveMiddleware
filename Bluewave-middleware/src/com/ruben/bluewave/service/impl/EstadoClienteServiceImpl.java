package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ruben.bluewave.dao.EstadoClienteDAO;
import com.ruben.bluewave.dao.criteria.EstadoClienteCriteria;
import com.ruben.bluewave.model.EstadoCliente;
import com.ruben.bluewave.service.EstadoClienteService;
import com.ruben.bluewave.util.JDBCUtils;

public class EstadoClienteServiceImpl implements EstadoClienteService {
	private Logger logger = LogManager.getLogger(EstadoClienteServiceImpl.class.getName());
	private EstadoClienteDAO estadoClienteDAO = null;

	public EstadoClienteServiceImpl() {
		estadoClienteDAO = new EstadoClienteDAO();
	}

	@Override
	public EstadoCliente findById(Long id) {
		if (id == null) {
			return null;
		}
		Connection c = JDBCUtils.getConnection();
		return estadoClienteDAO.findById(c, id);
	}

	@Override
	public List<EstadoCliente> findBy(EstadoClienteCriteria criteria) throws Exception {
		Connection c = null;
		boolean commit = false;
		try {

			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			List<EstadoCliente> resultados = estadoClienteDAO.findBy(c, criteria);
			return resultados;
		} catch (Exception e) {
			logger.error("Buscando {}: {}", criteria, e.getMessage(), e);

			throw e;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<EstadoCliente> findAll() throws Exception {
		Connection c = null;
		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			List<EstadoCliente> resultados = estadoClienteDAO.findAll(c);
			return resultados;
		} catch (Exception e) {

			logger.error("Buscando {}:", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public Long create(EstadoCliente estadoCliente) throws Exception {
		Connection c = null;
		boolean commit = false;
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			EstadoCliente created = estadoClienteDAO.create(c, estadoCliente);
			commit = true;
			if (created == null) {
				return null;
			}
			return created.getId();
		} catch (Exception e) {
			logger.error("Creando {}: {}", estadoCliente, e.getMessage(), e);
			throw e;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public boolean update(EstadoCliente estadoCliente) {
//		Connection c = null;
//		boolean commit = false;
//		try {
//			c = JDBCUtils.getConnection();
//			c.setAutoCommit(false);
//			boolean update = estadoClienteDAO.update(c, estadoCliente);
//			commit = true;
//			return update;
//		} catch(Exception e){
//				throw e;
//		} finally {
//			JDBCUtils.close(c, commit);
//		}
		return false;
	}

	@Override
	public boolean delete(Long id) throws Exception {
		Connection c = null;
		boolean commit = false;
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			boolean deleted = estadoClienteDAO.delete(c, id);
			commit = true;
			return deleted;
		} catch (Exception e) {
			logger.error("Error al eliminar estadoClietne {}: {}", id, e.getMessage(), e);
			throw e;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}
}