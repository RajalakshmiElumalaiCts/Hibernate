package com.hibernate.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.hibernate")
@EnableTransactionManagement
public class HibernateConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(getDatasource());
		factoryBean.setHibernateProperties(getHibernateProperties());
		return factoryBean;
	}

	private Properties getHibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.connection.hbm2ddl.auto",
	              env.getProperties().getProperty("hibernate.connection.hbm2ddl.auto"));
		props.setProperty("hibernate.connection.dialect",
	              env.getProperties().getProperty("hibernate.connection.dialect"));
		props.setProperty("hibernate.globally_quoted_identifiers",
	             "true");
		props.setProperty("hibernate.connection.show_sql",
				 env.getProperties().getProperty("hibernate.connection.show_sql"));
		
		return null;
	}

	@Bean
	public DataSource getDatasource() {
		BasicDataSource dataSource = new BasicDataSource();
	      dataSource.setDriverClassName(env.getProperties().getProperty("hibernate.connection.driver_class"));
	      dataSource.setUrl(env.getProperties().getProperty("hibernate.connection.url"));
	      dataSource.setUsername(env.getProperties().getProperty("hibernate.connection.username"));
	      dataSource.setPassword(env.getProperties().getProperty("hibernate.connection.password"));
		return dataSource;
	}

	@Bean
	   @Autowired
	   public HibernateTransactionManager transactionManager() {
	  
	      HibernateTransactionManager txManager = new HibernateTransactionManager();
	      txManager.setSessionFactory(sessionFactory().getObject());
	 
	      return txManager;
	   }

	@Bean
	   public static PropertySourcesPlaceholderConfigurer PropertySourcesPlaceholderConfigurer() {
	      return new PropertySourcesPlaceholderConfigurer();
	   }
}
