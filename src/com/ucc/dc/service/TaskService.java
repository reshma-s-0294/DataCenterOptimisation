package com.ucc.dc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import com.ucc.dc.dao.ServerDao;
import com.ucc.dc.dao.ServerStackDao;
import com.ucc.dc.dao.TaskDao;
import com.ucc.dc.models.Server;
import com.ucc.dc.models.Task;

public class TaskService {

	ServerDao serverDao = new ServerDao();
	TaskDao taskDao = new TaskDao();
	ServerStackDao serverStackDao = new ServerStackDao();

	public boolean assignTask(Task task) {
		ArrayList<Server> serverList = serverDao.getServers();
		for (Server server : serverList) {
			if (server.getUtilization() > 50 || server.getCapacity() >= 100) {
				continue;
			} else {
				
				serverDao.increaseUtilizationAndCapacity(server);
				
				
				task.setServerId(server.getId());
				taskDao.updateTask(task);
				//taskDao.setProcessedBit(task);
				serverStackDao.increaseTemp(server);
				if (serverStackDao.checkTemp(server).getTemperature() > 50) {
					serverStackDao.turnOnHVAC(server);
				}
				
				return true;
			}
		}
		return false;
	}

	public ArrayList<Task> processTasks() {

		ArrayList<Task> tasks = taskDao.getTasks();
		Collections.sort(tasks);
		ArrayList<Task> unassignedTasks = new ArrayList<>();
		ArrayList<Task> processedTasks = new ArrayList<>();
		if (tasks.size() > 0) {
			Iterator<Task> iterator = tasks.iterator();
			while(iterator.hasNext()) {
				Task currentTask = iterator.next();
				if(!assignTask(currentTask)) {
					unassignedTasks.add(currentTask);
				}else {
					currentTask.setProcessed(true);
					processedTasks.add(currentTask);
					iterator.remove();
				}
			}
			System.out.println("Tasks assigned");
		}
		System.out.println("This is lenght of task in service: "+processedTasks.size());
		return processedTasks;

	}

}
