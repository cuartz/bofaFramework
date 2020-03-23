package com.wind.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.project.demo.model.Student;
import com.wind.util.WindEntity;

@Service
public class WindService {
	
	  @Autowired
	  private SimpMessageSendingOperations messagingTemplate;


	  public void notifyChange(WindEntity message) {
		  messagingTemplate.convertAndSend("/topic/entity/"+message.getClass().toString().replace("class ", "")+"/" + message.getWindId(), message);
	    
	  }   

}
