package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public enum DB {
	instance;
	
	private Connection conn;
	
	private DB() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("jdbc/ProgIngSw");
			conn = ds.getConnection();

		} catch (Exception e) {
			conn = null;
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public Statement createStatement() throws SQLException {
		return conn.createStatement();
	}
}
