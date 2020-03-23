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
public class GlobalCounter implements WindEntity{
    
    public Integer count;
    
    private String windId="0";

    @Override
    public String getWindId() {
        return windId;
    }

    @Override
    public void setWindId(String windId) {
        this.windId=windId;
    }
    
}
