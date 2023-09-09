package com.babor;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

public class HibernateJpaStandardApiPersistenceXmlEntityManagerFactory {
    private final EntityManagerFactory entityManagerFactory;
    public HibernateJpaStandardApiPersistenceXmlEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("com.babor.jpa");
    }

    public void test() {
        System.out.println("========= EntityManagerFactory =========");

        inTransaction(em -> {
            System.out.println("Saving data");

            em.persist(new User("Babor", LocalDate.now()));
            em.persist(new User("Nadim", LocalDate.now()));
        });

        inTransaction(em -> {
            System.out.println("Reading data");

            List<User> userList = em.createQuery("from User", User.class)
                    .getResultList();
            userList.forEach(System.out::println);
        });

        inTransaction(em -> {
            System.out.println("Criteria API");

            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(cb.equal(root.get("name"), "Babor"));

            TypedQuery<User> query = em.createQuery(criteriaQuery);
            List<User> userList = query.getResultList();
            userList.forEach(System.out::println);
        });
    }

    private void inTransaction(Consumer<EntityManager> work) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            work.accept(entityManager);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        finally {
            entityManager.close();
        }
    }
}
