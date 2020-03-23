package com.wind.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.type.classreading.MetadataReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.demo.model.Student;
import com.wind.annotations.WindPage;
import com.wind.annotations.WindParameter;
import com.wind.annotations.WindSpace;
import com.wind.annotations.WindProperty;
import com.wind.services.WindService;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.wind.annotations.BofaMethod;

public class HtmlCOdeUtils2 {
	
	public final static String EXTRA_HTML_CODE="EXTRA_BODY_CODE";
	public final static String EXTRA_JAVASCRIPT_CODE="EXTRA_JAVASCRIPT_CODE";
	public final static String CONTROLLERS_CODE="CONTROLLERS_CODE";

	public final static String CONTROLLER_VARIABLES_CODE="CONTROLLER_VARIABLES_CODE";
	//public final static String LIVE_VARIABLES_CODE="LIVE_VARIABLES_CODE";
	public final static String CONTROLLER_METHODS_CODE="CONTROLLER_METHODS_CODE";
    
	public final static String HTML_PAGE_CODE="<!DOCTYPE HTML>"+
"<html lang=\"en \">"+
"  <head>"+
"    <link href=\"http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700\" rel=\"stylesheet\" type=\"text/css\" />"+
"    <link href=\"../assets/style.css\" rel=\"stylesheet\" type=\"text/css\" />"+
"  </head>"+
"  <body ng-app=\"windApp\">"+   
//"  <div ng-controller=\"windCtrl\">"+  
EXTRA_HTML_CODE+

"    <script src=\"../libs/sockjs/sockjs.min.js\" type=\"text/javascript\"></script>"+
"    <script src=\"../libs/stomp-websocket/lib/stomp.min.js\" type=\"text/javascript\"></script>"+
"    <script src=\"../libs/angular/angular.min.js\"></script>"+
"    <script src=\"../libs/lodash/dist/lodash.min.js\"></script>"+
"    <script src=\"../app/app.js\" type=\"text/javascript\"></script>"+
"	 <script src=\"../app/liveService.js\" type=\"text/javascript\"></script>"+
"	 <script type=\"text/javascript\">"+
EXTRA_JAVASCRIPT_CODE+
"	 </script>"+
//"    <script src=\"app/controllers.js\" type=\"text/javascript\"></script>"+
//"    <script src=\"app/services.js\" type=\"text/javascript\"></script>"+
//"  </div>"+
"  </body>"+
"</html>";
	
	
	public final static String CONTROLLER_HTML_CODE=
"  <div ng-controller=\"$1\">$2</div>";
	
	public final static String SCRIPT_PAGE_CODE=
"(function(angular){  "+  
//"	  var app = angular.module('windApp', []);"+
//	"  angular.module(\"windApp.controllers\").controller(\"windCtrl\", function ($scope, EntityService) {   "+

CONTROLLERS_CODE+ 

"	})(angular);";
	
	public final static String CONTROLLER_PAGE_CODE=//nombre del controlador
	     "angular.module(\"windApp.controllers\").controller('$?', function($scope,$http,EntityService){"+
	            
	     CONTROLLER_VARIABLES_CODE+
	    // CONTROLLER_METHODS_CODE+
	      //   "$scope.userInfo=EntityService.initialize(1,'com.project.demo.model.Student').receive();"+ 
	   // LIVE_VARIABLES_CODE+
	     "});";
	//public final static String LIVE_VARIABLES_TEMPLATE= "$scope.userInfo=EntityService.initialize(windId,'com.project.demo.model.Student').receive();";
	
	
	public final static String LIVE_VARIABLES_TEMPLATE= "EntityService.initialize($2,'$3').receive().then(null, null, function(entityMessage) {$scope.$1=entityMessage;});";
	public final static String VARIABLE_PAGE_CODE=//nombre del campo
	        "$scope.$?=";

	public final static String METHOD_PAGE_CODE=//nombre del metodo, pagina, nombre del metodo, parametros
	    "    $scope.$1=function(){"+
	
"$http({"+
"    url: \"/wind/windFWK2/$2/$1\", "+
"    method: \"post\","+
" data:{ METHOD_DATA } "+
//"    params: $?"+
" }).success(function(data){" +
//"var methodResponse=JSON.parse(data);" +
"POPULATION" +
"}).error(function(){alert('error');});"+
	
"};";
	
