package com.school.bank_java.springtest.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.school.bank_java.springtest.test.A;
import com.school.bank_java.springtest.test.B;

@Configuration
public class SpringTestConfig {
  
	@Bean
	public A a(){
		return new A();
	}
	
	@Bean B b(){
		return new B(a());
	}
	
	

}
