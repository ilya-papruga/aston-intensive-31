package ru.aston.university.repository.api;

import ru.aston.university.dto.CrossCourseStudentDto;
import ru.aston.university.dto.CrossCourseStudentDtoGet;
import ru.aston.university.dto.CrossGroupStudentDto;
import ru.aston.university.dto.CrossGroupStudentDtoGet;

import java.util.List;

public interface CrossCourseStudentRepo extends AutoCloseable {

    /**
     * Получить все связи курсов и студентов
     */
    List<CrossCourseStudentDtoGet> readAll();



    /**
     * Создать новую связь
     * @param cross данные курса и списка студентов
     */
    void create(CrossCourseStudentDto cross);


    /**
     * Удалить существующую связь
     * @param cross данные курса и списка студентов
     */
    void delete(CrossCourseStudentDto cross);

}
