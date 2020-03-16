package com.lilin.cms.dao;

import java.util.List;

import com.lilin.cms.domain.User;

public interface UserMapper {

	List<User> selects(User user);
	
	int update(User user);
	
	int insert(User user);
	
	User selectByUsername(String username);
}