	public final static String METHOD_DATA="$1 : $scope.$1";
	
	public final static String POPULATION="$scope.$1=data.$1;";

	public static String renderPage(Class<?> pageClass,
			ApplicationContext appContext2, HttpServletRequest request, WindService wService) {
		// TODO Auto-generated method stub

		String htmlCode="";
		try{
		htmlCode=HTML_PAGE_CODE;
		
			DynamicContent content=getDynamicContent(pageClass,appContext2,request,wService);
			String parentHtmlCode=CONTROLLER_HTML_CODE.replace("$1", pageClass.getName());
			content.dynamicHtmlCode=parentHtmlCode.replace("$2", content.dynamicHtmlCode);
			htmlCode=htmlCode.replace(EXTRA_HTML_CODE, content.dynamicHtmlCode);
			
			String jsCode=SCRIPT_PAGE_CODE;
			jsCode=jsCode.replace(CONTROLLERS_CODE, CONTROLLER_PAGE_CODE.replace("$?", pageClass.getName()));
			jsCode=jsCode.replace(CONTROLLER_VARIABLES_CODE, content.dynamicScriptCode);
			
			htmlCode=htmlCode.replace(EXTRA_JAVASCRIPT_CODE, jsCode);
		}catch(WindException e){
			htmlCode=e.getMessage();
			e.printStackTrace();
		}catch(Exception e){
			htmlCode="ERROR RENDERING PAGE";
			e.printStackTrace();
		}
		return htmlCode;
	}
	
	public static String getPageResponse(Class<?> pageClass, String method,
			ApplicationContext appContext2, HttpServletRequest request, String requestString, WindService wService) {
		// TODO Auto-generated method stub
		String content="";
		try{
		
			content=getDynamicContentForCall(pageClass,method,appContext2,requestString, wService);
//			String parentHtmlCode=CONTROLLER_HTML_CODE.replace("$1", pageClass.getName());
//			content.dynamicHtmlCode=parentHtmlCode.replace("$2", content.dynamicHtmlCode);
//			htmlCode=htmlCode.replace(EXTRA_HTML_CODE, content.dynamicHtmlCode);
//			
//			String jsCode=SCRIPT_PAGE_CODE;
//			jsCode=jsCode.replace(CONTROLLERS_CODE, CONTROLLER_PAGE_CODE.replace("$?", pageClass.getName()));
//			jsCode=jsCode.replace(CONTROLLER_VARIABLES_CODE, content.dynamicScriptCode);
//			
//			htmlCode=htmlCode.replace(EXTRA_JAVASCRIPT_CODE, jsCode);
		}catch(WindException e){
			content=e.getMessage();
		
		}catch(Exception e){
			content="ERROR RENDERING PAGE";
		}
		return content;
	}
	
	private static DynamicContent getDynamicContent(Class<?> pageClass,
			ApplicationContext appContext2, HttpServletRequest request, WindService wService) throws WindException {
		// TODO Auto-generated method stub
		Object instancedPage=null;
		
		try{
			instancedPage=pageClass.newInstance();
		}catch(Exception e){
			e.printStackTrace();
			throw new WindException("ERROR TRYING TO INSTANCE PAGE ");
		}
		
		try{
			injectSpringBeans(instancedPage, appContext2);
		}catch(Exception e){
			e.printStackTrace();
			throw new WindException("ERROR TRYING TO INJECT SPRING SERVICES ON "+instancedPage.getClass().getName());
		}
		
		try{
			injectRequestParameters(instancedPage,request);
		}catch(Exception e){
			e.printStackTrace();
			throw new WindException("ERROR TRYING TO INJECT WIND PARAMETERS ON "+instancedPage.getClass().getName());
		}
		
		try{
			executeSetup(instancedPage, wService);
		}catch(Exception e){
			e.printStackTrace();
			throw new WindException("ERROR TRYING TO EXECUTE SETUP METHOD ON "+instancedPage.getClass().getName());
		}
		
		return generaterHtmlCode(instancedPage);
	}
	
