package com.lilin.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lilin.cms.domain.Category;
import com.lilin.cms.domain.Channel;
import com.lilin.cms.service.ChannelService;
/**
 * 
 * @ClassName: ChannelController 
 * @Description: 栏目控制器
 * @author: asus
 * @date: 2020年3月16日 上午10:17:42
 */
@Controller
@RequestMapping("channel")
public class ChannelController {
	
	@Resource
	private ChannelService service;
	/**
	 * 
	 * @Title: channels 
	 * @Description: 查看所有栏目
	 * @return
	 * @return: List<Channel>
	 */
	@ResponseBody
    @RequestMapping("channels")
	public List<Channel> channels(){
		return service.selects();
	}
	/**
	 * 栏目分类
	 */
	@ResponseBody
    @RequestMapping("selectByChannelId")
	public List<Category> selectByChannelId(Integer channelId){
		return service.selectByChannelId(channelId);
	}
}
