package br.com.eventos.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

	public static Connection con;

	public ResultSet executeQuery(String sql, Statement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	public ConnectionFactory() {
		try {
			
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.mariadb.jdbc.Driver");
			String urldb = "jdbc:mariadb://127.0.0.1:54654/eventos?user=azure&password=6#vWHD_$";
			if (con != null) {
				System.out.println("Utilizando coonexão existente");
			} else {
				con = DriverManager.getConnection(urldb);
			}
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
