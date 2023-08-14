package ru.aston.university.service.api;

import ru.aston.university.dto.request.StudentDtoRequest;
import ru.aston.university.dto.response.StudentDtoResponse;

import java.util.List;

public interface StudentService {

    List<StudentDtoResponse> readAll();

    void create(StudentDtoRequest student);

    void delete(Long id);

    void update(StudentDtoRequest student, Long id);

}
