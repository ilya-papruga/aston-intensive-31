package ru.aston.university.service.api;

import ru.aston.university.dto.StudentDto;
import ru.aston.university.dto.StudentNoId;
import ru.aston.university.service.StudentServiceImpl;

public interface StudentService {

    String create(StudentNoId student);

    void delete(Long id);

    void update(StudentNoId student, Long id);

}
