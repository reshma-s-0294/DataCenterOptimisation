package com.ucc.dc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.ucc.dc.models.Server;

public class ServerStackDao {
	DBManager dbManager = new DBManager();
	Connection connection = dbManager.getConnection();
	
	public void increaseTemp(Server server) {
		String query = "update serverstack set temparature = temperature+10 where stackid = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, server.getStackId());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
