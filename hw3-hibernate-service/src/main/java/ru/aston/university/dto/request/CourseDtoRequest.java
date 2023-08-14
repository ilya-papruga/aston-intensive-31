package ru.aston.university.dto.request;

public class CourseDtoRequest {

    private String title;

    public CourseDtoRequest() {
    }

    public CourseDtoRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
