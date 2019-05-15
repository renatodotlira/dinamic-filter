package com.filter.demo.service.impl;

import com.filter.demo.model.Student;
import com.filter.demo.service.Filter;
import com.filter.demo.service.GenericFilter;
import org.springframework.stereotype.Service;

@Service
public class StudentFilter extends Filter<Student> implements GenericFilter {

    public void initialize(){
        super.initialize(Student.class);
    }

}
