package com.filter.demo.model;

public class TestBuilder {

    private String nome;
    private String sobrenome;

    private static TestBuilder TESTE_BUILDER;

    public TestBuilder nome(String nome){
        this.nome = nome;
        return this;
    }

    public TestBuilder sobrenome(String sobrenome){
        this.sobrenome = sobrenome;
        return this;
    }

    public String print(){
        return this.nome+" "+this.sobrenome;
    }

    public static TestBuilder get(){
        TESTE_BUILDER  = new TestBuilder();
        return TESTE_BUILDER;
    }

    private TestBuilder(){}


}

