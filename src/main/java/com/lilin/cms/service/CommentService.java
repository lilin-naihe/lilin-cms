package com.lilin.cms.service;


import com.github.pagehelper.PageInfo;
import com.lilin.cms.domain.Article;
import com.lilin.cms.domain.Comment;

public interface CommentService {
	
	/**
	 * 
	 * @Title: selectsByCommentNum 
	 * @Description: 按照评论数量排序
	 * @return
	 * @return: List<Comment>
	 */
	PageInfo<Article> selectsByCommentNum(Integer page,Integer pageSize);

    int insert(Comment comment);

	PageInfo<Comment> selects(Article article, Integer page, Integer pageSize);
	
}
