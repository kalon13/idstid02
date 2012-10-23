package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public enum DB {
	instance;
	
	private Connection aConn;
	
	private DB() {
		try {
//			String url = "jdbc:mysql://127.0.0.1:3306/ProgIngSw";
//			Class.forName ("com.mysql.jdbc.Driver").newInstance();
//	        aConn = DriverManager.getConnection (url, "root", "root");
			
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("jdbc/ProgIngSw");
			aConn = ds.getConnection();

		} catch (Exception e) {
			aConn = null;
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return aConn;
	}
}
