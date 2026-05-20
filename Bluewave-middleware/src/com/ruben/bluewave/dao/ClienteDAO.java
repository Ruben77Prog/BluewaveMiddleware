package com.ruben.bluewave.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ruben.bluewave.dao.criteria.ClienteCriteria;
import com.ruben.bluewave.model.Cliente;
import com.ruben.bluewave.model.ClienteDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.util.JDBCUtils;
import com.ruben.bluewave.util.SQLUtils;

public class ClienteDAO {

	private static Logger logger = LogManager.getLogger(ClienteDAO.class.getName());

	private static final String BASE_QUERY = "SELECT c.id, c.nombre, c.apellido1, c.apellido2, c.dni, c.telefono, "
			+ "c.telefono2, c.telefono3, c.email, c.password, c.fecha_nacimiento, " + "c.fecha_creacion, c.fecha_baja, "
			+ "c.estado_cliente_id, ec.nombre AS estado_cliente_nombre, "
			+ "c.direccion_id, d.calle, d.numero, d.piso, d.apartamento, d.codigo_postal, "
			+ "ci.id AS ciudad_id, ci.nombre AS ciudad_nombre, "
			+ "p.id AS provincia_id, p.nombre AS provincia_nombre, "
			+ "pais.id AS pais_id, pais.nombre AS pais_nombre, pais.codigo_iso AS pais_codigo_iso, "
			+ "c.empleado_asignado_id, e.nombre AS empleado_nombre, e.apellido1 AS empleado_apellido, "
			+ "c.genero_id, g.nombre AS genero_nombre, g.codigo AS genero_codigo " + "FROM cliente c "
			+ "INNER JOIN estado_cliente ec ON c.estado_cliente_id = ec.id "
			+ "INNER JOIN direccion d ON c.direccion_id = d.id " + "INNER JOIN ciudad ci ON d.ciudad_id = ci.id "
			+ "INNER JOIN provincia p ON ci.provincia_id = p.id " + "INNER JOIN pais ON p.pais_id = pais.id "
			+ "INNER JOIN empleado e ON c.empleado_asignado_id = e.id " + "INNER JOIN genero g ON c.genero_id = g.id";

	public ClienteDAO() {
	}

	public ClienteDTO findById(Connection c, Long id) {
		logger.debug("Buscando cliente por id: {}", id);

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE c.id = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);
			rs = ps.executeQuery();

