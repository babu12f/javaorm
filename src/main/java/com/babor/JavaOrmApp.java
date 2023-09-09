package com.babor;

/**
 * JavaOrmApp!
 *
 */
public class JavaOrmApp {

    public static void main( String[] args ) {
        HibernateNativeApisHibernatePropertiesSessionFactory sessionFactory =
                new HibernateNativeApisHibernatePropertiesSessionFactory();
        sessionFactory.test();
    }
}
