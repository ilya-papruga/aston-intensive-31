package ru.aston.university.repository.api;

import ru.aston.university.dto.CourseNoId;
import ru.aston.university.entity.Course;

import java.util.List;

public interface CourseRepository extends AutoCloseable{

    /**
     * Получить все курсы
     */
    List<Course> readAll();

    /**
     * Создать новый курс
     * @param course курс без id
     */
    String create(CourseNoId course);

    /**
     * Удалить курс
     * @param id id курса
     */
    void delete(Long id);


    /**
     * Обновление записи по курсу
     * @param course обновлённное название курса
     * @param id указанаие id курса
     */
    void update(CourseNoId course, Long id);

}
