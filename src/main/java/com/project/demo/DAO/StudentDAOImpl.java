package com.project.demo.DAO;

import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.project.demo.model.*;

@Component
public class StudentDAOImpl extends DAOImpl<Student, Integer>  
        implements StudentDAO {  

    @Override

    public Student findByName(String studentName) {
        return (Student) getSession().createCriteria(Student.class)
                .add(Restrictions.eq("name", studentName)).uniqueResult(); 
    }

     
}
