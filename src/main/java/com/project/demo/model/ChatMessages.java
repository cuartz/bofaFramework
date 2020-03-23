/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.demo.model;

import com.wind.util.WindEntity;
import java.util.List;

/**
 *
 * @author cuartz
 */
public class ChatMessages implements WindEntity{

    private String windId;
       public List <String> messages;
       
       public List <String> ready;
       
       public String terrorist;
       
    @Override
    public String getWindId() {
        return "0";
    }

    @Override
    public void setWindId(String windId) {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
