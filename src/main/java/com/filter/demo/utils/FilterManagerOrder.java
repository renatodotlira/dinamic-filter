package com.filter.demo.utils;

public final class FilterManagerOrder extends FilterManagerAbstract {

    public FilterManagerOrder(FilterManagerAbstract filterManagerAbstract){
        super.classFilter = filterManagerAbstract.classFilter;
        predicates = filterManagerAbstract.predicates;
        parameters = filterManagerAbstract.parameters;
        orders = filterManagerAbstract.orders;
        path = filterManagerAbstract.path;
    }

    public void asc(){
        orders.add(classFilter.getCriteriaBuilder().asc(this.path));
    }
    public void desc(){
        orders.add(classFilter.getCriteriaBuilder().desc(this.path));
    }

}
