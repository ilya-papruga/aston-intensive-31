package ru.aston.university.dto;

import ru.aston.university.entity.Course;

import java.util.List;

public class StudentCoursesDto {
    private String name;
    private List<Course> CoursesList;

    public StudentCoursesDto() {
    }

    public StudentCoursesDto(String name) {
        this.name = name;
    }

    public StudentCoursesDto(String name, List<Course> coursesList) {
        this.name = name;
        CoursesList = coursesList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCoursesList() {
        return CoursesList;
    }

    public void setCoursesList(List<Course> coursesList) {
        CoursesList = coursesList;
    }
}
