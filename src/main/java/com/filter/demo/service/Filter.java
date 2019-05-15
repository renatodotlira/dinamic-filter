package com.filter.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Filter<T>{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    private CriteriaQuery criteriaQuery;
    private Root root;
    private TypedQuery query;

    protected void initialize(Class<T> c){
        close();
        entityManager = entityManagerFactory.createEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(c);
        root = criteriaQuery.from(c);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public CriteriaQuery getCriteriaQuery() {
        return criteriaQuery;
    }

    public Root getRoot() {
        return root;
    }

    public TypedQuery getQuery() {
        return query;
    }

    public void setQuery(TypedQuery typedQuery) {
        this.query = typedQuery;
    }

    private void close(){
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.clear();
            entityManager.close();
        }
    }
}
