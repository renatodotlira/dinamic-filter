package com.filter.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Class {

    @Id
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String name;

}
