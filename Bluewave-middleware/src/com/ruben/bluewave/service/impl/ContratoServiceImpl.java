package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ruben.bluewave.dao.ContratoDAO;
import com.ruben.bluewave.dao.criteria.ContratoCriteria;
import com.ruben.bluewave.model.ContratoDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.ContratoService;
import com.ruben.bluewave.service.MailService;
import com.ruben.bluewave.util.JDBCUtils;

public class ContratoServiceImpl implements ContratoService {

	private Logger logger = LogManager.getLogger(ContratoServiceImpl.class.getName());

	private ContratoDAO contratoDAO = null;
	private MailService mailService = null;

	public ContratoServiceImpl() {
		contratoDAO = new ContratoDAO();
		mailService = new MailServiceApacheImpl();
	}

	@Override
	public Long create(ContratoDTO contrato) throws Exception {

		Connection c = null;
		boolean commit = false;

		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);

			if (contratoDAO == null) {
				contratoDAO = new ContratoDAO();
			}

			contrato = contratoDAO.create(c, contrato);

			if (contrato != null && contrato.getId() != null) {
				commit = true;
				return contrato.getId();
			}

			return null;

		} catch (Exception e) {
			logger.error("Error creando contrato: {}", e.getMessage(), e);
			throw e;

		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public ContratoDTO findById(Long id) throws Exception {

		Connection c = null;
		boolean commit = false;

		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);

			if (contratoDAO == null) {
				contratoDAO = new ContratoDAO();
			}

			ContratoDTO result = contratoDAO.findById(c, id);

			commit = true;
			return result;

		} catch (Exception e) {
			logger.error("Error buscando contrato con id {}: {}", id, e.getMessage(), e);
			throw e;

		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public Results<ContratoDTO> findByCriteria(ContratoCriteria criteria, int from, int pageSize) throws Exception {

	    Connection c = null;
	    boolean commit = false;

	    try {
	        c = JDBCUtils.getConnection();
	        c.setAutoCommit(false);

	        if (contratoDAO == null) {
	            contratoDAO = new ContratoDAO();
	        }

	        Results<ContratoDTO> resultados =
	                contratoDAO.findByCriteria(c, criteria, from, pageSize);

	        commit = true;
	        return resultados;

	    } catch (Exception e) {
	        logger.error("Error buscando contratos: {}", e.getMessage(), e);
	        throw e;

	    } finally {
	        JDBCUtils.close(c, commit);
	    }
	}

	@Override
	public List<ContratoDTO> findByCliente(Long clienteId) throws Exception {
	    Connection c = null;
	    boolean commit = false;
	    
	    try {
	        c = JDBCUtils.getConnection();
	        c.setAutoCommit(false);

	        

	        ContratoCriteria criteria = new ContratoCriteria();
	        criteria.setClienteId(clienteId);

	       
	        Results<ContratoDTO> resultados = contratoDAO.findByCriteria(c, criteria, 0, Integer.MAX_VALUE);
	        commit = true;
	        return resultados.getPage();

	    } catch (Exception e) {
	        logger.error("Error buscando contratos por cliente {}: {}", clienteId, e.getMessage(), e);
	        throw e;
	    } finally {
	        JDBCUtils.close(c, commit);
	    }
	}
	@Override
	public List<ContratoDTO> findByNumeroContrato(String numeroContrato) throws Exception {
	    Connection c = null;
	    boolean commit = false;
	    try {
	        c = JDBCUtils.getConnection();
	        c.setAutoCommit(false);


	        ContratoCriteria criteria = new ContratoCriteria();
	        criteria.setNumeroContrato(numeroContrato);

	        Results<ContratoDTO> resultados = contratoDAO.findByCriteria(c, criteria, 0, Integer.MAX_VALUE);
	        commit = true;
	        return resultados.getPage();

	    } catch (Exception e) {
	        logger.error("Error buscando contrato {}: {}", numeroContrato, e.getMessage(), e);
	        throw e;
	    } finally {
	        JDBCUtils.close(c, commit);
	    }
	}

	@Override
	public List<ContratoDTO> findByEstado(Long estadoContratoId) throws Exception {

		Connection c = null;
		boolean commit = false;

		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);


			ContratoCriteria criteria = new ContratoCriteria();
			criteria.setEstadoContratoId(estadoContratoId);

			Results<ContratoDTO> result = contratoDAO.findByCriteria(c, criteria, 0, Integer.MAX_VALUE);

			commit = true;
			return result.getPage();

		} catch (Exception e) {
			logger.error("Error buscando contratos por estado {}: {}", estadoContratoId, e.getMessage(), e);
			throw e;

		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public boolean update(ContratoDTO contrato) throws Exception {

		Connection c = null;
		boolean commit = false;

		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);

			if (contrato == null || contrato.getId() == null) {
				logger.error("Contrato inválido");
				return false;
			}

			if (contratoDAO == null) {
				contratoDAO = new ContratoDAO();
			}

			boolean actualizado = contratoDAO.update(c, contrato);

			if (actualizado) {
				logger.info("Contrato actualizado: {}", contrato.getNumeroContrato());
				commit = true;
			}

			return actualizado;

		} catch (Exception e) {
			logger.error("Error actualizando contrato {}: {}", contrato.getId(), e.getMessage(), e);
			throw e;

		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public void delete(Long id) throws Exception {

		Connection c = null;
		boolean commit = false;

		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);

			if (id == null) {
				logger.error("id es null");
				return;
			}

			if (contratoDAO == null) {
				contratoDAO = new ContratoDAO();
			}

			boolean eliminado = contratoDAO.delete(c, id);

			if (eliminado) {
				logger.info("Contrato eliminado con id: {}", id);
				commit = true;
			} else {
				logger.warn("No se pudo eliminar el contrato con id: {}", id);
			}

		} catch (Exception e) {
			logger.error("Error eliminando contrato {}: {}", id, e.getMessage(), e);
			throw e;

		} finally {
			JDBCUtils.close(c, commit);
		}
	}
}