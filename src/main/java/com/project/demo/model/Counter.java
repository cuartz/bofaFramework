package com.project.demo.model;

import com.wind.util.WindEntity;

public class Counter implements WindEntity {
	
	public Integer count;
	
	private String windId;
	
	public Counter(String windId){
		this.windId=windId;
	}
	
	public Counter(){
		this.windId="0";
	}

	@Override
	public String getWindId() {
		// TODO Auto-generated method stub
		return windId;
	}

	@Override
	public void setWindId(String windId) {
		this.windId=windId;
	}

}
