package com.ucc.dc.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ucc.dc.dao.ServerDao;
import com.ucc.dc.dao.ServerStackDao;
import com.ucc.dc.models.Hvac;
import com.ucc.dc.models.Server;
import com.ucc.dc.models.ServerStack;
import com.ucc.dc.models.Task;
import com.ucc.dc.models.Task.taskTypes;
import com.ucc.dc.service.TaskService;

public class TaskServiceAssignTaskAllUsesTest extends Mockito{
	
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
	 * Test to confirm that utilization and capacity values not being exceeded.
	 * Test for all-uses path (3,(4,f),server)
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
	 * Test for the all-uses path (3, (4,t), server)
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
	
	/**
	 * Test to check if server of task has been updated
	 * Test for all-uses path (3,8,server)
	 */
	@Test
	public void testIfTaskIsUpdated() {
		initializeTask();
		assertTrue(task.getServerId() == 0);
		
		//check if task is assigned
		assertTrue(true == service.assignTask(task));
		
		assertTrue(task.getServerId() != 0);
	}
	
	/**
	 * Test to check if server utilization has increased
	 * Test for all-uses path (3,7,server)
	 * 
	 */
	@Test
	public void testIfUtilizationIsUpdated() {
		initializeTask();
		
		//check if task is assigned
		assertTrue(true == service.assignTask(task));
		Server oldServerStatus = null, newServerStatus = null;
		for (Server server : servers) {
			if (server.getId() == task.getServerId()) {
				oldServerStatus = server;
				break;
			}
		}
		ArrayList<Server> updatedServers = serverDao.getServers();
		for (Server server : updatedServers) {
			if (server.getId() == task.getServerId()) {
				newServerStatus = server;
				break;
			}
		}
		assertTrue(oldServerStatus.getUtilization() < newServerStatus.getUtilization());
	}
	
	/**
	 * Test to check if server stack temperature has increased
	 * Test for all-uses path (3,10,server)
	 * 
	 */
	@Test
	public void testIfTempIsUpdated() {
		initializeTask();
		Server server = servers.get(0);
		ServerStackDao serverStackDao = new ServerStackDao();
		ServerStack oldStackData = serverStackDao.checkTemp(server);
		
		//check if task is assigned
		assertTrue(true == service.assignTask(task));
		
		ServerStack newStackData = serverStackDao.checkTemp(server);
		assertTrue(oldStackData.getTemperature() < newStackData.getTemperature());
	}
	
	/**
	 * Test to check of HVAC is turned on when temperature exceeds threshold
	 * Test for all-uses path (3,(11,t),server)
	 */
	@Test
	public void testIfHVACTurnsOn() {
		initializeTask();
		
		// Turn off HVAC initially
		Server server = servers.get(0);
		ServerStackDao serverStackDao = new ServerStackDao();
		serverStackDao.resetTemp(server);
		serverStackDao.turnOffHVAC(server);
		Hvac hvac = serverStackDao.getHvacStatus(server);
		assertTrue(false == hvac.isStatus());
		
		//increase temp of serverstack to 50
		for (int i=0; i<4; i++)
			serverStackDao.increaseTemp(server);
		
		//check if task is assigned
		assertTrue(true == service.assignTask(task));
		
		hvac = serverStackDao.getHvacStatus(server);
		assertTrue(true == hvac.isStatus());
	}

	/**
	 * Test to check that HVAC is not turned on when temperature is less than threshold
	 * Test for all-uses path (3,(11,f),server)
	 * 
	 */
	@Test
	public void testIfHVACDoesNotTurnOn() {
		initializeTask();
		
		// Turn off HVAC initially
		Server server = servers.get(0);
		ServerStackDao serverStackDao = new ServerStackDao();
		serverStackDao.resetTemp(server);
		serverStackDao.turnOffHVAC(server);
		Hvac hvac = serverStackDao.getHvacStatus(server);
		assertTrue(false == hvac.isStatus());
		
		//check if task is assigned
		assertTrue(true == service.assignTask(task));
		
		hvac = serverStackDao.getHvacStatus(server);
		assertTrue(false == hvac.isStatus());
	}
	

}
