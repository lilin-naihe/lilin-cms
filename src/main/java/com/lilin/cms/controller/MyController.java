package com.lilin.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.lilin.cms.domain.Article;
import com.lilin.cms.domain.User;
import com.lilin.cms.service.ArticleService;
/**
 * 
 * @ClassName: MyController 
 * @Description: 个人中心
 * @author: asus
 * @date: 2020年3月4日 上午10:52:38
 */
@RequestMapping("my")
@Controller
public class MyController {
	@Resource
	private ArticleService service;
    /**
     * 
     * @Title: index 
     * @Description: 进入个人中心首页
     * @return
     * @return: String
     */
	//提供三种映射
	@RequestMapping(value = {"","/","index"})
	public String index(){
		
		return "my/index";
	}
	/**
	 * 
	 * @Title: 单个文章内容 
	 * @Description: TODO
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
	 * @Description: 我的文章
	 * @param m
	 * @param session
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	
	@RequestMapping("articles")
	public String articles(Model m,HttpSession session,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="4")Integer pageSize){
		Article article=new Article();
        User user=(User) session.getAttribute("user");
		article.setUserId(user.getId());
		PageInfo<Article> info=service.selects(article, page, pageSize);
		m.addAttribute("info", info);
		return "my/articles";
	}
	/**
	 * 
	 * @Title: publish 
	 * @Description: 去发布文章
	 * @return
	 * @return: String
	 */
	@GetMapping("publish")
	public String publish(){
		
		return "my/publish";
	}
	/**
	 * 
	 * @Title: publish 
	 * @Description: 发布文章
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@PostMapping("publish")
	public boolean publish(MultipartFile file,HttpSession session,Article article){
		//文件上传
		if(null!=file &&!file.isEmpty()){
			String path="d:/pic/";
			String filename = file.getOriginalFilename();
			String uuid = UUID.randomUUID()+filename.substring(filename.lastIndexOf("."));
			File f=new File(path,uuid);
			try {
				file.transferTo(f);
				article.setPicture(uuid);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//文章初始化数据
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());//发布人
		article.setCreated(new Date());
		article.setHits(0);//点击量默认0
		article.setDeleted(0);//默认没删除
		article.setHot(0);//默认非热门
		article.setStatus(0);//默认待审核
		return service.insert(article)>0;//增加文章
	}
}
