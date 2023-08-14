package ru.aston.university.dto;

import java.util.List;

public class CrossCourseStudentDto {

    private List<Long> courseId;
    private List<Long> studentId;

    public CrossCourseStudentDto() {
    }

    public CrossCourseStudentDto(List<Long> courseId, List<Long> studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }


    public List<Long> getCourseId() {
        return courseId;
    }

    public void setCourseId(List<Long> courseId) {
        this.courseId = courseId;
    }

    public List<Long> getStudentId() {
        return studentId;
    }

    public void setStudentId(List<Long> studentId) {
        this.studentId = studentId;
    }
}
