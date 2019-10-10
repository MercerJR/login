package com.example.log.controller;

import com.example.log.been.Choose;
import com.example.log.been.Course;
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
public class ChooseController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/choose",produces = "application/json")
    public Response choose(Choose choose, HttpSession session){
        String name = (String)session.getAttribute("name");
        choose.setName(name);
        if (StringUtils.isEmpty(choose.getCourse())){
            log.error("输入课程未空");
            return new Response("课程名不能未空","选课失败");
        }
        Course course = studentService.findByCourse(choose.getCourse());
        if (course == null){
            log.error("所选课程不存在");
            return new Response("所选课程不存在","选课失败");
        }
        boolean flag = studentService.choose(choose.getCourse(),choose.getName());
        if (flag){
            int number = studentService.getNumber(choose.getCourse());
            course.setNumber(number + 1);
            studentService.addNum(course);
            return new Response("您已成功选择课程："+choose.getCourse(),"选课成功");
        }else {
            log.error("不可重复选择课程");
            return new Response("您已选择了此课程，不可重复选择","选课失败");
        }
    }
}
