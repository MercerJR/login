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

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/user")
public class FillController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/fill",produces = "application/json")
    public Response fillInfo(Student student, HttpSession session){
        String name = (String) session.getAttribute("name");
        Student student1 = studentService.findByName(name);
        student1.setGender(student.getGender());
        student1.setMajor(student.getMajor());
        student1.setStuNumber(student.getStuNumber());
        if (StringUtils.isEmpty(student.getGender()) || StringUtils.isEmpty(student.getStuNumber()) ||
                StringUtils.isEmpty(student.getMajor())){
            System.out.println(student.getGender());
            log.error("有信息未填完");
            return new Response("信息未完善","信息完善失败");
        }else {
            boolean flag = studentService.fill(student1);
            if (flag){
                return new Response("信息已完善","信息完善成功");
            }else {
                return new Response("信息完善出错","信息完善失败");
            }
        }
    }
}
