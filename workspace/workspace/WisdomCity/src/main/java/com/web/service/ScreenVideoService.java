package com.web.service;

import java.util.List;

import com.web.entity.ScreenVideo;

public interface ScreenVideoService {

	List<ScreenVideo> selectScreenVideoByPage(ScreenVideo screenVideo);
	void insert(ScreenVideo screenVideo);
}
