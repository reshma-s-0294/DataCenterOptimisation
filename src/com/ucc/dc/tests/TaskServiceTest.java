package com.ucc.dc.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ucc.dc.dao.ServerDao;
import com.ucc.dc.models.Server;
import com.ucc.dc.models.Task;
import com.ucc.dc.models.Task.taskTypes;
import com.ucc.dc.service.TaskService;

public class TaskServiceTest extends Mockito{
	
	private TaskService service;
	private Task task;
	private ArrayList<Server> servers;
	private ServerDao serverDao;
	
	@Before
	public void setUp() throws Exception { 
		service = new TaskService();
		task = new Task("test", 4000, taskTypes.WEB);
		serverDao = new ServerDao();
		servers = serverDao.getServers();
		
	}

	/**
	 * Test to confirm that utilization and capacity values not being exceeded.
	 * 
	 * by
	 * Adarsh Bhat
	 */
	@Test
	public void testAssignTaskNotExceed(){
		
		boolean expected = true;
		
		assertTrue(expected == service.assignTask(task));
		
	}
	/**
	 * Test to confirm server doesnt get assigned and the method return false when utilization or capacity is over set limit
	 * 
	 * by
	 * Adarsh Bhat
	 */
	@Test
	public void testAssignTaskExceed(){
		
		
		boolean expected = false;
		
		for(Server server : servers) {
			server.setUtilization(70);
			server.setCapacity(120);
			serverDao.decreaseUtilizationAndCapacity(server);
		}
		assertTrue(expected == service.assignTask(task));
		service.resetUtilizationAndCapacity();
		
	}
	
	@After
	public void atEnd() throws Exception{
		for(Server server : servers) {
			server.setCapacity(10);
			server.setUtilization(10);
		}
	}

}
