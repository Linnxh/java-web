package com.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Controller 负责注册一个bean 到spring 上下文中
 * @RequestMapping 注解为控制器指定可以处理哪些 URL 请求
 *                 也就是说启动项目后，通过访问：http://localhost:8080/SpringMVCDemo/test2/hello3这个访问到这个action
 */
@Controller
@RequestMapping("/test2")
public class ControllerDemo_2 {
	@RequestMapping("/hello3")
	public String he333333333llo() {
		return "hello";//return 地址代表映射的地址，
	}
}
