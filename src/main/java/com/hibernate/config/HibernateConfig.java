package com.hibernate.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.hibernate")
@EnableTransactionManagement
public class HibernateConfig {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(getDatasource());
		factoryBean.setHibernateProperties(getHibernateProperties());
		return factoryBean;
	}

	private Properties getHibernateProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	private DataSource getDatasource() {
		// TODO Auto-generated method stub
		return null;
	}

}
