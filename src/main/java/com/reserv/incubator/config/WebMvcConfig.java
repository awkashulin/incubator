package com.reserv.incubator.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(LiquibaseProperties.class)
public class WebMvcConfig extends WebMvcConfigurationSupport {
    public static final String CHARACTER_ENCODING = "UTF-8";

    @Autowired
    LiquibaseProperties properties;
    @Autowired
    DataSource dataSource;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");

        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/javascripts/**").addResourceLocations("classpath:/static/javascripts/");
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding(CHARACTER_ENCODING);
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        properties.setChangeLog("classpath:/liquibase/changelog-master.xml");
        liquibase.setChangeLog(this.properties.getChangeLog());
        liquibase.setContexts(this.properties.getContexts());
        liquibase.setDataSource(dataSource);
        liquibase.setDefaultSchema(this.properties.getDefaultSchema());
        liquibase.setDropFirst(this.properties.isDropFirst());
        liquibase.setShouldRun(this.properties.isEnabled());
        return liquibase;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }
}
