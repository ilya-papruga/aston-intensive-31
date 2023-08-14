package ru.aston.university.service;

import ru.aston.university.repository.GroupJournalRepositoryImpl;
import ru.aston.university.dto.GroupDto;
import ru.aston.university.dto.GroupJournalDto;
import ru.aston.university.service.api.GroupJournalService;

import java.util.List;

public class GroupJournalServiceImpl implements GroupJournalService {

     private static final GroupJournalServiceImpl instance = new GroupJournalServiceImpl();

    private final GroupJournalRepositoryImpl repository;

    public GroupJournalServiceImpl() {
        this.repository = GroupJournalRepositoryImpl.getInstance();
    }

    public GroupDto readJournal(String groupNumber){
        return this.repository.readJournal(groupNumber);
    }

    public List<GroupJournalDto> readAll(){
        return this.repository.readAll();
    }

    public static GroupJournalServiceImpl getInstance() {
        return instance;
    }

}
