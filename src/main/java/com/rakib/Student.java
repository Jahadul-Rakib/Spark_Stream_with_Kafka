package com.rakib;

import java.io.Serializable;

public class Student implements Serializable {
    private Integer id;
    private String name;
    private String Subject;
    private Integer Age;

    public Student() {
    }

    public Student(Integer id, String name, String subject, Integer age) {
        this.id = id;
        this.name = name;
        Subject = subject;
        Age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }
}
