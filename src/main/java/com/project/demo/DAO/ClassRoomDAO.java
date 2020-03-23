package com.project.demo.DAO;

import com.project.demo.model.*;
public interface ClassRoomDAO extends DAO<ClassRoom, Integer> { 

    public ClassRoom findByName(String classRoomName);
    

}
