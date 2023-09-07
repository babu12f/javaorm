package com.babor;

/**
 * JavaOrmApp!
 *
 */
public class JavaOrmApp {

    public static void main( String[] args ) {
        HibernateConfigCfgSessionFactory sessionFactory = new HibernateConfigCfgSessionFactory();
        sessionFactory.test();

        HibernateConfigCfgEntityManagerFactory entityManagerFactory = new HibernateConfigCfgEntityManagerFactory();
        entityManagerFactory.test();
    }
}
