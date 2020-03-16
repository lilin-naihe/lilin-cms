package com.lilin.cms.service;

import java.util.List;

import com.lilin.cms.domain.Category;
import com.lilin.cms.domain.Channel;

public interface ChannelService {

	/**
	 * 
	 * @Title: selects 
	 * @Description: 查看所有的栏目
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();
     /**
      * 
      * @Title: selectByChannelId 
      * @Description: 根据栏目查询分类
      * @param channelId
      * @return
      * @return: List<Channel>
      */
	List<Category> selectByChannelId(Integer channelId);
}
