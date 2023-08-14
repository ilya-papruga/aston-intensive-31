package ru.aston.university.dto.request;

import ru.aston.university.entity.Student;

import java.util.List;

public class GroupDtoRequest {

    private long id;
    private String number;
    private List<Student> studentList;

    public GroupDtoRequest() {
    }

    public GroupDtoRequest(long id, String number, List<Student> studentList) {
        this.id = id;
        this.number = number;
        this.studentList = studentList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
