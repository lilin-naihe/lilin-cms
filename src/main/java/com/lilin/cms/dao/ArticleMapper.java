package com.lilin.cms.dao;

import java.util.List;

import com.lilin.cms.domain.Article;

public interface ArticleMapper {
    /**
     * 
     * @Title: insert 
     * @Description: 添加
     * @param article
     * @return
     * @return: int
     */
	int insert(Article article);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
     List<Article> selects(Article article);
     
     
     Article select(Integer id);
     
     
     int update(Article article);
}
