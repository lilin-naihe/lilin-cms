package com.lilin.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lilin.cms.dao.SlideMapper;
import com.lilin.cms.domain.Slide;
import com.lilin.cms.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService{

	@Resource
	private SlideMapper mapper;
	@Override
	public List<Slide> selects() {
		// TODO Auto-generated method stub
		return mapper.selects();
	}

}
