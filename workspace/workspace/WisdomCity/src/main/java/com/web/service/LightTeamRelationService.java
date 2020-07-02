package com.web.service;

import com.web.entity.LightTeamRelation;

public interface LightTeamRelationService {
	boolean deleteById(Integer id);
	boolean deleteByTeamid(Integer teamid);
	boolean deleteByLightid(Integer lightid);
	boolean insert(LightTeamRelation lightTeamRelation);

}
