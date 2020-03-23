package com.wind.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface BofaListSpace{
	boolean editable() default false;
	String tag() default "div";
	String label() default "";
	boolean defaultLabel() default true;
	String valueIn() default "";
	String listTag() default "div";
	String selectedItem();
	String selectTag();
	String controller() default "";
	String selectAttribute() default "";

}

