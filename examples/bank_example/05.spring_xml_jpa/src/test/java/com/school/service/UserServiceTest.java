package com.school.service;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.school.AbstractIntegrationTest;
import com.school.DBData;
import com.school.vo.User;

public class UserServiceTest  extends AbstractIntegrationTest{
	@Autowired
	UserService service;

	@Test
	public void test_01_doLogin(){
		User result = service.doLogin(DBData.USER.getUserId());
		assertThat(result.getUserId(), is(DBData.USER.getUserId()));
		assertThat(result.getName(), is(DBData.USER.getName()));
	}
}
