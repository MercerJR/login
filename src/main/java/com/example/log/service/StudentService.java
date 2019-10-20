package com.example.log.service;

import com.example.log.been.Choose;
import com.example.log.been.Course;
import com.example.log.been.Student;
import com.example.log.dao.StudentDao;
import com.train.mybatis.dao2.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StudentMapper mapper;

    public String S(String name){
        return mapper.selectByName(name);
    }

    public boolean register(Student student){
        String name = student.getName();
        Student student1 = studentDao.findByName(name);
        if (student1 == null){
            return studentDao.addStudent(student.getName(),student.getPassword());
        }
        return false;
    }

    public boolean login(String name,String password){
        Student student =  studentDao.selectStudent(name,password);
        if (student != null){
            return true;
        }
        return false;
    }

    public boolean fill(Student student){
        return studentDao.fillInfo(student.getGender(),student.getStuNumber(),
                student.getMajor(),student.getName());
    }

    public Student findByName(String name){
        return studentDao.findByName(name);
    }

    public boolean choose(String course,String name){
        Choose choose = studentDao.exist(name,course);
        if (choose == null){
            return studentDao.chooseCourse(course, name);
        }else {
            return false;
        }
    }

    public void addNum(Course course){
        studentDao.addNum(course.getCourse(),course.getNumber());
    }

    public Course findByCourse(String course){
        return studentDao.findByCourse(course);
    }

    public int getNumber(String course){
        return studentDao.getNumber(course);
    }

    public int getCourseNum(){
        return studentDao.getCourseNum();
    }

    public Course findByID(int id){
        return studentDao.findById(id);
    }

    public List<String> findAll(String name){
        return studentDao.findAll(name);
    }
}
