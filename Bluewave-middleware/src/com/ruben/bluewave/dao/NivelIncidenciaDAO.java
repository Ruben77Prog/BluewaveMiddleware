package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.NivelIncidenciaCriteria;
import com.ruben.bluewave.model.NivelIncidencia;
import com.ruben.bluewave.util.SQLUtils;

public class NivelIncidenciaDAO {

    public NivelIncidenciaDAO() {
    }

    public NivelIncidencia findById(Connection c,Long id) {
    
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           

            String sql = "SELECT id, nombre, descripcion, prioridad, horas_respuesta FROM nivel_incidencia WHERE id = ?";

            ps = c.prepareStatement(sql);
            ps.setLong(1, id);

            rs = ps.executeQuery();

            NivelIncidencia nivel = null;
            if (rs.next()) {
                int i = 1;
                nivel = new NivelIncidencia();
                nivel.setId(rs.getLong(i++));
                nivel.setNombre(rs.getString(i++));
                nivel.setDescripcion(rs.getString(i++));
                nivel.setPrioridad(rs.getInt(i++));
                nivel.setHorasRespuesta(rs.getInt(i++));
            }

            return nivel;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public List<NivelIncidencia> findBy(Connection c,NivelIncidenciaCriteria criteria) {
       
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           
            String sql = "SELECT id, nombre, descripcion, prioridad, horas_respuesta FROM nivel_incidencia ";

            List<String> condiciones = new ArrayList<String>();
            List<Object> parameterValues = new ArrayList<Object>();

            SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", parameterValues, criteria.getId());
            SQLUtils.addClause(criteria.getNombre(), condiciones, " nombre LIKE ? ", parameterValues, "%" + criteria.getNombre() + "%");
            SQLUtils.addClause(criteria.getPrioridad(), condiciones, " prioridad = ? ", parameterValues, criteria.getPrioridad());

            if (criteria.getHorasRespuestaMin() != null) {
                SQLUtils.addClause(criteria.getHorasRespuestaMin(), condiciones, 
                    " horas_respuesta >= ? ", parameterValues, criteria.getHorasRespuestaMin());
            }
            if (criteria.getHorasRespuestaMax() != null) {
                SQLUtils.addClause(criteria.getHorasRespuestaMax(), condiciones, 
                    " horas_respuesta <= ? ", parameterValues, criteria.getHorasRespuestaMax());
            }

            if (condiciones.size() > 0) {
                sql += " WHERE " + condiciones.get(0);
                for (int i = 1; i < condiciones.size(); i++) {
                    sql += " AND " + condiciones.get(i);
                }
            }

            System.out.println("SQL: " + sql);
            ps = c.prepareStatement(sql);

            int i = 1;
            for (Object parameterValue : parameterValues) {
                ps.setObject(i++, parameterValue);
            }

            rs = ps.executeQuery();

            List<NivelIncidencia> niveles = new ArrayList<>();
            while (rs.next()) {
                int col = 1;
                NivelIncidencia nivel = new NivelIncidencia();
                nivel.setId(rs.getLong(col++));
                nivel.setNombre(rs.getString(col++));
                nivel.setDescripcion(rs.getString(col++));
                nivel.setPrioridad(rs.getInt(col++));
                nivel.setHorasRespuesta(rs.getInt(col++));
                niveles.add(nivel);
            }

            return niveles;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public NivelIncidencia create(Connection c,NivelIncidencia nivelIncidencia) {
       
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           

            String sql = "INSERT INTO nivel_incidencia (nombre, descripcion, prioridad, horas_respuesta) " +
                        "VALUES (?, ?, ?, ?)";
            ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int i = 1;
            ps.setString(i++, nivelIncidencia.getNombre());
            ps.setString(i++, nivelIncidencia.getDescripcion());
            ps.setInt(i++, nivelIncidencia.getPrioridad());
            ps.setInt(i++, nivelIncidencia.getHorasRespuesta());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                nivelIncidencia.setId(rs.getLong(1));
            }

            return nivelIncidencia;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public NivelIncidencia update(Connection c,NivelIncidencia nivelIncidencia) {
        if (nivelIncidencia == null || nivelIncidencia.getId() == null)
            return null;

       
        PreparedStatement ps = null;

        try {
           

            String sql = "UPDATE nivel_incidencia SET nombre = ?, descripcion = ?, " +
                        "prioridad = ?, horas_respuesta = ? WHERE id = ?";
            ps = c.prepareStatement(sql);

            int i = 1;
            ps.setString(i++, nivelIncidencia.getNombre());
            ps.setString(i++, nivelIncidencia.getDescripcion());
            ps.setInt(i++, nivelIncidencia.getPrioridad());
            ps.setInt(i++, nivelIncidencia.getHorasRespuesta());
            ps.setLong(i++, nivelIncidencia.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                return nivelIncidencia;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return null;
    }

    public boolean delete(Connection c,Long id) {
        if (id == null)
            return false;

      
        PreparedStatement ps = null;

        try {
            

            String sql = "DELETE FROM nivel_incidencia WHERE id = ?";
            ps = c.prepareStatement(sql);
            ps.setLong(1, id);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return false;
    }

   
}