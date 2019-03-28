package com.filter.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String code;

    @Setter
    @Getter
    private String gender;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classe;

}
