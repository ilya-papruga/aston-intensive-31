package ru.aston.university.service.api;

import ru.aston.university.dto.GroupNoId;
import ru.aston.university.entity.Group;

import java.util.List;

public interface GroupService {

    List<Group> readAll();

    String create(GroupNoId group);

    void delete(Long id);

    void update(GroupNoId group, Long id);

}
