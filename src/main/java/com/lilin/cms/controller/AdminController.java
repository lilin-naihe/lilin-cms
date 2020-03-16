package com.lilin.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lilin.cms.domain.Article;
import com.lilin.cms.domain.User;
import com.lilin.cms.service.ArticleService;
import com.lilin.cms.service.UserService;

@RequestMapping("admin")
@Controller
public class AdminController {

	@Resource
	private ArticleService service;
	@Resource
	private UserService userService;
	@RequestMapping(value={"","/","index"})
	public String index(){
		
		return "admin/index";
	}
	
	/**
	 * 
	 * @Title: users 
	 * @Description: 用户列表
	 * @param user
	 * @param m
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping("users")
	public String users(User user,Model m,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="5")Integer pageSize){
		PageInfo<User> info=userService.selects(user, page, pageSize);
		m.addAttribute("info", info);
		m.addAttribute("user", user);
		return "admin/users";
	}
	
	@ResponseBody
	@RequestMapping("updateUser")
	public boolean updateUser(User user){
		return userService.update(user)>0;
	}
	
	@ResponseBody
	@RequestMapping("articleDetail")
	public Article articleDetail(Integer id){
		return service.select(id);
		
	}
	
	@RequestMapping("articles")
	public String articles(Article article,Model m,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="5")Integer pageSize){
		PageInfo<Article> info=service.selects(article, page, pageSize);
		m.addAttribute("info", info);
		m.addAttribute("article", article);
		return "admin/articles";
	}
	@ResponseBody
	@RequestMapping("update")
	public boolean update(Article article){
		return service.update(article)>0;
	}
	
	
	
	
	
	
}
