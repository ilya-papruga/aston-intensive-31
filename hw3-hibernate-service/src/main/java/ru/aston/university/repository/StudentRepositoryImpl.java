package ru.aston.university.repository;

import ru.aston.university.configuration.ManagerFactory;
import ru.aston.university.dto.request.StudentDtoRequest;
import ru.aston.university.entity.Student;
import ru.aston.university.repository.api.StudentRepository;

import javax.persistence.EntityManager;
import java.util.List;


public class StudentRepositoryImpl implements StudentRepository {

    private final static StudentRepositoryImpl instance = new StudentRepositoryImpl();

    private final ManagerFactory managerFactory;

    public StudentRepositoryImpl() {
        this.managerFactory = ManagerFactory.getInstance();
    }


    public List<Student> readAll() {

        EntityManager entityManager = managerFactory.createEntityManager();
        List<Student> result = entityManager.createQuery(
                "from Student", Student.class).getResultList();
        entityManager.close();
        return result;
    }

    public void create(Student student) {

        EntityManager entityManager = managerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Long id) {

        EntityManager entityManager = managerFactory.createEntityManager();

        Student student = entityManager.find(Student.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(student);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void update(StudentDtoRequest student, Long id) {

        EntityManager entityManager = managerFactory.createEntityManager();

        Student entity = entityManager.find(Student.class, id);

        entity.setAge(student.getAge());
        entity.setName(student.getName());
        entity.setScore(student.getScore());
        entity.setOlympicGamer(student.isOlympicGamer());

        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public static StudentRepositoryImpl getInstance() {
        return instance;
    }
}
