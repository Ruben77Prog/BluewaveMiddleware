package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ruben.bluewave.dao.MensajeDAO;
import com.ruben.bluewave.dao.criteria.MensajeCriteria;
import com.ruben.bluewave.model.Mensaje;
import com.ruben.bluewave.service.MensajeService;
import com.ruben.bluewave.util.JDBCUtils;

public class MensajeServiceImpl implements MensajeService {

    private static final Logger logger = LogManager.getLogger(MensajeServiceImpl.class);
    private MensajeDAO dao;

    public MensajeServiceImpl() {
        this.dao = new MensajeDAO();
    }

    @Override
    public Mensaje findById(Long id) {
        if (id == null) {
            logger.warn("Intento de búsqueda de mensaje con id nulo");
            return null;
        }
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            Mensaje mensaje = dao.findById(c, id);
            commit = true;
            return mensaje;
        } catch (Exception e) {
            logger.error("Error buscando mensaje por id {}: {}", id, e.getMessage(), e);
            return null;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public List<Mensaje> findBy(MensajeCriteria criteria) {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<Mensaje> resultados = dao.findBy(c, criteria);
            commit = true;
            return resultados;
        } catch (Exception e) {
            logger.error("Error en búsqueda de mensajes con criteria {}: {}", criteria, e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public List<Mensaje> findAll() {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<Mensaje> resultados = dao.findAll(c);
            commit = true;
            return resultados;
        } catch (Exception e) {
            logger.error("Error listando todos los mensajes: {}", e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public Long create(Mensaje mensaje) {
        if (mensaje == null) {
            logger.warn("Intento de crear mensaje nulo");
            return null;
        }
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            Mensaje creado = dao.create(c, mensaje);
            if (creado == null || creado.getId() == null) {
                logger.error("Error al crear mensaje en DAO");
                return null;
            }
            commit = true;
            logger.info("Mensaje creado con id: {}", creado.getId());
            return creado.getId();
        } catch (Exception e) {
            logger.error("Error creando mensaje: {}", e.getMessage(), e);
            return null;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean update(Mensaje mensaje) {
        if (mensaje == null || mensaje.getId() == null) {
            logger.warn("Intento de actualizar mensaje con id nulo o nulo");
            return false;
        }
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            Mensaje actualizado = dao.update(c, mensaje); 
            if (actualizado != null) {
                commit = true;
                logger.info("Mensaje actualizado con id: {}", mensaje.getId());
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Error actualizando mensaje id {}: {}", mensaje.getId(), e.getMessage(), e);
            return false;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            logger.warn("Intento de eliminar mensaje con id nulo");
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
                logger.info("Mensaje eliminado con id: {}", id);
            }
            return eliminado;
        } catch (Exception e) {
            logger.error("Error eliminando mensaje id {}: {}", id, e.getMessage(), e);
            return false;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }
}