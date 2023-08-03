package ru.aston.university.repository.api;

import ru.aston.university.dto.CourseDto;
import ru.aston.university.dto.CourseJournalDto;
import ru.aston.university.dto.StudentCoursesDto;
import ru.aston.university.dto.StudentDto;

import java.util.List;

public interface CourseJournalRepository extends AutoCloseable {

    /**
     * Получить все журналы с курсами и студентами
     */
    List<CourseJournalDto> readAll();


    /**
     * Получить журнал студентов курса
     *
     * @param title название курса
     */
    CourseDto readCourseStudents(String title);

    /**
     * Получить список курсов студента
     *
     * @param title название курса
     */
    StudentCoursesDto readStudentCourses(String name);

}
