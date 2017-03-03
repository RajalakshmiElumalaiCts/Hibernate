package com.hibernate.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
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
		factoryBean.setPackagesToScan(
		        new String[] { "com.hibernate.entity" });
		return factoryBean;
	}

	private Properties getHibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.connection.hbm2ddl.auto",
	              env.getProperty("hibernate.connection.hbm2ddl.auto"));
		props.setProperty("hibernate.connection.dialect",
	              env.getProperty("hibernate.connection.dialect"));
		props.setProperty("hibernate.globally_quoted_identifiers",
	             "true");
		props.setProperty("hibernate.connection.show_sql",
				 env.getProperty("hibernate.connection.show_sql"));
		props.setProperty("hibernate.connection.provider_class",
				env.getProperty("hibernate.connection.provider_class"));
		
		return props;
	}

	@Bean
	public DataSource getDatasource() {
		BasicDataSource dataSource = new BasicDataSource();
	      dataSource.setDriverClassName(env.getProperty("hibernate.connection.driver_class"));
	      dataSource.setUrl(env.getProperty("hibernate.connection.url"));
	      dataSource.setUsername(env.getProperty("hibernate.connection.username"));
	      dataSource.setPassword(env.getProperty("hibernate.connection.password"));
		return dataSource;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager() {

		HibernateTransactionManager txManager = new HibernateTransactionManager(sessionFactory().getObject());
		return txManager;
	}

	@Bean
	   public static PropertySourcesPlaceholderConfigurer PropertySourcesPlaceholderConfigurer() {
	      return new PropertySourcesPlaceholderConfigurer();
	   }
}
