package com.mytwits.service;

import java.sql.Connection;

import org.apache.commons.dbcp.BasicDataSource;

public final class PersistenceService {

	private BasicDataSource bds;

	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://192.168.2.6:3306/mytwits";
	private String login = "root";
	private String password = "";


	private void connect() throws Exception {
		this.bds = new BasicDataSource();
		this.bds.setDriverClassName(this.driver);
		this.bds.setUsername(this.login);
		this.bds.setPassword(this.password);
		this.bds.setUrl(this.url);
	}

	public Connection getConnection() {
		return getConnection(true);
	}

	public Connection getConnection(boolean autoCommit) {
		try {
			Connection c = this.bds.getConnection();
			c.setAutoCommit(autoCommit);
			return c;
		} catch (Throwable e) {
			try {
				connect();
				Connection c = this.bds.getConnection();
				c.setAutoCommit(autoCommit);
				return c;
			} catch (Exception e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}

	private static PersistenceService instance = new PersistenceService();

	private PersistenceService() {
	}

	public static PersistenceService getInstance() {
		return instance;
	}

	/* ***************************************************************** */

}