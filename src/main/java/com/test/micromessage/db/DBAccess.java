package com.test.micromessage.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBAccess {

	public SqlSession getSqlSession() throws IOException {
		// 通过配置文件获取数据库链接信息
		Reader reader = Resources.getResourceAsReader("com/test/micromessage/configuration.xml");
		// 通过构建配置信息构建一个sqlfactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
}
