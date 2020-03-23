package com.project.demo.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import com.project.demo.DAO.StudentDAO;
import com.project.demo.components.DemoHeader;
import com.project.demo.model.Student;
import com.project.demo.service.StudentService;
import com.wind.annotations.WindPage;
import com.wind.annotations.WindParameter;
import com.wind.annotations.WindSpace;
import com.wind.annotations.BofaButton;
import com.wind.annotations.BofaMethod;

@WindPage(url="modifyStudent") //Root page, it content will be launched when the user hit the url path
public class modifyStudentPage {
	
//	@WindSpace//(tag="li",label=this.getName())//simple <div><div>User Name: </div><div>{{userName}}</div></div>
//	private String userName;
	
	@WindSpace(alive=true,editable=true)//simple <div>entity<div>entity.name</div><div>entity.value</div>...</div>
	private Student student;
	@Autowired//spring service
	StudentService studentService;
	
	@WindParameter
	private Integer studentId;
	
	@BofaButton(action="server.save")
	private String saveButton="Save";
	
//	@WindSpace(editable=false)//simple <div>entity<div>entity.name</div><div>entity.value</div>...</div> b
//							  //but the setup method will be executed before to populate its fields
//							  //also the parameters that the method receives are getted from the request parameters,
//	//this is the only diference between a POJO and a windSpace object, its parameters get populated automatically and the setup method gets executed
//	private DemoHeader header;
	
	public void setup(){// method that will be executed before launching the html content, its parameters 
									  // came from the request parameters with the same name
		
		student=studentService.findStudent(studentId);
		//userName="Carlos Bayona";
		
	}
	  @Autowired
	  private SimpMessageSendingOperations messagingTemplate;

		 
	@BofaMethod(retainedObjects="student")
	public void save(){
		//WindService.notifyChange(student);
		 messagingTemplate.convertAndSend("/topic/entity/"+student.getClass().getName()+"/" + student.getWindId(), student);
	}

}