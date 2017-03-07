package com.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@ComponentScan("com.hibernate")
public class HibernateApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean configureWebXml(DispatcherServlet dispacher){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean();
		registrationBean.setServlet(dispacher);
		registrationBean.addUrlMappings("/myApp/*");
		return registrationBean;
		
	}
}
