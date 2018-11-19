package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

	public Connection con;

	public ResultSet executeQuery(String sql, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	public ConnectionFactory() {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.mariadb.jdbc.Driver");
			String urldb = "jdbc:mariadb://sql10.freemysqlhosting.net/sql10264413?user=sql10264413&password=cvbBJqBPmf";
			con = DriverManager.getConnection(urldb);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Connection getCon() {
		return con;
	}
	
	
}
