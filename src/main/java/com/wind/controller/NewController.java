package com.wind.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wind.annotations.WindPage;
import com.wind.services.WindService;
import com.wind.util.HtmlCOdeUtils2;
@Controller
@RequestMapping("/windFWK2" +
		"")
public class NewController {
	/*TODO
	 * 
	 * agregar windComponentMehod y WindComponentMethod que se encargaran de ir a una nueva pagina o 
	 * actualizar un pedazo de html en el codigo
	 * 
	 */
	@Autowired
	private ApplicationContext appContext;
        
        @Autowired
	private WindService wService;
	
    
	@RequestMapping(value = "/{page}", 
			method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public
	@ResponseBody
	String windPages(//@RequestBody String requestString, 
			@PathVariable String page,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		
		

	    response.setContentType("html=text/html");
	    response.setCharacterEncoding("UTF-8");


	    return getPageCode(page, appContext, request);//HtmlCodeUtils.generateEntityDynamicHtmlCode(student);
	}
	
	@RequestMapping(value = "/{page}/{method}", 
			method = RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public
	@ResponseBody
	String windMethods(@RequestBody String requestString, 
			@PathVariable String page,
			@PathVariable String method,
			HttpServletRequest request
			//,HttpServletResponse response
			) {
		
	
		
//		   response.setContentType("text/plain");
//		    response.setCharacterEncoding("UTF-8");
//	    response.setContentType("html=text/html");
//	    response.setCharacterEncoding("UTF-8");
	    
//	    Student student=studentService.findStudent(1);//findById(1L, false);
//	    student.setId(1);
//	    student.setName("Carlos");
//	    student.setAge(20);

	    return getPageResponse(page, method, appContext, request, requestString);//HtmlCodeUtils.generateEntityDynamicHtmlCode(student);
	}


	private String getPageCode(String page, ApplicationContext appContext2,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Class<?> pageClass=getPage(page);
		if (pageClass==null){
			return "PAGE NOT FOUND";
		}
		return HtmlCOdeUtils2.renderPage(pageClass, appContext2,request, wService);
	}
	
	private String getPageResponse(String page, String method, ApplicationContext appContext2,
			HttpServletRequest request,String requestString) {
		// TODO Auto-generated method stub
		
		Class<?> pageClass=getPage(page);
		if (pageClass==null){
			return "PAGE NOT FOUND";
		}
		return HtmlCOdeUtils2.getPageResponse(pageClass, method, appContext2,request,requestString, wService);
	}
	
	public Class<?> getPage(String page){
		
//		 Reflections reflections = new Reflections("my.project.prefix");
//
//		 Set<Class<? extends Object>> allClasses = 
//		     reflections.getSubTypesOf(Object.class);
		try {
			Class<?> pageClass= findMyPage("com.project.demo.pages",page);
			
			return pageClass;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private Class<?> findMyPage(String basePackage,String page) throws IOException, ClassNotFoundException
	{
	    ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
	    MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

	    List<Class> candidates = new ArrayList<Class>();
	    String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
	                               resolveBasePackage(basePackage) + "/" + "**/*.class";
	    Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
	    for (Resource resource : resources) {
	        if (resource.isReadable()) {
	            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
	            if (isCandidateForPage(metadataReader,page)) {
	            	return Class.forName(metadataReader.getClassMetadata().getClassName());
	                //candidates.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
	            }
	        }
	    }
	    return null;
	}
	
	private String resolveBasePackage(String basePackage) {
	    return ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage));
	}

	private boolean isCandidate(MetadataReader metadataReader) throws ClassNotFoundException
	{
	    try {
	        Class c = Class.forName(metadataReader.getClassMetadata().getClassName());
	        if (c.getAnnotation(WindPage.class) != null) {
	            return true;
	        }
	    }
	    catch(Throwable e){
	    }
	    return false;
	}
	
	private boolean isCandidateForPage(MetadataReader metadataReader, String page) throws ClassNotFoundException
	{
	    try {
	        Class c = Class.forName(metadataReader.getClassMetadata().getClassName());
	        if (c.getAnnotation(WindPage.class) != null) {
	        	WindPage pageAnnottation=(WindPage) c.getAnnotation(WindPage.class);
	        	if (pageAnnottation.url().equals(page)){
	        		return true;
	        	}
	        }
	    }
	    catch(Throwable e){
	    }
	    return false;
	}
}
