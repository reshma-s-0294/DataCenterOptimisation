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
	private taskTypes type;
	
	
	public static enum taskTypes{
		WEB, DATA, COMPUTATIONAL, NETWORK
	}
	
	public Task(String taskName, int deadline, taskTypes type) {
		super();
		this.taskName = taskName;
		this.deadline = deadline;
		this.type = type;
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

	

	public taskTypes getType() {
		return type;
	}

	public void setType(taskTypes type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", deadline=" + deadline + ", serverId=" + serverId
				+ ", arrivalTime=" + arrivalTime + ", processed=" + processed + ", type=" + type + "]";
	}

	@Override
	public int compareTo(Task arg0) {
		int compareDeadline = ((Task) arg0).getDeadline();
		
		return this.deadline - compareDeadline;
	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
		result = prime * result + deadline;
		result = prime * result + (processed ? 1231 : 1237);
		result = prime * result + serverId;
		result = prime * result + taskId;
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (arrivalTime == null) {
			if (other.arrivalTime != null)
				return false;
		} else if (!arrivalTime.equals(other.arrivalTime))
			return false;
		if (deadline != other.deadline)
			return false;
		if (processed != other.processed)
			return false;
		if (serverId != other.serverId)
			return false;
		if (taskId != other.taskId)
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
	
	

}
