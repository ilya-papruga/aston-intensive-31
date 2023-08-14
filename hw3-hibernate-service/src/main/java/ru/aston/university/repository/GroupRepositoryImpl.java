package ru.aston.university.repository;

import ru.aston.university.configuration.ManagerFactory;
import ru.aston.university.dto.request.GroupDtoRequest;
import ru.aston.university.entity.Group;
import ru.aston.university.repository.api.GroupRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class GroupRepositoryImpl implements GroupRepository {

    private final static GroupRepositoryImpl instance = new GroupRepositoryImpl();

    private final ManagerFactory managerFactory;

    public GroupRepositoryImpl() {
        this.managerFactory = ManagerFactory.getInstance();
    }

    public List<Group> readAll() {

        EntityManager entityManager = managerFactory.createEntityManager();
        List<Group> result = entityManager.createQuery("from Group", Group.class).getResultList();
        entityManager.close();
        return result;
    }


    public void create(Group group) {

        EntityManager entityManager = managerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(group);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void update(GroupDtoRequest group, Long id) {

        EntityManager entityManager = managerFactory.createEntityManager();

        Group entity = entityManager.find(Group.class, id);

        entity.setNumber(group.getNumber());

        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void delete(Long id) {

        EntityManager entityManager = managerFactory.createEntityManager();

        Group group = entityManager.find(Group.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(group);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static GroupRepositoryImpl getInstance() {
        return instance;
    }

}
