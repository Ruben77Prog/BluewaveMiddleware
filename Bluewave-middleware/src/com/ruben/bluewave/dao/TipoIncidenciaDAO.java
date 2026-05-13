package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.TipoIncidenciaCriteria;
import com.ruben.bluewave.model.TipoIncidencia;
import com.ruben.bluewave.util.DAOUtils;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class TipoIncidenciaDAO {

	private static final String BASE_QUERY = "SELECT id, nombre, descripcion, prioridad_default, nivel_incidencia_default_id, activo "
			+ "FROM tipo_incidencia";

	public TipoIncidenciaDAO() {

	}

	public TipoIncidencia findById(Connection c, Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE id = ?");
			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				return loadNext(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public List<TipoIncidencia> findBy(Connection c, TipoIncidenciaCriteria criteria) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TipoIncidencia> tipos = new ArrayList<>();

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> parametros = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, "id = ?", parametros, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, "nombre LIKE ?", parametros,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getPrioridadDefecto(), condiciones, "prioridad_default = ?", parametros,
					criteria.getPrioridadDefecto());
			SQLUtils.addClause(criteria.getNivelDefectoId(), condiciones, "nivel_incidencia_default_id = ?", parametros,
					criteria.getNivelDefectoId());
			SQLUtils.addClause(criteria.getActivo(), condiciones, "activo = ?", parametros, criteria.getActivo());

			if (!condiciones.isEmpty()) {
				sql.append(" WHERE ");
				sql.append(String.join(" AND ", condiciones));
			}

			ps = c.prepareStatement(sql.toString());
			int i = 1;
			for (Object param : parametros) {
				ps.setObject(i++, param);
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				TipoIncidencia tipo = loadNext(rs);
				if (tipo != null)
					tipos.add(tipo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return tipos;
	}

	public List<TipoIncidencia> findAll(Connection c) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TipoIncidencia> lista = new ArrayList<>();

		try {
			
			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" ORDER BY nombre ASC");

			ps = c.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				TipoIncidencia tipo = loadNext(rs);
				if (tipo != null)
					lista.add(tipo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return lista;
	}

	public TipoIncidencia create(Connection c,TipoIncidencia tipoIncidencia) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			String sql = "INSERT INTO tipo_incidencia (nombre, descripcion, prioridad_default, nivel_incidencia_default_id, activo) VALUES (?, ?, ?, ?, ?)";
			ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, tipoIncidencia.getNombre());
			ps.setString(i++, tipoIncidencia.getDescripcion());
			ps.setString(i++, tipoIncidencia.getPrioridadDefault());
			ps.setLong(i++, tipoIncidencia.getNivelIncidenciaDefaultId());
			ps.setBoolean(i++, tipoIncidencia.getActivo());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				tipoIncidencia.setId(rs.getLong(1));
			}

			return tipoIncidencia;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public TipoIncidencia update(Connection c,TipoIncidencia tipoIncidencia) {

		
		PreparedStatement ps = null;

		try {
			
			String sql = "UPDATE tipo_incidencia SET nombre = ?, descripcion = ?, prioridad_default = ?, nivel_incidencia_default_id = ?, activo = ? WHERE id = ?";
			ps = c.prepareStatement(sql);

			int i = 1;
			ps.setString(i++, tipoIncidencia.getNombre());
			ps.setString(i++, tipoIncidencia.getDescripcion());
			ps.setString(i++, tipoIncidencia.getPrioridadDefault());
			ps.setLong(i++, tipoIncidencia.getNivelIncidenciaDefaultId());
			ps.setBoolean(i++, tipoIncidencia.getActivo());
			ps.setLong(i++, tipoIncidencia.getId());

			int rows = ps.executeUpdate();
			if (rows > 0)
				return tipoIncidencia;

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
			
			String sql = "DELETE FROM tipo_incidencia WHERE id = ?";
			ps = c.prepareStatement(sql);
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

	private TipoIncidencia loadNext(ResultSet rs) {
		try {
			TipoIncidencia tipo = new TipoIncidencia();
			tipo.setId(rs.getLong("id"));
			tipo.setNombre(rs.getString("nombre"));
			tipo.setDescripcion(rs.getString("descripcion"));
			tipo.setPrioridadDefault(rs.getString("prioridad_default"));
			tipo.setNivelIncidenciaDefaultId(rs.getLong("nivel_incidencia_default_id"));
			tipo.setActivo(rs.getBoolean("activo"));
			return tipo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}