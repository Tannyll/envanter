package com.emirci.envanter;

import com.emirci.envanter.config.Initializer;
import com.emirci.envanter.config.WebMvcConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Locale;

@Configuration
@ComponentScan
@EnableJpaRepositories
@EnableAutoConfiguration
@SpringBootApplication
public class EnvanterApplication extends SpringBootServletInitializer implements ServletContextAware {

    private final String PARAM_LANGUAGE = "lang";

    public static void main(String[] args) throws Throwable {
        //ApplicationContext ctx = SpringApplication.run(EnvanterApplication.class);

        SpringApplication.run(EnvanterApplication.class, args);

        //SpringApplication.run(HomeController.class,args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(EnvanterApplication.class, WebMvcConfigurerAdapter.class, Initializer.class);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

    }

    @Bean
    public ServletContextInitializer servletContextCustomizer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

                servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".html");
                servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", Boolean.TRUE.toString());

                servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
                servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
                servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");

                servletContext.setInitParameter("primefaces.THEME", "bootstrap");
                servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
                servletContext.setInitParameter("primefaces.UPLOADER", "commons");
                servletContext.setInitParameter("encoding", "UTF-8");
            }
        };
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
