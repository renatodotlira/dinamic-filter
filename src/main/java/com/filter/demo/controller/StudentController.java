package com.filter.demo.controller;

import com.filter.demo.model.Student;
import com.filter.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public ResponseEntity<List<Student>> findFilter(
            @RequestParam(value = "class", defaultValue = "") String className,
            @RequestParam(value = "gender", defaultValue = "") String gender,
            @RequestParam(value = "code", defaultValue = "") String code,
            @RequestParam(value = "name", defaultValue = "") String name
    ){
        return new ResponseEntity<>(service.findByFilter(className, gender, code, name), HttpStatus.OK);
    }

}
