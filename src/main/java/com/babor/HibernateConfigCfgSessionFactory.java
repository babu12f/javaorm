package com.babor;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.util.List;

public class HibernateConfigCfgSessionFactory {
    private SessionFactory sessionFactory;

    public HibernateConfigCfgSessionFactory() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public void test() {
        System.out.println("========= SessionFactory =========");

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(new User("Babor", LocalDate.now()));
            session.persist(new User("Nadim", LocalDate.now()));
            session.getTransaction().commit();
        }

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> userList = session.createQuery("select u from User u", User.class).list();
            userList.forEach(System.out::println);
            session.getTransaction().commit();
        }

        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(cb.equal(root.get("name"), "Babor"));

            TypedQuery<User> query = session.createQuery(criteriaQuery);
            List<User> userList = query.getResultList();
            userList.forEach(System.out::println);
        }
    }
}
