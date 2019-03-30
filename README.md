# Dinamic Filter Search with Spring Boot
 
This project makes use of polimofism to turn the use of Criteria JPA worth of a few lines of code. 

## Prerequisites

A Spring boot project with JPA and Web dependencies.

## What is needed to use

It's needed to create a class for an entity that will be searched. For instance, this project uses the Student entity.
So, the class StudentFilter represents this entity. The class must have the @Service annotation, and implement the 
initialize method, calling the initialize method of the superclass, and passing the entity as a parameter. 

```java

@Service
public class StudentFilter extends Filter implements GenericFilter {

    public void initialize(){
        super.initialize(Student.class);
    }

}
```
How to use
---

Add a dependency of the StudentFlter in the class that will make a search. And intantialize a FilterManager class 
passing the studentFilter as argument. And add all params that will be used in the filter:

```java

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentFilter studentFilter;

    @Override
    public List<Student> findByFilter(String classe, String gender, String code, String name) {
        FilterManager manager = new FilterManager(studentFilter);
        manager.addParameter("classe.name", classe);
        manager.addParameter("gender", gender);
        manager.addParameter("code", code);
        manager.addParameter("name", name);
        return manager.findByFilter();
    }
}
```
### Like Query

In order to use the like on query, add the % to the value of the param, like bellow:

```java

manager.addParameter("name", "%"+name+"%");

```


## Built With

* [Spring boot](https://spring.io/projects/spring-boot) - Framework Java
* [Maven](https://maven.apache.org/) - Dependency Management

