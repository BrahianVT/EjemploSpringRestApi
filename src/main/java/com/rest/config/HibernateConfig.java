package com.rest.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.rest.config"})
public class HibernateConfig {
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean(){
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan(new String[]{"com.rest.model"});
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		return sessionFactoryBean;
	}
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://localhost:5433/prueba");
		ds.setUsername("postgres");
		ds.setPassword("root");
		return ds;
	}
	
	
	private Properties hibernateProperties(){
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.show_sql","true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.use_sql_comments", "false");
		properties.put("hibernate.default_schema", "public");
		return properties;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sf){
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sf);
		return txManager;
	}
}
