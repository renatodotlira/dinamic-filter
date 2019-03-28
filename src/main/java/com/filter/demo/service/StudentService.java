package com.filter.demo.service;

import com.filter.demo.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> findByFilter(String classe, String gender, String code, String name);

}
