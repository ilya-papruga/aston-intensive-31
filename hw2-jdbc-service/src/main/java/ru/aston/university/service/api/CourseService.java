package ru.aston.university.service.api;

import ru.aston.university.dto.CourseNoId;
import ru.aston.university.dto.GroupNoId;
import ru.aston.university.entity.Course;
import ru.aston.university.entity.Group;

import java.util.List;

public interface CourseService {

    List<Course> readAll();

    String create(CourseNoId group);

    void delete(Long id);

    void update(CourseNoId course, Long id);

}
