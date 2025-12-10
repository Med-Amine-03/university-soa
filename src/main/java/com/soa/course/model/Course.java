package com.soa.course.model;

import java.io.Serializable;

public class Course implements Serializable {

    private int id;
    private String name;
    private String teacher;
    private int credits;

    public Course() {}

    public Course(int id, String name, String teacher, int credits) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
