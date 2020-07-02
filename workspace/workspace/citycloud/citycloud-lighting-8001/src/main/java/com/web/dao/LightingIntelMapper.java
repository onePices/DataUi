package com.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.entity.LightingIntel;

@Mapper
public interface LightingIntelMapper {
	LightingIntel selectByPrimaryKey(Integer id);
	List<LightingIntel> selectLightingIntelByPage(LightingIntel lightingIntel);
	List<LightingIntel> selectAllByInchargeof(Integer inchargeof);
	List<LightingIntel> selectAllByTeamId(Integer teamid);
	void deleteByPrimaryKey(Integer id);
	void insert(LightingIntel lightingIntel);
	void updateByPrimaryKeySelective(LightingIntel lightingIntel);
	void updateByPrimaryKey(LightingIntel lightingIntel);
	void updateByTeamid(Integer teamid);

	Integer countLightingIntelNum(Integer inchargeof);
	Integer countLightingIntelstatus(Map<String,Object> param);
	Integer countLightingStatus(Map<String,Object> param);
	Double countLightingEnergy(Map<String,Object> param);
	Double countLightingEnergyByTeam(Map<String,Object> param);
}