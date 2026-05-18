package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.PaisCriteria;
import com.ruben.bluewave.model.Pais;
import com.ruben.bluewave.util.SQLUtils;

public class PaisDAO {

	private static final String BASE_QUERY = " SELECT id, nombre, codigo_iso " + " FROM pais ";

	public PaisDAO() {
	}

	/**
	 * Busca países por su id
	 */
	public Pais findById(Connection c, Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE id = ? ");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			rs = ps.executeQuery();

			Pais pais = null;
			if (rs.next()) {
				pais = loadNext(rs);
			}
			return pais;
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Busca países por código ISO
	 */
	public List<Pais> findByCodigoIso(Connection c, String codigoIso) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pais> paises = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE codigo_iso = ? ");

			ps = c.prepareStatement(sql.toString());
			ps.setString(1, codigoIso);

			rs = ps.executeQuery();

			paises = new ArrayList<Pais>();
			while (rs.next()) {
				paises.add(loadNext(rs));
			}

			return paises;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error finding pais by codigo iso: " + codigoIso, e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (c != null)
					c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Busca países por nombre
	 */
	public List<Pais> findByNombre(Connection c, String nombre) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pais> paises = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE nombre LIKE ? ");

			ps = c.prepareStatement(sql.toString());
			ps.setString(1, "%" + nombre + "%");

			rs = ps.executeQuery();

			paises = new ArrayList<Pais>();
			while (rs.next()) {
				paises.add(loadNext(rs));
			}

			return paises;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error finding pais by nombre: " + nombre, e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (c != null)
					c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Búsqueda por criterios
	 */
	public List<Pais> findBy(Connection c, PaisCriteria criteria) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> parametros = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", parametros, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, " UPPER(nombre) LIKE UPPER(?) ", parametros,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getCodigoIso(), condiciones, " UPPER(codigo_iso) LIKE UPPER(?) ", parametros,
					"%" + criteria.getCodigoIso() + "%");

			if (!condiciones.isEmpty()) {
				sql.append(" WHERE ");
				sql.append(String.join(" AND ", condiciones));
			}

			sql.append(" ORDER BY nombre ASC ");

			System.out.println("SQL = " + sql.toString());
			ps = c.prepareStatement(sql.toString());

			int i = 1;
			for (Object param : parametros) {
				ps.setObject(i++, param);
			}

			rs = ps.executeQuery();
			List<Pais> resultados = new ArrayList<>();

			while (rs.next()) {
				resultados.add(loadNext(rs));
			}
			return resultados;
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<>();
	}

	/**
	 * Obtiene todos los países
	 */
	public List<Pais> findAll(Connection c) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" ORDER BY nombre ASC ");

			ps = c.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			List<Pais> resultados = new ArrayList<>();
			while (rs.next()) {
				resultados.add(loadNext(rs));
			}
			return resultados;
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<>();
	}

	

	

	

	/**
	 * Carga el resultado del ResultSet en un objeto Pais
	 */
	private Pais loadNext(ResultSet rs) throws SQLException {
		Pais pais = new Pais();
		int i = 1;

		pais.setId(rs.getLong(i++));
		pais.setNombre(rs.getString(i++));
		pais.setCodigoIso(rs.getString(i++));

		return pais;
	}

}