package com.school.test;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.school.login.LoginManager;
import com.school.repository.AccountDetailRepository;
import com.school.repository.AccountRepository;
import com.school.repository.UserRepository;
import com.school.type.AccountDetailType;
import com.school.vo.Account;
import com.school.vo.AccountDetail;
import com.school.vo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:META-INF/spring/application-*.xml"})
@ActiveProfiles(profiles = {"test" })
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class TTest  {
	@Autowired
	UserRepository repository;

	@Autowired
	UserRepository userRepo;
	@Autowired
	AccountRepository accRepo;
	@Autowired
	AccountDetailRepository accDetailRepo;
	@PersistenceContext
	  private EntityManager em;


	@Test
	public void t1(){
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("data.sql"));



//		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//		dataSource.setDriver(new org.h2.Driver());
//		dataSource.setUrl("jdbc:h2:tcp://localhost/~/test;AUTO_SERVER=TRUE;DATABASE_TO_UPPER=false");
//		dataSource.setUsername("sa");
//		dataSource.setPassword("1234");

		Connection connection = null;
		User user = new User("boojongmin", "부종민");

		userRepo.save(user);

        LoginManager.loginMap.put("User", user);


		Account account = new Account("000100001",0);
		user.getAccounts().add(account);
		AccountDetail accountDetail1 = new AccountDetail(AccountDetailType.SAVE, 10000);
		AccountDetail accountDetail2 = new AccountDetail(AccountDetailType.WITHDRAW, -9000);
		accountDetail1.setAccount(account);
		accountDetail2.setAccount(account);
		account.getAccountDetails().add(accountDetail1);
		account.getAccountDetails().add(accountDetail2);
		account.setAmount(1000);
		account.setUser(user);
		accRepo.save(account);

		accountDetail2.setAmount(1000000);
		accDetailRepo.save(accountDetail2);
//		userRepo.save(user);






//		accRepo.save(account);
//		user.setUserId("booj2");
//		userRepo.save(user);
//		account.getAccountDetails().remove(accountDetail1);
//		account.getAccountDetails().remove(accountDetail2);
//		user.getAccounts().remove(account);

//		user.getAccounts().remove(account);
//		userRepo.save(user);
//		accRepo.save(account);
//		System.out.println(">>>>>>>>>>>>>" +accRepo.count());
//		accRepo.delete(account.getId());
//		System.out.println(">>>>>>>>>>>>>" + accRepo.count());

//		em.createQuery("delete Account").executeUpdate();

//accDetailRepo.deleteInactiveUsers();
//		System.out.println(">>>>>>>>>>>>>" + accDetailRepo.count());


	}

//	@Test
//	public void t2(){
//		LoginManager.loginMap.put("User", repository.findOne(1L));
//		repository.save(new User("asdf", "asdfffff"));
//	}

}
