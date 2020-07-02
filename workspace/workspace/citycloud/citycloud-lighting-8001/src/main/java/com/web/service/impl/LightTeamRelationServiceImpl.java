package com.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.LightTeamRelationMapper;
import com.web.entity.LightTeamRelation;
import com.web.service.LightTeamRelationService;

@Service
public class LightTeamRelationServiceImpl implements LightTeamRelationService {
	
	@Autowired
	private LightTeamRelationMapper lightTeamRelationMapper;

	
	public boolean deleteById(Integer id) {
		lightTeamRelationMapper.deleteById(id);
		return true;
	}

	
	public boolean deleteByTeamid(Integer teamid) {
		lightTeamRelationMapper.deleteByTeamid(teamid);
		return true;
	}

	
	public boolean deleteByLightid(Integer lightid) {
		lightTeamRelationMapper.deleteByLightid(lightid);
		return true;
	}

	
	public boolean insert(LightTeamRelation lightTeamRelation) {
		lightTeamRelationMapper.insert(lightTeamRelation);
		return true;
	}

}
