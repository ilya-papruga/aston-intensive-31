package ru.aston.university.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ru.aston.university.dto.CourseNoId;
import ru.aston.university.entity.Course;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseRepositoryTest {

    private final CourseRepositoryImpl repository;

    public CourseRepositoryTest() {
        this.repository = CourseRepositoryImpl.getInstance();
    }

    @Order(1)
    @Test
    public void readAllTest() {
        List<Course> list = repository.readAll();

        assertThat(list.size()).isEqualTo(6);
        assertThat(list.get(0).getId()).isEqualTo(2);
        assertThat(list.get(0).getTitle()).isEqualTo("Spanish");
    }

    @Order(2)
    @Test
    public void createTest() {

        repository.create(new CourseNoId("Arabic"));

        List<Course> list = repository.readAll();
        assertThat(list.size()).isEqualTo(7);

    }

    @Order(3)
    @Test
    public void updateTest() {

        List<Course> list = repository.readAll();

        repository.update(new CourseNoId("Chinese"), list.get((list.size() - 1)).getId());

        List<Course> list2 = repository.readAll();

        assertThat(list2.get(list2.size() - 1).getTitle()).isEqualTo("Chinese");
    }

    @Order(4)
    @Test
    public void deleteTest() {

        List<Course> list = repository.readAll();

        repository.delete(list.get((list.size() - 1)).getId());

        List<Course> list2 = repository.readAll();

        assertThat(list2.size()).isEqualTo(6);
    }


}
