package ru.aston.university.repository.api;

import ru.aston.university.dto.CrossGroupStudentDto;
import ru.aston.university.dto.CrossGroupStudentDtoGet;

import java.util.List;

public interface CrossGroupStudentRepo extends AutoCloseable {

    /**
     * Получить все связи групп и студентов
     */
    List<CrossGroupStudentDtoGet> readAll();



    /**
     * Создать новую связь
     * @param cross данные группы и списка студентов
     */
    void create(CrossGroupStudentDto cross);


    /**
     * Удалить существующую связь
     * @param cross данные группы и списка студентов
     */
    void delete(CrossGroupStudentDto cross);

}
