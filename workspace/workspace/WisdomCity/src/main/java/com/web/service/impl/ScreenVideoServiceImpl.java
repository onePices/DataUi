package com.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.ScreenVideoMapper;
import com.web.entity.ScreenVideo;
import com.web.service.ScreenVideoService;

@Service
public class ScreenVideoServiceImpl implements ScreenVideoService {
	@Autowired
    private ScreenVideoMapper screenVideoMapper;
	
	

	@Override
	public List<ScreenVideo> selectScreenVideoByPage(ScreenVideo screenVideo) {
		screenVideo.countFirstResult();
		return screenVideoMapper.selectScreenVideoByPage(screenVideo);
	}

	@Override
	public void insert(ScreenVideo screenVideo) {
		screenVideoMapper.insert(screenVideo);
	}

}
