package com.school.bank_java.spring.config;

import java.io.InputStream;
import java.nio.charset.Charset;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;

public class MybatisDBInit {
	private static MybatisDBInit dbInit = new MybatisDBInit();

	private final String JDBC_DRIVER = org.h2.Driver.class.getName();
	private static String dbUrl = "jdbc:h2:tcp://localhost/~/test;AUTO_SERVER=TRUE;DATABASE_TO_UPPER=false";
	private static String username = "sa";
	private static String password = "1234";
	private final String SCHEMA = "classpath:com/school/bank_java/spring/config/schema.sql";
	private final String DATA = "classpath:com/school/bank_java/spring/config/data.sql";

	public static void run(){
		try {
			dbInit.importDataSet(dbUrl, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("db init fail");
		}
	}

	public void importDataSet( String dbUrl, String username, String password) throws Exception {
        RunScript.execute(dbUrl, username, password, SCHEMA, Charset.forName("UTF-8"), false);
        RunScript.execute(dbUrl, username, password, DATA, Charset.forName("UTF-8"), false);

	}


}
