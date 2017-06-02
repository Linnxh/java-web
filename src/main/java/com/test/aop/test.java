package com.test.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {

	// @Test
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:SpringMVC-servlet.xml");

		User user = (User) ctx.getBean("user");
		System.out.println(user.getUsername());
		ctx.close();
		ctx.destroy();
	}

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// ClassPathXmlApplicationContext ctx = new
	// ClassPathXmlApplicationContext("applicationContext.xml");
	//
	// UserService service = (UserService) ctx.getBean("userService");
	// System.out.println(service.getClass());
	// service.add(new User());
	// System.out.println("###");
	// ctx.close();
	// ctx.destroy();
	// }

}
