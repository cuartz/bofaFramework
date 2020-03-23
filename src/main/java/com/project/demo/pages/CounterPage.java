package com.project.demo.pages;

import com.project.demo.model.ChatMessages;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.demo.model.Counter;
import com.project.demo.model.GlobalCounter;
import com.project.demo.model.Student;
import com.project.demo.service.StudentService;
import com.wind.annotations.WindPage;
import com.wind.annotations.WindParameter;
import com.wind.annotations.WindProperty;
import com.wind.services.WindService;
import java.util.ArrayList;
import java.util.List;
import com.wind.annotations.BofaMethod;

@WindPage(url="counter")
public class CounterPage {
	
//	@Autowired
//	private WindService wService;
    
	
	@Autowired
	private StudentService studentService;

	@WindProperty(code="<p ng-repeat=\"(key,value) in userInfo\">{{key}}:<input type=\"text\" ng-model=\"userInfo[key]\" ng-change=\"modifyStudent()\"/></p><a ng-click='modifyStudent()'>Save</a>",alive=true)
	private Student userInfo;
        
    @WindProperty(code="<div>{{globalCounter.count}}</div> global times clicked", alive=true)
    private GlobalCounter globalCounter;
	
	@WindParameter
	private Integer studentId;
	
        @BofaMethod()
	public void setup(){
                    globalCounter= new GlobalCounter();
        globalCounter.count=0;
		userInfo=studentService.find(studentId);
//		count=0;
//		liveCounter = new Counter("0");
//		liveCounter.count=0;
//		liveCounter2 = new Counter("1");
//		liveCounter2.count=0;
//                chatMessages = new ChatMessages();
//                
//                List<String> messages=new ArrayList<>();
//                messages.add("Has Entrado al canal de chat");
//                chatMessages.messages=messages;
//                
//                myNewMessage="Escribe tu mensaje";
                
	}
        
//

	@BofaMethod(retainedObjects={"userInfo"})
	public Object modifyStudent(){
		studentService.modifyStudentName(userInfo.getId(), userInfo.getName());
		studentService.modifyStudentAge(userInfo.getId(), userInfo.getAge());
		return null;
	}

        
        
        
 //               @BofaMethod(retainedObjects={"myNewMessage","chatMessages"})
//	public Object sendMyMessage(){
//            
//                chatMessages.messages.add(myNewMessage);
//		wService.notifyChange(chatMessages);
//                myNewMessage="";
//		return null;
//	}
        
        //	
//	@WindProperty(code="<a ng-href='#' ng-click='incrementCount()' >{{count}}</a> times clicked")
//	private Integer count;
//	
//	@WindProperty(code="<br/><a ng-href='#' ng-click='incrementLiveCount()' >{{liveCounter.count}}</a> live times clicked",alive=true)
//	private Counter liveCounter;
//	
//	@WindProperty(code="<br/><a ng-href='#' ng-click='incrementLiveCount2()' >{{liveCounter2.count}}</a> live times clicked2",alive=true)
//	private Counter liveCounter2;
//	
        
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
        
//                @WindProperty(code="<p ng-repeat=\"chatMessage in chatMessages.messages\">{{chatMessage}}</p>",alive=true)
//	private ChatMessages chatMessages;
//	
//
//        @WindProperty(code="<input type=\"text\" ng-model=\"myNewMessage\"/></p><a ng-click='sendMyMessage()'>Send</a>")
//	private String myNewMessage;
}
