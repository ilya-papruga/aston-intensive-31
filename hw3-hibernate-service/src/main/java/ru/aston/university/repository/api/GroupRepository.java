package ru.aston.university.repository.api;

import ru.aston.university.dto.request.GroupDtoRequest;
import ru.aston.university.entity.Group;

import java.util.List;

public interface GroupRepository {


    List<Group> readAll();


    void create(Group group);


    void delete(Long id);


    void update(GroupDtoRequest group, Long id);

}
