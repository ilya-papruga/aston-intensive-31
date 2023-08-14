package ru.aston.university.service;

import ru.aston.university.repository.GroupRepositoryImpl;
import ru.aston.university.dto.GroupNoId;
import ru.aston.university.entity.Group;
import ru.aston.university.service.api.GroupService;

import java.util.List;


public class GroupServiceImpl implements GroupService {

    private static final GroupServiceImpl instance = new GroupServiceImpl();

    private final GroupRepositoryImpl repository;

    public GroupServiceImpl() {
        this.repository = GroupRepositoryImpl.getInstance();
    }

    public List<Group> readAll() {

        return this.repository.readAll();
    }

    public String create(GroupNoId group){

        return this.repository.create(group);
    }

    public void delete(Long id){

        this.repository.delete(id);

    }

    public void update(GroupNoId group, Long id)
    {
        this.repository.update(group, id);
    }

    public static GroupServiceImpl getInstance() {
        return instance;
    }

}
