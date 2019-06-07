package com.ucc.dc.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.ucc.dc.dao.ServerDao;
import com.ucc.dc.dao.ServerStackDao;
import com.ucc.dc.models.Hvac;
import com.ucc.dc.models.Server;
import com.ucc.dc.models.Task;
import com.ucc.dc.models.Task.taskTypes;
import com.ucc.dc.service.TaskService;

public class TaskServiceAssignTaskAllPairsTest {

	private TaskService service;
	private Task task;
	private ArrayList<Server> servers;
	private ServerDao serverDao;
	
	@Before
	public void setUp() throws Exception { 
		service = new TaskService();
		initializeTask();
		serverDao = new ServerDao();
		servers = serverDao.getServers();
		
	}
	
	private void initializeTask() {
		task = new Task("test", 4000, taskTypes.WEB);
	}
	
	/**
	 * Test case for the pair (utilization <= 50, capacity < 100)
	 */
	@Test
	public void inputInRangeTest() {
		ServerStackDao serverStackDao = new ServerStackDao();
		for(Server server : servers) {
			server.setUtilization(10);
			server.setCapacity(20);
			serverDao.decreaseUtilizationAndCapacity(server);
			serverStackDao.resetTemp(server);
		}
		assertTrue(true == service.assignTask(task));
		service.resetUtilizationAndCapacity();
		
	}
	
	/**
	 * Test case for the pair (utilization > 50, capacity < 100)
	 */
	@Test
	public void utilOutOfRange() {
		
		ServerStackDao serverStackDao = new ServerStackDao();
		for(Server server : servers) {
			server.setUtilization(130);
			server.setCapacity(20);
			serverDao.decreaseUtilizationAndCapacity(server);
			serverStackDao.resetTemp(server);
		}
		assertTrue(false == service.assignTask(task));
		service.resetUtilizationAndCapacity();
	}
	
	/**
	 * Test case for the pair (utilization <= 50, capacity >= 100)
	 */
	@Test
	public void capacityOutOfRange() {
		ServerStackDao serverStackDao = new ServerStackDao();
		for(Server server : servers) {
			server.setUtilization(20);
			server.setCapacity(120);
			serverDao.decreaseUtilizationAndCapacity(server);
			serverStackDao.resetTemp(server);
		}
		assertTrue(false == service.assignTask(task));
		service.resetUtilizationAndCapacity();
	}
	
	/**
	 * Test case for the pair (utilization > 50, capacity >= 100)
	 */
	@Test
	public void utilAndCapacityOOR() {
		ServerStackDao serverStackDao = new ServerStackDao();
		for(Server server : servers) {
			server.setUtilization(120);
			server.setCapacity(120);
			serverDao.decreaseUtilizationAndCapacity(server);
			serverStackDao.resetTemp(server);
		}
		assertTrue(false == service.assignTask(task));
		service.resetUtilizationAndCapacity();
	}
	
	/**
	 * Test case for the pair (utilization <= 50, temperature < 50)
	 */
	@Test
	public void tempAndUtilInRange() {
		ServerStackDao serverStackDao = new ServerStackDao();
		Server firstServer = servers.get(0);
		serverStackDao.turnOffHVAC(firstServer);
		for(Server server : servers) {
			server.setUtilization(20);
			serverDao.decreaseUtilizationAndCapacity(server);
			serverStackDao.resetTemp(server);
		}
		assertTrue(true == service.assignTask(task));
		Hvac hvac = serverStackDao.getHvacStatus(firstServer);
		assertTrue(false == hvac.isStatus());
		service.resetUtilizationAndCapacity();
		
	}
	
	/**
	 * Test case for the pair (utilization <= 50, temperature >= 50)
	 */
	@Test
	public void tempNotInRange() {
		ServerStackDao serverStackDao = new ServerStackDao();
		for(Server server : servers) {
			server.setUtilization(20);
			serverDao.decreaseUtilizationAndCapacity(server);
			serverStackDao.setTemp(server);
		}
		assertTrue(true == service.assignTask(task));
		Server server = servers.get(0);
		Hvac hvac = serverStackDao.getHvacStatus(server);
		assertTrue(true == hvac.isStatus());
		service.resetUtilizationAndCapacity();
		
	}
	
	/**
	 * Test case for the pair (utilization > 50, temperature < 50)
	 */
	@Test
	public void utilNotInRange() {
		ServerStackDao serverStackDao = new ServerStackDao();
		for(Server server : servers) {
			server.setUtilization(70);
			serverDao.decreaseUtilizationAndCapacity(server);
			serverStackDao.resetTemp(server);
		}
		assertTrue(false == service.assignTask(task));
		service.resetUtilizationAndCapacity();
		
	}
	
	/**
	 * Test case for the pair (utilization > 50, temperature >= 50)
	 */
	@Test
	public void utilAndTempNotInRange() {
		ServerStackDao serverStackDao = new ServerStackDao();
		for(Server server : servers) {
			server.setUtilization(70);
			serverDao.decreaseUtilizationAndCapacity(server);
			serverStackDao.setTemp(server);
		}
		assertTrue(false == service.assignTask(task));
		service.resetUtilizationAndCapacity();
		
	}
	
	/**
	 * Test case for the pair (capacity < 100, temperature < 50)
	 */
	@Test
	public void tempAndCapacityInRange() {
		
		ServerStackDao serverStackDao = new ServerStackDao();
		for(Server server : servers) {
			server.setCapacity(20);
			serverDao.decreaseUtilizationAndCapacity(server);
			serverStackDao.resetTemp(server);
		}
		assertTrue(true == service.assignTask(task));
		service.resetUtilizationAndCapacity();
		
	}
	
	/**
	 * Test case for the pair (capacity < 100, temperature >= 50)
	 */
	@Test
	public void tempOutOfRange() {
			
		ServerStackDao serverStackDao = new ServerStackDao();
		for(Server server : servers) {
			server.setCapacity(20);
			serverDao.decreaseUtilizationAndCapacity(server);
			serverStackDao.setTemp(server);
		}
		assertTrue(true == service.assignTask(task));
		Server server = servers.get(0);
		Hvac hvac = serverStackDao.getHvacStatus(server);
		assertTrue(true == hvac.isStatus());
		service.resetUtilizationAndCapacity();
			
	}
	
	/**
	 * Test case for the pair (capacity >= 100, temperature < 50)
	 */
	@Test
	public void capacityNotInRange() {
			
		ServerStackDao serverStackDao = new ServerStackDao();
		for(Server server : servers) {
			server.setCapacity(120);
			serverDao.decreaseUtilizationAndCapacity(server);
			serverStackDao.resetTemp(server);
		}
		assertTrue(false == service.assignTask(task));
		service.resetUtilizationAndCapacity();
			
	}

	/**
	 * Test case for the pair (capacity >= 100, temperature >= 50)
	 */
	@Test
	public void tempAndCapacityNotInRange() {
		
		ServerStackDao serverStackDao = new ServerStackDao();
		for(Server server : servers) {
			server.setCapacity(120);
			serverDao.decreaseUtilizationAndCapacity(server);
			serverStackDao.setTemp(server);
		}
		assertTrue(false == service.assignTask(task));
		service.resetUtilizationAndCapacity();
		
	}

}
