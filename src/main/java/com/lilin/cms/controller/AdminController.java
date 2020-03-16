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
/**
 * 
 * @ClassName: AdminController 
 * @Description: 管理员中心
 * @author: asus
 * @date: 2020年3月16日 上午10:15:49
 */
@RequestMapping("admin")
@Controller
public class AdminController {

	@Resource
	private ArticleService service;
	@Resource
	private UserService userService;
	/**
	 * 
	 * @Title: index 
	 * @Description: 进入管理员首页
	 * @return
	 * @return: String
	 */
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
	/**
	 * 
	 * @Title: updateUser 
	 * @Description: 更新用户
	 * @param user
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("updateUser")
	public boolean updateUser(User user){
		return userService.update(user)>0;
	}
	/**
	 * 
	 * @Title: articleDetail 
	 * @Description: 单个文章内容
	 * @param id
	 * @return
	 * @return: Article
	 */
	@ResponseBody
	@RequestMapping("articleDetail")
	public Article articleDetail(Integer id){
		return service.select(id);
		
	}
	/**
	 * 
	 * @Title: articles 
	 * @Description: 进入文章审核列表
	 * @param article
	 * @param m
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(Article article,Model m,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="5")Integer pageSize){
		PageInfo<Article> info=service.selects(article, page, pageSize);
		m.addAttribute("info", info);
		m.addAttribute("article", article);
		return "admin/articles";
	}
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改文章
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("update")
	public boolean update(Article article){
		return service.update(article)>0;
	}
	
	
	
	
	
	
}
