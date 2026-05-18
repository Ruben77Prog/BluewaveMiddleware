package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ruben.bluewave.dao.RolDAO;
import com.ruben.bluewave.dao.criteria.RolCriteria;
import com.ruben.bluewave.model.Rol;
import com.ruben.bluewave.service.RolService;
import com.ruben.bluewave.util.JDBCUtils;

public class RolServiceImpl implements RolService {

    private static final Logger logger = LogManager.getLogger(RolServiceImpl.class);
    private RolDAO rolDAO;

    public RolServiceImpl() {
        this.rolDAO = new RolDAO();
    }

    @Override
    public Rol findById(Long id) {
        if (id == null) {
            logger.warn("Intento de búsqueda de rol con id nulo");
            return null;
        }
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            Rol rol = rolDAO.findById(c, id);
            commit = true;
            return rol;
        } catch (Exception e) {
            logger.error("Error buscando rol por id {}: {}", id, e.getMessage(), e);
            return null;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public List<Rol> findBy(RolCriteria criteria) {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<Rol> resultados = rolDAO.findBy(c, criteria);
            commit = true;
            return resultados;
        } catch (Exception e) {
            logger.error("Error en búsqueda de roles con criteria {}: {}", criteria, e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public List<Rol> findAll() {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            RolCriteria criteria = new RolCriteria();
            List<Rol> resultados = rolDAO.findBy(c, criteria);
            commit = true;
            return resultados;
        } catch (Exception e) {
            logger.error("Error listando todos los roles: {}", e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public Long create(Rol rol) {
        if (rol == null) {
            logger.warn("Intento de crear rol nulo");
            return null;
        }
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            Rol creado = rolDAO.create(c, rol);
            if (creado == null || creado.getId() == null) {
                logger.error("Error al crear rol en DAO");
                return null;
            }
            commit = true;
            logger.info("Rol creado con id: {}", creado.getId());
            return creado.getId();
        } catch (Exception e) {
            logger.error("Error creando rol: {}", e.getMessage(), e);
            return null;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean update(Rol rol) {
        if (rol == null || rol.getId() == null) {
            logger.warn("Intento de actualizar rol con id nulo o nulo");
            return false;
        }
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            Rol actualizado = rolDAO.update(c, rol);
            if (actualizado != null) {
                commit = true;
                logger.info("Rol actualizado con id: {}", rol.getId());
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Error actualizando rol id {}: {}", rol.getId(), e.getMessage(), e);
            return false;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }
    @Override
    public boolean delete(Long id) {
        if (id == null) {
            logger.warn("Intento de eliminar rol con id nulo");
            return false;
        }
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            boolean eliminado = rolDAO.delete(c, id);
            if (eliminado) {
                commit = true;
                logger.info("Rol eliminado con id: {}", id);
            }
            return eliminado;
        } catch (Exception e) {
            logger.error("Error eliminando rol id {}: {}", id, e.getMessage(), e);
            return false;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }
}