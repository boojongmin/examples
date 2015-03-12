/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.school;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.school.repository.AccountRepository;
import com.school.repository.UserRepository;

/**
 * Base class for integration tests.
 *
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:META-INF/spring/application-*.xml"})
@ActiveProfiles(profiles = {"test" })
@TransactionConfiguration(defaultRollback=true)
@Transactional
public abstract class AbstractIntegrationTest {

	@Autowired
	DataSource dataSource;

	@Autowired
	ApplicationContext ctx;

	@Autowired
	UserRepository userRepo;
	@Autowired
	AccountRepository accRepo;

	@Before
	public void populateDatabase() throws SQLException {

		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("data.sql"));

//		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//		dataSource.setDriver(new org.h2.Driver());
//		dataSource.setUrl("jdbc:h2:tcp://localhost/~/test;AUTO_SERVER=TRUE;DATABASE_TO_UPPER=false");
//		dataSource.setUsername("sa");
//		dataSource.setPassword("1234");

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
