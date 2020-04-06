package com.agri.wen.controller;

import com.agri.wen.entity.StudentEntity;
import com.agri.wen.service.UserService;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.itoo.tool.business.ItooResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;


    /***
     * 根据姓名查询成绩
     * @param name
     * @return
     */
    @ApiOperation(value = "根据姓名查询")
    @GetMapping("/findByName")
    public IntegralResult<StudentEntity> findByName(@RequestParam(required = false,defaultValue = "")String name){
        List<StudentEntity> list=userService.findByName(name);
        if(list.isEmpty()){
            return IntegralResult.build(IntegralResult.FAIL,"查询结果为空",list);
        }
        return IntegralResult.build(IntegralResult.SUCCESS,"查询成功",list);
    }








}
