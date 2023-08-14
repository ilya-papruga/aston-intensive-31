package ru.aston.university.service;

import ru.aston.university.dto.request.GroupDtoRequest;
import ru.aston.university.dto.response.GroupDtoResponse;
import ru.aston.university.mapper.GroupMapper;
import ru.aston.university.repository.GroupRepositoryImpl;
import ru.aston.university.service.api.GroupService;

import java.util.List;
import java.util.stream.Collectors;


public class GroupServiceImpl implements GroupService {

    private static final GroupServiceImpl instance = new GroupServiceImpl();

    private final GroupRepositoryImpl repository;

    public GroupServiceImpl() {
        this.repository = GroupRepositoryImpl.getInstance();
    }

    public List<GroupDtoResponse> readAll() {

        return this.repository.readAll().stream()
                .map(GroupMapper.INSTANCE::toDtoResponse)
                .collect(Collectors.toList());
    }

    public void create(GroupDtoRequest group) {

        this.repository.create(GroupMapper.INSTANCE.toEntity(group));
    }

    public void delete(Long id) {

        this.repository.delete(id);

    }

    public void update(GroupDtoRequest group, Long id) {
        this.repository.update(group, id);
    }

    public static GroupServiceImpl getInstance() {
        return instance;
    }

}
