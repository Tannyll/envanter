package com.emirci.envanter.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:application-dev.properties"})
public class DBConfiguration {

    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "db.datasource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSource.setUrl(env.getRequiredProperty("db.url"));
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    @Primary
    public LocalSessionFactoryBean sessionFactory() throws ClassNotFoundException {

        Properties hibernateProp = new Properties();

        hibernateProp.put(AvailableSettings.DIALECT, env.getRequiredProperty("hibernate.dialect"));
        hibernateProp.put(AvailableSettings.SHOW_SQL, env.getRequiredProperty("hibernate.show-sql"));
        hibernateProp.put(AvailableSettings.HBM2DDL_AUTO, env.getRequiredProperty("hibernate.ddl-auto"));
        hibernateProp.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, env.getRequiredProperty("hibernate.current.session.context.class"));

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"com.emirci.envanter.model"});
        sessionFactory.setHibernateProperties(hibernateProp);

        return sessionFactory;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource());
        em.setPersistenceUnitName("customPersistence");
        em.setPackagesToScan(new String[]{"com.emirci.envanter.model"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        //HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        //vendorAdapter.setGenerateDdl(false);

        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }

    @Bean(name = "hibernateTemplate")
    public HibernateTemplate hibernateTemplate() throws ClassNotFoundException {

        return new HibernateTemplate(sessionFactory().getObject());
    }

    @Bean(name = "customTransactionManager")
    public HibernateTransactionManager transactionManager(SessionFactory sf) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();

        transactionManager.setSessionFactory(sf);

        return transactionManager;
    }


}
//https://stackoverflow.com/questions/37704414/spring-boot-hibernate-5-integration-error
//http://www.onlinetutorialspoint.com/spring-boot/spring-boot-hibernate-integration-example.html
//http://www.codesenior.com/en/tutorial/Spring-Generic-DAO-and-Generic-Service-Implementation