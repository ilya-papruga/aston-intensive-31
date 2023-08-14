package ru.aston.university.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ru.aston.university.dto.GroupNoId;
import ru.aston.university.entity.Group;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupRepositoryTest {

    private final GroupRepositoryImpl repository;

    public GroupRepositoryTest() {
        this.repository = GroupRepositoryImpl.getInstance();
    }

    @Order(1)
    @Test
    public void findAllTest() {
        List<Group> list = repository.readAll();

        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0).getId()).isEqualTo(1);
        assertThat(list.get(0).getNumber()).isEqualTo("K-888");
    }

    @Order(2)
    @Test
    public void createTest() {

        repository.create(new GroupNoId("K-100"));

        List<Group> list = repository.readAll();
        assertThat(list.size()).isEqualTo(4);

    }

    @Order(3)
    @Test
    public void updateTest() {

        List<Group> list = repository.readAll();

        repository.update(new GroupNoId("K-111"), (long) list.get((list.size() - 1)).getId());

        List<Group> list2 = repository.readAll();

        assertThat(list2.get(list.size() - 1).getNumber()).isEqualTo("K-111");
    }

    @Order(4)
    @Test
    public void deleteTest() {

        List<Group> list = repository.readAll();

        repository.delete(list.get((list.size() - 1)).getId());

        List<Group> list2 = repository.readAll();

        assertThat(list2.size()).isEqualTo(3);
    }


}
