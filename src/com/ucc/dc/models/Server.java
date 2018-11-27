package com.ucc.dc.models;

public class Server {
	
	private int utilization;
	private int capacity;
	private int id;
	private int tasksCompleted;
	private int stackId;
	
	public Server(int utilization, int capacity, int id, int tasksCompleted, int stackId) {
		super();
		this.utilization = utilization;
		this.capacity = capacity;
		this.id = id;
		this.tasksCompleted = tasksCompleted;
		this.stackId = stackId;
	}
	
	public int getUtilization() {
		return utilization;
	}
	public void setUtilization(int utilization) {
		this.utilization = utilization;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTasksCompleted() {
		return tasksCompleted;
	}
	public void setTasksCompleted(int tasksCompleted) {
		this.tasksCompleted = tasksCompleted;
	}
	
	public int getStackId() {
		return stackId;
	}

	public void setStackId(int stackId) {
		this.stackId = stackId;
	}

	@Override
	public String toString() {
		return "Server [utilization=" + utilization + ", capacity=" + capacity + ", id=" + id + ", tasksCompleted="
				+ tasksCompleted + ", stackId=" + stackId + "]";
	}

	
	
	

}