			ClienteDTO cliente = null;
			if (rs.next()) {
				logger.debug("Cliente encontrado: {}", cliente);
				cliente = loadNext(rs);
			}
			return cliente;

		} catch (Exception e) {
			logger.error("Error buscando cliente id: {}", id, e);
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	public List<Cliente> findByEmail(Connection c, String email) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE c.email = ?");

			ps = c.prepareStatement(sql.toString());
			ps.setString(1, email);
			rs = ps.executeQuery();

			List<Cliente> clientes = new ArrayList<>();
			while (rs.next()) {
				clientes.add(loadNextCliente(rs));
			}
			return clientes;

		} catch (Exception e) {
			logger.error("Error buscando cliente por email: {}", email, e);
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return new ArrayList<>();
	}

	public Results<ClienteDTO> findByCriteria(Connection c, ClienteCriteria criteria, int from, int pageSize) {
		if (logger.isDebugEnabled()) {
			logger.info("Criteria: {}", criteria);
		}

		Results<ClienteDTO> results = new Results<>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<>();
			List<Object> parametros = new ArrayList<>();

			SQLUtils.addClause(criteria.getId(), condiciones, " c.id = ? ", parametros, criteria.getId());
			SQLUtils.addClause(criteria.getNombre(), condiciones, " UPPER(c.nombre) LIKE UPPER(?) ", parametros,
					"%" + criteria.getNombre() + "%");
			SQLUtils.addClause(criteria.getApellido1(), condiciones, " UPPER(c.apellido1) LIKE UPPER(?) ", parametros,
					"%" + criteria.getApellido1() + "%");
			SQLUtils.addClause(criteria.getApellido2(), condiciones, " UPPER(c.apellido2) LIKE UPPER(?) ", parametros,
					"%" + criteria.getApellido2() + "%");
			SQLUtils.addClause(criteria.getDni(), condiciones, " c.dni LIKE ? ", parametros,
					"%" + criteria.getDni() + "%");
			SQLUtils.addClause(criteria.getTelefono(), condiciones, " c.telefono LIKE ? ", parametros,
					"%" + criteria.getTelefono() + "%");
			SQLUtils.addClause(criteria.getEmail(), condiciones, " UPPER(c.email) LIKE UPPER(?) ", parametros,
					"%" + criteria.getEmail() + "%");
			SQLUtils.addClause(criteria.getEstadoClienteId(), condiciones, " c.estado_cliente_id = ? ", parametros,
					criteria.getEstadoClienteId());
			SQLUtils.addClause(criteria.getDireccionId(), condiciones, " c.direccion_id = ? ", parametros,
					criteria.getDireccionId());
			SQLUtils.addClause(criteria.getEmpleadoAsignadoId(), condiciones, " c.empleado_asignado_id = ? ",
					parametros, criteria.getEmpleadoAsignadoId());
			SQLUtils.addClause(criteria.getGeneroId(), condiciones, " c.genero_id = ? ", parametros,
					criteria.getGeneroId());

			if (criteria.getFechaNacimientoDesde() != null) {
				SQLUtils.addClause(true, condiciones, " c.fecha_nacimiento >= ? ", parametros,
						new java.sql.Date(criteria.getFechaNacimientoDesde().getTime()));
			}
			if (criteria.getFechaNacimientoHasta() != null) {
				SQLUtils.addClause(true, condiciones, " c.fecha_nacimiento <= ? ", parametros,
						new java.sql.Date(criteria.getFechaNacimientoHasta().getTime()));
			}
			if (criteria.getFechaCreacionDesde() != null) {
				SQLUtils.addClause(true, condiciones, " c.fecha_creacion >= ? ", parametros,
						new java.sql.Date(criteria.getFechaCreacionDesde().getTime()));
			}
			if (criteria.getFechaCreacionHasta() != null) {
				SQLUtils.addClause(true, condiciones, " c.fecha_creacion <= ? ", parametros,
						new java.sql.Date(criteria.getFechaCreacionHasta().getTime()));

			}

			if (!condiciones.isEmpty()) {
				sql.append(" WHERE ");
				sql.append(String.join(" AND ", condiciones));
			}

			if (logger.isInfoEnabled()) {
				logger.info("Criteria SQL:{} {}", criteria, sql);
			}
			sql.append(" ORDER BY ").append(criteria.getOrderBy());
			sql.append(Boolean.FALSE.equals(criteria.getAscDesc()) ? " DESC " : " ASC ");

			ps = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			for (Object param : parametros) {
				ps.setObject(i++, param);
			}

			rs = ps.executeQuery();
			List<ClienteDTO> resultados = new ArrayList<>();

			int filaInicial = Math.max(1, from);
			if (pageSize > 0 && rs.absolute(filaInicial)) {
				int count = 0;
				do {
					resultados.add(loadNext(rs));
					++count;
				} while (count < pageSize && rs.next());
			}

			int total = 0;
			if (rs.last()) {
				total = rs.getRow();
			}

			results.setPage(resultados);
			results.setTotal(total);
			return results;

		} catch (Exception e) {
			logger.error(e.getMessage() + ": " + criteria, e);
		} finally {
			JDBCUtils.close(rs, ps);
		}
		results.setPage(new ArrayList<>());
		results.setTotal(0);
		return results;
	}

	public List<ClienteDTO> findAll(Connection c) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ClienteDTO> clientes = new ArrayList<>();
		try {
			String sql = BASE_QUERY + " ORDER BY c.id";
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				clientes.add(loadNext(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return clientes;
	}

	public Cliente create(Connection c, Cliente cliente) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO cliente (nombre, apellido1, apellido2, dni, telefono, ");
			sql.append("telefono2, telefono3, email, password, fecha_nacimiento, fecha_creacion, ");
			sql.append("fecha_baja, estado_cliente_id, direccion_id, empleado_asignado_id, genero_id) ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			ps = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, cliente.getNombre());
			ps.setString(i++, cliente.getApellido1());
			ps.setString(i++, cliente.getApellido2());
			ps.setString(i++, cliente.getDni());
			ps.setString(i++, cliente.getTelefono());
			ps.setString(i++, cliente.getTelefono2());
			ps.setString(i++, cliente.getTelefono3());
			ps.setString(i++, cliente.getEmail());
			ps.setString(i++, cliente.getContrasena());

			ps.setDate(i++,
					cliente.getFechaNacimiento() != null ? new java.sql.Date(cliente.getFechaNacimiento().getTime())
							: null);

			ps.setDate(i++, cliente.getFechaAlta() != null ? new java.sql.Date(cliente.getFechaAlta().getTime())
					: new java.sql.Date(System.currentTimeMillis()));
			ps.setDate(i++,
					cliente.getFechaBaja() != null ? new java.sql.Date(cliente.getFechaBaja().getTime()) : null);

			ps.setLong(i++, cliente.getEstadoClienteId());
			ps.setLong(i++, cliente.getDireccionId());
			ps.setLong(i++, cliente.getEmpleadoAsignadoId());
			ps.setLong(i++, cliente.getGeneroId());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				cliente.setId(rs.getLong(1));
			}
			return cliente;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}

		return null;
	}

	public boolean update(Connection c, Cliente cliente) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE cliente SET ");
			sql.append("nombre = ?, apellido1 = ?, apellido2 = ?, dni = ?, telefono = ?, ");
			sql.append("telefono2 = ?, telefono3 = ?, email = ?, fecha_nacimiento = ?, ");
			sql.append("fecha_baja = ?, estado_cliente_id = ?, direccion_id = ?, ");
			sql.append("empleado_asignado_id = ?, genero_id = ? ");
			sql.append("WHERE id = ?");

			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, cliente.getNombre());
			ps.setString(i++, cliente.getApellido1());
			ps.setString(i++, cliente.getApellido2());
			ps.setString(i++, cliente.getDni());
			ps.setString(i++, cliente.getTelefono());
			ps.setString(i++, cliente.getTelefono2());
			ps.setString(i++, cliente.getTelefono3());
			ps.setString(i++, cliente.getEmail());

			if (cliente.getFechaNacimiento() != null) {
				ps.setDate(i++, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
			} else {
				ps.setDate(i++, null);
			}

			if (cliente.getFechaBaja() != null) {
				ps.setDate(i++, new java.sql.Date(cliente.getFechaBaja().getTime()));
			} else {
				ps.setDate(i++, null);
			}

			if (cliente.getEstadoClienteId() != null) {
				ps.setLong(i++, cliente.getEstadoClienteId());
			} else {
				ps.setLong(i++, 1L);
			}

			if (cliente.getDireccionId() != null) {
				ps.setLong(i++, cliente.getDireccionId());
			} else {
				ps.setNull(i++, java.sql.Types.BIGINT);
			}

			if (cliente.getEmpleadoAsignadoId() != null) {
				ps.setLong(i++, cliente.getEmpleadoAsignadoId());
			} else {
				ps.setLong(i++, 1L);
			}

			if (cliente.getGeneroId() != null) {
				ps.setLong(i++, cliente.getGeneroId());
			} else {
				ps.setLong(i++, 1L);
			}

			ps.setLong(i++, cliente.getId());

			int rows = ps.executeUpdate();
			return rows > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtils.close(null, ps);
		}
	}

	public boolean updatePassword(Connection c, Long id, String password) {
		if (id == null) {
			return false;
		}

		PreparedStatement ps = null;

		try {

			String sql = "UPDATE cliente SET password = ? WHERE id = ?";

			ps = c.prepareStatement(sql);
			ps.setString(1, password);
			ps.setLong(2, id);

			int rows = ps.executeUpdate();
			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps);
		}
		return false;
	}

	public boolean delete(Connection c, Long id) {
		if (id == null) {
			return false;
		}

		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM cliente WHERE id = ?");

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

	private ClienteDTO loadNext(ResultSet rs) {
		try {
			ClienteDTO cliente = new ClienteDTO();

			cliente.setId(rs.getLong("id"));
			cliente.setNombre(rs.getString("nombre"));
			cliente.setApellido1(rs.getString("apellido1"));
			cliente.setApellido2(rs.getString("apellido2"));
			cliente.setDni(rs.getString("dni"));
			cliente.setTelefono(rs.getString("telefono"));
			cliente.setTelefono2(rs.getString("telefono2"));
			cliente.setTelefono3(rs.getString("telefono3"));
			cliente.setEmail(rs.getString("email"));
			cliente.setPassword(rs.getString("password"));

			cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
			cliente.setFechaCreacion(rs.getDate("fecha_creacion"));
			cliente.setFechaBaja(rs.getDate("fecha_baja"));

			cliente.setEstadoClienteId(rs.getLong("estado_cliente_id"));
			cliente.setEstadoClienteNombre(rs.getString("estado_cliente_nombre"));

			cliente.setDireccionId(rs.getLong("direccion_id"));
			cliente.setDireccionCalle(rs.getString("calle"));
			cliente.setDireccionNumero(rs.getString("numero"));
			cliente.setDireccionPiso(rs.getString("piso"));
			cliente.setDireccionDepartamento(rs.getString("apartamento"));
			cliente.setDireccionCodigoPostal(rs.getString("codigo_postal"));

			cliente.setCiudadId(rs.getLong("ciudad_id"));
			cliente.setCiudadNombre(rs.getString("ciudad_nombre"));

			cliente.setProvinciaId(rs.getLong("provincia_id"));
			cliente.setProvinciaNombre(rs.getString("provincia_nombre"));

			cliente.setPaisId(rs.getLong("pais_id"));
			cliente.setPaisNombre(rs.getString("pais_nombre"));
			cliente.setPaisCodigoIso(rs.getString("pais_codigo_iso"));

			cliente.setEmpleadoAsignadoId(rs.getLong("empleado_asignado_id"));
			cliente.setEmpleadoNombre(rs.getString("empleado_nombre"));
			cliente.setEmpleadoApellido(rs.getString("empleado_apellido"));

			cliente.setGeneroId(rs.getLong("genero_id"));
			cliente.setGeneroNombre(rs.getString("genero_nombre"));
			cliente.setGeneroCodigo(rs.getString("genero_codigo"));

			return cliente;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Cliente loadNextCliente(ResultSet rs) {
		try {
			Cliente cliente = new Cliente();

			cliente.setId(rs.getLong("id"));
			cliente.setNombre(rs.getString("nombre"));
			cliente.setApellido1(rs.getString("apellido1"));
			cliente.setApellido2(rs.getString("apellido2"));
			cliente.setDni(rs.getString("dni"));
			cliente.setTelefono(rs.getString("telefono"));
			cliente.setTelefono2(rs.getString("telefono2"));
			cliente.setTelefono3(rs.getString("telefono3"));
			cliente.setEmail(rs.getString("email"));
			cliente.setContrasena(rs.getString("password"));

			cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
			cliente.setFechaAlta(rs.getDate("fecha_creacion"));
			cliente.setFechaBaja(rs.getDate("fecha_baja"));

			cliente.setEstadoClienteId(rs.getLong("estado_cliente_id"));
			cliente.setDireccionId(rs.getLong("direccion_id"));
			cliente.setEmpleadoAsignadoId(rs.getLong("empleado_asignado_id"));
			cliente.setGeneroId(rs.getLong("genero_id"));

			return cliente;
		} catch (Exception e) {
			logger.error("Error en loadNextCliente", e);
			e.printStackTrace();
			return null;
		}
	}
}