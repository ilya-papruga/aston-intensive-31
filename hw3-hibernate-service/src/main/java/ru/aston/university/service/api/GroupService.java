package ru.aston.university.service.api;

import ru.aston.university.dto.request.GroupDtoRequest;
import ru.aston.university.dto.response.GroupDtoResponse;

import java.util.List;

public interface GroupService {

    List<GroupDtoResponse> readAll();

    void create(GroupDtoRequest group);

    void delete(Long id);

    void update(GroupDtoRequest group, Long id);

}
