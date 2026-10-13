package in.co.rays.proj4.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//SingleTonClass & factory design pattern

//Advantages:- 
//1. Provide connection re-useability
//2. Provide reliable connection with database
//3. Provide maximum connection limitation with database

public final class JDBCDataSource {

	private static JDBCDataSource jdbc = null;
	private ComboPooledDataSource cpds = null;

	private ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.proj4.bundle.System");

	private JDBCDataSource() {

		// ComboPoolDataSource class provide reliable connection(connection+limitations)
		// CPDS is a predefine class
		// It provide four properties

		cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass(rb.getString("driver"));
			cpds.setJdbcUrl(rb.getString("url"));
			cpds.setUser(rb.getString("username"));
			cpds.setPassword(rb.getString("password"));
			cpds.setMaxPoolSize(30); // 1 property
			cpds.setMinPoolSize(10); // 2 property
			cpds.setAcquireIncrement(5); // 3 property
			cpds.setInitialPoolSize(10); // 4 property
		} catch (Exception e) {
			e.getMessage();
		}
	}

	private static JDBCDataSource getInstance() {

		if (jdbc == null) {
			jdbc = new JDBCDataSource();
			return jdbc;
		}

		return jdbc;
	}

	public static Connection getConnection() { // factory design pattern
		try {
			getInstance();
			return getInstance().cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void trnRollBack(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
