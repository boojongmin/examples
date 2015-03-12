package com.school.command;


public interface Command<T> {
	public void run(T service);
}
