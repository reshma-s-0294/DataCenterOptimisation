package com.ucc.dc.models;

public class TaskResponse {
	
	private int serverId;
	private boolean isReject;
	
	public TaskResponse() {
		serverId = -1;
		isReject = true;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public boolean isReject() {
		return isReject;
	}
	public void setReject(boolean isReject) {
		this.isReject = isReject;
	}
	@Override
	public String toString() {
		return "TaskResponse {\"serverId\" :" + serverId + ", \"isReject\" : " + isReject + "}";
	}
	
	

}
