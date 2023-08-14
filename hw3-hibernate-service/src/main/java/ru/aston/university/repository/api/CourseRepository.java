package ru.aston.university.repository.api;

import ru.aston.university.dto.request.CourseDtoRequest;
import ru.aston.university.entity.Course;

import java.util.List;

public interface CourseRepository {


    List<Course> readAll();


    void create(Course course);


    void delete(Long id);


    void update(CourseDtoRequest course, Long id);

}
