package com.lilin.cms.service;

import com.github.pagehelper.PageInfo;
import com.lilin.cms.domain.User;

public interface UserService {

	PageInfo<User> selects(User user,Integer page,Integer pageSize);
	
	int update(User user);
	
    int insert(User user);
	
	User selectByUsername(String username);
	
	User login(User user);
}
