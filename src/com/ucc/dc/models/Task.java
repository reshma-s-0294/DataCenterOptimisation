package com.ucc.dc.models;

import java.sql.Date;
import java.sql.Timestamp;

public class Task implements Comparable<Task>{
	
	private int taskId;
	private String taskName;
	private int deadline;
	private int serverId;
	private Timestamp arrivalTime;
	private boolean processed;
	
	public Task(String taskName, int deadline) {
		super();
		this.taskName = taskName;
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
	public Timestamp getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Timestamp arrivalTime) {
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
	
	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", deadline=" + deadline + ", serverId=" + serverId
				+ ", arrivalTime=" + arrivalTime + ", processed=" + processed + "]";
	}

	@Override
	public int compareTo(Task arg0) {
		int compareDeadline = ((Task) arg0).getDeadline();
		
		return this.deadline - compareDeadline;
	
	}
	
	
	
	

}
