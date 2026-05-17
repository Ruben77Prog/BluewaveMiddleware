package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ruben.bluewave.dao.IncidenciaDAO;
import com.ruben.bluewave.dao.criteria.IncidenciaCriteria;
import com.ruben.bluewave.model.Incidencia;
import com.ruben.bluewave.model.IncidenciaDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.IncidenciaService;
import com.ruben.bluewave.util.JDBCUtils;

public class IncidenciaServiceImpl implements IncidenciaService {

    private static final Logger logger = LogManager.getLogger(IncidenciaServiceImpl.class);

    private IncidenciaDAO incidenciaDAO;

    public IncidenciaServiceImpl() {
        this.incidenciaDAO = new IncidenciaDAO();
    }

    @Override
    public IncidenciaDTO findById(Long id) {
        if (id == null) {
            logger.warn("Intento de búsqueda de incidencia con id nulo");
            return null;
        }

        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            IncidenciaDTO result = incidenciaDAO.findById(c, id);
            commit = true;
            return result;
        } catch (Exception e) {
            logger.error("Error buscando incidencia por id {}: {}", id, e.getMessage(), e);
            return null;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public Results<IncidenciaDTO> findByCriteria(IncidenciaCriteria criteria, int from, int pageSize) {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            Results<IncidenciaDTO> results = incidenciaDAO.findByCriteria(c, criteria, from, pageSize);
            commit = true;
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            Results<IncidenciaDTO> empty = new Results<>();
            empty.setTotal(0);
            empty.setPage(new ArrayList<>());
            return empty;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public List<IncidenciaDTO> findAll() {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<IncidenciaDTO> result = incidenciaDAO.findAll(c);
            commit = true;
            return result;
        } catch (Exception e) {
            logger.error("Error listando todas las incidencias: {}", e.getMessage(), e);
            return new ArrayList<>();  
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public Long create(Incidencia incidencia) {
        if (incidencia == null) {
            logger.warn("Intento de crear incidencia nula");
            return null;
        }

        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            
            if (incidencia.getEstadoIncidenciaId() == null) {
                incidencia.setEstadoIncidenciaId(1L);
            }

            Incidencia creada = incidenciaDAO.create(c, incidencia);
            if (creada == null || creada.getId() == null) {
                logger.error("Error al crear incidencia en DAO");
                throw new Exception("Error al crear incidencia en DAO");
            }

            commit = true;
            logger.info("Incidencia creada exitosamente con id: {}", creada.getId());
            return creada.getId();

        } catch (Exception e) {
            logger.error("Error creando incidencia: {}", e.getMessage(), e);
            return null;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean update(Incidencia incidencia) {
        if (incidencia == null || incidencia.getId() == null) {
            logger.warn("Intento de actualizar incidencia con id nulo o nula");
            return false;
        }

        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            boolean result = incidenciaDAO.update(c, incidencia);
            if (result) {
                commit = true;
                logger.info("Incidencia actualizada exitosamente con id: {}", incidencia.getId());
            } else {
                logger.warn("No se actualizó la incidencia con id: {}", incidencia.getId());
            }
            return result;

        } catch (Exception e) {
            logger.error("Error actualizando incidencia con id {}: {}", incidencia.getId(), e.getMessage(), e);
            return false;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            logger.warn("Intento de eliminar incidencia con id nulo");
            return false;
        }

        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            boolean result = incidenciaDAO.delete(c, id);
            if (result) {
                commit = true;
                logger.info("Incidencia eliminada exitosamente con id: {}", id);
            } else {
                logger.warn("No se eliminó la incidencia con id: {}", id);
            }
            return result;

        } catch (Exception e) {
            logger.error("Error eliminando incidencia con id {}: {}", id, e.getMessage(), e);
            return false;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }
}