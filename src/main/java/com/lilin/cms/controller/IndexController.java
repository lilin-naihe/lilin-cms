package com.lilin.cms.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.lilin.cms.domain.Article;
import com.lilin.cms.domain.Category;
import com.lilin.cms.domain.Channel;
import com.lilin.cms.domain.Collect;
import com.lilin.cms.domain.Comment;
import com.lilin.cms.domain.Slide;
import com.lilin.cms.domain.User;
import com.lilin.cms.service.ArticleService;
import com.lilin.cms.service.ChannelService;
import com.lilin.cms.service.CollectService;
import com.lilin.cms.service.CommentService;
import com.lilin.cms.service.SlideService;
/**
 * 
 * @ClassName: IndexController 
 * @Description: 系统首页入口
 * @author: asus
 * @date: 2020年3月16日 上午10:18:04
 */
@Controller
public class IndexController {

	@Resource
	private ChannelService service;

	@Resource
	private ArticleService articleService;

	@Resource
	private SlideService slideService;

	@Resource
	private CommentService commentService;

	@Resource
	private CollectService collectService;

	/**
	 * 
	 * @Title: index
	 * @Description: 进入首页
	 * @param m
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = { "", "/", "index" })
	public String index(Model m, Article article,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "4") Integer pageSize) {
		article.setStatus(1);
		article.setDeleted(0);
		m.addAttribute("article", article);
		// 查询左侧栏目
		List<Channel> channels = service.selects();
		m.addAttribute("channels", channels);
		if (article.getChannelId() != null) {
			List<Category> categorys = service.selectByChannelId(article
					.getChannelId());
			m.addAttribute("categorys", categorys);
		}
		// 如果栏目为空，说明没有点击左侧栏目
		if (article.getChannelId() == null) {
			article.setHot(1);
            /**
             * 轮播图
             */
			List<Slide> slides = slideService.selects();
			m.addAttribute("slides", slides);
		}
		// 查询所有的文章
		PageInfo<Article> info = articleService
				.selects(article, page, pageSize);
		m.addAttribute("info", info);

		// 右侧10篇文章
		Article article2 = new Article();
		article2.setStatus(1);
		article2.setDeleted(0);
		PageInfo<Article> lastArticles = articleService
				.selects(article2, 1, 10);
		m.addAttribute("lastArticles", lastArticles);
		return "index/index";
	}
    /**
     * 
     * @Title: articleDetail 
     * @Description: 文章详情
     * @param session
     * @param id
     * @param m
     * @param page
     * @param pageSize
     * @return
     * @return: String
     */
	@RequestMapping("articleDetail")
	public String articleDetail(HttpSession session, Integer id, Model m,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "4") Integer pageSize) {
		Article article = articleService.select(id);
		m.addAttribute("article", article);
		//查询当前文章的评论信息
		PageInfo<Comment> info = commentService
				.selects(article, page, pageSize);
		// 查询所有文章评论
		PageInfo<Article> info2 = commentService.selectsByCommentNum(1, 10);
		m.addAttribute("info", info);
		m.addAttribute("info2", info2);
       //查询文章是否被收藏
		User user = (User) session.getAttribute("user");
		Collect collect = null;
		if (null != user) {// 如果用户已经登录，则查询是否没收藏过
			collect = collectService.selectByTitleAndUserId(article.getTitle(),
					user.getId());
		}
		m.addAttribute("collect", collect);

		return "index/articleDetail";
	}

	// 收藏文章
	@ResponseBody
	@RequestMapping("deleteCollect")
	public boolean collect(Integer id) {
		return collectService.delete(id) > 0;
	}

	// 收藏文章
	@ResponseBody
	@RequestMapping("collect")
	public boolean collect(Collect collect, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (null == user)
			return false;// 没有登录的用户不能收藏
		collect.setUser(user);
		collect.setCreated(new Date());
		return collectService.insert(collect) > 0;
	}

	// 增加评论
	@ResponseBody
	@RequestMapping("addComment")
	public boolean addComment(Comment comment, Integer articleId,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (null == user)
			return false;// 没有登录不能评论
		comment.setUserId(user.getId());
		comment.setArticleId(articleId);
		comment.setCreated(new Date());

		return commentService.insert(comment) > 0;
	}
	

}
