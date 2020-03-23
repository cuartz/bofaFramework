package com.project.demo.DAO;

import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import com.project.demo.model.*;

@Component
public class ClassRoomDAOImpl extends DAOImpl<ClassRoom, Integer>  
        implements ClassRoomDAO {  

    @Override
    public ClassRoom findByName(String classRoomName) {
        return (ClassRoom) getSession().createCriteria(ClassRoom.class)
                .add(Restrictions.eq("name", classRoomName)).uniqueResult(); 
    }

     
}
