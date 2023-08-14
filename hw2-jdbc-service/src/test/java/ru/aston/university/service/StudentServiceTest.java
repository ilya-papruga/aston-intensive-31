package ru.aston.university.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ru.aston.university.dto.StudentNoId;
import ru.aston.university.entity.Student;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest {

    private final StudentServiceImpl service;

    public StudentServiceTest() {
        this.service = StudentServiceImpl.getInstance();
    }

    @Order(1)
    @Test
    public void readAllTest() {
        List<Student> list = service.readAll();

        assertThat(list.size()).isEqualTo(9);
        assertThat(list.get(0).getId()).isEqualTo(1);
        assertThat(list.get(1).getName()).isEqualTo("Vlad");
    }

    @Order(2)
    @Test
    public void createTest() {

        service.create(new StudentNoId("Ilya", 30, 10, true));

        List<Student> list = service.readAll();
        assertThat(list.size()).isEqualTo(10);

    }

    @Order(3)
    @Test
    public void updateTest() {

        List<Student> list = service.readAll();

        service.update(new StudentNoId("Masha", 28, 9, true), (long) list.get((list.size() - 1)).getId());

        List<Student> list2 = service.readAll();

        assertThat(list2.get(list.size() - 1).getName()).isEqualTo("Masha");
    }

    @Order(4)
    @Test
    public void deleteTest() {

        List<Student> list = service.readAll();

        service.delete(list.get((list.size() - 1)).getId());

        List<Student> list2 = service.readAll();

        assertThat(list2.size()).isEqualTo(9);
    }


}
