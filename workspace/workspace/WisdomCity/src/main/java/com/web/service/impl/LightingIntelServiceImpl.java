package com.web.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.LightingIntelMapper;
import com.web.dao.LightingIntelRecordMapper;
import com.web.entity.CommonVar;
import com.web.entity.LightingIntel;
import com.web.service.LightingIntelService;

@Service
public class LightingIntelServiceImpl implements LightingIntelService {
    
    @Autowired
    private LightingIntelMapper lightingIntelMapper; 
	@Autowired
    private LightingIntelRecordMapper lightingIntelRecordMapper;

	
	public LightingIntel selectByPrimaryKey(Integer id) {
		return lightingIntelMapper.selectByPrimaryKey(id);
	}

	
	public List<LightingIntel> selectLightingIntelByPage(LightingIntel lightingIntel) {
		if(lightingIntel.getName()!=null)
			lightingIntel.setName("%"+lightingIntel.getName()+"%");
		lightingIntel.countFirstResult();
		return lightingIntelMapper.selectLightingIntelByPage(lightingIntel);
	}

	
	public boolean deleteByPrimaryKey(Integer id) {
		lightingIntelMapper.deleteByPrimaryKey(id);
		return true;
	}

	
	public boolean insert(LightingIntel lightingIntel) {
		lightingIntelMapper.insert(lightingIntel);
		return true;
	}

	
	public boolean updateByPrimaryKeySelective(LightingIntel lightingIntel) {
		lightingIntelMapper.updateByPrimaryKeySelective(lightingIntel);
		return true;
	}

	
	public boolean updateByPrimaryKey(LightingIntel lightingIntel) {
		lightingIntelMapper.updateByPrimaryKey(lightingIntel);
		return true;
	}


	
	public List<LightingIntel> selectAllByInchargeof(Integer inchargeof) {
		return lightingIntelMapper.selectAllByInchargeof(inchargeof);
	}


	
	public Integer countLightingIntelNum(Integer inchargeof) {
		return lightingIntelMapper.countLightingIntelNum(inchargeof);
	}


	
	public Integer countLightingIntelstatus(Map<String, Object> param) {
		return lightingIntelMapper.countLightingIntelstatus(param);
	}
    

	public Integer countLightingStatus(Map<String,Object> param) {
		return lightingIntelMapper.countLightingStatus(param);
	}


	
	public Double selectTotalPower(Integer inchargeof) {
		List<LightingIntel> list = lightingIntelMapper.selectAllByInchargeof(inchargeof);
		if(list!=null && list.size()>0) {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("inchargeof", inchargeof);
			param.put("teambegin", 0);
			param.put("teamend", list.size());
			return lightingIntelMapper.countLightingEnergy(param);
		}else {
			return null;
		}
	}


	
	public Double selectTodayPower(Integer inchargeof) {
		List<LightingIntel> list = lightingIntelMapper.selectAllByInchargeof(inchargeof);
		if(list!=null && list.size()>0) {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("inchargeof", inchargeof);
			param.put("teambegin", 0);
			param.put("teamend", list.size());
			Double totalEnergy = lightingIntelMapper.countLightingEnergy(param);
			
			Integer count = lightingIntelRecordMapper.getLightingCount();
			Integer teambegin = (count -1)*list.size();
			Map<String,Object> today = new HashMap<String,Object>();
			today.put("inchargeof", inchargeof);
			today.put("teambegin", teambegin);
			today.put("teamend", list.size());
			Double todayEnergy = lightingIntelMapper.countLightingEnergy(today);
			Double result = totalEnergy - todayEnergy;
			return result;
		}else {
			return null;
		}
		
	}


	
	public Double[] dayWeekMonthPower(String type, Integer inchargeof) {
		List<LightingIntel> list = lightingIntelMapper.selectAllByInchargeof(inchargeof);
		if(list!=null && list.size()>0) {
			if(type.equals("day")) {
				Integer hourTimes = CommonVar.LightOneDayCountTimes/24;
				Integer count = lightingIntelRecordMapper.getLightingCount();
				Integer remainder=count%hourTimes;
				Integer times = (count-remainder)/hourTimes;
				Double[] result = new Double[times];
				for(int i=1; i<=times; i++) {
					Map<String,Object> param = new HashMap<String,Object>();
					param.put("inchargeof", inchargeof);
					param.put("teambegin", (count-hourTimes*(i-1)-1)*list.size());
					param.put("teamlength", list.size());
					Double beginEnergy = lightingIntelMapper.countLightingEnergy(param);
					;
					Map<String,Object> today = new HashMap<String,Object>();
					today.put("inchargeof", inchargeof);
					today.put("teambegin", (count-hourTimes*i-1)*list.size());
					today.put("teamlength", list.size());
					Double endEnergy = lightingIntelMapper.countLightingEnergy(today);
					result[i-1] = endEnergy-beginEnergy;
				}
				return result;
			}else if(type.equals("week")) {
				Calendar calendar = Calendar.getInstance();
				Date today = new Date();
				calendar.setTime(today);
				int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
				if (weekDay == 1) {
					weekDay = 7;
				} else {
					weekDay = weekDay - 1;
				}
				Double[] result = new Double[weekDay];
				Integer count = lightingIntelRecordMapper.getLightingCount();
				for(int i=1; i<=weekDay; i++) {
					Map<String,Object> param = new HashMap<String,Object>();
					param.put("inchargeof", inchargeof);
					param.put("teambegin", ((i-1)*CommonVar.LightOneDayCountTimes+count-1)*list.size());
					param.put("teamlength", list.size());
					Double beginEnergy = lightingIntelMapper.countLightingEnergy(param);
					Map<String,Object> week = new HashMap<String,Object>();
					week.put("inchargeof", inchargeof);
					if(i==1)
						week.put("teambegin", 0);
					else
						week.put("teambegin", ((i-2)*CommonVar.LightOneDayCountTimes+count)*list.size());
					week.put("teamlength", list.size());
					Double endEnergy = lightingIntelMapper.countLightingEnergy(week);
					result[i-1] = endEnergy-beginEnergy;
				}
				return result;
			}else if(type.equals("month")) {
				Calendar calendar = Calendar.getInstance();
				Date today = new Date();
				calendar.setTime(today);
				int monthDay = calendar.get(Calendar.DAY_OF_MONTH);
				Double[] result = new Double[monthDay];
				Integer count = lightingIntelRecordMapper.getLightingCount();
				for(int i=1; i<=monthDay; i++) {
					Map<String,Object> param = new HashMap<String,Object>();
					param.put("inchargeof", inchargeof);
					param.put("teambegin", ((i-1)*CommonVar.LightOneDayCountTimes+count-1)*list.size());
					param.put("teamlength", list.size());
					Double beginEnergy = lightingIntelMapper.countLightingEnergy(param);
					Map<String,Object> month = new HashMap<String,Object>();
					month.put("inchargeof", inchargeof);
					if(i==1)
						month.put("teambegin", 0);
					else
						month.put("teambegin", ((i-2)*CommonVar.LightOneDayCountTimes+count)*list.size());
					month.put("teamlength", list.size());
					Double endEnergy = lightingIntelMapper.countLightingEnergy(month);
					result[i-1] = endEnergy-beginEnergy;
				}
				return result;	
			}
			return null;
		}else {
			return null;
		}
	}
	
	
	
