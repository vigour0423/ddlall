package com.ddl.learn.ext;

import org.springframework.context.ApplicationEvent;

//@Service
public class UserService {
	
	//@EventListener(classes={ApplicationEvent.class})
	public void listen(ApplicationEvent event){
		System.out.println("UserService。。监听到的事件："+event);
	}

}
