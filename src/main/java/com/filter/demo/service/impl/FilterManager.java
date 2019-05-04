package com.filter.demo.service.impl;

import com.filter.demo.service.GenericFilter;
import com.filter.demo.utils.FilterManagerAbstract;
import com.filter.demo.utils.FilterManagerOrder;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;

class FilterManager<T> extends FilterManagerAbstract {

    FilterManager(GenericFilter genericFilter){
        genericFilter.initialize();
        this.classFilter = genericFilter;
    }

    List<T> findByFilter(){
        classFilter.getCriteriaQuery().select(
                classFilter.getRoot()
        ).where(
                classFilter.getCriteriaBuilder().and(
                        predicates.toArray(
                                new Predicate[predicates.size()]
                        )
                )
        ).orderBy(orders);
        classFilter.setQuery(
                classFilter.getEntityManager().createQuery(
                        classFilter.getCriteriaQuery()
                )
        );
        setParameters();
        return classFilter.getQuery().getResultList();
    }

    FilterManagerOrder orderBy(String field){
        path = null;
        String[] fieldV = field.split("\\.");
        for (String s : fieldV){
            path = (path != null)?path.get(s):classFilter.getRoot().get(s);
        }
        return new FilterManagerOrder(this);
    }

    void addParameter(String field, String value){
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

    void addBoolParameter(String field, Boolean value){
        if(value){
            ParameterExpression<Boolean> parameter = classFilter.getCriteriaBuilder().parameter(Boolean.class);
            Predicate predicate = classFilter.getCriteriaBuilder().equal(getFieldToCompare(field), parameter);
            predicates.add(predicate);
            parameters.put(parameter, true);
        }
    }

    void addLongParameter(String field, Long value){
        if(value>0){
            ParameterExpression<Long> parameter = classFilter.getCriteriaBuilder().parameter(Long.class);
            Predicate predicate = classFilter.getCriteriaBuilder().equal(getFieldToCompare(field), parameter);
            predicates.add(predicate);
            parameters.put(parameter, value);
        }
    }

//    void addEnumParameter(String field, String value){
//        if(!value.equals("")){
//            ParameterExpression<InstructionStatusEnum> parameter = classFilter.getCriteriaBuilder().parameter(InstructionStatusEnum.class);
//            Predicate predicate = classFilter.getCriteriaBuilder().equal(getFieldToCompare(field), parameter);
//            predicates.add(predicate);
//            parameters.put(parameter, InstructionStatusEnum.valueOf(value));
//        }
//    }

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
            ParameterExpression<Object> s = (ParameterExpression<Object>) o;
            classFilter.getQuery().setParameter(s, parameters.get(s));
        }
    }

}
