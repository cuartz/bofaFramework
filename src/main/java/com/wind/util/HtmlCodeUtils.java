package com.wind.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.demo.model.Student;
import com.wind.annotations.WindParameter;
import com.wind.annotations.WindSpace;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import com.wind.annotations.BofaIgnore;

public class HtmlCodeUtils {
	
	public static String generateWebPageResponseCode(Class<?> Page, String method, ApplicationContext appContext2, HttpServletRequest request){
		
		String pageCode="";//htmlCode;
		
		try {
			Object instancedPage=Page.newInstance();
			
			
			
			injectSpringBeans(instancedPage, appContext2);
			injectRequestParameters(instancedPage,request);
			
			
			Method methodRequested=instancedPage.getClass().getMethod(method);//, param1.class, param2.class, ..);
			try {
				methodRequested.invoke(instancedPage, new Object[]{});
			} catch (IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Boolean editable=false;
			EntityWrapper entity=getWindEntity(instancedPage);
			//editable=
			pageCode= generateEntityDynamicHtmlCode(entity.entity, entity.editable,htmlCode, appContext2);//generateEntityDynamicHtmlCode(entity);
			//code=code.replace(dynamicEntityHtmlString, liveEntityCode);
			
		} catch (NoSuchMethodException e) {	
		} catch (IllegalAccessException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageCode;
	}
	
	public static String generateWebPageHtmlCode(Class<?> Page, ApplicationContext appContext2, HttpServletRequest request){
		
		String pageCode="";//htmlCode;
		
		try {
			Object instancedPage=Page.newInstance();
			
			
			
			injectSpringBeans(instancedPage, appContext2);
			injectRequestParameters(instancedPage,request);
			
			
			Method method=instancedPage.getClass().getMethod("setup");//, param1.class, param2.class, ..);
			try {
				method.invoke(instancedPage, new Object[]{});
			} catch (IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Boolean editable=false;
			EntityWrapper entity=getWindEntity(instancedPage);
			//editable=
			pageCode= generateEntityDynamicHtmlCode(entity.entity, entity.editable,htmlCode, appContext2);//generateEntityDynamicHtmlCode(entity);
			//code=code.replace(dynamicEntityHtmlString, liveEntityCode);
			
		} catch (NoSuchMethodException e) {	
		} catch (IllegalAccessException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageCode;
	}

	
	public static EntityWrapper getWindEntity(Object pageClass) throws IllegalAccessException, InstantiationException{
		EntityWrapper entityW=new EntityWrapper();// editable=false;
		for (Field field : pageClass.getClass().getDeclaredFields()){
			
			if (WindEntity.class.isAssignableFrom(field.getType())) {
				
				if (field.isAnnotationPresent(WindSpace.class)) {
					WindSpace wSAnnotation=field.getAnnotation(WindSpace.class);
					entityW.editable=wSAnnotation.editable();
				}
				
				field.setAccessible(true);
				Class<?> theClass = field.getType();//Class.forName(field.getType());
				entityW.entity= (Student) theClass.cast(field.get(pageClass));
				return entityW;
				//Class<? extends field.getType()> klass;
				
				
				//return (field.getType().) field.get(pageClass);
				//return (WindEntity) field.getType().newInstance();//getClass().newInstance();
				//sdfsdfsdfs
				//return (WindEntity) field.getType().cast(WindEntity.class);//getClass();//.getClass();
			}
		}
		return entityW;
	}
//		//entity.getClass().cl
//		Class<? extends WindEntity> obj = entity.getClass();//TestExample.class;
//		String ignored="";
//		for (Field field : obj.getDeclaredFields()){//getDeclaredMethods()) {
//			 
//			// if method is annotated with @Test
//			if (field.getClass().isAssignableFrom(WindEntity.class)) {
//				
//			System.out.println(field.getName());
//			 ignored=ignored+" && key!='"+field.getName()+"' ";


	public static void injectRequestParameters(Object windPage, HttpServletRequest request){
		for (Field field : windPage.getClass().getDeclaredFields()){
			if (field.isAnnotationPresent(WindParameter.class)) {
			    field.setAccessible(true); // You might want to set modifier to public first.
			    try {
					//Object value = field.get(windPage);
			    	String param=request.getParameter(field.getName());
			    	if (Integer.class.isAssignableFrom(field.getType())) {
			    		
			    		field.set(windPage, Integer.parseInt(param));
					}else{
				    		
				    		field.set(windPage, param);
						
					}
					//windPage.
					//value=context.getBean(field.getType());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
		}
	}
	
	public static void injectSpringBeans(Object windPage, ApplicationContext context){
		//entity.getClass().cl
		//Class<?> obj = windPage.getClass();
		for (Field field : windPage.getClass().getDeclaredFields()){
			if (field.isAnnotationPresent(Autowired.class)) {
			    field.setAccessible(true); // You might want to set modifier to public first.
			    try {
					//Object value = field.get(windPage);
					field.set(windPage, context.getBean(field.getType()));
					//windPage.
					//value=context.getBean(field.getType());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
		}
	}
	
	public static String generateEntityDynamicHtmlCode(final Student entity, boolean editable, String htmlCode, ApplicationContext context){
		//entity.getClass().cl
		Class<? extends Student> obj = entity.getClass();//TestExample.class;
		String ignored="";
		//boolean editable=false;
		for (Field field : obj.getDeclaredFields()){//getDeclaredMethods()) {
			 
			// if method is annotated with @Test
			if (field.isAnnotationPresent(BofaIgnore.class)) {
				
			System.out.println(field.getName());
			 ignored=ignored+" && key!='"+field.getName()+"' ";
	 
//				Annotation annotation = method.getAnnotation(Test.class);
//				Test test = (Test) annotation;
//	 
//				// if enabled = true (default)
//				if (test.enabled()) {
//	 
//				  try {
//					method.invoke(obj.newInstance());
//					System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
//					passed++;
//				  } catch (Throwable ex) {
//					System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
//					failed++;
//				  }
//	 
//				} else {
//					System.out.printf("%s - Test '%s' - ignored%n", ++count, method.getName());
//					ignore++;
//				}
	 
			}
		}

		
		
		if (!editable){
			htmlCode=htmlCode.replace(dynamicEntityHtmlString, liveEntityCode);
		}else{
			htmlCode=htmlCode.replace(dynamicEntityHtmlString, liveEditEntityCode);
		}
		
		htmlCode=htmlCode.replace(dynamicEntityjSString, jsLiveEntityCode);
		
		htmlCode=htmlCode.replace(dynamicEntityjSControllerString, entity.getClass().getName());
		htmlCode=htmlCode.replace(ignoredFIelds, ignored);
		
		//((Student)entity).setId(1);
		//((Student)entity).setName("Carlos Abraham Bayona Smythe");//=new Student();
		//entity.
		try {
			//Hibernate.initialize(entity);
//			HibernateTransactionManager transactionManager=context.getBean(HibernateTransactionManager.class);//org.springframework.transaction.PlatformTransactionManager.class);//support.TransactionTemplate.class);
//			TransactionTemplate transactionTemplate=new TransactionTemplate(transactionManager);//context.getBean(org.springframework.transaction.support.TransactionTemplate.class);
			
		    String entityJson= "";//populateEntityy(entity,context);
		    		
		    		
				    ObjectMapper mapper = new ObjectMapper();
				    try {
				    	Hibernate.initialize(entity);
				    	entityJson=mapper.writeValueAsString(entity);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		//transactionTemplate.execute(new TransactionCallback() {
//				@Override
//				public Object doInTransaction(TransactionStatus arg0) {
//					// TODO Auto-generated method stub
//					ObjectMapper mapper = new ObjectMapper();
//					try {
//						Hibernate.initialize(entity);
//						return mapper.writeValueAsString(entity);
//					} catch (JsonProcessingException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}return "";
//				}
//		      });
//			 context.getBean(org.hibernate.SessionFactory.class);//hibernate.SessionFactory);
//			 ((org.hibernate.SessionFactory)context.getBean(org.hibernate.SessionFactory.class)).getCurrentSession().update(entity);
//			
			htmlCode=htmlCode.replace(initJSONEntityCode,entityJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return htmlCode;
	}
	
	public static String populateEntityy(Student entity, ApplicationContext context) {
		// query for the Book Descriptors - 1st query works!!!
		//List<BookDescriptor> bookDescriptors= bookDescriptorService.list();
		String entityJsonCode="";
		Session session =  ((org.hibernate.SessionFactory)context.getBean(org.hibernate.SessionFactory.class)).openSession();//getCurrentSession().//sessionFactory.openSession();
		Transaction transaction = null;
		try {
		    transaction = session.beginTransaction();

		    //Hibernate.initialize(entity);
		    ObjectMapper mapper = new ObjectMapper();
		    try {
				entityJsonCode=mapper.writeValueAsString(entity);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    //transaction.commit();
		} catch (HibernateException e) {

		    transaction.rollback();
		    e.printStackTrace();
		} finally {
		    session.close();
		}
		return entityJsonCode;
		}
	
	public static final String dynamicEntityHtmlString="WIND_HTML_COMPONENT_CODE";
	public static final String dynamicEntityjSControllerString="ENTITY_NAME_JS_CODE";
	public static final String dynamicEntityjSString="ENTITY_CONTROLLER_JS_CODE";
	
	public static final String initJSONEntityCode="ENTITY_JSON_JS_CODE";
	
	public static final String ignoredFIelds="IGNORE_FIELDS";
	public static final String htmlCode="<!DOCTYPE HTML>"+
"<html lang=\"en\">"+
"  <head>"+
"    <link href=\"http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700\" rel=\"stylesheet\" type=\"text/css\" />"+
	"    <link href=\"../assets/style.css\" rel=\"stylesheet\" type=\"text/css\" />"+
	"  </head>"+
"  <body ng-app=\"windApp\">"+
dynamicEntityHtmlString+
    
"    <script src=\"../libs/sockjs/sockjs.min.js\" type=\"text/javascript\"></script>"+
"    <script src=\"../libs/stomp-websocket/lib/stomp.min.js\" type=\"text/javascript\"></script>"+
"    <script src=\"../libs/angular/angular.min.js\"></script>"+
"   <script src=\"../libs/lodash/dist/lodash.min.js\"></script>"+
"   <script src=\"../app/app.js\" type=\"text/javascript\"></script>"+
"   <script src=\"../app/controllers.js\" type=\"text/javascript\"></script>"+
"   <script type=\"text/javascript\">"+dynamicEntityjSString+"</script>"+
"    <script src=\"../app/services.js\" type=\"text/javascript\"></script>"+
"  </body>"+
"</html>";

	public static final String liveEntityCode="    <div ng-controller=\"EntityCtrlDynamic\" class=\"container\">"+
			"           <p ng-repeat=\"(key,value) in filterHiddens(entity)\">"+
			"        <span>{{key}} :</span><span>{{value}}</span>"+
			"        </p>"+
			"    </div>";
	
	public static final String liveEditEntityCode="    <div ng-controller=\"EntityCtrlDynamic\" class=\"container\">"+
			" <form ng-submit=\"modifyEntity()\" name=\"messageForm\">    "+ 
   " <hr />"+
    "<p ng-repeat=\"(key,value) in filterHiddens(entity)\">"+
   "	<span>{{key}} :</span> <input type=\"text\" ng-model=\"entity[key]\" />"+
   " </p>"+
   " <button ng-disabled=\"entity.id.length === 0\">Save</button>"+
   " </form>"+
   "    </div>";
	
	public static <T> T initializeAndUnproxy(T entity) {
	    if (entity == null) {
	        throw new 
	           NullPointerException("Entity passed for initialization is null");
	    }

	    Hibernate.initialize(entity);
	    if (entity instanceof HibernateProxy) {
	        entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
	                .getImplementation();
	    }
	    return entity;
	}
	public static final String jsLiveEntityCode="(function(angular) {"+
"	  angular.module(\"windApp.controllers\").controller(\"EntityCtrlDynamic\", function($scope, EntityService) {"+
"	    $scope.entity = "+initJSONEntityCode+";"+//{\"id\":\"1\",\"name\":\"Carlos\"};"+
"	    $scope.modifyEntity = function() {"+
"	        EntityService.send($scope.entity);"+
"	      };"+
"	    EntityService.receive().then(null, null, function(entityMessage) {"+
"	      $scope.entity=entityMessage;"+
"	    });"+
"	    EntityService.initialize($scope.entity,\"com.project.demo.model.Student\");"+//+dynamicEntityjSControllerString+"\");"+
"$scope.filterHiddens = function(items) {"+
"    var result = {};"+
"    angular.forEach(items, function(value, key) {"+
"        if (key!='windId' "+ignoredFIelds+") {"+
"           result[key] = value;"+
"        }"+
"    });"+
"    return result;"+
"};"+
"	  });"+
"	})(angular);";
}
