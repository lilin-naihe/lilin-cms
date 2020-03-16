package com.lilin.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lilin.cms.dao.ChannelMapper;
import com.lilin.cms.domain.Category;
import com.lilin.cms.domain.Channel;
import com.lilin.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService{

	@Resource
	private ChannelMapper mapper;
	@Override
	public List<Channel> selects() {
		return mapper.selects();
	}
	@Override
	public List<Category> selectByChannelId(Integer channelId) {
		// TODO Auto-generated method stub
		return mapper.selectByChannelId(channelId);
	}

}
