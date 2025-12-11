package mk.ukim.finki.wp.lab.config;

import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import mk.ukim.finki.wp.lab.web.AddReviewServlet;
import mk.ukim.finki.wp.lab.web.ChefDetailsServlet;
import mk.ukim.finki.wp.lab.web.ChefListServlet;
import mk.ukim.finki.wp.lab.web.DishServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<ChefListServlet> chefListServletRegistration(
            SpringTemplateEngine springTemplateEngine,
            ChefService chefService) {
        ServletRegistrationBean<ChefListServlet> registration = new ServletRegistrationBean<>(
                new ChefListServlet(springTemplateEngine, chefService), "/listChefs");
        registration.setName("ChefListServlet");
        return registration;
    }

    @Bean
    public ServletRegistrationBean<ChefDetailsServlet> chefDetailsServletRegistration(
            SpringTemplateEngine springTemplateEngine,
            ChefService chefService,
            DishService dishService) {
        ServletRegistrationBean<ChefDetailsServlet> registration = new ServletRegistrationBean<>(
                new ChefDetailsServlet(springTemplateEngine, chefService, dishService), "/chefDetails");
        registration.setName("ChefDetailsServlet");
        return registration;
    }

    @Bean
    public ServletRegistrationBean<DishServlet> dishServletRegistration(
            SpringTemplateEngine springTemplateEngine,
            ChefService chefService,
            DishService dishService) {
        ServletRegistrationBean<DishServlet> registration = new ServletRegistrationBean<>(
                new DishServlet(springTemplateEngine, chefService, dishService), "/dish");
        registration.setName("DishServlet");
        return registration;
    }

    @Bean
    public ServletRegistrationBean<AddReviewServlet> addReviewServletRegistration(
            SpringTemplateEngine springTemplateEngine,
            ChefService chefService) {
        ServletRegistrationBean<AddReviewServlet> registration = new ServletRegistrationBean<>(
                new AddReviewServlet(springTemplateEngine, chefService), "/addReview");
        registration.setName("AddReview");
        return registration;
    }
}

