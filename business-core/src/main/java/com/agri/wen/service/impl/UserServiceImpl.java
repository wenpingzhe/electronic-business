package com.agri.wen.service.impl;

import com.dmsdbj.itoo.tool.business.ItooResult;
import com.agri.wen.dao.UserDao;
import com.agri.wen.entity.StudentEntity;
import com.agri.wen.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("userService")
public class UserServiceImpl  implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public List<StudentEntity> findByName(String name) {
        return userDao.findByName(name);
    }

}
