package com.lilin.cms.service;


import com.github.pagehelper.PageInfo;
import com.lilin.cms.domain.Article;

public interface ArticleService {

	Article select(Integer id);
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
	PageInfo<Article> selects(Article article,Integer page,Integer pageSize);

	int update(Article article);
}
