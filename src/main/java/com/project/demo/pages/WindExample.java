/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.demo.pages;

import com.project.demo.model.ChatMessages;
import com.wind.annotations.WindPage;
import com.wind.annotations.WindProperty;
import java.util.ArrayList;
import java.util.List;
import com.wind.annotations.BofaMethod;

/**
 *
 * @author cuartz
 */
@WindPage(url="example")
public class WindExample {
    
    @WindProperty(code="<p ng-repeat='chatMessage in chatMessages.messages'/>{{chatMessage}}</p>", alive = true)
    private static ChatMessages chatMessages;
    
    @WindProperty(code="<input type='text'ng-model='myNewMessage' /></p><a ng-click='sendMyMessage()'> Send </a>")
    private String myNewMessage;
   	//@WindComponent
        private CounterPage counterPage;
    public void setup(){
        myNewMessage="Write the message";
        if (chatMessages==null){
            chatMessages=new ChatMessages();
            List<String> messages=new ArrayList<>();
            chatMessages.messages=messages;
        }
    }
    
    @BofaMethod(retainedObjects = {"myNewMessage","chatMessages"})
    public void sendMyMessage(){
        chatMessages.messages.add(myNewMessage);
        myNewMessage="";
    }
    
    
}
