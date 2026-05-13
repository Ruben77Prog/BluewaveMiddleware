package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.UnidadCriteria;
import com.ruben.bluewave.model.Unidad;
import com.ruben.bluewave.util.SQLUtils;

public class UnidadDAO {

	public UnidadDAO() {
	}

	public Unidad findById(Connection c, Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT id, nombre, simbolo, descripcion FROM unidad WHERE id = ?";

			ps = c.prepareStatement(sql);
			ps.setLong(1, id);

			rs = ps.executeQuery();

			Unidad unidad = null;
			if (rs.next()) {
				int i = 1;
				unidad = new Unidad();
				unidad.setId(rs.getLong(i++));
				unidad.setNombre(rs.getString(i++));
				unidad.setSimbolo(rs.getString(i++));
				unidad.setDescripcion(rs.getString(i++));
			}

			return unidad;

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

	public List<Unidad> findBy(Connection c,UnidadCriteria criteria) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			String sql = "SELECT id, nombre, simbolo, descripcion FROM unidad ";

			List<String> condiciones = new ArrayList<String>();
			List<Object> parameterValues = new ArrayList<Object>();

			SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", parameterValues, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, " nombre LIKE ? ", parameterValues,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getSimbolo(), condiciones, " simbolo LIKE ? ", parameterValues,
					"%" + criteria.getSimbolo() + "%");

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

			List<Unidad> unidades = new ArrayList<>();
			while (rs.next()) {
				int col = 1;
				Unidad unidad = new Unidad();
				unidad.setId(rs.getLong(col++));
				unidad.setNombre(rs.getString(col++));
				unidad.setSimbolo(rs.getString(col++));
				unidad.setDescripcion(rs.getString(col++));
				unidades.add(unidad);
			}

			return unidades;

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

	public Unidad create(Connection c,Unidad unidad) {
	
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			

			String sql = "INSERT INTO unidad (nombre, simbolo, descripcion) VALUES (?, ?, ?)";
			ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, unidad.getNombre());
			ps.setString(i++, unidad.getSimbolo());
			ps.setString(i++, unidad.getDescripcion());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				unidad.setId(rs.getLong(1));
			}

			return unidad;

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

	public Unidad update(Connection c,Unidad unidad) {
		if (unidad == null || unidad.getId() == null)
			return null;

		
		PreparedStatement ps = null;

		try {
			
			String sql = "UPDATE unidad SET nombre = ?, simbolo = ?, descripcion = ? WHERE id = ?";
			ps = c.prepareStatement(sql);

			int i = 1;
			ps.setString(i++, unidad.getNombre());
			ps.setString(i++, unidad.getSimbolo());
			ps.setString(i++, unidad.getDescripcion());
			ps.setLong(i++, unidad.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return unidad;
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

	public boolean delete(Connection c,Long id) {
		if (id == null)
			return false;

		
		PreparedStatement ps = null;

		try {
			

			String sql = "DELETE FROM unidad WHERE id = ?";
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