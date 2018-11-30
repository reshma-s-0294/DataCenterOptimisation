package com.ucc.dc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ucc.dc.models.Server;
import com.ucc.dc.models.ServerStack;

public class ServerStackDao {
	DBManager dbManager = new DBManager();
	Connection connection = dbManager.getConnection();
	
	public void increaseTemp(Server server) {
		String query = "update serverstack set temparature = temperature+10 where stackid = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, server.getStackId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void turnOnHVAC(Server server) {
		String query = "update hvac set status = 1 where serverstack_id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, server.getStackId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public ServerStack checkTemp(Server server) {
		String query = "select * from serverstack where stackid = ?";
		ServerStack stack = new ServerStack();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, server.getStackId());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int id = resultSet.getInt(1);
				float temp = resultSet.getFloat(2);
				stack.setStackId(id);
				stack.setTemperature(temp);
				return stack;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return stack;
		
	}

}
