/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.demo.pages;

import com.project.demo.model.ChatMessages;
import com.project.demo.model.Player;
import com.project.demo.model.TerroristModel;
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
@WindPage(url="terroristn")
public class Terrorist2 {
    
        @WindProperty(code=
                "Jugadores: <br/><p><div  ng-repeat='thisPlayer in terroristModel.users'>"
                        + "<li ng-show='thisPlayer.ready' style='color:green' >{{thisPlayer.user}}  Puntuacion: {{thisPlayer.score}} </li>"
                        + "<li ng-show='!thisPlayer.ready'                    >{{thisPlayer.user}}  Puntuacion: {{thisPlayer.score}} </li>"
                              + "</div></p>", alive = true)
        private static TerroristModel terroristModel;
    
    	@WindParameter
	private String userP;
        
        @WindProperty(code="<div ng-show='terroristModel.allReady'> <div ng-show='me==terroristModel.terrorist.user' style='color:red'>ERES TERRORISTA</div> <div ng-show='me!=terroristModel.terrorist.user' style='color:green'>ERES INOCENTE</div> </div><button type='button' ng-click='comienza()' >Presiona el boton para comenzar</button>",alive=true)
	private String me;
        
        
        @WindProperty(code="<div ng-show='me ==\"Bayona\"'> {{texto}}"
                + "<br/><div><button type='button' ng-click='terrorista()'>GANO TERRORISTAS</button></div>"
                + "<br/><div><button type='button' ng-click='inocentes()'>GANO INOCENTES</button>"
                + "<br/><br/><div><button type='button' ng-click='limpia()'>LIMPIA</button>",alive=true)
	private String texto;
        
        @BofaMethod(retainedObjects = {"terroristModel"})
        public void setup(){
            me=userP;
            //texto="false";
        if (terroristModel==null){
            terroristModel=new TerroristModel();
            List<Player> users=new ArrayList<>();
            terroristModel.users=users;
        }
        boolean userExists=false;
        for (Player currentUser:terroristModel.users){
            if (currentUser.user.trim().toLowerCase().equals(userP.trim().toLowerCase())){
                userExists=true;
                break;
            }
        }
        if (!userExists){
            Player player= new Player();
            player.user=userP;
            terroristModel.users.add(player);
            terroristModel.allReady=false;
        }
    }
    Random random=new Random();
    @BofaMethod(retainedObjects = {"me","terroristModel","texto"})
    public void comienza(){
        terroristModel.allReady=true;
        //texto="false";
//        System.out.println("1!!!!!!!!");
        for (Player readyUser:terroristModel.users){
            if (readyUser.user.trim().toLowerCase().equals(me.trim().toLowerCase())){
                readyUser.ready=true;
            }
            System.out.println("2!!!!!!!!");
            if (!readyUser.ready){
                terroristModel.allReady=false;
                System.out.println("3!!!!!!!!");
            }
        }
//        System.out.println("4!!!!!!!!");
//        if (!userReadyExists){
//            System.out.println("5!!!!!!!!");
//            chatMessages.ready.add(user);
//        }
        if (terroristModel.allReady){
            //texto="true";
            System.out.println("6!!!!!!!!");
            int rand=random.nextInt(terroristModel.users.size());
            Player terrorist=terroristModel.users.get(rand);
            terrorist.terrorist=true;
            terroristModel.terrorist=terrorist;
            for (Player readyUser:terroristModel.users){
                readyUser.ready=false;
            }
        }
    }
    
    @BofaMethod(retainedObjects = {"terroristModel"})
    public void terrorista(){
        for (Player readyUser:terroristModel.users){
            if (readyUser.user.trim().toLowerCase().equals(terroristModel.terrorist.user.trim().toLowerCase())){
                readyUser.score=readyUser.score+(terroristModel.users.size()/2);
                break;
            }
        }
    }
    
    @BofaMethod(retainedObjects = {"terroristModel"})
    public void inocentes(){
        for (Player readyUser:terroristModel.users){
            if (!readyUser.user.trim().toLowerCase().equals(terroristModel.terrorist.user.trim().toLowerCase())){
                readyUser.score++;
            }
        }
    }
    
        @BofaMethod(retainedObjects = {"terroristModel"})
    public void limpia(){
                    terroristModel=new TerroristModel();
            List<Player> users=new ArrayList<>();
            terroristModel.users=users;
    }
    
    
//    @BofaMethod(retainedObjects = {"user","chatMessages"})
//    public void limpia(){
//                    chatMessages=new ChatMessages();
//            List<String> messages=new ArrayList<>();
//            chatMessages.messages=messages;
//                        List<String> ready=new ArrayList<>();
//            chatMessages.ready=ready;
//            chatMessages.terrorist="";
//    }
}
