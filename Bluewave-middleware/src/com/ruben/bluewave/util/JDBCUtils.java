package com.ruben.bluewave.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCUtils {
	private static Logger logger = LogManager.getLogger(JDBCUtils.class.getName());

	public static Connection getConnection() {
		try {
			// Carga el driver JDBC
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3308/bluewave", "root", "abc123.");
		} catch (Exception e) {
			logger.fatal(e);
		}
		return null;
	}

	public static void close(ResultSet rs, PreparedStatement ps) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception ignored) {
		}
		try {
			if (ps != null)
				ps.close();
		} catch (Exception ignored) {
		}
	}

	private static void close(Connection c) {
		try {
			if (c != null)
				c.close();
		} catch (Exception e) {

		}
	}

	/**
	 * Factoriza el (commit o rollback) + cierre de conexión.
	 * 
	 * @param c
	 * @param commitOrRollback
	 */
	public static void close(Connection c, boolean commitOrRollback) {
		if (c != null) {
			try {
				if (commitOrRollback) {
					c.commit();
				} else {
					c.rollback();
				}

			} catch (SQLException e) {
				logger.error(e);
			}
			try {
				c.close();
			} catch (SQLException sqle) {
				logger.warn(sqle);
			}
		}
	}

	public static void rollback(Connection c) {
		try {
			c.rollback();
		} catch (SQLException sqle) {
			logger.error(sqle);
		}
	}
}
