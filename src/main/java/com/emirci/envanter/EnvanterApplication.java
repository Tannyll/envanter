package com.emirci.envanter;

import com.emirci.envanter.config.WebAppInitializer;
import com.emirci.envanter.config.WebMvcConfigurerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.faces.webapp.FacesServlet;

@Configuration
@ComponentScan
//@EnableJpaRepositories("com.emirci.envanter.Repository")
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
@SpringBootApplication

public class EnvanterApplication extends SpringBootServletInitializer implements ApplicationContextAware {

    private final String PARAM_LANGUAGE = "lang";
    private static final Class<EnvanterApplication> applicationClass = EnvanterApplication.class;
    private static final Logger log = LoggerFactory.getLogger(applicationClass);
    private ApplicationContext applicationContext;

    public static void main(String[] args) throws Throwable {
        //ApplicationContext ctx = SpringApplication.run(EnvanterApplication.class);
        SpringApplication.run(EnvanterApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EnvanterApplication.class, WebMvcConfigurerAdapter.class, WebAppInitializer.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

    @Bean
    @Description("FacesServlet Resolver")
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();

        ServletRegistrationBean servletRegistrationBean
                = new ServletRegistrationBean(servlet, "*.html", "*.jsf");
        return servletRegistrationBean;
    }

    @Bean
    @Description("InternalResourceViewResolver Resolver")
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        return resolver;
    }

    @Bean

    @Autowired
    public LocalValidatorFactoryBean validatorFactoryBean(MessageSource messageSource) {
        LocalValidatorFactoryBean lvfb = new LocalValidatorFactoryBean();
        lvfb.setValidationMessageSource(messageSource);
        return lvfb;
    }

}

/*
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactoryBean() {
        return new HibernateJpaSessionFactoryBean();
    }*/


