package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ruben.bluewave.dao.EmpleadoDAO;
import com.ruben.bluewave.dao.criteria.EmpleadoCriteria;
import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.EmpleadoService;
import com.ruben.bluewave.service.EncryptionService;
import com.ruben.bluewave.util.JDBCUtils;

public class EmpleadoServiceImpl implements EmpleadoService {

    private static final Logger logger = LogManager.getLogger(EmpleadoServiceImpl.class);

    private EncryptionService encryptionService = null;
    private EmpleadoDAO empleadoDAO = null;

    public EmpleadoServiceImpl() {
        encryptionService = new EncryptionServiceBCCryptImpl();
        empleadoDAO = new EmpleadoDAO();
    }

    @Override
    public Long create(EmpleadoDTO empleado) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            empleado.setPassword(encryptionService.encrypt(empleado.getPassword()));

            EmpleadoDTO creado = empleadoDAO.create(c, empleado);
            Long id = (creado != null) ? creado.getId() : null;
            if (id != null) {
                commit = true;
                logger.info("Empleado creado con id: {}", id);
            }
            return id;
        } catch (Exception e) {
            logger.error("Error creando empleado: {}", e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public EmpleadoDTO findById(Long id) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            EmpleadoDTO empleado = empleadoDAO.findById(c, id);
            commit = true;
            return empleado;
        } catch (Exception e) {
            logger.error("Error buscando empleado id {}: {}", id, e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public List<EmpleadoDTO> findByEmail(String email) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<EmpleadoDTO> result = empleadoDAO.findByEmail(c, email);
            commit = true;
            return result;
        } catch (Exception e) {
            logger.error("Error buscando empleados por email {}: {}", email, e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

  
    @Override
    public List<EmpleadoDTO> findAll() throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<EmpleadoDTO> result = empleadoDAO.findAll(c);
            commit = true;
            return result;
        } catch (Exception e) {
            logger.error("Error listando todos los empleados: {}", e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

 
   

    @Override
    public boolean update(EmpleadoDTO empleado) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (empleado == null || empleado.getId() == null) {
                logger.error("Empleado inválido para actualizar");
                return false;
            }
            boolean actualizado = empleadoDAO.update(c, empleado);
            if (actualizado) {
                commit = true;
                logger.info("Empleado actualizado id: {}", empleado.getId());
            }
            return actualizado;
        } catch (Exception e) {
            logger.error("Error actualizando empleado id {}: {}", empleado.getId(), e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            if (id == null) {
                logger.error("Id nulo para eliminar empleado");
                return false;
            }
            boolean eliminado = empleadoDAO.delete(c, id);
            if (eliminado) {
                commit = true;
                logger.info("Empleado eliminado id: {}", id);
            }
            return eliminado;
        } catch (Exception e) {
            logger.error("Error eliminando empleado id {}: {}", id, e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public EmpleadoDTO login(String correo, String contrasena) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<EmpleadoDTO> empleados = empleadoDAO.findByEmail(c, correo);
            if (empleados == null || empleados.isEmpty()) {
                return null;
            }
            EmpleadoDTO empleado = empleados.get(0);
            if (encryptionService.checkEncription(contrasena, empleado.getPassword())) {
                commit = true;
                return empleado;
            }
            return null;
        } catch (Exception e) {
            logger.error("Error en login para {}: {}", correo, e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean activar(Long empleadoId) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            EmpleadoDTO empleado = empleadoDAO.findById(c, empleadoId);
            if (empleado == null) {
                return false;
            }
            empleado.setActivo(true);
            boolean actualizado = empleadoDAO.update(c, empleado);
            if (actualizado) {
                commit = true;
                logger.info("Empleado activado id: {}", empleadoId);
            }
            return actualizado;
        } catch (Exception e) {
            logger.error("Error activando empleado {}: {}", empleadoId, e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean desactivar(Long empleadoId) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            EmpleadoDTO empleado = empleadoDAO.findById(c, empleadoId);
            if (empleado == null) {
                return false;
            }
            empleado.setActivo(false);
            boolean actualizado = empleadoDAO.update(c, empleado);
            if (actualizado) {
                commit = true;
                logger.info("Empleado desactivado id: {}", empleadoId);
            }
            return actualizado;
        } catch (Exception e) {
            logger.error("Error desactivando empleado {}: {}", empleadoId, e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean cambiarRol(Long empleadoId, Long nuevoRolId) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            EmpleadoDTO empleado = empleadoDAO.findById(c, empleadoId);
            if (empleado == null) {
                return false;
            }
            empleado.setRolId(nuevoRolId);
            boolean actualizado = empleadoDAO.update(c, empleado);
            if (actualizado) {
                commit = true;
                logger.info("Rol cambiado a {} para empleado {}", nuevoRolId, empleadoId);
            }
            return actualizado;
        } catch (Exception e) {
            logger.error("Error cambiando rol del empleado {}: {}", empleadoId, e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public Results<EmpleadoDTO> findByCriteria(EmpleadoCriteria criteria, int from, int pageSize) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            Results<EmpleadoDTO> results = empleadoDAO.findByCriteria(c, criteria, from, pageSize);
            commit = true;
            return results;
        } catch (Exception e) {
            logger.error("Error en búsqueda paginada de empleados: {}", e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

   
   
    @Override
    public List<EmpleadoDTO> findByRol(Long rolId) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            
            EmpleadoCriteria criteria = new EmpleadoCriteria();
            criteria.setRolId(rolId);
            
            Results<EmpleadoDTO> results = empleadoDAO.findByCriteria(c, criteria, 0, Integer.MAX_VALUE);
            commit = true;
            return results.getPage(); 
        } catch (Exception e) {
            logger.error("Error buscando empleados por rol {}: {}", rolId, e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }
    @Override
    public List<EmpleadoDTO> findActivos() throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<EmpleadoDTO> result = empleadoDAO.findAllActivos(c);
            commit = true;
            return result;
        } catch (Exception e) {
            logger.error("Error buscando empleados activos: {}", e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }
	
	
}