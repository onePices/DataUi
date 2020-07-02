package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.entity.LightTeam;

@Mapper
public interface LightTeamMapper {
	LightTeam selectByPrimaryKey(Integer id);
	List<LightTeam> selectLightTeamByPage(LightTeam lightTeam);
	void deleteByPrimaryKey(Integer id);
	void insert(LightTeam lightTeam);
	void updateByPrimaryKeySelective(LightTeam lightTeam);
	void updateByPrimaryKey(LightTeam lightTeam);
	void updateByStrategyid(Integer strategyid);
}
