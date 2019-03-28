package com.filter.demo.service.impl;

import com.filter.demo.model.Student;
import com.filter.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentFilter studentFilter;

    @Override
    public List<Student> findByFilter(String classe, String gender, String code, String name) {
        FilterManager manager = new FilterManager(studentFilter);
        manager.addParameter("classe.name", classe);
        manager.addParameter("gender", gender);
        manager.addParameter("code", code);
        manager.addParameter("name", name);
        return manager.findByFilter();
    }
}
