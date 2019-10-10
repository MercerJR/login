package com.example.log.controller;

import com.example.log.been.Course;
import com.example.log.service.StudentService;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
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
    public Vector<Course> showChoose(HttpSession session){
        String name = (String) session.getAttribute("name");
        List<String> list = studentService.findAll(name);
//        System.out.println(list.get(0));
        Vector<Course> Courses = new Vector<Course>();
        for (int i = 0;i < list.size();i++){
            Courses.add(studentService.findByCourse(list.get(i)));
        }
        return Courses;
    }
}
