package com.home.test.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.home.test.dao.GeolocationRepository;

@Configuration
@EnableJpaRepositories(basePackageClasses = {GeolocationRepository.class})
@EnableTransactionManagement
@ComponentScan(basePackages = "com.home.test.service")
@EntityScan( basePackages = {"com.home.test.domain"} )
public class MainConfiguration {
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl(
				"jdbc:h2:file:./geodb;AUTOCOMMIT=OFF;mv_store=false;INIT=CREATE SCHEMA IF NOT EXISTS ESTORE;USER=test;PASSWORD=test;DB_CLOSE_DELAY=-1;LOCK_TIMEOUT=10000");
		dataSource.setUsername("test");
		dataSource.setPassword("test");
		
		return dataSource;
	}
}
