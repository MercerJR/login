package com.example.log.dao;

import com.example.log.been.Choose;
import com.example.log.been.Course;
import com.example.log.been.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StudentDao {
    @Insert("Insert into student set name = #{name}," +
            "password = #{password}")
    boolean addStudent(@Param("name") String name,@Param("password") String password);

    @Select("Select * from student where name = #{name} and password = #{password}")
    Student selectStudent(@Param("name") String name,@Param("password") String password);

    @Select("Select * from student where name = #{name}")
    Student findByName(@Param("name") String name);

    @Update("Update student set gender = #{gender} , stuNumber = #{stuNumber} , major = #{major} where name = #{name}")
    boolean fillInfo(@Param("gender") String gender,@Param("stuNumber") String stuNumber,
                     @Param("major") String major, @Param("name") String name);

    @Insert("Insert into choose set course = #{course} , name = #{name}")
    boolean chooseCourse (@Param("course") String course,@Param("name") String name);

    @Update("Update course set number = #{number} where course = #{course}")
    void addNum(@Param("course") String course,@Param("number") int number);

    @Select("Select * from choose where name = #{name} and course = #{course}")
    Choose exist(@Param("name") String name, @Param("course") String course);

    @Select("Select * from course where course = #{course}")
    Course findByCourse(@Param("course") String course);

    @Select("Select number from course where course = #{course}")
    int getNumber(@Param("course") String course);

    @Select("Select count(*) from course")
    int getCourseNum();

    @Select("Select * from course where id = #{id}")
    Course findById(@Param("id") int id);

    @Select("Select course from choose where name = #{name}")
    List<String>findAll(@Param("name") String name);
}
//map搞定实体，，解决自己和别人的问题，，列出自己的问题，，我想达到一个什么样的目标，，思考模块太多怎么简化，，
//提出小组问题，，
//完善这个作业，，