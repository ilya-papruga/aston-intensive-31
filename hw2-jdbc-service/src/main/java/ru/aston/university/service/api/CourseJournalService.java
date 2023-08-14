package ru.aston.university.service.api;

import ru.aston.university.dto.CourseDto;
import ru.aston.university.dto.CourseJournalDto;
import ru.aston.university.dto.GroupDto;
import ru.aston.university.dto.GroupJournalDto;
import ru.aston.university.dto.StudentCoursesDto;

import java.util.List;

public interface CourseJournalService {

    CourseDto readCourse(String title);

    StudentCoursesDto readStudent(String name);

    List<CourseJournalDto> readAll();

}
