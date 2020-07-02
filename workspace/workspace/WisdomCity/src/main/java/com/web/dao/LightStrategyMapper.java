package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.entity.LightStrategy;

@Mapper
public interface LightStrategyMapper {
	LightStrategy selectByPrimaryKey(Integer id);
	List<LightStrategy> selectLightStrategyByPage(LightStrategy lightStrategy);
	LightStrategy selectByLightId(Integer lightid);
	void deleteByPrimaryKey(Integer id);
	void insert(LightStrategy lightStrategy);
	void updateByPrimaryKeySelective(LightStrategy lightStrategy);
	void updateByPrimaryKey(LightStrategy lightStrategy);
}
