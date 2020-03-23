/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.demo.pages;

import com.project.demo.model.GlobalCounter;
import com.wind.annotations.WindPage;
import com.wind.annotations.WindProperty;

/**
 *
 * @author cuartz
 */
@WindPage(url="new")
public class NewClass {
        @WindProperty(code="<a ng-href='#' ng-click='incrementCount()'>{{count}}</a> times clicked")
    private Integer count;
    
    @WindProperty(code="<a ng-href='#' ng-click='incrementGlobalCount()'>{{globalCounter.count}}</a> global times clicked")
    private GlobalCounter globalCounter;
    
    public void setup(){
        count=0;
       
        globalCounter= new GlobalCounter();
        globalCounter.count=0;
    }
}

