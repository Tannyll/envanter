package com.emirci.envanter.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

//@Configuration
//@EnableTransactionManagement
public class DBConfiguration {
    @Value("${spring.datasource.driver-class-name}")
    private String DRIVER;

    @Value("${spring.datasource.password}")
    private String PASSWORD;

    @Value("${spring.datasource.url}")
    private String URL;

    @Value("${spring.datasource.username}")
    private String USERNAME;

    @Value("${spring.jpa.hibernate.dialect}")
    private String DIALECT;

    @Value("${spring.jpa.show-sql}")
    private String SHOW_SQL;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String HBM2DDL_AUTO;

    @Value("${entitymanager.packagesToScan}")
    private String PACKAGES_TO_SCAN;

    @Bean
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://172.16.101.36;databaseName=OTS");
        dataSource.setUsername("OTS");
        dataSource.setPassword("Ae25SeE8");
        return dataSource;
    }

    //http://www.onlinetutorialspoint.com/spring-boot/spring-boot-hibernate-integration-example.html
    //http://www.codesenior.com/en/tutorial/Spring-Generic-DAO-and-Generic-Service-Implementation
    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().getObject().createEntityManager();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.SQLServer2012Dialect");

        return jpaVendorAdapter;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", DIALECT);
        hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
        return hibernateProperties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setJpaVendorAdapter(jpaVendorAdapter());
        em.setPackagesToScan("com.emirci.envanter");
        em.setJpaProperties(hibernateProperties());
        return em;
    }

/*    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.emirci.envanter" });
        sessionFactory.setAnnotatedPackages(new String[] { "com.emirci.envanter" });
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }*/

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }


    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }

    @Bean
    public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }
}