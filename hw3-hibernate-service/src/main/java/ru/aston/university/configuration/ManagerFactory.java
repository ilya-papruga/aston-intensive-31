package ru.aston.university.configuration;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManagerFactory {

    private static final ManagerFactory instance = new ManagerFactory();
    private final EntityManagerFactory sessionFactory;

    public ManagerFactory() {
        this.sessionFactory = Persistence.createEntityManagerFactory("ru.aston.university.entity");
    }

    public EntityManager createEntityManager() {
        return sessionFactory.createEntityManager();
    }

    public static ManagerFactory getInstance() {
        return instance;
    }
}
