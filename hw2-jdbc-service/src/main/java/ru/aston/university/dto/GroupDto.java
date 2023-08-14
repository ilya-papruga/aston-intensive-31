package ru.aston.university.dto;

import ru.aston.university.entity.Student;

import java.util.List;

public class GroupDto {
    private String number;
    private List<Student> studentList;

    public GroupDto(String number) {
        this.number = number;
    }

    public GroupDto(String number, List<Student> studentList) {
        this.number = number;
        this.studentList = studentList;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
