package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.entity.ScreenVideo;

@Mapper
public interface ScreenVideoMapper {
	List<ScreenVideo> selectScreenVideoByPage(ScreenVideo screenVideo);
	void insert(ScreenVideo screenVideo);
}