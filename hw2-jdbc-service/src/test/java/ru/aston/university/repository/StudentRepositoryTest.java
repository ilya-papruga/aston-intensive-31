package ru.aston.university.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ru.aston.university.dto.StudentNoId;
import ru.aston.university.entity.Student;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentRepositoryTest {

    private final StudentRepositoryImpl repository;

    public StudentRepositoryTest() {
        this.repository = StudentRepositoryImpl.getInstance();
    }

    @Order(1)
    @Test
    public void readAllTest() {
        List<Student> list = repository.readAll();

        assertThat(list.size()).isEqualTo(9);
        assertThat(list.get(0).getId()).isEqualTo(1);
        assertThat(list.get(1).getName()).isEqualTo("Vlad");
    }

    @Order(2)
    @Test
    public void createTest() {

        repository.create(new StudentNoId("Ilya", 30, 10, true));

        List<Student> list = repository.readAll();
        assertThat(list.size()).isEqualTo(10);

    }

    @Order(3)
    @Test
    public void updateTest() {

        List<Student> list = repository.readAll();

        repository.update(new StudentNoId("Masha", 28, 9, true), (long) list.get((list.size() - 1)).getId());

        List<Student> list2 = repository.readAll();

        assertThat(list2.get(list.size() - 1).getName()).isEqualTo("Masha");
    }

    @Order(4)
    @Test
    public void deleteTest() {

        List<Student> list = repository.readAll();

        repository.delete(list.get((list.size() - 1)).getId());

        List<Student> list2 = repository.readAll();

        assertThat(list2.size()).isEqualTo(9);
    }


}
