package com.project.demo.DAO;

import com.project.demo.model.*;
public interface StudentDAO extends DAO<Student, Integer> { 

    public Student findByName(String studentName);
    

}
