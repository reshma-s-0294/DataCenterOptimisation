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
				continue;
			} else {
				serverDao.increaseUtilizationAndCapacity(server);
				task.setServerId(server.getId());
				taskDao.updateTask(task);
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
		if (tasks.size() > 0) {
			for (Iterator<Task> task =  tasks.iterator() ; task.hasNext();) {
				Task t = task.next();
				if (!assignTask(t)) {
					unassignedTasks.add(t);
				} else {
					task.remove();
				}
			}
			System.out.println("Tasks assigned");
		}
		System.out.println("*****************"+tasks.size());
		return tasks;

	}

}
