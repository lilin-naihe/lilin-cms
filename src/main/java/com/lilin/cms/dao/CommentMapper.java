package com.lilin.cms.dao;

import java.util.List;

import com.lilin.cms.domain.Article;
import com.lilin.cms.domain.Comment;

public interface CommentMapper {

	int insert(Comment comment);
	
	List<Comment> selects(Article article);
	//评论数量
	List<Article> selectsByCommentNum();
	
	int updateArticle(Integer articleId);
}
