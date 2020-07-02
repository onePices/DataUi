package com.web.service;

import java.util.List;
import java.util.Map;

import com.web.entity.LightingIntel;

public interface LightingIntelService {
	LightingIntel selectByPrimaryKey(Integer id);
	List<LightingIntel> selectLightingIntelByPage(LightingIntel lightingIntel);
	List<LightingIntel> selectAllByInchargeof(Integer inchargeof);
	List<LightingIntel> selectAllByTeamId(Integer teamid);
	Double selectTotalPower(Integer inchargeof);
	Double selectTodayPower(Integer inchargeof);
	boolean deleteByPrimaryKey(Integer id);
	boolean insert(LightingIntel lightingIntel);
	boolean updateByPrimaryKeySelective(LightingIntel lightingIntel);
	boolean updateByPrimaryKey(LightingIntel lightingIntel);
	boolean updateByTeamid(Integer teamid);
	Integer countLightingIntelNum(Integer inchargeof);
	Integer countLightingIntelstatus(Map<String,Object> param);
	Integer countLightingStatus(Map<String,Object> param);
	Double[] dayWeekMonthPower(String type, Integer inchargeof);
	Double[] sevenDayPowerByTeam(Integer teamid);
	Double[] sevenDayPowerByIncharge(Integer inchargeof);
}
