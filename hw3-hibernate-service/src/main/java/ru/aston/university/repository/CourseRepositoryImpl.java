package ru.aston.university.repository;

import ru.aston.university.configuration.ManagerFactory;
import ru.aston.university.dto.request.CourseDtoRequest;
import ru.aston.university.entity.Course;
import ru.aston.university.repository.api.CourseRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository {

    private final static CourseRepositoryImpl instance = new CourseRepositoryImpl();

    private final ManagerFactory managerFactory;

    public CourseRepositoryImpl() {
        this.managerFactory = ManagerFactory.getInstance();
    }

    public List<Course> readAll() {

        EntityManager entityManager = managerFactory.createEntityManager();
        List<Course> result = entityManager.createQuery("from Course", Course.class).getResultList();
        entityManager.close();
        return result;

    }


    public void create(Course course) {

        EntityManager entityManager = managerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void update(CourseDtoRequest course, Long id) {

        EntityManager entityManager = managerFactory.createEntityManager();

        Course entity = entityManager.find(Course.class, id);

        entity.setTitle(course.getTitle());

        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();


    }

    public void delete(Long id) {

        EntityManager entityManager = managerFactory.createEntityManager();

        Course course = entityManager.find(Course.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(course);
        entityManager.getTransaction().commit();
        entityManager.close();

    }


    public static CourseRepositoryImpl getInstance() {
        return instance;
    }

}
