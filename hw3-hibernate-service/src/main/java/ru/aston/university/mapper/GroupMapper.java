package ru.aston.university.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.aston.university.dto.request.GroupDtoRequest;
import ru.aston.university.dto.response.GroupDtoResponse;
import ru.aston.university.entity.Group;
import ru.aston.university.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GroupDtoRequest toDtoRequest(Group entity);

    default GroupDtoResponse toDtoResponse(Group entity) {
        if (entity == null) {
            return null;
        }

        GroupDtoResponse groupDtoResponse = new GroupDtoResponse();

        groupDtoResponse.setId(entity.getId());
        groupDtoResponse.setNumber(entity.getNumber());
        List<Student> list = entity.getStudentList();
        if (list != null) {

            List<String> dtoList = list.stream()
                    .map(Student::getName)
                    .collect(Collectors.toList());

            groupDtoResponse.setStudentList(dtoList);
        }

        return groupDtoResponse;
    }

    Group toEntity(GroupDtoRequest dto);

}
