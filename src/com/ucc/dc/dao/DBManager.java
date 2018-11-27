package com.ucc.dc.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	
public Connection getConnection(){
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dcdb", "root", "root");
		return connection;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		
	}

}
