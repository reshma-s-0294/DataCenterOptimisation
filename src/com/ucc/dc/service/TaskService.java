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
		System.out.println("this is the number of servers: "+serverList.size());
		for (Server server : serverList) {
			if (server.getUtilization() > 50 || server.getCapacity() >= 100) {
				System.out.println(server.getUtilization() +"*********"+server.getCapacity());
				continue;
			} else {
				System.out.println("in else part!!!!!!!!!!!!!!!!!!!" +server.toString());
				serverDao.increaseUtilizationAndCapacity(server);
				System.out.println("after increasing capacity: ");
				
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
		System.out.println("This is the numbe rof tasks: "+tasks.size());
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
					processedTasks.add(currentTask);
					iterator.remove();
				}
			}
			System.out.println("Tasks assigned");
		}
		System.out.println("*****************"+processedTasks.size());
		return processedTasks;

	}

}
