package ru.aston.university.dto.response;

import java.util.List;

public class CourseDtoResponse {

    private long id;
    private String title;
    private List<String> studentList;

    public CourseDtoResponse(long id, String title, List<String> studentList) {
        this.id = id;
        this.title = title;
        this.studentList = studentList;
    }

    public CourseDtoResponse() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<String> studentList) {
        this.studentList = studentList;
    }
}

