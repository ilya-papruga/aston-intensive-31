package ru.aston.university.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.aston.university.dto.request.CourseDtoRequest;
import ru.aston.university.dto.response.CourseDtoResponse;
import ru.aston.university.entity.Course;
import ru.aston.university.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseDtoRequest toDtoRequest(Course entity);

    default CourseDtoResponse toDtoResponse(Course entity) {
        if (entity == null) {
            return null;
        }

        CourseDtoResponse courseDtoResponse = new CourseDtoResponse();

        courseDtoResponse.setId(entity.getId());
        courseDtoResponse.setTitle(entity.getTitle());
        List<Student> list = entity.getStudents();
        if (list != null) {

            List<String> dtoList = list.stream()
                    .map(Student::getName)
                    .collect(Collectors.toList());

            courseDtoResponse.setStudentList(dtoList);
        }

        return courseDtoResponse;
    }

    Course toEntity(CourseDtoRequest dto);


}
