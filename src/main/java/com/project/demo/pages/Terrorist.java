/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.demo.pages;

import com.project.demo.model.ChatMessages;
import com.wind.annotations.WindPage;
import com.wind.annotations.WindParameter;
import com.wind.annotations.WindProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.wind.annotations.BofaMethod;

/**
 *
 * @author cuartz
 */
@WindPage(url="terrorist2")
public class Terrorist {
    
        @WindProperty(code="Jugadores: <br/><p ng-repeat='chatMessage in chatMessages.messages'/>{{chatMessage}}</p>  Jugadores Listos: <br/><p ng-repeat='chatMessage in chatMessages.ready' style='color:green'/>{{chatMessage}}</p>", alive = true)
        private static ChatMessages chatMessages;
    
    	@WindParameter
	private String userP;
        
        @WindProperty
	private String user;
        
        @WindProperty(code="<div ng-show='!chatMessages.ready.length'>Tu eres: <div ng-show='user == chatMessages.terrorist && chatMessages.terrorist !=null && chatMessages.terrorist!=\"\"' style='color:red'>TERRORISTA</div> <div ng-show='user != chatMessages.terrorist  && chatMessages.terrorist !=null && chatMessages.terrorist!=\"\"' style='color:green'>INOCENTE</div> </div><button type='button' ng-click='comienza()'>Presiona el boton para comenzar</button>",alive=true)
	private String texto;
        
//        @WindProperty(alive=true)
//	private boolean ready;
        
        public void setup(){
        user=userP;
        if (chatMessages==null){
            chatMessages=new ChatMessages();
            List<String> messages=new ArrayList<>();
            chatMessages.messages=messages;
                                    List<String> ready=new ArrayList<>();
            chatMessages.ready=ready;
            chatMessages.terrorist="";
        }
        boolean userExists=false;
        for (String currentUser:chatMessages.messages){
            if (currentUser.trim().toLowerCase().equals(user.trim().toLowerCase())){
                userExists=true;
                break;
            }
        }
        if (!userExists){
            chatMessages.messages.add(user);
        }
    }
    Random random=new Random();
    @BofaMethod(retainedObjects = {"user","chatMessages"})
    public void comienza(){
        boolean userReadyExists=false;
        System.out.println("1!!!!!!!!");
        for (String readyUser:chatMessages.ready){
            System.out.println("2!!!!!!!!");
            if (readyUser.trim().toLowerCase().equals(user.trim().toLowerCase())){
                userReadyExists=true;
                System.out.println("3!!!!!!!!");
                break;
                
            }
        }
        System.out.println("4!!!!!!!!");
        if (!userReadyExists){
            System.out.println("5!!!!!!!!");
            chatMessages.ready.add(user);
        }
        if (chatMessages.messages.size()==chatMessages.ready.size()){
            System.out.println("6!!!!!!!!");
            int rand=random.nextInt(chatMessages.messages.size());
            int curr=0;
            for (String readyUser:chatMessages.ready){
                
                System.out.println("7!!!!!!!!");
                if (curr ==rand){
                    chatMessages.terrorist=readyUser;
                }
                curr++;
            }
            System.out.println("8!!!!!!!!");
            List<String> ready=new ArrayList<>();
            chatMessages.ready=ready;
        }
        
    }
    
    
    @BofaMethod(retainedObjects = {"user","chatMessages"})
    public void limpia(){
                    chatMessages=new ChatMessages();
            List<String> messages=new ArrayList<>();
            chatMessages.messages=messages;
                        List<String> ready=new ArrayList<>();
            chatMessages.ready=ready;
            chatMessages.terrorist="";
    }
}
