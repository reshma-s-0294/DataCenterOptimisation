package com.ucc.dc.service;

import java.util.ArrayList;

import com.ucc.dc.dao.ServerDao;
import com.ucc.dc.dao.TaskDao;
import com.ucc.dc.models.Server;
import com.ucc.dc.models.Task;

public class TaskService {
	
	ServerDao serverDao = new ServerDao();
	TaskDao taskDao = new TaskDao();
	
	public boolean assignTask(Task task) {
		ArrayList<Server> serverList = serverDao.getServers();
		for(Server server : serverList) {
			if(server.getUtilization() > 50 || server.getCapacity() >=100) {
				continue;
			}else {
				serverDao.increaseUtilizationAndCapacity(server);
				task.setServerId(server.getId());
				taskDao.updateTask(task);
				return true;
			}
		}
		return false; 
	}

}
