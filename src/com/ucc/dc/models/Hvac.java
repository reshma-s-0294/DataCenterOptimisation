package com.ucc.dc.models;

public class Hvac {
	
	private int hvacId;
	private int stackId;
	private boolean status;
	
	
	
	public Hvac(int hvacId, int stackId, boolean status) {
		super();
		this.hvacId = hvacId;
		this.stackId = stackId;
		this.status = status;
	}
	public int getHvacId() {
		return hvacId;
	}
	public void setHvacId(int hvacId) {
		this.hvacId = hvacId;
	}
	public int getStackId() {
		return stackId;
	}
	public void setStackId(int stackId) {
		this.stackId = stackId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Hvac [hvacId=" + hvacId + ", stackId=" + stackId + ", status=" + status + "]";
	}
	
	
	

}
