/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.demo.pages;

import com.project.demo.model.GlobalCounter;
import com.wind.annotations.WindPage;
import com.wind.annotations.WindProperty;
import com.wind.annotations.BofaMethod;

/**
 *
 * @author cuartz
 */
@WindPage(url="firstpage1")
public class WindFirstPage {
    
    @WindProperty(code="<a ng-href='#' ng-click='incrementCount()'>{{count}}</a> times clicked")
    private Integer count;
    
    @WindProperty(code="<a ng-href='#' ng-click='incrementGlobalCount()'>{{globalCounter.count}}</a> global times clicked", alive=true)
    private GlobalCounter globalCounter;
    
    public void setup(){
        count=0;
       
        globalCounter= new GlobalCounter();
        globalCounter.count=0;
    }
    
    @BofaMethod(retainedObjects={"count"})
    public void incrementCount(){
        count=count+1;
    }
    
    @BofaMethod(retainedObjects={"globalCounter"})
    public void incrementGlobalCount(){
        globalCounter.count=globalCounter.count+1;
    }
    
}
