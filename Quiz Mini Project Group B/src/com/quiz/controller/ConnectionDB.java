package com.quiz.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {
	

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/quiz";
		String userName = "root";
		String password = "root";

		Connection con = DriverManager.getConnection(url, userName, password);

		return con;
	}

}
