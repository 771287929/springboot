package com.artbrain;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.artbrain.dao.UserDetailsServiceDAO;

@SpringBootApplication
@EnableAutoConfiguration
public class Application extends WebMvcConfigurerAdapter {

  @Autowired
  DataSource dataSource;

  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new UserDetailsServiceDAO();
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/home").setViewName("home");
    registry.addViewController("/").setViewName("home");
    registry.addViewController("/profile").setViewName("profile");
  }
  
 /* 默认再prefix：/templates/，suffix:.html 
  * @Bean
  public InternalResourceViewResolver viewResolver() {
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setPrefix("/WEB-INF/view/");
      resolver.setSuffix(".jsp");
      return resolver;
  }*/

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
