/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.demo.model;

import com.wind.util.WindEntity;

/**
 *
 * @author cuartz
 */
public class MyContador implements WindEntity{
    public Integer contador=0;
    
    public String windId="miId";
    
    @Override
    public String getWindId() {
        return windId;
    }

    @Override
    public void setWindId(String windId) {
        this.windId=windId;
    }
}
