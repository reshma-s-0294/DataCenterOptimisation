package com.ucc.dc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ucc.dc.models.ServerStack;
import com.ucc.dc.models.Task;
import com.ucc.dc.models.Task.taskTypes;

public class TaskDao {
	
	DBManager dbManager = new DBManager();
	Connection connection = dbManager.getConnection();
	
	public int insertTask(Task task) {
		String query = "insert into task(task_name, arrival_time, deadline, processed, task_type) values(?,?,?,?,?)";
		int id = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, task.getTaskName());
			preparedStatement.setTimestamp(2, task.getArrivalTime());
			preparedStatement.setInt(3, task.getDeadline());
			preparedStatement.setBoolean(4, task.isProcessed());
			preparedStatement.setString(5, task.getType().toString());
			preparedStatement.executeUpdate();
			
			String idQuery = "select task_id from task order by task_id desc limit 1";
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(idQuery);
			while(rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return id;
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
				Task.taskTypes type = Task.taskTypes.valueOf(resultSet.getString(7));
				Task task = new Task(name, deadline, type);
				task.setTaskId(id);
				task.setArrivalTime(arrival);
				taskList.add(task);
				
			}
		} catch (Exception e) {
			System.out.println("+++++++++++++++++"+e.getMessage());
		}
		System.out.println("***************************lenght of tasks in DAO: " +taskList.size());
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

	public Task getTaskFromId(int taskId) {
		String query = "select * from task where task_id = ?";
		Task task = null;
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, taskId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				int deadline = resultSet.getInt(3);
				int serverId = resultSet.getInt(4);
				Timestamp arrivalTime = resultSet.getTimestamp(5);
				boolean processed = resultSet.getBoolean(6);
				Task.taskTypes type = taskTypes.valueOf(resultSet.getString(7));
				task = new Task(name, deadline, type);
				task.setTaskId(id);
				task.setServerId(serverId);
				task.setProcessed(processed);
				task.setArrivalTime(arrivalTime);
				
				return task;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return task;
	}
}
