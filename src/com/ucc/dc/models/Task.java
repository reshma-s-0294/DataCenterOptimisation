package com.ucc.dc.models;

import java.sql.Date;

public class Task {
	
	private int taskId;
	private String taskName;
	private Date arrivalTime;
	private int deadline;
	private int serverId;
	
	public Task(String taskName, Date arrivalTime, int deadline) {
		super();
		this.taskName = taskName;
		this.arrivalTime = arrivalTime;
		this.deadline = deadline;
	}
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getDeadline() {
		return deadline;
	}
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", arrivalTime=" + arrivalTime + ", deadline="
				+ deadline + ", serverId=" + serverId + "]";
	}
	
	
	
	

}
