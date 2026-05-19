package com.ruben.bluewave.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ruben.bluewave.dao.PlanDAO;
import com.ruben.bluewave.dao.criteria.PlanCriteria;
import com.ruben.bluewave.model.PlanDTO;
import com.ruben.bluewave.service.PlanService;
import com.ruben.bluewave.util.JDBCUtils;

public class PlanServiceImpl implements PlanService {

    private static final Logger logger = LogManager.getLogger(PlanServiceImpl.class);
    private PlanDAO planDAO;

    public PlanServiceImpl() {
        this.planDAO = new PlanDAO();
    }

    @Override
    public PlanDTO findById(Long id) throws Exception {
        if (id == null) return null;
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            PlanDTO plan = planDAO.findById(c, id);
            commit = true;
            return plan;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public List<PlanDTO> findByCriteria(PlanCriteria criteria) throws Exception {
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            List<PlanDTO> lista = planDAO.findByCriteria(c, criteria);
            commit = true;
            return lista;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public List<PlanDTO> findAll() throws Exception {
        PlanCriteria criteria = new PlanCriteria();
        return findByCriteria(criteria);
    }

    @Override
    public List<PlanDTO> findActivos() throws Exception {
        PlanCriteria criteria = new PlanCriteria();
        criteria.setActivo(true);
        return findByCriteria(criteria);
    }

    @Override
    public Long create(PlanDTO plan) throws Exception {
        if (plan == null) return null;
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            PlanDTO creado = planDAO.create(c, plan);
            if (creado == null || creado.getId() == null) {
                return null;
            }
            commit = true;
            return creado.getId();
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean update(PlanDTO plan) throws Exception {
        if (plan == null || plan.getId() == null) return false;
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            PlanDTO actualizado = planDAO.update(c, plan);
            commit = actualizado != null;
            return actualizado != null;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        if (id == null) return false;
        Connection c = null;
        boolean commit = false;
        try {
            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            boolean eliminado = planDAO.delete(c, id);
            commit = eliminado;
            return eliminado;
        } finally {
            JDBCUtils.close(c, commit);
        }
    }
}