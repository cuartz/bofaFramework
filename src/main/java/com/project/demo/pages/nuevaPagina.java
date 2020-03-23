/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.demo.pages;

import com.project.demo.model.Counter;
import com.project.demo.model.MyContador;
import com.wind.annotations.WindPage;
import com.wind.annotations.WindProperty;
import com.wind.services.WindService;
import org.springframework.beans.factory.annotation.Autowired;
import com.wind.annotations.BofaMethod;

/**
 *
 * @author cuartz
 */
@WindPage(url="miPagina")
public class nuevaPagina {
    
    	@Autowired
	private WindService wService;
    
    @WindProperty(code="<a ng-click='incrementa()' >{{variable.count}}</a> texto",alive=true)
    public Counter variable;
    public void setup(){
        variable = new Counter();
        variable.count=0;
        //contador=0;
    }
    @BofaMethod(retainedObjects={"variable"})
    public void incrementa(){
        variable.count=variable.count+1;
        //wService.notifyChange(variable);
    }
    
    
}
