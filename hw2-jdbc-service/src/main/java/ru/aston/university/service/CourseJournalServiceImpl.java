package ru.aston.university.service;

import ru.aston.university.dto.CourseDto;
import ru.aston.university.dto.CourseJournalDto;
import ru.aston.university.dto.StudentCoursesDto;
import ru.aston.university.repository.CourseJournalRepositoryImpl;
import ru.aston.university.service.api.CourseJournalService;

import java.util.List;

public class CourseJournalServiceImpl implements CourseJournalService {

     private static final CourseJournalServiceImpl instance = new CourseJournalServiceImpl();

    private final CourseJournalRepositoryImpl repository;

    public CourseJournalServiceImpl() {
        this.repository = CourseJournalRepositoryImpl.getInstance();
    }

    public CourseDto readCourse(String title){
        return this.repository.readCourseStudents(title);
    }

    @Override
    public StudentCoursesDto readStudent(String name) {
        return this.repository.readStudentCourses(name);
    }

    public List<CourseJournalDto> readAll(){
        return this.repository.readAll();
    }

    public static CourseJournalServiceImpl getInstance() {
        return instance;
    }

}
