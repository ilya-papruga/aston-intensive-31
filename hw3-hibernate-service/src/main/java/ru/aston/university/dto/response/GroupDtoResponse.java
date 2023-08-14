package ru.aston.university.dto.response;

import java.util.List;

public class GroupDtoResponse {

    private long id;
    private String number;
    private List<String> studentList;

    public GroupDtoResponse() {
    }

    public GroupDtoResponse(long id, String number, List<String> studentList) {
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

    public List<String> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<String> studentList) {
        this.studentList = studentList;
    }
}
