package com.school.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Profile("test")
public class DataBaseTestConfig {

	@Bean
	JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		return adapter;
	}


	@Bean
	DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(org.h2.Driver.class);
//		dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/test;AUTO_SERVER=TRUE;DATABASE_TO_UPPER=false");
		dataSource.setUsername("sa");
		dataSource.setPassword("1234");
		return dataSource;
	}

	@Bean
    public EntityManagerFactory entityManagerFactory() {

      HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      vendorAdapter.setDatabase(Database.H2);
      vendorAdapter.setGenerateDdl(true);
      vendorAdapter.setShowSql(true);

      LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
      factory.setJpaVendorAdapter(vendorAdapter);
      factory.setPackagesToScan("com.school.domain");
      factory.setDataSource(dataSource());
      Properties properties = new Properties();
      properties.put("hibernate.hbm2dll.auto", "create");
      factory.setJpaProperties(properties);
      factory.afterPropertiesSet();

      return factory.getObject();
    }

//	@Bean
//	public DataSource dataSource() {
//	  EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//	  return builder.setType(EmbeddedDatabaseType.HSQL).build();
//	}
}
