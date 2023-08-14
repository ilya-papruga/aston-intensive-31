package ru.aston.university.service;

import ru.aston.university.dto.request.StudentDtoRequest;
import ru.aston.university.dto.response.StudentDtoResponse;
import ru.aston.university.mapper.StudentMapper;
import ru.aston.university.repository.StudentRepositoryImpl;
import ru.aston.university.service.api.StudentService;

import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {

    private static final StudentServiceImpl instance = new StudentServiceImpl();

    private final StudentRepositoryImpl repository;

    public StudentServiceImpl() {
        this.repository = StudentRepositoryImpl.getInstance();
    }

    public List<StudentDtoResponse> readAll() {

        return this.repository.readAll().stream()
                .map(StudentMapper.INSTANCE::toDtoResponse)
                .collect(Collectors.toList());
    }

    public void create(StudentDtoRequest student) {

        this.repository.create(StudentMapper.INSTANCE.toEntity(student));
    }

    public void delete(Long id) {
        this.repository.delete(id);
    }

    public void update(StudentDtoRequest student, Long id) {
        this.repository.update(student, id);
    }

    public static StudentServiceImpl getInstance() {
        return instance;
    }

}
