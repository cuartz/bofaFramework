package com.project.demo.pages;

//import com.project.demo.model.ChatMessages;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.project.demo.model.Counter;
//import com.project.demo.model.Student;
//import com.project.demo.service.StudentService;
//import com.wind.annotations.BofaMethod;
import com.wind.annotations.WindPage;
//import com.wind.annotations.WindParameter;
import com.wind.annotations.WindProperty;
import com.wind.annotations.BofaMethod;
//import com.wind.services.WindService;
//import java.util.ArrayList;
//import java.util.List;
@WindPage(url="counter1")
public class CounterPage1 {

	

        @WindProperty(code="<div >{{texto}}</div><button type='button' ng-click='saludame()'>Push the button to say hello</button>",alive=true)
	private String texto;
	
	public void setup(){
               texto="Hi my name is carlos";       
	}
        
        @BofaMethod(retainedObjects={"texto"})
        public void saludame(){
               texto="Hello Carlos!!!!";
                
	}
        
        
//        @BofaMethod(retainedObjects={"myNewMessage","chatMessages"})
//	public Object sendMyMessage(){
//            
//                chatMessages.messages.add(myNewMessage);
//		wService.notifyChange(chatMessages);
//                myNewMessage="";
//		return null;
//	}
//	
//	@BofaMethod(retainedObjects={"count"})
//	public Object incrementCount(){
//		count=count+1;
//		return count;
//	}
//	
//	@BofaMethod(retainedObjects={"liveCounter2"})
//	public Object incrementLiveCount2(){
//		liveCounter2.count=liveCounter2.count+1;
//		wService.notifyChange(liveCounter2);
//		return liveCounter2;
//	}
//	
//	@BofaMethod(retainedObjects={"liveCounter"})
//	public Object incrementLiveCount(){
//		liveCounter.count=liveCounter.count+1;
//		wService.notifyChange(liveCounter);
//		return liveCounter;
//	}
//	
//	@BofaMethod(retainedObjects={"userInfo"})
//	public Object modifyStudent(){
//		studentService.modifyStudentName(userInfo.getId(), userInfo.getName());
//		studentService.modifyStudentAge(userInfo.getId(), userInfo.getAge());
//		return null;
//	}

}
