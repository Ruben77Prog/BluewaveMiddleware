package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.TipoMensajeCriteria;
import com.ruben.bluewave.model.TipoMensaje;
import com.ruben.bluewave.util.SQLUtils;

public class TipoMensajeDAO {

	public TipoMensajeDAO() {
	}

	public TipoMensaje findById(Connection c, Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT id, nombre, descripcion FROM tipo_mensaje WHERE id = ?";

			ps = c.prepareStatement(sql);
			ps.setLong(1, id);

			rs = ps.executeQuery();

			TipoMensaje tipoMensaje = null;
			if (rs.next()) {
				int i = 1;
				tipoMensaje = new TipoMensaje();
				tipoMensaje.setId(rs.getLong(i++));
				tipoMensaje.setNombre(rs.getString(i++));
				tipoMensaje.setDescripcion(rs.getString(i++));
			}

			return tipoMensaje;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (c != null)
					c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	public List<TipoMensaje> findBy(Connection c, TipoMensajeCriteria criteria) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT id, nombre, descripcion FROM tipo_mensaje ";

			List<String> condiciones = new ArrayList<String>();
			List<Object> parameterValues = new ArrayList<Object>();

			SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", parameterValues, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, " nombre LIKE ? ", parameterValues,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getDescripcion(), condiciones, " descripcion LIKE ? ", parameterValues,
					"%" + criteria.getDescripcion() + "%");

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

			List<TipoMensaje> tipos = new ArrayList<>();
			while (rs.next()) {
				int col = 1;
				TipoMensaje tipo = new TipoMensaje();
				tipo.setId(rs.getLong(col++));
				tipo.setNombre(rs.getString(col++));
				tipo.setDescripcion(rs.getString(col++));
				tipos.add(tipo);
			}

			return tipos;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (c != null)
					c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	public TipoMensaje create(Connection c, TipoMensaje tipoMensaje) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = "INSERT INTO tipo_mensaje (nombre, descripcion) VALUES (?, ?)";
			ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, tipoMensaje.getNombre());
			ps.setString(i++, tipoMensaje.getDescripcion());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				tipoMensaje.setId(rs.getLong(1));
			}

			return tipoMensaje;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (c != null)
					c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	public TipoMensaje update(Connection c, TipoMensaje tipoMensaje) {
		if (tipoMensaje == null || tipoMensaje.getId() == null)
			return null;

		PreparedStatement ps = null;

		try {

			String sql = "UPDATE tipo_mensaje SET nombre = ?, descripcion = ? WHERE id = ?";
			ps = c.prepareStatement(sql);

			int i = 1;
			ps.setString(i++, tipoMensaje.getNombre());
			ps.setString(i++, tipoMensaje.getDescripcion());
			ps.setLong(i++, tipoMensaje.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return tipoMensaje;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (c != null)
					c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return null;
	}

	public boolean delete(Connection c, Long id) {
		if (id == null)
			return false;

		PreparedStatement ps = null;

		try {

			String sql = "DELETE FROM tipo_mensaje WHERE id = ?";
			ps = c.prepareStatement(sql);
			ps.setLong(1, id);

			int rows = ps.executeUpdate();
			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (c != null)
					c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return false;
	}

}