package com.agri.wen.service;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.agri.wen.entity.StudentEntity;

import java.util.List;

public interface UserService {

     List<StudentEntity> findByName(String name);

}
