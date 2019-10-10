package com.example.log.been;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private String name;
    private String password;
    private String gender;
    private String stuNumber;
    private String major;
    private String course;

//    public Student(String name,String password){
//        this.name = name;
//        this.password = password;
//    }
}
