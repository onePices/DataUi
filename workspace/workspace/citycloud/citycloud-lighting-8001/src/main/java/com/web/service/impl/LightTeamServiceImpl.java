package com.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.LightTeamMapper;
import com.web.entity.LightTeam;
import com.web.service.LightTeamService;

@Service
public class LightTeamServiceImpl implements LightTeamService {
	@Autowired
    private LightTeamMapper lightTeamMapper;
	
	
	
	public LightTeam selectByPrimaryKey(Integer id) {
		return lightTeamMapper.selectByPrimaryKey(id);
	}

	
	public List<LightTeam> selectLightTeamByPage(LightTeam lightTeam) {
		return lightTeamMapper.selectLightTeamByPage(lightTeam);
	}

	
	public boolean deleteByPrimaryKey(Integer id) {
		lightTeamMapper.deleteByPrimaryKey(id);
		return true;
	}

	
	public boolean insert(LightTeam lightTeam) {
		lightTeamMapper.insert(lightTeam);
		return true;
	}

	
	public boolean updateByPrimaryKeySelective(LightTeam lightTeam) {
		lightTeamMapper.updateByPrimaryKeySelective(lightTeam);
		return true;
	}

	
	public boolean updateByPrimaryKey(LightTeam lightTeam) {
		lightTeamMapper.updateByPrimaryKey(lightTeam);
		return true;
	}

	

	public boolean updateByStrategyid(Integer strategyid) {
		lightTeamMapper.updateByStrategyid(strategyid);
		return true;
	}
}
