package ru.aston.university.service;

import ru.aston.university.repository.StudentRepositoryImpl;
import ru.aston.university.dto.StudentNoId;
import ru.aston.university.entity.Student;
import ru.aston.university.service.api.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private static final StudentServiceImpl instance = new StudentServiceImpl();

    private final StudentRepositoryImpl repository;

    public StudentServiceImpl() {
        this.repository = StudentRepositoryImpl.getInstance();
    }

    public List<Student> readAll() {

        return this.repository.readAll();
    }

    public String create(StudentNoId student) {

        return this.repository.create(student);
    }

    public void delete(Long id) {
        this.repository.delete(id);
    }

    public void update(StudentNoId student, Long id) {
        this.repository.update(student, id);
    }

    public static StudentServiceImpl getInstance() {
        return instance;
    }

}
