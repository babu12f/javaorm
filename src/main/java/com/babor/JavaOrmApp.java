package com.babor;

/**
 * JavaOrmApp!
 *
 */
public class JavaOrmApp {

    public static void main( String[] args ) {
        HibernateJpaStandardApiPersistenceXmlEntityManagerFactory entityManagerFactory =
                new HibernateJpaStandardApiPersistenceXmlEntityManagerFactory();
        entityManagerFactory.test();
    }
}
