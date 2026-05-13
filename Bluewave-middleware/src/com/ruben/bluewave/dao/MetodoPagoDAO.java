package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.MetodoPagoCriteria;
import com.ruben.bluewave.model.MetodoPago;
import com.ruben.bluewave.util.DAOUtils;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class MetodoPagoDAO {

	private static final String BASE_QUERY = "SELECT id, nombre, descripcion, activo FROM metodo_pago";

	public MetodoPagoDAO() {
	}

	public MetodoPago findById(Connection c,Long id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			rs = ps.executeQuery();

			MetodoPago metodoPago = null;
			if (rs.next()) {
				int i = 1;
				metodoPago = new MetodoPago();
				metodoPago.setId(rs.getLong(i++));
				metodoPago.setNombre(rs.getString(i++));
				metodoPago.setDescripcion(rs.getString(i++));
				metodoPago.setActivo(rs.getBoolean(i++));
			}

			return metodoPago;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public List<MetodoPago> findBy(Connection c,MetodoPagoCriteria criteria) {
		
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

			List<MetodoPago> metodosPago = new ArrayList<>();
			while (rs.next()) {
				int col = 1;
				MetodoPago metodo = new MetodoPago();
				metodo.setId(rs.getLong(col++));
				metodo.setNombre(rs.getString(col++));
				metodo.setDescripcion(rs.getString(col++));
				metodo.setActivo(rs.getBoolean(col++));
				metodosPago.add(metodo);
			}

			return metodosPago;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return new ArrayList<>();
	}

	public MetodoPago create(Connection c,MetodoPago metodoPago) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO metodo_pago (nombre, descripcion, activo) ");
			sql.append("VALUES (?, ?, ?)");
			ps = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, metodoPago.getNombre());
			ps.setString(i++, metodoPago.getDescripcion());
			ps.setBoolean(i++, metodoPago.getActivo());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				metodoPago.setId(rs.getLong(1));
			}

			return metodoPago;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public MetodoPago update(Connection c,MetodoPago metodoPago) {
		if (metodoPago == null || metodoPago.getId() == null)
			return null;

		
		PreparedStatement ps = null;

		try {
		

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE metodo_pago SET nombre = ?, descripcion = ?, activo = ? WHERE id = ?");
			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, metodoPago.getNombre());
			ps.setString(i++, metodoPago.getDescripcion());
			ps.setBoolean(i++, metodoPago.getActivo());
			ps.setLong(i++, metodoPago.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return metodoPago;
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
			sql.append("DELETE FROM metodo_pago WHERE id = ?");
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