	private static String getDynamicContentForCall(Class<?> pageClass, String method,
			ApplicationContext appContext2, String requestString, WindService wService) throws WindException {
		// TODO Auto-generated method stub
		Object instancedPage=null;
		String response="";
		try{
			instancedPage=pageClass.newInstance();
		}catch(Exception e){
			throw new WindException("ERROR TRYING TO INSTANCE PAGE ");
		}
		
		try{
			injectSpringBeans(instancedPage, appContext2);
		}catch(Exception e){
			throw new WindException("ERROR TRYING TO INJECT SPRING SERVICES ON "+instancedPage.getClass().getName());
		}
		
//		try{
//			injectRequestParameters(instancedPage,request);
//		}catch(Exception e){
//			throw new WindException("ERROR TRYING TO INJECT WIND PARAMETERS ON "+instancedPage.getClass().getName());
//		}
		//List<Object> retainedObjects=new ArrayList<>();
		
		try{
			response=executeMethod(instancedPage, method, requestString, wService);
		}catch(Exception e){
			e.printStackTrace();
			throw new WindException("ERROR TRYING TO EXECUTE SETUP METHOD ON "+instancedPage.getClass().getName());
		}
		
//		try{
//			retainedObjects=getRetained(instancedPage);
//		}catch(Exception e){
//			throw new WindException("ERROR TRYING TO EXECUTE SETUP METHOD ON "+instancedPage.getClass().getName());
//		}
		
		return response;//return generaterHtmlCode(instancedPage);
	}
	
	public static DynamicContent generaterHtmlCode(Object pageClass) throws WindException{
		//EntityWrapper entityW=new EntityWrapper();// editable=false;
		DynamicContent content=new DynamicContent();
		ObjectMapper mapper = new ObjectMapper();
	    try {
	    	
		for (Field field : pageClass.getClass().getDeclaredFields()){

				if (field.isAnnotationPresent(WindProperty.class)) {
					field.setAccessible(true);
					WindProperty wSAnnotation=field.getAnnotation(WindProperty.class);
					content.dynamicHtmlCode=content.dynamicHtmlCode+wSAnnotation.code();
					
					content.dynamicScriptCode=content.dynamicScriptCode+
							
					VARIABLE_PAGE_CODE.replace("$?", field.getName())
					+
					mapper.writeValueAsString(field.get(pageClass))
					+
					";"
					
					;
					if (wSAnnotation.alive() && WindEntity.class.isAssignableFrom(field.getType())){//field.get(pageClass) instanceof WindEntity){
						try{
							String windId=((WindEntity)field.get(pageClass)).getWindId();
							content.dynamicScriptCode=content.dynamicScriptCode+LIVE_VARIABLES_TEMPLATE.replace("$1", field.getName()).replace("$2", windId).replace("$3", field.getType().toString().replace("class ", ""));
						}catch(Exception e){
							throw new WindException("FIELD "+field.getName()+" WAS NOT INITIALIZED, MAYBE YOU FORGET TO INITIALIZE IT ON THE SETUP METHOD ON PAGE "+pageClass.getClass().getName());
						}
					}
					
				}
		}
		
		content.dynamicScriptCode=content.dynamicScriptCode+generaterJSMethods(pageClass);

		}catch(Exception e){
			e.printStackTrace();
			throw new WindException("ERROR TRYING TO GENERATE HTML CODE FOR PAGE "+pageClass.getClass().getName());
		}
	    
		return content;
	}
	
	public static String generaterJSMethods(Object pageClass) throws WindException{
		String methodCode="";
	    try {
	    	
		for (Method method : pageClass.getClass().getDeclaredMethods()){

				if (method.isAnnotationPresent(BofaMethod.class)) {
					methodCode=methodCode+METHOD_PAGE_CODE;
					method.setAccessible(true);
					BofaMethod wSAnnotation=method.getAnnotation(BofaMethod.class);
					
					methodCode=methodCode.replace("$1", method.getName());
			        Class c =pageClass.getClass();
			        if (c.getAnnotation(WindPage.class) != null) {
			        	WindPage pageAnnottation=(WindPage) c.getAnnotation(WindPage.class);
			        	methodCode=methodCode.replace("$2", pageAnnottation.url());
			        }
			        String parameters=" ";
			        String parametersPopulation=" ";
			        for (String retained:wSAnnotation.retainedObjects()){
			        	parameters=parameters+METHOD_DATA.replace("$1", retained)+",";
			        	parametersPopulation=parametersPopulation+POPULATION.replace("$1", retained);
			        }
			        methodCode=methodCode.replace("METHOD_DATA", parameters.substring(0, parameters.length()-1));
			        methodCode=methodCode.replace("POPULATION", parametersPopulation);
					
					
				}
		}

		}catch(Exception e){
			throw new WindException("ERROR TRYING TO GENERATE JS CODE FOR METHODS ON PAGE "+pageClass.getClass().getName());
		}
	    
		return methodCode;
	}

