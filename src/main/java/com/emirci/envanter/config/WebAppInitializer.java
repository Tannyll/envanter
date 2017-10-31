package com.emirci.envanter.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
@ComponentScan
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        //AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        //rootContext.register(WebMvcConfigurerAdapter.class);

/*        sc.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "true");
        sc.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
        sc.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

        sc.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".html");
        sc.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", Boolean.TRUE.toString());

        sc.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
        sc.setInitParameter("facelets.DEVELOPMENT", "true");
        sc.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");

        sc.setInitParameter("primefaces.THEME", "bootstrap");
        sc.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
        sc.setInitParameter("primefaces.UPLOADER", "commons");*/
        sc.setInitParameter("encoding", "UTF-8");

        //sc.addListener(new ContextLoaderListener(rootContext));
    }
}