	public Double[] sevenDayPowerByTeam(Integer teamid) {
		List<LightingIntel> list = lightingIntelMapper.selectAllByTeamId(teamid);
		if(list!=null && list.size()>0) {
			int weekDay = 7;
			Double[] result = new Double[weekDay];
			Integer count = lightingIntelRecordMapper.getLightingCount();
			for(int i=1; i<=weekDay; i++) {
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("teamid", teamid);
				param.put("teambegin", ((i-1)*CommonVar.LightOneDayCountTimes+count-1)*list.size());
				param.put("teamlength", list.size());
				Double beginEnergy = lightingIntelMapper.countLightingEnergyByTeam(param);
				Map<String,Object> week = new HashMap<String,Object>();
				week.put("teamid", teamid);
				if(i==1)
					week.put("teambegin", 0);
				else
					week.put("teambegin", ((i-2)*CommonVar.LightOneDayCountTimes+count)*list.size());
				week.put("teamlength", list.size());
				Double endEnergy = lightingIntelMapper.countLightingEnergyByTeam(week);
				result[i-1] = endEnergy-beginEnergy;
			}
			return result;
		}else {
			return null;
		}
	}
	

	
	public List<LightingIntel> selectAllByTeamId(Integer teamid) {
		return lightingIntelMapper.selectAllByTeamId(teamid);
	}



	public boolean updateByTeamid(Integer teamid) {
		lightingIntelMapper.updateByTeamid(teamid);
		return true;
	}


	@Override
	public Double[] sevenDayPowerByIncharge(Integer inchargeof) {
		List<LightingIntel> list = lightingIntelMapper.selectAllByInchargeof(inchargeof);
		if(list!=null && list.size()>0) {
			int weekDay = 7;
			Double[] result = new Double[weekDay];
			Integer count = lightingIntelRecordMapper.getLightingCount();
			for(int i=1; i<=weekDay; i++) {
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("inchargeof", inchargeof);
				param.put("teambegin", ((i-1)*CommonVar.LightOneDayCountTimes+count-1)*list.size());
				param.put("teamlength", list.size());
				Double beginEnergy = lightingIntelMapper.countLightingEnergy(param);
				Map<String,Object> week = new HashMap<String,Object>();
				week.put("inchargeof", inchargeof);
				if(i==1)
					week.put("teambegin", 0);
				else
					week.put("teambegin", ((i-2)*CommonVar.LightOneDayCountTimes+count)*list.size());
				week.put("teamlength", list.size());
				Double endEnergy = lightingIntelMapper.countLightingEnergy(week);
				result[i-1] = endEnergy-beginEnergy;
			}
			return result;
		}else {
			return null;
		}
		
	}

}