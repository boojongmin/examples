package com.school.bank_java.springtest.test;

public class A {
	public A(){
		System.out.println("A 객체 생성");
	}
	public void destoryA(){
		System.out.println("A 객체 소멸");
	}
	@Override
	public String toString() {
		return "A 객체";
	}
}
