package com.ucc.dc.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.ucc.dc.dao.ServerDao;
import com.ucc.dc.dao.TaskDao;
import com.ucc.dc.models.Server;
import com.ucc.dc.models.Task;
import com.ucc.dc.models.Task.taskTypes;
import com.ucc.dc.service.TaskService;

public class TaskServiceProcessTaskAllDUPathsTest {

	private TaskService service;
	private Task task;
	private ArrayList<Server> servers;
	private ServerDao serverDao;
	private TaskDao taskDao;
	
	@Before
	public void setUp() throws Exception { 
		service = new TaskService();
		taskDao = new TaskDao();
		serverDao = new ServerDao();
		servers = serverDao.getServers();
	}
	
	private void initializeTask() {
		task = new Task("test", 4000, taskTypes.WEB);
	}
	
	/**
	 * Test processing with a new unprocessed task
	 * Test for du-path (3,(6,t),tasks)
	 */
	@Test
	public void testProcessTasksWithUnprocessedTasks() {
		initializeTask();

		taskDao.insertTask(task);
		ArrayList<Task> proccessedTasks = service.processTasks();
		assertTrue(proccessedTasks.size() != 0);
	}
	
	/**
	 * Test processing without any unprocessed tasks
	 * Test for du-path (3,(6,f),tasks)
	 */
	@Test
	public void testProcessTasksWithoutAnyTasks() {
		ArrayList<Task> tasks = taskDao.getTasks();
		if (!tasks.isEmpty()) {
			//update all to processed
			for (Task task : tasks) {
				taskDao.setProcessedBit(task);
			}
		}
		
		ArrayList<Task> proccessedTasks = service.processTasks();
		assertTrue(proccessedTasks.size() == 0);
	}

	/**
	 * Test processing with a pending task and is unassigned
	 * Test for du-path (9,(10,t),currentTask)
	 */
	@Test
	public void testProcessTasksWithUnassignedPendingTask() {
		
		for(Server server : servers) {
			server.setUtilization(70);
			server.setCapacity(120);
			serverDao.decreaseUtilizationAndCapacity(server);
		}
		
		initializeTask();
		taskDao.insertTask(task);
		ArrayList<Task> proccessedTasks = service.processTasks();
		assertTrue(proccessedTasks.size() == 0);
		
		service.resetUtilizationAndCapacity();
	}

	/**
	 * Test processing with a pending task and is assigned to a server
	 * Test for du-path (9,(10,f),currentTask)
	 */
	@Test
	public void testProcessTasksWithProcessedPendingTask() {
		
		initializeTask();
		taskDao.insertTask(task);
		ArrayList<Task> proccessedTasks = service.processTasks();
		assertTrue(proccessedTasks.size() != 0);
	}
	
	/**
	 * Test processing with a pending task and is unassigned
	 * Test for du-path (9,11,currentTask)
	 */
	@Test
	public void testProcessTasksWithUnprocessedPendingTask() {
		
		for(Server server : servers) {
			server.setUtilization(70);
			server.setCapacity(120);
			serverDao.decreaseUtilizationAndCapacity(server);
		}
		
		initializeTask();
		int taskId = taskDao.insertTask(task);
		ArrayList<Task> proccessedTasks = service.processTasks();
		assertTrue(proccessedTasks.size() == 0);
		
		Task updatedTask = taskDao.getTaskFromId(taskId);
		assertTrue(updatedTask.isProcessed() == false);
		service.resetUtilizationAndCapacity();
	}
	
	/**
	 * Test processing with a new task and check that it is returned in processed tasks
	 * Test for du-path (14,20,processedTasks)
	 */
	@Test
	public void testProcessTasksWithTaskReturnedInProcessed() {
		
		initializeTask();
		int taskId = taskDao.insertTask(task);
		ArrayList<Task> proccessedTasks = service.processTasks();
		assertTrue(proccessedTasks.size() != 0);
		Task updatedTask = taskDao.getTaskFromId(taskId);
		assertTrue(proccessedTasks.contains(updatedTask));
	}
	
	/**
	 * Test processing with a new task and check that it has been marked as processed and has been added in processedTasks
	 * Test for du-path (13,14,currentTask)
	 */
	@Test
	public void testProcessTasksWithTaskMarkedAsProcessed() {
		
		initializeTask();
		int taskId = taskDao.insertTask(task);
		ArrayList<Task> proccessedTasks = service.processTasks();
		assertTrue(proccessedTasks.size() > 0);
		Task updatedTask = taskDao.getTaskFromId(taskId);
		assertTrue(updatedTask.isProcessed() == true);
		assertTrue(proccessedTasks.contains(updatedTask));
	}
}
