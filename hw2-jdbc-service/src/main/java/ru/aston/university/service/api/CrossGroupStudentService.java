package ru.aston.university.service.api;

import ru.aston.university.dto.CrossGroupStudentDto;
import ru.aston.university.dto.CrossGroupStudentDtoGet;

import java.util.List;

public interface CrossGroupStudentService {

     List<CrossGroupStudentDtoGet> readAll();

    void create(CrossGroupStudentDto cross);

    void delete(CrossGroupStudentDto cross);

}
