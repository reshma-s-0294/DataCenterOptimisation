package com.ucc.dc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ucc.dc.models.Task;

public class TaskDao {
	
	DBManager dbManager = new DBManager();
	Connection connection = dbManager.getConnection();
	
	public void insertTask(Task task) {
		String query = "insert into task(task_name, arrival_time, deadline, server_id) values(?,?,?,?)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, task.getTaskName());
			preparedStatement.setTimestamp(2, task.getArrivalTime());
			preparedStatement.setInt(3, task.getDeadline());
			preparedStatement.setInt(4, task.getServerId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public ArrayList<Task> getTasks(){
		String query = "select * from task";
		ArrayList<Task> taskList = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				int deadline = resultSet.getInt(3);
				Timestamp arrival = resultSet.getTimestamp(5);
				
				Task task = new Task(name, deadline);
				task.setTaskId(id);
				task.setArrivalTime(arrival);
				taskList.add(task);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return taskList;
		
	}
	
	public void updateTask(Task task) {
		String query = "update task set server_id = ? where task_id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, task.getServerId());
			preparedStatement.setInt(2, task.getTaskId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
