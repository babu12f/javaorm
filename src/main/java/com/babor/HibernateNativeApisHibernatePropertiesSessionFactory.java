package com.babor;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.util.List;

public class HibernateNativeApisHibernatePropertiesSessionFactory {
    private SessionFactory sessionFactory;

    public HibernateNativeApisHibernatePropertiesSessionFactory() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(User.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public void test() {
        System.out.println("========= SessionFactory =========");

        sessionFactory.inTransaction(session -> {
            System.out.println("Saving data");
            session.persist(new User("Babor", LocalDate.now()));
            session.persist(new User("Nadim", LocalDate.now()));
        });

        sessionFactory.inTransaction(session -> {
            System.out.println("Getting Data");
            session.createSelectionQuery("select u from User u", User.class)
                    .getResultList()
                    .forEach(System.out::println);
        });

        sessionFactory.inTransaction(session -> {
            System.out.println("Criteria API");
            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(cb.equal(root.get("name"), "Babor"));

            TypedQuery<User> query = session.createQuery(criteriaQuery);
            List<User> userList = query.getResultList();
            userList.forEach(System.out::println);
        });
    }
}
