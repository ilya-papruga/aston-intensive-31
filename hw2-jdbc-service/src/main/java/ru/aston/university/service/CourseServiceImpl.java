package ru.aston.university.service;

import ru.aston.university.dto.CourseNoId;
import ru.aston.university.entity.Course;
import ru.aston.university.repository.CourseRepositoryImpl;
import ru.aston.university.service.api.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private static final CourseServiceImpl instance = new CourseServiceImpl();

    private final CourseRepositoryImpl repository;

    public CourseServiceImpl() {
        this.repository = CourseRepositoryImpl.getInstance();
    }

    public List<Course> readAll() {

        return this.repository.readAll();
    }

    public String create(CourseNoId course){

        return this.repository.create(course);
    }

    public void delete(Long id){

        this.repository.delete(id);

    }

    public void update(CourseNoId course, Long id)
    {
        this.repository.update(course, id);
    }

    public static CourseServiceImpl getInstance() {
        return instance;
    }

}
