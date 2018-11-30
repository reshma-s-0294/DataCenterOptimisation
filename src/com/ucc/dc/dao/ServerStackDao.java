package com.ucc.dc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.ucc.dc.models.Hvac;
import com.ucc.dc.models.Server;
import com.ucc.dc.models.ServerStack;

public class ServerStackDao {
	DBManager dbManager = new DBManager();
	Connection connection = dbManager.getConnection();
	
	public void increaseTemp(Server server) {
		String query = "update serverstack set temperature = temperature+10 where stackid = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, server.getStackId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
			// TODO: handle exception
		}
		return stack;
		
	}
	
	public ArrayList<Hvac> getHvacStatus(){
		String query = "select * from hvac";
		ArrayList<Hvac> hvacs = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				int hvacId = resultSet.getInt(1);
				int stackId = resultSet.getInt(2);
				boolean status = resultSet.getBoolean(3);
				Hvac hvac = new Hvac(hvacId, stackId, status);
				hvacs.add(hvac);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return hvacs;
	}

}
