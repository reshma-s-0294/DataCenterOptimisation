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
		String query = "insert into task(task_name, arrival_time, deadline, processed) values(?,?,?,?)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, task.getTaskName());
			preparedStatement.setTimestamp(2, task.getArrivalTime());
			preparedStatement.setInt(3, task.getDeadline());
			preparedStatement.setBoolean(4, task.isProcessed());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<Task> getTasks(){
		String query = "select * from task where processed = 0";
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
			System.out.println(e.getMessage());
		}
		return taskList;
		
	}
	
	public void updateTask(Task task) {
		String query = "update task set server_id = ?, processed = ? where task_id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, task.getServerId());
			preparedStatement.setBoolean(2, true);
			preparedStatement.setInt(3, task.getTaskId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteAllTasks() {
		String query = "delete from task";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setProcessedBit(Task task) {
		String query = "update table task set processed = ? where task_id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, task.getTaskId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
