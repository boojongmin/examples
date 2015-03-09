package com.school.bank_java.springtest.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.school.bank_java.springtest.test.A;
import com.school.bank_java.springtest.test.B;

public class SpringMain {

	public static void main(String[] args) {
		ApplicationContext ctx = 
				new AnnotationConfigApplicationContext(SpringTestConfig.class);
        A a = (A)ctx.getBean("a");
		B b = ctx.getBean(B.class);
		System.out.println(a);
		System.out.println(b);

	}

}
