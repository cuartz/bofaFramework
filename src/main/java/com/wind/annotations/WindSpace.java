package com.wind.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
 
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface WindSpace {
	boolean editable() default false;
	boolean alive() default false;
	String tag() default "div";
	String label() default "";
	boolean defaultLabel() default true;
	String valueIn() default "";
	String attributes() default "";
	String listTag() default "";
	boolean isList() default false;
	//String[] valuesList();
	//Class<? extends WindObject>[] parameters();

}

