package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.RolCriteria;
import com.ruben.bluewave.model.Rol;
import com.ruben.bluewave.util.DAOUtils;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class RolDAO {

	private static final String BASE_QUERY = "SELECT id, nombre, descripcion, activo FROM rol";

	public RolDAO() {
	}

	public Rol findById(Connection c, Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			rs = ps.executeQuery();

			Rol rol = null;
			if (rs.next()) {
				int i = 1;
				rol = new Rol();
				rol.setId(rs.getLong(i++));
				rol.setNombre(rs.getString(i++));
				rol.setDescripcion(rs.getString(i++));
				rol.setActivo(rs.getBoolean(i++));
			}

			return rol;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public List<Rol> findBy(Connection c, RolCriteria criteria) {

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
			SQLUtils.addClause(criteria.getActivo(), condiciones, " activo = ? ", parametros, criteria.getActivo());

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

			List<Rol> roles = new ArrayList<>();
			while (rs.next()) {
				int col = 1;
				Rol rol = new Rol();
				rol.setId(rs.getLong(col++));
				rol.setNombre(rs.getString(col++));
				rol.setDescripcion(rs.getString(col++));
				rol.setActivo(rs.getBoolean(col++));
				roles.add(rol);
			}

			return roles;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return new ArrayList<>();
	}

	public Rol create(Connection c, Rol rol) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO rol (nombre, descripcion, activo) ");
			sql.append("VALUES (?, ?, ?)");
			ps = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, rol.getNombre());
			ps.setString(i++, rol.getDescripcion());
			ps.setBoolean(i++, rol.getActivo());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				rol.setId(rs.getLong(1));
			}

			return rol;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public Rol update(Connection c, Rol rol) {
		if (rol == null || rol.getId() == null)
			return null;

		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE rol SET nombre = ?, descripcion = ?, activo = ? WHERE id = ?");
			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, rol.getNombre());
			ps.setString(i++, rol.getDescripcion());
			ps.setBoolean(i++, rol.getActivo());
			ps.setLong(i++, rol.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return rol;
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
			sql.append("DELETE FROM rol WHERE id = ?");
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

	private RolDAO loadNext(ResultSet rs) {

		return null;
	}

}