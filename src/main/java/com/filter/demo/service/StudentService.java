package com.filter.demo.service;

import java.util.List;

public interface StudentService {

    List findByFilter(String classe, String gender, String code, String name);

}
