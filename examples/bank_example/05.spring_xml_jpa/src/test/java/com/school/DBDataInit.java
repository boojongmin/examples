package com.school;

import java.sql.Connection;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.school.vo.User;

public class DBDataInit {
	public static void main( String[] args){

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("test");
		ctx.load("classpath*:META-INF/spring/*.xml");
		ctx.refresh();


		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("data.sql"));

		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriver(new org.h2.Driver());
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/test;AUTO_SERVER=TRUE;DATABASE_TO_UPPER=false");
		dataSource.setUsername("sa");
		dataSource.setPassword("1234");

		Connection connection = null;
//		User user = new User("boojongmin", "부종민");
//
//		Account account = new Account("000100001",0);
//		user.getAccounts().add(account);
//		AccountDetail accountDetail1 = new AccountDetail(AccountDetailType.SAVE, 10000);
//		AccountDetail accountDetail2 = new AccountDetail(AccountDetailType.TRANSFER, -9000);
//		account.getAccountDetails().add(accountDetail1);
//		account.getAccountDetails().add(accountDetail2);
//		account.setAmount(3000);
//		account.setUser(user);
//		userRepo.save(user);
//		accRepo.save(account);

		try {
			connection = DataSourceUtils.getConnection(dataSource);
			populator.populate(connection);
		} finally {
			if (connection != null) {
				DataSourceUtils.releaseConnection(connection, dataSource);
			}
		}
	}
}
