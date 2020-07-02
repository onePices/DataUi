package com.web.dao;

import org.apache.ibatis.annotations.Mapper;

import com.web.entity.LightTeamRelation;

@Mapper
public interface LightTeamRelationMapper {
	void deleteById(Integer id);
	void deleteByTeamid(Integer teamid);
	void deleteByLightid(Integer lightid);
	void insert(LightTeamRelation lightTeamRelation);
}
