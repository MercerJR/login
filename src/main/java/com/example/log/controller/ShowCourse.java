package com.example.log.controller;

import com.example.log.been.Course;
import com.example.log.been.Generic;
import com.example.log.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Vector;

@RestController
@RequestMapping("/user")
public class ShowCourse {
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/showCourse",produces = "application/json")
    public Generic<Vector<Course>> showCourse(){
        int num = studentService.getCourseNum();
        Vector<Course> Courses = new Vector<Course>();
        for (int i = 1;i <= num;i++){
            Courses.add(studentService.findByID(i));
        }
        Generic<Vector<Course>> result = new Generic<>();
        result.course = Courses;
        result.length = num;
        return result;
    }
}
