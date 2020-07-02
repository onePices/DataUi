package com.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.LightStrategyMapper;
import com.web.entity.LightStrategy;
import com.web.service.LightStrategyService;

@Service
public class LightStrategyServiceImpl implements LightStrategyService {

	@Autowired
    private LightStrategyMapper lightStrategyMapper;
	
	public LightStrategy selectByPrimaryKey(Integer id) {
		return lightStrategyMapper.selectByPrimaryKey(id);
	}

	
	public List<LightStrategy> selectLightStrategyByPage(LightStrategy lightStrategy) {
		lightStrategy.countFirstResult();
		return lightStrategyMapper.selectLightStrategyByPage(lightStrategy);
	}

	
	public boolean deleteByPrimaryKey(Integer id) {
		lightStrategyMapper.deleteByPrimaryKey(id);
		return true;
	}

	
	public boolean insert(LightStrategy lightStrategy) {
		lightStrategyMapper.insert(lightStrategy);
		return true;
	}

	
	public boolean updateByPrimaryKeySelective(LightStrategy lightStrategy) {
		lightStrategyMapper.updateByPrimaryKeySelective(lightStrategy);
		return true;
	}

	
	public boolean updateByPrimaryKey(LightStrategy lightStrategy) {
		lightStrategyMapper.updateByPrimaryKey(lightStrategy);
		return true;
	}


	public LightStrategy selectByLightId(Integer lightid) {
		return lightStrategyMapper.selectByLightId(lightid);
	}

}
