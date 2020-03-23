package com.project.demo.components;

import com.wind.annotations.WindObject;

public class AngularObject extends WindObject{
	
	private String value;
	//@Override
	public String toString(){
		return value;
	}
	
	public ChildAngularObject child;
}
