package com.ucc.dc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ucc.dc.models.Server;

public class ServerDao {
	
	DBManager dbManager = new DBManager();
	Connection connection = dbManager.getConnection();
	
	public ArrayList<Server> getServers(){
		
		ArrayList<Server> serverList = new ArrayList<>();
		
		String query = "select id, utilization, capacity, taskscompleted, stack_id from server";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				int util = resultSet.getInt(2);
				int capacity = resultSet.getInt(3);
				int completed = resultSet.getInt(4);
				int stack_id = resultSet.getInt(5);
				
				Server server = new Server(util, capacity, id, completed, stack_id);
				
				serverList.add(server);
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return serverList;
	}
	
	public void increaseUtilizationAndCapacity(Server server) {
		String query = "update server set utilization = ?, capacity = ?, taskscompleted = ? where id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, server.getUtilization()+10);
			preparedStatement.setInt(2, server.getCapacity()+20);
			preparedStatement.setInt(3, server.getTasksCompleted()+1);
			preparedStatement.setInt(4, server.getId());
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*public ServerStack getServerStack(int serverId) {
		
		String query = "select stackid, temperature from serverstack, server where server.stack_id = serverstack.stackid and server.id=" + serverId;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if(resultSet.next()) {
				int stackId = resultSet.getInt(1);
				float temp = resultSet.getFloat(2);
				
				ServerStack serverStack = new ServerStack(stackId, temp);
				return serverStack;
			}
		} catch(Exception e) {
			// TODO: handle exception
		}
	}*/
}