	private static void executeSetup(Object instancedPage, WindService wService) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            try {
                		Method method=instancedPage.getClass().getMethod("setup");//, param1.class, param2.class, ..);
//
			method.invoke(instancedPage, new Object[]{});
                
                //executeMethod(instancedPage, "setup", null,wService);
            } catch (Exception ex) {
               
            }

		
	}
	
	private static String executeMethod(Object instancedPage, String methodName, String requestString, WindService wService) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, JsonParseException, JsonMappingException, IOException {
		Method method=instancedPage.getClass().getMethod(methodName);//, param1.class, param2.class, ..);
		BofaMethod wm=method.getAnnotation(BofaMethod.class);
		



		ObjectMapper mapper = new ObjectMapper();
                if (requestString!=null){
		for (String retainedObject:wm.retainedObjects()){
			
			
			Field field = instancedPage.getClass().getDeclaredField(retainedObject);
				
				    field.setAccessible(true); 
				    JsonNode actualObj = mapper.readTree(requestString).path(retainedObject);//actualObj.path("count");
				    //actualObj.mapper.
				    		field.set(instancedPage,  mapper.readValue(actualObj.traverse(), field.getType()));//class););
			
//			instanced
//			
//			
//			User user = mapper.readValue(new File("c:\\user.json"), User.class);
//			
//			
//			String myObject=req.getParameter(retainedObject);
//			System.out.println(myObject);//req.getParameterNames()
		}
                }

			method.invoke(instancedPage, new Object[]{});
			String jsonResponse="{ ";
			//"{ ";
			for (String retainedObject:wm.retainedObjects()){
			
				
				Field field=instancedPage.getClass().getDeclaredField(retainedObject);//field.set(instancedPage,  mapper.readValue(actualObj.traverse(), field.getType()));//class););
				field.setAccessible(true);
				jsonResponse=jsonResponse+"\""+field.getName()+"\":"+mapper.writeValueAsString(field.get(instancedPage))+",";
                                WindProperty wSAnnotation=field.getAnnotation(WindProperty.class);
				if (wSAnnotation.alive() && WindEntity.class.isAssignableFrom(field.getType())){//field.get(pageClass) instanceof WindEntity){
					

					wService.notifyChange((WindEntity)field.get(instancedPage));
				}
                        }
			jsonResponse=jsonResponse.substring(0,jsonResponse.length()-1);
			jsonResponse=jsonResponse+"}";
			return jsonResponse;
                        
	}
	
	public static void injectRequestParameters(Object windPage, HttpServletRequest request) throws IllegalArgumentException, IllegalAccessException{
		for (Field field : windPage.getClass().getDeclaredFields()){
			if (field.isAnnotationPresent(WindParameter.class)) {
			    field.setAccessible(true); // You might want to set modifier to public first.
//			    try {
					//Object value = field.get(windPage);
			    	String param=request.getParameter(field.getName());
			    	if (Integer.class.isAssignableFrom(field.getType())) {
			    		
			    		field.set(windPage, Integer.parseInt(param));
					}else{
				    		
				    		field.set(windPage, param);
						
					}
					//windPage.
					//value=context.getBean(field.getType());
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} 
				
			}
		}
	}
	
	public static void injectSpringBeans(Object windPage, ApplicationContext context) throws BeansException, IllegalArgumentException, IllegalAccessException{
		//entity.getClass().cl
		//Class<?> obj = windPage.getClass();
		for (Field field : windPage.getClass().getDeclaredFields()){
			if (field.isAnnotationPresent(Autowired.class)) {
			    field.setAccessible(true); // You might want to set modifier to public first.
//			    try {
					//Object value = field.get(windPage);
					field.set(windPage, context.getBean(field.getType()));
					//windPage.
					//value=context.getBean(field.getType());
//				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} 
				
			}
		}
	}


}
