package com.example.log.controller;

import com.example.log.been.Course;
import com.example.log.been.Generic;
import com.example.log.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("/user")
public class ShowChoose {
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/showChoose",produces = "application/json")
    public Generic<Vector<Course>> showChoose(HttpSession session){
        String name = (String) session.getAttribute("name");
        List<String> list = studentService.findAll(name);
        Vector<Course> Courses = new Vector<Course>();
        for (int i = 0;i < list.size();i++){
            Courses.add(studentService.findByCourse(list.get(i)));
        }
        Generic<Vector<Course>> result = new Generic<>();
        result.course = Courses;
        result.length = list.size();

        return result;
    }
}
