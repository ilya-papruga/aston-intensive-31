package ru.aston.university.service;

import ru.aston.university.dto.request.CourseDtoRequest;
import ru.aston.university.dto.response.CourseDtoResponse;
import ru.aston.university.mapper.CourseMapper;
import ru.aston.university.repository.CourseRepositoryImpl;
import ru.aston.university.service.api.CourseService;

import java.util.List;
import java.util.stream.Collectors;

public class CourseServiceImpl implements CourseService {

    private static final CourseServiceImpl instance = new CourseServiceImpl();

    private final CourseRepositoryImpl repository;

    public CourseServiceImpl() {
        this.repository = CourseRepositoryImpl.getInstance();
    }

    public List<CourseDtoResponse> readAll() {

        return this.repository.readAll().stream()
                .map(CourseMapper.INSTANCE::toDtoResponse)
                .collect(Collectors.toList());
    }

    public void create(CourseDtoRequest course) {

        this.repository.create(CourseMapper.INSTANCE.toEntity(course));
    }

    public void delete(Long id) {

        this.repository.delete(id);

    }

    public void update(CourseDtoRequest course, Long id) {
        this.repository.update(course, id);
    }

    public static CourseServiceImpl getInstance() {
        return instance;
    }

}
