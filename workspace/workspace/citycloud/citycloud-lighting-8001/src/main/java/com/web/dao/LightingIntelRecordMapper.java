package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.entity.LightingIntelRecord;
@Mapper
public interface LightingIntelRecordMapper {
	List<LightingIntelRecord> selectLightingIntelByPage(LightingIntelRecord lightingIntelRecord);
	void deleteByCreatedate(String createdate);
	void insert(LightingIntelRecord lightingIntelRecord);
	Integer getLightingCount();
	void updateLightingCount(Integer todaytime);
}
