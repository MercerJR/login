package com.example.log.controller;

import com.example.log.been.Course;
import com.example.log.been.Student;
import com.example.log.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/user")
public class ShowController {
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/show",produces = "application/json")
    public Student show(HttpSession session){
        String name = (String) session.getAttribute("name");
        Student student = studentService.findByName(name);

        return student;
    }
}
