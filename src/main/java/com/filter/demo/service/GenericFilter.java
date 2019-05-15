package com.filter.demo.service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public interface GenericFilter {

    EntityManager getEntityManager();
    CriteriaBuilder getCriteriaBuilder();

    void initialize();
    CriteriaQuery getCriteriaQuery();
    Root getRoot();
    TypedQuery getQuery();
    void setQuery(TypedQuery typedQuery);


}
