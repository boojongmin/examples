package com.school.bank_java.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionMaker {
	public static SqlSession getSession(boolean autoCommit){
		String resource = "com/school/bank_java/mybatis/conf/mybatis-config.xml";
		return getSession(resource, autoCommit);
	}
	public static SqlSession getSession(String resource, boolean autoCommit){
		InputStream inputStream;
		SqlSessionFactory sqlSessionFactory = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSession sqlSession = sqlSessionFactory.openSession(autoCommit);
		return sqlSession;
	}

}
