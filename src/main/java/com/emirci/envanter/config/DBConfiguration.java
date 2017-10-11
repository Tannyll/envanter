package com.emirci.envanter.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

//@Configuration
//@EnableTransactionManagement
//@PropertySource(value = {"classpath:application-dev.properties"})
public class DBConfiguration {

    private Environment env;

    public JpaProperties jpaProperties;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSource.setUrl(env.getRequiredProperty("db.url"));
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));
        return dataSource;
    }

    @Bean("sessionFactory")
    @Primary
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.emirci.envanter.model"});

        Properties hibernateProp = new Properties();

        hibernateProp.put(AvailableSettings.DIALECT, env.getRequiredProperty("hibernate.dialect"));
        hibernateProp.put(AvailableSettings.SHOW_SQL, env.getRequiredProperty("hibernate.show-sql"));
        hibernateProp.put(AvailableSettings.HBM2DDL_AUTO, jpaProperties.getHibernate().getDdlAuto());
        //env.getRequiredProperty("hibernate.ddl-auto"));
        hibernateProp.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, env.getRequiredProperty("hibernate.current.session.context.class"));

        sessionFactory.setHibernateProperties(hibernateProp);

        return sessionFactory;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.emirci.envanter.model"});
        em.setPersistenceUnitName("notDefaultDb");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }


}

//http://www.onlinetutorialspoint.com/spring-boot/spring-boot-hibernate-integration-example.html
//http://www.codesenior.com/en/tutorial/Spring-Generic-DAO-and-Generic-Service-Implementation