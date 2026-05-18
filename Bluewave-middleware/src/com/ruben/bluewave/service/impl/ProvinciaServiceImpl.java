package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ruben.bluewave.dao.ProvinciaDAO;
import com.ruben.bluewave.dao.criteria.ProvinciaCriteria;
import com.ruben.bluewave.model.Provincia;
import com.ruben.bluewave.service.ProvinciaService;
import com.ruben.bluewave.util.JDBCUtils;

public class ProvinciaServiceImpl implements ProvinciaService {

    private static final Logger logger = LogManager.getLogger(ProvinciaServiceImpl.class);
    private ProvinciaDAO provinciaDAO;

    public ProvinciaServiceImpl() {
        this.provinciaDAO = new ProvinciaDAO();
    }

    @Override
    public Provincia findById(Long id) {
        if (id == null) {
            logger.warn("Intento de búsqueda de provincia con id nulo");
            return null;
        }
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            Provincia provincia = provinciaDAO.findById(c, id);
            commit = true;
            return provincia;
        } catch (Exception e) {
            logger.error("Error buscando provincia por id {}: {}", id, e.getMessage(), e);
            return null;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public List<Provincia> findAll() {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            ProvinciaCriteria criteria = new ProvinciaCriteria();
            List<Provincia> lista = provinciaDAO.findByCriteria(c, criteria);
            commit = true;
            return lista;
        } catch (Exception e) {
            logger.error("Error listando todas las provincias: {}", e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public List<Provincia> findByPais(Long paisId) {
        if (paisId == null) {
            logger.warn("Intento de búsqueda de provincias con paisId nulo");
            return new ArrayList<>();
        }
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            ProvinciaCriteria criteria = new ProvinciaCriteria();
            criteria.setPaisId(paisId);
            List<Provincia> lista = provinciaDAO.findByCriteria(c, criteria);
            commit = true;
            return lista;
        } catch (Exception e) {
            logger.error("Error buscando provincias por paisId {}: {}", paisId, e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            JDBCUtils.close(c, commit);
        }
    }
}