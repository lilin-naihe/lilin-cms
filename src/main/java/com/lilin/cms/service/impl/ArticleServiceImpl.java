package com.lilin.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lilin.cms.dao.ArticleMapper;
import com.lilin.cms.domain.Article;
import com.lilin.cms.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Resource
    private ArticleMapper mapper;
	@Override
	public int insert(Article article) {
		return mapper.insert(article);
	}

	@Override
	public PageInfo<Article> selects(Article article,Integer page,Integer pageSize) {
		PageHelper.startPage(page,pageSize);
		List<Article> list=mapper.selects(article);
		return new PageInfo<Article>(list);
	}

	@Override
	public Article select(Integer id) {
		// TODO Auto-generated method stub
		return mapper.select(id);
	}

	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		return mapper.update(article);
	}

}
