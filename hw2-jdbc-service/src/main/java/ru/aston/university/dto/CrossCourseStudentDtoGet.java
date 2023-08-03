package ru.aston.university.dto;

public class CrossCourseStudentDtoGet {

    private long courseId;
    private long studentId;

    public CrossCourseStudentDtoGet() {
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}
