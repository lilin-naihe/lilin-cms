package com.lilin.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lilin.cms.dao.UserMapper;
import com.lilin.cms.domain.User;
import com.lilin.cms.service.UserService;
import com.lilin.cms.util.CMSException;
import com.lilin.cms.util.Md5Util;
import com.lilin.common.utils.StringUtil;
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper mapper;
	@Override
	public PageInfo<User> selects(User user, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<User> list=mapper.selects(user);
		return new PageInfo<User>(list);
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return mapper.update(user);
	}

	@Override
	public int insert(User user) {
		// 自定义效验
		if(!StringUtil.hasText(user.getUsername()))
			throw new CMSException("用户名不能为空");
		if(!(user.getUsername().length()>=2 && user.getUsername().length()<=10))
			throw new CMSException("用户名长度在2-10之间");
		User findUser=this.selectByUsername(user.getUsername());
		if(null!=findUser)
			throw new CMSException("用户名已被注册");
		
		if(!StringUtil.hasText(user.getPassword()))
			throw new CMSException("密码不能为空");
		if(!(user.getPassword().length()>=6 && user.getPassword().length()<=10))
			throw new CMSException("密码长度在6-10之间");
		//密码一致
		if(!StringUtil.hasText(user.getRepassword()))
			throw new CMSException("确认密码不能为空");
		if(!user.getRepassword().equals(user.getPassword()))
			throw new CMSException("两次密码必须一致");
		user.setPassword(Md5Util.encode(user.getPassword()));
		user.setCreated(new Date());
		user.setNickname(user.getUsername());
		user.setLocked("0");
		return mapper.insert(user);
	}

	@Override
	public User selectByUsername(String username) {
		return mapper.selectByUsername(username);
	}
	
	public User login(User user){
		// 自定义效验
		if(!StringUtil.hasText(user.getUsername()))
			throw new CMSException("用户名不能为空");
		
		User u = this.selectByUsername(user.getUsername());
		if(null==u){
			throw new CMSException("用户名不存在");
		}
		if(!Md5Util.encode(user.getPassword()).equals(u.getPassword()))
			throw new CMSException("密码不知道，请重新登录");
		
		if(u.getLocked().equals("1"))
			throw new CMSException("用户已经被禁用,不能登录");
		return u;
	}

}
