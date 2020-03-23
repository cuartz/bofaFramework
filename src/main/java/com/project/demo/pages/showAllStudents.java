package com.project.demo.pages;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.demo.DAO.StudentDAO;
import com.project.demo.components.AngularObject;
import com.project.demo.components.StudentSpace;
import com.project.demo.model.Student;
import com.wind.annotations.WindObject;
import com.wind.annotations.WindPage;
import com.wind.annotations.WindSpace;
import com.wind.annotations.BofaButton;
import com.wind.annotations.BofaListSpace;
import com.wind.annotations.BofaMethod;
@WindPage
public class showAllStudents {
	@BofaListSpace(listTag="ul", tag="li",selectedItem="selectedStudent",selectAttribute="select",selectTag="a")
	private List<StudentSpace> studentSpaces;
	
//	@WindObject(watch="server.showStudent")//client.angularMethod
//	private StudentSpace selectedStudent;
	
	@BofaButton(action="server.clear")
	private String clearButton="clear students";
	
	@Autowired
	StudentDAO studentDao;
	
//	@WindSpace(valuesList = { "" }, parameters = { this.newType })
//	public AngularObject newType;
	
	public void setup(){
		List<Student> students=studentDao.findAll();
		studentSpaces= new ArrayList<>();
		for (Student student:students){
			StudentSpace studentSpace= new StudentSpace();
			studentSpace.name=student.getName();
			studentSpace.select="Select";
		}
	}
	
	@BofaMethod(retainedObjects="selectedStudent")
	public Object showStudent(){
		showStudentPage showPage= new showStudentPage();
		//showPage.studentId=selectedStudent.id;
		return showPage;
	}
	
	@BofaMethod(retainedObjects="studentSpaces")
	public Object clear(){
		studentSpaces.clear();
		return studentSpaces;
	}
}
