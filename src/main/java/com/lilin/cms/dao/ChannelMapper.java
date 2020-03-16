package com.lilin.cms.dao;

import java.util.List;

import com.lilin.cms.domain.Category;
import com.lilin.cms.domain.Channel;

/**
 * 
 * @ClassName: ChannelMapper 
 * @Description: 栏目
 * @author: asus
 * @date: 2020年3月5日 上午10:06:58
 */
public interface ChannelMapper {

	List<Channel> selects();
	
	List<Category> selectByChannelId(Integer channelId);
}
