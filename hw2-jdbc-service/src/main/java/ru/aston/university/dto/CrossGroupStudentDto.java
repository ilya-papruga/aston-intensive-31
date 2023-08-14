package ru.aston.university.dto;

import java.util.List;

public class CrossGroupStudentDto {

    private long groupId;
    private List<Long> studentId;

    public CrossGroupStudentDto() {
    }

    public CrossGroupStudentDto(long groupId, List<Long> studentId) {
        this.groupId = groupId;
        this.studentId = studentId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public List<Long> getStudentId() {
        return studentId;
    }

    public void setStudentId(List<Long> studentId) {
        this.studentId = studentId;
    }
}
