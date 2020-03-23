package com.project.demo.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wind.util.WindEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import static javax.persistence.GenerationType.IDENTITY;
import com.wind.annotations.BofaIgnore;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","classRoom"})
@Entity
@Table(name="student"
)
public class Student implements java.io.Serializable, WindEntity {
	
	@BofaIgnore
    private Integer id;
    private ClassRoom classRoom;
    private String name;
    private Integer age;
    
    public Student(){
    	
    }
    
    public Student(int id, ClassRoom classRoom, String name, Integer age){
    	this.id=id;
    	this.classRoom=classRoom;
    	this.name=name;
    	this.age=age;
    }
    
   // @Id 
    @Id @GeneratedValue(strategy=IDENTITY)
   @Column(name="id", unique=true, nullable=false)
   public Integer getId() {
       return this.id;
   }
   
   public void setId(Integer id) {
       this.id = id;
   }
   
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="classroom_id", nullable=false)
   public ClassRoom getClassRoom() {
       return this.classRoom;
   }
   
   public void setClassRoom(ClassRoom classRoom) {
       this.classRoom = classRoom;
   }
   
   @Column(name="name", nullable=false, length=45)
   public String getName() {
       return this.name;
   }
   
   public void setName(String name) {
       this.name = name;
   }
   
   @Column(name="age")
   public Integer getAge() {
       return this.age;
   }
   
   public void setAge(Integer age) {
       this.age = age;
   }
   
   private String windId;
   @Transient
@Override
public String getWindId() {
	return id.toString();
}
   
   @Override
   public void setWindId(String windId) {
   	
   }

}
