package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ruben.bluewave.dao.criteria.EmpleadoCriteria;
import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class EmpleadoDAO {

	private static final String BASE_QUERY = "SELECT id, nombre, apellido1, apellido2, dni, telefono, "
			+ "email, password, fecha_creacion, fecha_baja, ultimo_login, activo, "
			+ "rol_id, genero_id, direccion_id FROM empleado";

	public EmpleadoDAO() {
	}

	public EmpleadoDTO findById(Connection c, Long id) {

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

	public List<EmpleadoDTO> findByEmail(Connection c, String email) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		List<EmpleadoDTO> list = new ArrayList<>();

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE email = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setString(1, email);

			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(loadNext(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}

		return list;
	}

	public List<EmpleadoDTO> findAll(Connection c) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		List<EmpleadoDTO> list = new ArrayList<>();

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" ORDER BY fecha_creacion DESC");

			ps = c.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(loadNext(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}

		return list;
	}

	public List<EmpleadoDTO> findByCriteria(Connection c, EmpleadoCriteria criteria) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		List<EmpleadoDTO> list = new ArrayList<>();

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> params = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, " id = ? ", params, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, " UPPER(nombre) LIKE UPPER(?) ", params,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getApellido1(), condiciones, " UPPER(apellido1) LIKE UPPER(?) ", params,
					"%" + criteria.getApellido1() + "%");
			SQLUtils.addClause(criteria.getApellido2(), condiciones, " UPPER(apellido2) LIKE UPPER(?) ", params,
					"%" + criteria.getApellido2() + "%");
			SQLUtils.addClause(criteria.getDni(), condiciones, " dni LIKE ? ", params, "%" + criteria.getDni() + "%");
			SQLUtils.addClause(criteria.getTelefono(), condiciones, " telefono LIKE ? ", params,
					"%" + criteria.getTelefono() + "%");
			SQLUtils.addClause(criteria.getEmail(), condiciones, " UPPER(email) LIKE UPPER(?) ", params,
					"%" + criteria.getEmail() + "%");
			SQLUtils.addClause(criteria.getActivo(), condiciones, " activo = ? ", params, criteria.getActivo());
			SQLUtils.addClause(criteria.getRolId(), condiciones, " rol_id = ? ", params, criteria.getRolId());
			SQLUtils.addClause(criteria.getGeneroId(), condiciones, " genero_id = ? ", params, criteria.getGeneroId());
			SQLUtils.addClause(criteria.getDireccionId(), condiciones, " direccion_id = ? ", params,
					criteria.getDireccionId());

			if (!condiciones.isEmpty()) {
				sql.append(" WHERE ").append(String.join(" AND ", condiciones));
			}

			sql.append(" ORDER BY ").append(criteria.getOrderBy());
			sql.append(Boolean.FALSE.equals(criteria.getAscDesc()) ? " DESC " : " ASC ");

			ps = c.prepareStatement(sql.toString());

			int i = 1;
			for (Object p : params) {
				ps.setObject(i++, p);
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(loadNext(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}

		return list;
	}

	public List<EmpleadoDTO> findByDni(Connection c, String dni) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		List<EmpleadoDTO> list = new ArrayList<>();

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE dni = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setString(1, dni);

			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(loadNext(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}

		return list;
	}

	public List<EmpleadoDTO> findAllActivos(Connection c) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		List<EmpleadoDTO> list = new ArrayList<>();

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE activo = true ORDER BY apellido1, nombre");

			ps = c.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(loadNext(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}

		return list;
	}

	public EmpleadoDTO create(Connection c, EmpleadoDTO dto) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO empleado ");
			sql.append("(nombre, apellido1, apellido2, dni, telefono, email, password, ");
			sql.append("fecha_creacion, activo, rol_id, genero_id, direccion_id) ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			ps = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, dto.getNombre());
			ps.setString(i++, dto.getApellido1());
			ps.setString(i++, dto.getApellido2());
			ps.setString(i++, dto.getDni());
			ps.setString(i++, dto.getTelefono());
			ps.setString(i++, dto.getEmail());
			ps.setString(i++, dto.getPassword());
			ps.setTimestamp(i++, new java.sql.Timestamp(System.currentTimeMillis()));
			ps.setBoolean(i++, dto.getActivo() != null ? dto.getActivo() : true);
			ps.setLong(i++, dto.getRolId());
			ps.setLong(i++, dto.getGeneroId());
			ps.setLong(i++, dto.getDireccionId());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				dto.setId(rs.getLong(1));
			}

			return dto;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}

		return null;
	}

	public boolean update(Connection c, EmpleadoDTO dto) {
		if (dto == null || dto.getId() == null)
			return false;

		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE empleado SET ");
			sql.append("nombre=?, apellido1=?, apellido2=?, dni=?, telefono=?, email=?, ");
			sql.append("password=?, fecha_baja=?, ultimo_login=?, activo=?, rol_id=?, genero_id=?, direccion_id=? ");
			sql.append("WHERE id=?");

			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, dto.getNombre());
			ps.setString(i++, dto.getApellido1());
			ps.setString(i++, dto.getApellido2());
			ps.setString(i++, dto.getDni());
			ps.setString(i++, dto.getTelefono());
			ps.setString(i++, dto.getEmail());
			ps.setString(i++, dto.getPassword());

			ps.setTimestamp(i++,
					dto.getFechaBaja() != null ? new java.sql.Timestamp(dto.getFechaBaja().getTime()) : null);

			ps.setTimestamp(i++,
					dto.getUltimoLogin() != null ? new java.sql.Timestamp(dto.getUltimoLogin().getTime()) : null);

			ps.setBoolean(i++, dto.getActivo() != null ? dto.getActivo() : true);
			ps.setLong(i++, dto.getRolId());
			ps.setLong(i++, dto.getGeneroId());
			ps.setLong(i++, dto.getDireccionId());
			ps.setLong(i++, dto.getId());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps);
		}

		return false;
	}

	public boolean delete(Connection c, Long id) {
		if (id == null)
			return false;

		PreparedStatement ps = null;

		try {

			ps = c.prepareStatement("DELETE FROM empleado WHERE id=?");
			ps.setLong(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps);
		}

		return false;
	}

	private EmpleadoDTO loadNext(ResultSet rs) {
		try {
			EmpleadoDTO dto = new EmpleadoDTO();

			dto.setId(rs.getLong("id"));
			dto.setNombre(rs.getString("nombre"));
			dto.setApellido1(rs.getString("apellido1"));
			dto.setApellido2(rs.getString("apellido2"));
			dto.setDni(rs.getString("dni"));
			dto.setTelefono(rs.getString("telefono"));
			dto.setEmail(rs.getString("email"));
			dto.setPassword(rs.getString("password"));
			dto.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
			dto.setFechaBaja(rs.getTimestamp("fecha_baja"));
			dto.setUltimoLogin(rs.getTimestamp("ultimo_login"));
			dto.setActivo(rs.getBoolean("activo"));
			dto.setRolId(rs.getLong("rol_id"));
			dto.setGeneroId(rs.getLong("genero_id"));
			dto.setDireccionId(rs.getLong("direccion_id"));

			return dto;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}