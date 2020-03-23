package com.project.demo.components;

import com.wind.annotations.WindSpace;

@WindSpace
public class DemoHeader{
	
	@WindSpace
	private String companyName;
	
	@WindSpace
	private String todaysDate;
	
	
	
	public void setup(){// method that will be executed before launching the html content, its parameters 
		  // came from the request parameters with the same name
		companyName="USAA";
		
		todaysDate= new java.util.Date().toString();
}
	
	
	//optional content like getters, setters or private methods that not belong to the framework
	public void setCompanyName(String companyName){
		this.companyName=companyName;
	}

}
