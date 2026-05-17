package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ruben.bluewave.dao.ClienteDAO;
import com.ruben.bluewave.dao.criteria.ClienteCriteria;
import com.ruben.bluewave.model.Cliente;
import com.ruben.bluewave.model.ClienteDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.ClienteService;
import com.ruben.bluewave.service.EncryptionService;
import com.ruben.bluewave.service.MailService;
import com.ruben.bluewave.util.JDBCUtils;

public class ClienteServiceImpl implements ClienteService {
	private Logger logger = LogManager.getLogger(ClienteServiceImpl.class.getName());

	private EncryptionService encryptionService = null;
	private MailService mailService = null;
	private ClienteDAO clienteDAO = null;

	public ClienteServiceImpl() {
		encryptionService = new EncryptionServiceBCCryptImpl();
		mailService = new MailServiceApacheImpl();
		clienteDAO = new ClienteDAO();
	}

	@Override
	public Long create(Cliente cliente) throws Exception {
		
		Connection c = null;
		boolean commit = false;
		
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);

			if (cliente.getEstadoClienteId() == null) {
				cliente.setEstadoClienteId(1L);
			}

			if (cliente.getEmpleadoAsignadoId() == null) {
				cliente.setEmpleadoAsignadoId(1L);
			}

			String passEnc = encryptionService.encrypt(cliente.getContrasena());
			cliente.setContrasena(passEnc);

			Cliente clienteCreado = clienteDAO.create(c, cliente);
			if (clienteCreado == null) {
				logger.error("Error al crear cliente en DAO");
				throw new Exception("Error al crear cliente en DAO");
			}

			try {
				mailService.sendEmail(clienteCreado.getEmail(), "Bienvenido a Bluewave",
						"Hola " + clienteCreado.getNombre());
			} catch (Exception e) {
				logger.error("Error al enviar email de bienvenida a: {}", clienteCreado.getEmail(), e);
			
			}

			commit = true;
			logger.info("Cliente creado exitosamente con id: {}", clienteCreado.getId());
			return clienteCreado.getId();

		} catch (Exception e) {
			logger.error("Error en create de cliente: {}", e.getMessage(), e);
			throw e;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public Cliente login(Cliente cliente) throws Exception {
		

		Connection c = null;
		boolean commit = false;
		
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			
			List<Cliente> clientes = clienteDAO.findByEmail(c, cliente.getEmail());

			if (clientes == null || clientes.isEmpty()) {
				logger.warn("Cliente no encontrado para email: {}", cliente.getEmail());
				return null;
			}

			Cliente clienteEncontrado = clientes.get(0);

			if (encryptionService.checkEncription(cliente.getContrasena(),
					clienteEncontrado.getContrasena())) {
				logger.info("Login exitoso para email: {}", cliente.getEmail());
				commit = true;
				return clienteEncontrado;
			} else {
				logger.warn("Contraseña incorrecta para email: {}", cliente.getEmail());
				return null;
			}

		} catch (Exception e) {
			logger.error("Error en login para email {}: {}", cliente.getEmail(), e.getMessage(), e);
			throw e;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<Cliente> findByEmail(String email) throws Exception {
		

		Connection c = null;
		boolean commit = false;
		
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			
			List<Cliente> result = clienteDAO.findByEmail(c, email);
			commit = true;
			return result;
			
		} catch (Exception e) {
			logger.error("Error buscando cliente por email {}: {}", email, e.getMessage(), e);
			throw e;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public ClienteDTO findById(Long id) throws Exception {
		

		Connection c = null;
		boolean commit = false;
		
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			
			ClienteDTO result = clienteDAO.findById(c, id);
			commit = true;
			return result;
			
		} catch (Exception e) {
			logger.error("Error buscando cliente por id {}: {}", id, e.getMessage(), e);
			throw e;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public Results<ClienteDTO> findByCriteria(ClienteCriteria criteria, int from, int pageSize) throws Exception {
		

		Connection c = null;
		boolean commit = false;
		
		try {
			if (clienteDAO == null) {
				clienteDAO = new ClienteDAO();
			}
			
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			
			Results<ClienteDTO> results = clienteDAO.findByCriteria(c, criteria, from, pageSize);
			commit = true;
			return results;
			
		} catch (Exception e) {
			logger.error("Error en findBy con criteria {}: {}", criteria, e.getMessage(), e);
			throw e;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	

	@Override
	public boolean update(Cliente cliente) throws Exception {
		

		Connection c = null;
		boolean commit = false;
		
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			
			boolean result = clienteDAO.update(c, cliente);
			
			if (result) {
				logger.info("Cliente actualizado exitosamente con id: {}", cliente.getId());
				commit = true;
			} else {
				logger.warn("No se actualizó el cliente con id: {}", cliente.getId());
			}
			
			return result;
			
		} catch (Exception e) {
			logger.error("Error actualizando cliente con id {}: {}", cliente.getId(), e.getMessage(), e);
			throw e;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public boolean updatePassword(Long id, String oldPassword, String newPassword) throws Exception {
		

		Connection c = null;
		boolean commit = false;
		
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			
			ClienteDTO cliente = clienteDAO.findById(c, id);

			if (cliente == null) {
				logger.error("Cliente no encontrado con id: {}", id);
				return false;
			}

			if (!encryptionService.checkEncription(oldPassword, cliente.getPassword())) {
				logger.warn("Contraseña antigua incorrecta para cliente id: {}", id);
				return false;
			}

			if (encryptionService.checkEncription(newPassword, cliente.getPassword())) {
				logger.warn("Nueva contraseña igual a la actual para cliente id: {}", id);
				return false;
			}

			String encryptedNewPassword = encryptionService.encrypt(newPassword);
			boolean result = clienteDAO.updatePassword(c, id, encryptedNewPassword);

			if (result) {
				logger.info("Contraseña actualizada exitosamente para cliente id: {}", id);
				commit = true;
			}
			
			return result;

		} catch (Exception e) {
			logger.error("Error actualizando contraseña para cliente id {}: {}", id, e.getMessage(), e);
			throw e;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public boolean delete(Cliente cliente) throws Exception {
		

		Connection c = null;
		boolean commit = false;
		
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			
			boolean delete = clienteDAO.delete(c, cliente.getId());
			
			if (delete) {
				logger.info("Cliente eliminado exitosamente con id: {}", cliente.getId());
				commit = true;
			} else {
				logger.warn("No se eliminó el cliente con id: {}", cliente.getId());
			}
			
			return delete;
			
		} catch (Exception e) {
			logger.error("Error eliminando cliente con id {}: {}", cliente.getId(), e.getMessage(), e);
			throw e;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}
}