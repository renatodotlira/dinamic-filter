package com.filter.demo.service.impl;

import com.filter.demo.service.GenericFilter;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterManager {

    private GenericFilter classFilter;

    final List<Predicate> predicates = new ArrayList<>();

    HashMap<ParameterExpression<String>, String> parameters = new HashMap<>();

    public FilterManager(GenericFilter testGeneric){
        testGeneric.initialize();
        this.classFilter = testGeneric;
    }

    List findByFilter(){
        classFilter.getCriteriaQuery().select(
                classFilter.getRoot()
        ).where(
                classFilter.getCriteriaBuilder().and(
                        predicates.toArray(
                                new Predicate[predicates.size()]
                        )
                )
        );
        classFilter.setQuery(
                classFilter.getEntityManager().createQuery(
                        classFilter.getCriteriaQuery()
                )
        );
        setParameters();
        return classFilter.getQuery().getResultList();
    }

    public void addParameter(String field, String value){
        if(!value.equals("")){
            ParameterExpression<String> parameter = classFilter.getCriteriaBuilder().parameter(String.class);
            Predicate predicate;
            if(value.charAt(0) == '%' || value.charAt(value.length()-1) == '%'){
                predicate = classFilter.getCriteriaBuilder().like(getFieldToCompareLike(field), parameter);
            }else{
                predicate = classFilter.getCriteriaBuilder().equal(getFieldToCompare(field), parameter);
            }

            predicates.add(predicate);
            parameters.put(parameter, value);
        }
    }

    private Expression<?> getFieldToCompare(String field){
        String[] fieldV = field.split("\\.");
        Expression<?> ex = null;
        for (String s : fieldV){
            ex = (ex != null)?((Path<?>) ex).get(s):classFilter.getRoot().get(s);
        }
        return ex;
    }

    private Expression<String> getFieldToCompareLike(String field){
        String[] fieldV = field.split("\\.");
        Expression<String> ex = null;
        for (String s : fieldV){
            ex = (ex != null)?((Path<?>) ex).get(s):classFilter.getRoot().get(s);
        }
        return ex;
    }

    private void setParameters(){
        for (Object o  : parameters.keySet()) {
            ParameterExpression<String> s = (ParameterExpression<String>) o;
            classFilter.getQuery().setParameter(s, parameters.get(s));
        }
    }
}
