package com.ddl.learn;

import com.ddl.learn.tx.TxConfig;
import com.ddl.learn.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Tx {
	
	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(TxConfig.class);
	
		UserService userService = applicationContext.getBean(UserService.class);
		
		userService.insertUser();
		applicationContext.close();
	}

}
