package ru.aston.university.service;

import ru.aston.university.repository.CrossGroupStudentRepoImpl;
import ru.aston.university.dto.CrossGroupStudentDto;
import ru.aston.university.dto.CrossGroupStudentDtoGet;
import ru.aston.university.service.api.CrossGroupStudentService;

import java.util.List;

public class CrossGroupStudentServiceImpl implements CrossGroupStudentService {

    private static final CrossGroupStudentServiceImpl instance = new CrossGroupStudentServiceImpl();

    private final CrossGroupStudentRepoImpl repository;

    public CrossGroupStudentServiceImpl() {
        this.repository = CrossGroupStudentRepoImpl.getInstance();
    }

    public List<CrossGroupStudentDtoGet> readAll(){
        return this.repository.readAll();
    }

    public void create(CrossGroupStudentDto cross){
        this.repository.create(cross);
    }

    public void delete(CrossGroupStudentDto cross){
        this.repository.delete(cross);
    }


    public static CrossGroupStudentServiceImpl getInstance() {
        return instance;
    }
}
