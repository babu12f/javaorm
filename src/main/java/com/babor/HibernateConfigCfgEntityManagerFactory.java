package com.babor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.util.List;

public class HibernateConfigCfgEntityManagerFactory {
    private EntityManagerFactory sessionFactory;
    public HibernateConfigCfgEntityManagerFactory() {
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
        System.out.println("========= EntityManagerFactory =========");

        try(EntityManager em = sessionFactory.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(new User("Babor", LocalDate.now()));
            em.persist(new User("Nadim", LocalDate.now()));
            em.getTransaction().commit();
        }

        try(EntityManager em = sessionFactory.createEntityManager()) {
            em.getTransaction().begin();
            List<User> userList = em.createQuery("select u from User u", User.class).getResultList();
            userList.forEach(System.out::println);
            em.getTransaction().commit();
        }

        try(EntityManager em = sessionFactory.createEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(cb.equal(root.get("name"), "Babor"));

            TypedQuery<User> query = em.createQuery(criteriaQuery);
            List<User> userList = query.getResultList();
            userList.forEach(System.out::println);
        }
    }
}
