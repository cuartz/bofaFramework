package com.wind.websocket;

import java.util.Date;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.project.demo.model.Student;


@Controller
@RequestMapping("/")
public class SocketController {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private SimpMessageSendingOperations messagingTemplate;

  @MessageMapping("/entities")
  //@SendTo("/topic/entity")
  public void sendMessage(Student message) {
	  messagingTemplate.convertAndSend("/topic/entity/com.project.demo.model.Student/" + message.getWindId(), message);
    
  }
}