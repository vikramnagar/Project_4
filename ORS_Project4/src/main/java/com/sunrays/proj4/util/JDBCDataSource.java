package com.sunrays.proj4.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sunrays.proj4.exception.ApplicationException;

/**
 * JDBC DataSource is a Data Connection Pool
 * 
* @author Interpreter
 * @version 1.0
 */

public final class JDBCDataSource {

	/**
	 * JDBC Database connection pool ( DCP )
	 */
	private static JDBCDataSource datasource;

	/**
	 * no one other class can instantiate single class
	 */
	private JDBCDataSource() {
	}

	/**
	 *  connection provite 
	 */
	private ComboPooledDataSource cpds = null;

	/**
	 * Create instance of Connection Pool
	 * 
	 * @return
	 */
	public static JDBCDataSource getInstance() {
		if (datasource == null) {

			ResourceBundle rb = ResourceBundle.getBundle("com.sunrays.proj4.bundle.system");

			datasource = new JDBCDataSource();
			//connection provide karti he
		 	datasource.cpds = new ComboPooledDataSource();
			try {
				datasource.cpds.setDriverClass(rb.getString("driver"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			datasource.cpds.setJdbcUrl(rb.getString("url"));
			datasource.cpds.setUser(rb.getString("username"));
			datasource.cpds.setPassword(rb.getString("password"));
			datasource.cpds.setAcquireIncrement(new Integer((String) rb.getString("acquireIncrement")));
			datasource.cpds.setInitialPoolSize(new Integer((String) rb.getString("initialPoolSize")));			
			datasource.cpds.setMaxIdleTime(DataUtility.getInt(rb.getString("timeout")));
			datasource.cpds.setMaxPoolSize(new Integer((String) rb.getString("maxPoolSize")));
			datasource.cpds.setMinPoolSize(new Integer((String) rb.getString("minPoolSize")));
		}
		return datasource; 
	}
 
	
	
	/**
	 * Gets the connection from ComboPooledDataSource
	 * 
	 * @return connection
	 */
	public static Connection getConnection() throws Exception {
		return getInstance().cpds.getConnection();
	}

	/**
	 * Closes a connection                                                    
	 * 
	 * @param connection
	 */
	public static void closeConnection(Connection connection) {
		if (connection != null) 
		{
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Transfer Rollback
	 * @param connection
	 * @throws ApplicationException
	 */
	public static void trnRollback(Connection connection) throws ApplicationException {
		if (connection != null) 
		{
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new ApplicationException(ex.toString());
			}
		}
	}

}
