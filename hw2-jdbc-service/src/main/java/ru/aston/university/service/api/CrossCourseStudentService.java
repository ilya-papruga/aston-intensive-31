package ru.aston.university.service.api;

import ru.aston.university.dto.CrossCourseStudentDto;
import ru.aston.university.dto.CrossCourseStudentDtoGet;

import java.util.List;

public interface CrossCourseStudentService {

     List<CrossCourseStudentDtoGet> readAll();

    void create(CrossCourseStudentDto cross);

    void delete(CrossCourseStudentDto cross);

}
