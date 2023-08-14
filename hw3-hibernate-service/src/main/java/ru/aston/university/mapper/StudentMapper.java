package ru.aston.university.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.aston.university.dto.request.StudentDtoRequest;
import ru.aston.university.dto.response.StudentDtoResponse;
import ru.aston.university.entity.Course;
import ru.aston.university.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDtoRequest toDtoRequest(Student entity);

    default StudentDtoResponse toDtoResponse(Student entity) {

        if (entity == null) {
            return null;
        }

        StudentDtoResponse studentDtoResponse = new StudentDtoResponse();

        studentDtoResponse.setId(entity.getId());
        studentDtoResponse.setName(entity.getName());
        studentDtoResponse.setAge(entity.getAge());
        studentDtoResponse.setScore(entity.getScore());
        studentDtoResponse.setOlympicGamer(entity.isOlympicGamer());

        if (entity.getGroup() != null) {
            studentDtoResponse.setGroup(entity.getGroup().getNumber());
        }

        List<Course> list = entity.getCourses();

        if (list != null) {

            List<String> dtoList = list.stream()
                    .map(Course::getTitle)
                    .collect(Collectors.toList());
            studentDtoResponse.setCourses(dtoList);
        }
        return studentDtoResponse;
    }

    Student toEntity(StudentDtoRequest dto);

}
