package ru.aston.university.dto;

public class CrossGroupStudentDtoGet {

    private long groupId;
    private long studentId;

    public CrossGroupStudentDtoGet() {
    }

    public long getGroupId() {
        return groupId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

}
