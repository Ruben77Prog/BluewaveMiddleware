package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.GeneroCriteria;
import com.ruben.bluewave.model.Genero;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class GeneroDAO {

	private static final String BASE_QUERY = "SELECT id, nombre, codigo FROM genero";

	public GeneroDAO() {
	}

	public Genero findById(Connection c,Long id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			rs = ps.executeQuery();

			Genero genero = null;
			if (rs.next()) {
				int i = 1;
				genero = new Genero();
				genero.setId(rs.getLong(i++));
				genero.setNombre(rs.getString(i++));
				genero.setCodigo(rs.getString(i++));
			}

			return genero;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public List<Genero> findBy(Connection c,GeneroCriteria criteria) {
	
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
		
			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> parametros = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", parametros, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, " nombre LIKE ? ", parametros,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getCodigo(), condiciones, " codigo LIKE ? ", parametros,
					"%" + criteria.getCodigo() + "%");

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

			List<Genero> generos = new ArrayList<>();
			while (rs.next()) {
				int col = 1;
				Genero genero = new Genero();
				genero.setId(rs.getLong(col++));
				genero.setNombre(rs.getString(col++));
				genero.setCodigo(rs.getString(col++));
				generos.add(genero);
			}

			return generos;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return new ArrayList<>();
	}

	public List<Genero> findAll(Connection c) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Genero> lista = new ArrayList<>();

		try {
			
			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" ORDER BY nombre");
			ps = c.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				Genero g = new Genero();
				g.setId(rs.getLong("id"));
				g.setNombre(rs.getString("nombre"));
				g.setCodigo(rs.getString("codigo"));
				lista.add(g);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return lista;
	}

	public Genero update(Connection c,Genero genero) {
		if (genero == null || genero.getId() == null)
			return null;

	
		PreparedStatement ps = null;

		try {
			

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE genero SET nombre = ?, codigo = ? WHERE id = ?");
			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, genero.getNombre());
			ps.setString(i++, genero.getCodigo());
			ps.setLong(i++, genero.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return genero;
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
			sql.append("DELETE FROM genero WHERE id = ?");
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