package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.EstadoIncidenciaCriteria;
import com.ruben.bluewave.model.EstadoIncidencia;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class EstadoIncidenciaDAO {

	private static final String BASE_QUERY = "SELECT id, nombre, descripcion, es_final FROM estado_incidencia";

	public EstadoIncidenciaDAO() {
	}

	public EstadoIncidencia findById(Connection c,Long id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			rs = ps.executeQuery();

			EstadoIncidencia estado = null;
			if (rs.next()) {
				int i = 1;
				estado = new EstadoIncidencia();
				estado.setId(rs.getLong(i++));
				estado.setNombre(rs.getString(i++));
				estado.setDescripcion(rs.getString(i++));
				estado.setEsFinal(rs.getBoolean(i++));
			}

			return estado;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public List<EstadoIncidencia> findBy(Connection c,EstadoIncidenciaCriteria criteria) {
	
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> parametros = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", parametros, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, " nombre LIKE ? ", parametros,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getEsFinal(), condiciones, " es_final = ? ", parametros,
					criteria.getEsFinal());

			if (!condiciones.isEmpty()) {
				sql.append(" WHERE ");
				sql.append(String.join(" AND ", condiciones));
			}

			System.out.println("SQL = " + sql.toString());
			ps = c.prepareStatement(sql.toString());

			int i = 1;
			for (Object param : parametros) {
				ps.setObject(i++, param);
			}

			rs = ps.executeQuery();

			List<EstadoIncidencia> estados = new ArrayList<>();
			while (rs.next()) {
				int col = 1;
				EstadoIncidencia estado = new EstadoIncidencia();
				estado.setId(rs.getLong(col++));
				estado.setNombre(rs.getString(col++));
				estado.setDescripcion(rs.getString(col++));
				estado.setEsFinal(rs.getBoolean(col++));
				estados.add(estado);
			}

			return estados;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return new ArrayList<>();
	}
	
	
	
	public List<EstadoIncidencia> findAll(Connection c) {
	    
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	       

	        String sql = BASE_QUERY; 
	        ps = c.prepareStatement(sql);

	        rs = ps.executeQuery();

	        List<EstadoIncidencia> estados = new ArrayList<>();
	        while (rs.next()) {
	            int i = 1;
	            EstadoIncidencia estado = new EstadoIncidencia();
	            estado.setId(rs.getLong(i++));
	            estado.setNombre(rs.getString(i++));
	            estado.setDescripcion(rs.getString(i++));
	            estado.setEsFinal(rs.getBoolean(i++));
	            estados.add(estado);
	        }

	        return estados;

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	JDBCUtils.close(rs, ps);
	    }

	    return new ArrayList<>();
	}

	public EstadoIncidencia create(Connection c,EstadoIncidencia estadoIncidencia) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO estado_incidencia (nombre, descripcion, es_final) ");
			sql.append("VALUES (?, ?, ?)");
			ps = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, estadoIncidencia.getNombre());
			ps.setString(i++, estadoIncidencia.getDescripcion());
			ps.setBoolean(i++, estadoIncidencia.getEsFinal());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				estadoIncidencia.setId(rs.getLong(1));
			}

			return estadoIncidencia;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public EstadoIncidencia update(Connection c,EstadoIncidencia estadoIncidencia) {
		if (estadoIncidencia == null || estadoIncidencia.getId() == null)
			return null;

		
		PreparedStatement ps = null;

		try {
			

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE estado_incidencia SET nombre = ?, descripcion = ?, es_final = ? WHERE id = ?");
			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, estadoIncidencia.getNombre());
			ps.setString(i++, estadoIncidencia.getDescripcion());
			ps.setBoolean(i++, estadoIncidencia.getEsFinal());
			ps.setLong(i++, estadoIncidencia.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return estadoIncidencia;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps);
		}

		return null;
	}

	public boolean delete(Connection c,Long id) {
		if (id == null)
			return false;

		
		PreparedStatement ps = null;

		try {
			

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM estado_incidencia WHERE id = ?");
			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			int rows = ps.executeUpdate();
			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps);
		}

		return false;
	}
}