package com.test.mybatis;

import org.springframework.stereotype.Component;

@Component("u")
public class UserDAOImpl implements UserDAO {
	public void save(User user) {

		System.out.println("user save11d!");
		/* throw new RuntimeException("exception"); */ // 抛异常
	}
}
