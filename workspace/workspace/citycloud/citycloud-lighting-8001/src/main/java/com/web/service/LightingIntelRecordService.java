package com.web.service;

import java.util.List;

import com.web.entity.LightingIntelRecord;

public interface LightingIntelRecordService {
	List<LightingIntelRecord> selectLightingIntelByPage(LightingIntelRecord lightingIntelRecord);
	boolean deleteByCreatedate(String createdate);
	boolean insert(LightingIntelRecord lightingIntelRecord);
	Integer getLightingCount();
	void updateLightingCount();
}
