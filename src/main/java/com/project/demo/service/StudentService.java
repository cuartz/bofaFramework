package com.project.demo.service;

import com.project.demo.model.Student;

public interface StudentService {
	
	public Student findStudent(Integer id);
	
	public Student find(Integer id);
	
	public Student modifyStudentName(Integer id, String name);
	
	public Student modifyStudentAge(Integer id, Integer age);

}
