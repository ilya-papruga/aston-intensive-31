package ru.aston.university.service;

import ru.aston.university.dto.CrossCourseStudentDto;
import ru.aston.university.dto.CrossCourseStudentDtoGet;
import ru.aston.university.repository.CrossCourseStudentRepoImpl;
import ru.aston.university.service.api.CrossCourseStudentService;

import java.util.List;

public class CrossCourseStudentServiceImpl implements CrossCourseStudentService {

    private static final CrossCourseStudentServiceImpl instance = new CrossCourseStudentServiceImpl();

    private final CrossCourseStudentRepoImpl repository;

    public CrossCourseStudentServiceImpl() {
        this.repository = CrossCourseStudentRepoImpl.getInstance();
    }

    public List<CrossCourseStudentDtoGet> readAll() {
        return this.repository.readAll();
    }

    public void create(CrossCourseStudentDto cross) {
        this.repository.create(cross);
    }

    public void delete(CrossCourseStudentDto cross) {
        this.repository.delete(cross);
    }


    public static CrossCourseStudentServiceImpl getInstance() {
        return instance;
    }
}
