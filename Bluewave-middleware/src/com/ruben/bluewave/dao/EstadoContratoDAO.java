package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.EstadoContratoCriteria;
import com.ruben.bluewave.model.EstadoContrato;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class EstadoContratoDAO {

	private static final String BASE_QUERY = "SELECT id, nombre, descripcion, activo FROM estado_contrato";

	public EstadoContratoDAO() {
	}

	public EstadoContrato findById(Connection c,Long id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
		
			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			rs = ps.executeQuery();

			EstadoContrato estadoContrato = null;
			if (rs.next()) {
				int i = 1;
				estadoContrato = new EstadoContrato();
				estadoContrato.setId(rs.getLong(i++));
				estadoContrato.setNombre(rs.getString(i++));
				estadoContrato.setDescripcion(rs.getString(i++));
				estadoContrato.setActivo(rs.getBoolean(i++));
			}

			return estadoContrato;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public List<EstadoContrato> findBy(Connection c,EstadoContratoCriteria criteria) {
	
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> parametros = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", parametros, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, " nombre LIKE ? ", parametros,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getActivo(), condiciones, " activo = ? ", parametros,
					criteria.getActivo());

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

			List<EstadoContrato> estados = new ArrayList<>();
			while (rs.next()) {
				int col = 1;
				EstadoContrato estado = new EstadoContrato();
				estado.setId(rs.getLong(col++));
				estado.setNombre(rs.getString(col++));
				estado.setDescripcion(rs.getString(col++));
				estado.setActivo(rs.getBoolean(col++));
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

	public EstadoContrato create(Connection c,EstadoContrato estadoContrato) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO estado_contrato (nombre, descripcion, activo) ");
			sql.append("VALUES (?, ?, ?)");
			ps = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, estadoContrato.getNombre());
			ps.setString(i++, estadoContrato.getDescripcion());
			ps.setBoolean(i++, estadoContrato.getActivo());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				estadoContrato.setId(rs.getLong(1));
			}

			return estadoContrato;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
			
		}
		return null;
	}

	public EstadoContrato update(Connection c,EstadoContrato estadoContrato) {
		if (estadoContrato == null || estadoContrato.getId() == null)
			return null;

		
		PreparedStatement ps = null;

		try {
			

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE estado_contrato SET nombre = ?, descripcion = ?, activo = ? WHERE id = ?");
			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, estadoContrato.getNombre());
			ps.setString(i++, estadoContrato.getDescripcion());
			ps.setBoolean(i++, estadoContrato.getActivo());
			ps.setLong(i++, estadoContrato.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return estadoContrato;
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
			sql.append("DELETE FROM estado_contrato WHERE id = ?");
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