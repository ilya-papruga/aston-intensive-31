package ru.aston.university.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ru.aston.university.dto.GroupNoId;
import ru.aston.university.entity.Group;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupServiceTest {

    private final GroupServiceImpl service;

    public GroupServiceTest() {
        this.service = GroupServiceImpl.getInstance();
    }

    @Order(1)
    @Test
    public void readAllTest(){

        List<Group> list = service.readAll();
        assertThat(list.size()).isEqualTo(3);

    }

    @Order(2)
    @Test
    public void createTest() {

        service.create(new GroupNoId("K-999"));
        List<Group> list = service.readAll();

        assertThat(list.size()).isEqualTo(4);
    }

    @Order(3)
    @Test
    public void updateTest() {
        List<Group> list = service.readAll();
        service.update(new GroupNoId("K-1000"), list.get((list.size() - 1)).getId());

        List<Group> list2 = service.readAll();
        assertThat(list2.get(list2.size() - 1).getNumber()).isEqualTo("K-1000");
    }

    @Order(4)
    @Test
    public void deleteTest() {

        List<Group> list = service.readAll();

        service.delete(list.get((list.size() - 1)).getId());

        List<Group> list2 = service.readAll();

        assertThat(list2.size()).isEqualTo(3);
    }


}
