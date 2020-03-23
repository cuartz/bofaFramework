package com.project.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.demo.DAO.ClassRoomDAO;
import com.project.demo.DAO.StudentDAO;
import com.project.demo.model.ClassRoom;
import com.project.demo.model.Student;
import com.wind.services.WindService;

@Service
public class StudentServiceImpl implements StudentService{
	
	private static List<Student> studentsDatasource=new ArrayList<>();;
	
	static{
		Student carlos= new Student();
		carlos.setAge(30);
		carlos.setId(1);
		carlos.setName("Carlos Abraham Bayona Smythe");
		
		Student other= new Student();
		other.setAge(28);
		other.setId(2);
		other.setName("Pepe Rueda");
		studentsDatasource.add(carlos);
		studentsDatasource.add(other);
	}
	@Autowired
	WindService wService;
	@Autowired
	StudentDAO studentDao;
	@Autowired
	ClassRoomDAO classDao;
	@Transactional
	public Student findStudent(Integer id){
		
//		ClassRoom cr= new ClassRoom();
//		//cr.setId(1);
//		cr.setName("Programacion");
//		classDao.makePersistent(cr);
//		Student student= new Student();
//		//student.setId(1);
//		student.setName("Carlos Bayona");
//		student.setAge(30);
//		student.setClassRoom(cr);
//		studentDao.makePersistent(student);
//		
//		Student student0= new Student();
//		//student0.setId(2);
//		student0.setName("Leonardo");
//		student0.setAge(30);
//		student0.setClassRoom(cr);
//		studentDao.makePersistent(student0);
//		
//		Student student1= new Student();
//		//student1.setId(3);
//		student1.setName("Abraham");
//		student1.setAge(30);
//		student1.setClassRoom(cr);
//		studentDao.makePersistent(student1);
//		
//		Student student2= new Student();
//		//student2.setId(4);
//		student2.setName("Saul");
//		student2.setAge(30);
//		student2.setClassRoom(cr);
//		studentDao.makePersistent(student2);
//		
//		Student student3= new Student();
//		//student3.setId(5);
//		student3.setName("Marco");
//		student3.setAge(30);
//		student3.setClassRoom(cr);
//		studentDao.makePersistent(student3);
		return studentDao.findById(id, false);
		
	}
	
	
	public Student find(Integer id){
		for (Student student:studentsDatasource){
			if (student.getId().equals(id)){
				return student;
			}
		}
		return null;
	}
	
	public Student modifyStudentName(Integer id, String name){
		for (Student student:studentsDatasource){
			if (student.getId().equals(id)){
				student.setName(name);
				wService.notifyChange(student);
				return student;
			}
		}
		return null;
	}
	
	public Student modifyStudentAge(Integer id, Integer age){
		for (Student student:studentsDatasource){
			if (student.getId().equals(id)){
				student.setAge(age);
				wService.notifyChange(student);
				return student;
			}
		}
		return null;
	}

}
