package com.agri.wen.dao;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.agri.wen.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("userDao")
public interface UserDao {

    /***
     * 根据姓名查询学生成绩信息
     * @param name
     * @return
     */
    List<StudentEntity> findByName(String name);

}
