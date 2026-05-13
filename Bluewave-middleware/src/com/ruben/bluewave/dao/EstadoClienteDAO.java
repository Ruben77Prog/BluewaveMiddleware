package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.EstadoClienteCriteria;
import com.ruben.bluewave.model.EstadoCliente;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class EstadoClienteDAO {

	private static final String BASE_QUERY = "SELECT id, nombre, descripcion FROM estado_cliente";

	public EstadoClienteDAO() {
	}

	public EstadoCliente findById(Connection c, Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);
			rs = ps.executeQuery();

			EstadoCliente estadoCliente = null;
			if (rs.next()) {
				int i = 1;
				estadoCliente = new EstadoCliente();
				estadoCliente.setId(rs.getLong(i++));
				estadoCliente.setNombre(rs.getString(i++));
				estadoCliente.setDescripcion(rs.getString(i++));
			}

			return estadoCliente;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public List<EstadoCliente> findBy(Connection c, EstadoClienteCriteria criteria) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> parametros = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", parametros, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, " nombre LIKE ? ", parametros,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getDescripcion(), condiciones, " descripcion LIKE ? ", parametros,
					"%" + criteria.getDescripcion() + "%");

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

			List<EstadoCliente> estados = new ArrayList<>();
			while (rs.next()) {
				int col = 1;
				EstadoCliente estado = new EstadoCliente();
				estado.setId(rs.getLong(col++));
				estado.setNombre(rs.getString(col++));
				estado.setDescripcion(rs.getString(col++));
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

	public List<EstadoCliente> findAll(Connection c) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<EstadoCliente> lista = new ArrayList<>();
		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" ORDER BY nombre");
			ps = c.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				EstadoCliente ec = new EstadoCliente();
				ec.setId(rs.getLong("id"));
				ec.setNombre(rs.getString("nombre"));
				ec.setDescripcion(rs.getString("descripcion"));
				lista.add(ec);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return lista;
	}

	public EstadoCliente create(Connection c, EstadoCliente estadoCliente) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO estado_cliente (nombre, descripcion) ");
			sql.append("VALUES (?, ?)");

			ps = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, estadoCliente.getNombre());
			ps.setString(i++, estadoCliente.getDescripcion());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				estadoCliente.setId(rs.getLong(1));
			}

			return estadoCliente;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public EstadoCliente update(Connection c, EstadoCliente estadoCliente) {
		if (estadoCliente == null || estadoCliente.getId() == null)
			return null;

		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE estado_cliente SET nombre = ?, descripcion = ? WHERE id = ?");

			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, estadoCliente.getNombre());
			ps.setString(i++, estadoCliente.getDescripcion());
			ps.setLong(i++, estadoCliente.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return estadoCliente;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps);
		}

		return null;
	}

	public boolean delete(Connection c, Long id) {
		if (id == null)
			return false;

		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM estado_cliente WHERE id = ?");

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