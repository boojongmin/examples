package com.school.bank_java.spring.config;

import java.io.InputStream;
import java.nio.charset.Charset;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;

public class DBInit {
	private static DBInit dbInit = new DBInit();

	private final String JDBC_DRIVER = org.h2.Driver.class.getName();
	private final String JDBC_URL = "jdbc:h2:mem:test;MODE=MYSQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false";
	private final String USER = "sa";
	private final String PASSWORD = "1234";
	private final String SCHEMA = "classpath:com/school/bank_java/spring/config/schema.sql";

	public static void run(){
		try {
			dbInit.importDataSet();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("db init fail");
		}
	}

	public void importDataSet() throws Exception {
        RunScript.execute(JDBC_URL, USER, PASSWORD, SCHEMA, Charset.forName("UTF-8"), false);
	}


}
