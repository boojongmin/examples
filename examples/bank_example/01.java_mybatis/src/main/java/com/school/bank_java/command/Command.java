package com.school.bank_java.command;


public interface Command<T> {
	public void run(T service);
}
