package ru.aston.university.repository.api;

import ru.aston.university.dto.StudentDto;
import ru.aston.university.dto.StudentNoId;
import ru.aston.university.entity.Student;

import java.util.List;

public interface StudentRepository extends AutoCloseable{

    /**
     * Получить всех студентов
     */
    List<Student> readAll();

    /**
     * Создать нового студента
     * @param student студент без id
     */
    String create(StudentNoId student);

    /**
     * Удалить студента
     * @param id id студента
     */
    void delete(Long id);


    /**
     * Обновление записи по студенту
     * @param student обновлённая информация
     * @param id указанаие id студента
     */
    void update(StudentNoId student, Long id);

}
