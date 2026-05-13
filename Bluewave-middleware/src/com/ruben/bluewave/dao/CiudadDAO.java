package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.CiudadCriteria;
import com.ruben.bluewave.model.Ciudad;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class CiudadDAO {

	private static final String BASE_QUERY = "SELECT id, nombre, provincia_id FROM ciudad";

	public CiudadDAO() {
	}

	public Ciudad findById(Connection c, Long id) {
	
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
		
			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);
			rs = ps.executeQuery();

			Ciudad ciudad = null;
			if (rs.next()) {
				ciudad = loadNext(rs);
			}
			return ciudad;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public List<Ciudad> findBy(Connection c, CiudadCriteria criteria) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
		
			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> parametros = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", parametros, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, " nombre LIKE ? ", parametros,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getProvinciaId(), condiciones, " provincia_id = ? ", parametros,
					criteria.getProvinciaId());

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
			List<Ciudad> resultados = new ArrayList<>();

			while (rs.next()) {
				resultados.add(loadNext(rs));
			}
			return resultados;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return new ArrayList<>();
	}

	
	public boolean update(Connection c, Ciudad ciudad) {
		if (ciudad == null || ciudad.getId() == null) {
			return false;
		}

		
		PreparedStatement ps = null;

		try {
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ciudad SET ");
			sql.append("nombre = ?, provincia_id = ? ");
			sql.append("WHERE id = ?");

			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, ciudad.getNombre());
			ps.setLong(i++, ciudad.getProvinciaId());
			ps.setLong(i++, ciudad.getId());

			int rows = ps.executeUpdate();
			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps);
		}
		return false;
	}

	
	private Ciudad loadNext(ResultSet rs) {
		try {
			Ciudad ciudad = new Ciudad();

			ciudad.setId(rs.getLong("id"));
			ciudad.setNombre(rs.getString("nombre"));
			ciudad.setProvinciaId(rs.getLong("provincia_id"));

			return ciudad;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}