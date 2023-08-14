package ru.aston.university.repository.api;


import ru.aston.university.dto.request.StudentDtoRequest;
import ru.aston.university.entity.Student;

import java.util.List;

public interface StudentRepository {


    List<Student> readAll();


    void create(Student student);


    void delete(Long id);


    void update(StudentDtoRequest student, Long id);

}
