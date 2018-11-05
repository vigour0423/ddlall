package com.ddl.learn.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserDao {
	
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insert(){
		String sql = "INSERT INTO `t_my_user`(id,user_name,age) VALUES(?,?,?)";
		String username = UUID.randomUUID().toString().substring(0, 5);
		jdbcTemplate.update(sql, 4,username,19);
		
	}

}
