package com.ucc.dc.models;

public class ServerStack {
	private float temperature;
	private int stackId;
	
	public ServerStack() {
		super();
	}

	public ServerStack(int stackId, float temperature) {
		super();
		this.stackId = stackId;
		this.temperature = temperature;
	}
	
	public int getStackId() {
		return stackId;
	}
	public void setStackId(int stackId) {
		this.stackId = stackId;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	
	
	@Override
	public String toString() {
		return "ServerStack [stackId=" + stackId + ", temperature=" + temperature + "]";
	}

}
