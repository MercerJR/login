package com.example.log.service;

public class Demo {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        String major = service.S("曾靖然");
        System.out.println(major);
    }
}
