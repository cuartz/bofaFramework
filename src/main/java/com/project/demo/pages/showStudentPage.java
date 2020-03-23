package com.project.demo.pages;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.demo.DAO.StudentDAO;
import com.project.demo.components.DemoHeader;
import com.project.demo.model.Student;
import com.project.demo.service.StudentService;
import com.wind.annotations.WindPage;
import com.wind.annotations.WindParameter;
import com.wind.annotations.WindSpace;

@WindPage(url="showStudent") //Root page, it content will be launched when the user hit the url path
public class showStudentPage{
	
	@WindSpace//(tag="div",label=this.getName())//simple <div><div>User Name: </div><div>{{userName}}</div></div>
	private String userName;
	
	@WindSpace(alive=true,editable=false)//simple <div>entity<div>entity.name</div><div>entity.value</div>...</div>
	private Student student;
	@Autowired//spring service
	StudentService studentService;
	
	@WindParameter
	public Integer studentId;
	
//	@WindSpace(editable=false)//simple <div>entity<div>entity.name</div><div>entity.value</div>...</div> b
//							  //but the setup method will be executed before to populate its fields
//							  //also the parameters that the method receives are getted from the request parameters,
//	//this is the only diference between a POJO and a windSpace object, its parameters get populated automatically and the setup method gets executed
//	private DemoHeader header;
	
	public void setup(){// method that will be executed before launching the html content, its parameters 
									  // came from the request parameters with the same name
		
		student=studentService.findStudent(studentId);
		userName="cbayona";
		
	}
	
/*	public void afterComponents(Long studentId){// method that will be executed before launching the html content, its parameters 
		  // came from the request parameters with the same name
			header.setCompanyName("USAA");

}*/
}
