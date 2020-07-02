package com.web.service;

import java.util.List;

import com.web.entity.LightStrategy;

public interface LightStrategyService {
	LightStrategy selectByPrimaryKey(Integer id);
	List<LightStrategy> selectLightStrategyByPage(LightStrategy lightStrategy);
	LightStrategy selectByLightId(Integer lightid);
	boolean deleteByPrimaryKey(Integer id);
	boolean insert(LightStrategy lightStrategy);
	boolean updateByPrimaryKeySelective(LightStrategy lightStrategy);
	boolean updateByPrimaryKey(LightStrategy lightStrategy);
}
