package com.web.service;

import java.util.List;

import com.web.entity.LightTeam;

public interface LightTeamService {
	LightTeam selectByPrimaryKey(Integer id);
	List<LightTeam> selectLightTeamByPage(LightTeam lightTeam);
	boolean deleteByPrimaryKey(Integer id);
	boolean insert(LightTeam lightTeam);
	boolean updateByPrimaryKeySelective(LightTeam lightTeam);
	boolean updateByPrimaryKey(LightTeam lightTeam);
	boolean updateByStrategyid(Integer strategyid);
}
