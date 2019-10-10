package com.example.log.controller;

import com.example.log.been.Response;
import com.example.log.been.Student;
import com.example.log.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/user")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/register",produces = "application/json")
    public Response register(Student student){
        if (StringUtils.isEmpty(student.getName())){
            log.error("注册失败");
            return new Response("姓名不能为空","失败");
        }else if (StringUtils.isEmpty(student.getPassword())){
            log.error("注册失败");
            return new Response("密码不能为空","失败");
        }else {
            boolean flag = studentService.register(student);
            if (flag){
                return new Response("注册成功","成功");
            }else {
                return new Response("用户名已经存在","失败");
            }
        }
    }

    @PostMapping(value = "/login",produces = "application/json")
    public Response login(Student student, HttpServletRequest request){
        if (StringUtils.isEmpty(student.getName())){
            log.error("登陆失败");
            return new Response("姓名不能为空","失败");
        }else if (StringUtils.isEmpty(student.getPassword())){
            log.error("登陆失败");
            return new Response("密码不能为空","失败");
        }else {
            boolean flag = studentService.login(student.getName(),student.getPassword());
            if (flag) {
                request.getSession().setAttribute("name",student.getName());
                return new Response("登陆成功","成功");
            }else {
                return new Response("登陆失败","失败");
            }
        }
    }
}
