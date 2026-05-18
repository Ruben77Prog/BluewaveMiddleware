package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ruben.bluewave.dao.DireccionDAO;
import com.ruben.bluewave.dao.criteria.DireccionCriteria;
import com.ruben.bluewave.model.Direccion;
import com.ruben.bluewave.service.DireccionService;
import com.ruben.bluewave.util.JDBCUtils;

public class DireccionServiceImpl implements DireccionService {

    private static final Logger logger = LogManager.getLogger(DireccionServiceImpl.class);
    private DireccionDAO dao;

    public DireccionServiceImpl() {
        this.dao = new DireccionDAO();
    }

    @Override
    public Direccion findById(Long id) throws Exception {
        if (id == null) {
            logger.warn("Intento de búsqueda de dirección con id nulo");
            return null;
        }
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            Direccion direccion = dao.findById(c, id);
            commit = true;
            return direccion;
        } catch (Exception e) {
            logger.error("Error buscando dirección por id {}: {}", id, e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public List<Direccion> findByCriteria(DireccionCriteria criteria) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<Direccion> lista = dao.findBy(c, criteria);
            commit = true;
            return lista;
        } catch (Exception e) {
            logger.error("Error buscando direcciones por criteria: {}", e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public List<Direccion> findAll() throws Exception {
        DireccionCriteria criteria = new DireccionCriteria();
        return findByCriteria(criteria);
    }

    @Override
    public Direccion create(Direccion direccion) throws Exception {
        if (direccion == null) {
            logger.warn("Intento de crear dirección nula");
            return null;
        }
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            Direccion creada = dao.create(c, direccion);
            if (creada == null || creada.getId() == null) {
                logger.error("Error al crear dirección en DAO");
                throw new Exception("No se pudo crear la dirección");
            }
            commit = true;
            logger.info("Dirección creada con id: {}", creada.getId());
            return creada;
        } catch (Exception e) {
            logger.error("Error creando dirección: {}", e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean update(Direccion direccion) throws Exception {
        if (direccion == null || direccion.getId() == null) {
            logger.warn("Intento de actualizar dirección con id nulo o nula");
            return false;
        }
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            boolean actualizado = dao.update(c, direccion);
            if (actualizado) {
                commit = true;
                logger.info("Dirección actualizada con id: {}", direccion.getId());
            }
            return actualizado;
        } catch (Exception e) {
            logger.error("Error actualizando dirección id {}: {}", direccion.getId(), e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        if (id == null) {
            logger.warn("Intento de eliminar dirección con id nulo");
            return false;
        }
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            boolean eliminado = dao.delete(c, id);
            if (eliminado) {
                commit = true;
                logger.info("Dirección eliminada con id: {}", id);
            }
            return eliminado;
        } catch (Exception e) {
            logger.error("Error eliminando dirección id {}: {}", id, e.getMessage(), e);
            throw e;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }
}