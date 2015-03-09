package com.school.bank_java.springtest.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.school.bank_java.Main;

public class SpringMain {

	public static void main(String[] args) {
		ConfigurableApplicationContext  ctx = 
  				 new FileSystemXmlApplicationContext("classpath:com/school/bank_java/springtest/test/spring-config.xml");
		A a1 = (A)ctx.getBean("a");
		A a2 = (A)ctx.getBean("a");
		B b = ctx.getBean(B.class);
		System.out.println(a1);
		System.out.println(b);
		
		System.out.println(a1 == a2);
		
		A nA1 = new A();
		A nA2 = new A();
		System.out.println(nA1 == nA2);
		
		ctx.close();
	}
}
