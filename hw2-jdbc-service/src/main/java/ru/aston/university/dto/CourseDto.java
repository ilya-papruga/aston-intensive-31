package ru.aston.university.dto;

import ru.aston.university.entity.Student;

import java.util.List;

public class CourseDto {

    private String title;
    private List<Student> studentList;

    public CourseDto(String title) {
        this.title = title;
    }

    public CourseDto(String title, List<Student> studentList) {
        this.title = title;
        this.studentList = studentList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
