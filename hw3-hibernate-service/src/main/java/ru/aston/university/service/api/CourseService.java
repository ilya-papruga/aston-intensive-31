package ru.aston.university.service.api;

import ru.aston.university.dto.request.CourseDtoRequest;
import ru.aston.university.dto.response.CourseDtoResponse;

import java.util.List;

public interface CourseService {

    List<CourseDtoResponse> readAll();

    void create(CourseDtoRequest course);

    void delete(Long id);

    void update(CourseDtoRequest course, Long id);

}
