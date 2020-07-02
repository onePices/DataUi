package com.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.LightingIntelRecordMapper;
import com.web.entity.CommonVar;
import com.web.entity.LightingIntelRecord;
import com.web.service.LightingIntelRecordService;

@Service
public class LightingIntelRecordServiceImpl implements LightingIntelRecordService {
	
	@Autowired
    private LightingIntelRecordMapper lightingIntelRecordMapper;

	
	public List<LightingIntelRecord> selectLightingIntelByPage(LightingIntelRecord lightingIntelRecord) {
		return lightingIntelRecordMapper.selectLightingIntelByPage(lightingIntelRecord);
	}

	
	public boolean deleteByCreatedate(String createdate) {
		lightingIntelRecordMapper.deleteByCreatedate(createdate);
		return true;
	}

	
	public boolean insert(LightingIntelRecord lightingIntelRecord) {
		lightingIntelRecordMapper.insert(lightingIntelRecord);
		return true;
	}


	public Integer getLightingCount() {
		return lightingIntelRecordMapper.getLightingCount();
	}


	public void updateLightingCount() {
		Integer lightingCount = lightingIntelRecordMapper.getLightingCount();
		lightingCount = lightingCount+1;
		if(lightingCount>CommonVar.LightOneDayCountTimes)
			lightingCount=0;
		lightingIntelRecordMapper.updateLightingCount(lightingCount);
	}

}
