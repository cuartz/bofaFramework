package com.project.demo.model;

import static javax.persistence.GenerationType.IDENTITY;

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

@Entity
@Table(name="classroom"
)
public class ClassRoom  implements java.io.Serializable{
	
	private int id;
	
	private String name;
	
	private Set<Student> students = new HashSet<Student>(0);
	
	public ClassRoom(){
		
	}
	
	public ClassRoom(int id, String name){
		this.id=id;
		this.name=name;
	}
	
	public ClassRoom(int id, String name, Set<Student> students){
		this.id=id;
		this.name=name;
		this.students=students;
	}

	
	 @Id @GeneratedValue(strategy=IDENTITY)
   @Column(name="id", unique=true, nullable=false)
   public int getId() {
       return this.id;
   }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name="name", nullable=false, length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="classRoom")
    public Set<Student> getStudents() {
        return this.students;
    }
    
    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
