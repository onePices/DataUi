package com.web.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.entity.CommonVar;
import com.web.entity.LightStrategy;
import com.web.entity.LightTeam;
import com.web.entity.LightingIntel;
import com.web.entity.LightingIntelRecord;
import com.web.service.LightStrategyService;
import com.web.service.LightTeamService;
import com.web.service.LightingIntelRecordService;
import com.web.service.LightingIntelService;


@RestController
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,RequestMethod.POST}, origins="*")
public class LightingIntelController {
	@Autowired
	private LightingIntelService lightingIntelService;
	@Autowired
	private LightingIntelRecordService lightingIntelRecordService;
	@Autowired
	private LightStrategyService lightStrategyService;
	@Autowired
	private LightTeamService lightTeamService;
	
	

	
	/**
	 * 新增照明设备信息
	 * @param lightingIntel 
	 * @return
	 */
	@RequestMapping(value="lightingIntel/add", method=RequestMethod.POST)
	public boolean add(HttpSession session, @ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		return lightingIntelService.insert(lightingIntel);
	}
	
	/**
	 * 根据主键修改照明设备信息
	 * @param lightingIntel 
	 * @return
	 */
	@RequestMapping(value="lightingIntel/update", method=RequestMethod.POST)
	public boolean update(HttpSession session, @ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		return lightingIntelService.updateByPrimaryKeySelective(lightingIntel);
	}
	
	/**
	 * 根据条件分页查询智慧照明设备
	 * @param lightingIntel 查询条件
	 * @return
	 */
	@RequestMapping(value="lightingIntel/getByPage", method=RequestMethod.GET)
	public List<LightingIntel> selectLightingIntelByPage(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		return lightingIntelService.selectLightingIntelByPage(lightingIntel);
	}
	
	/**
	 * 根据主键查询智慧照明设备信息
	 * @param lightingIntel 查询条件
	 * @return
	 */
	@RequestMapping(value="lightingIntel/selectById", method=RequestMethod.POST)
	public LightingIntel selectById(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		return lightingIntelService.selectByPrimaryKey(lightingIntel.getId());
	}

	/**
	 * 根据主键删除智慧照明设备信息
	 * @param lightingIntel 查询条件
	 * @return
	 */
	@RequestMapping(value="lightingIntel/deleteById", method=RequestMethod.POST)
	public boolean deleteByPrimaryKey(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		return lightingIntelService.deleteByPrimaryKey(lightingIntel.getId());
	}

	/**
	 * 根据设备负责人查询 智慧照明设备信息
	 * @param lightingIntel 查询条件
	 * @return
	 */
	@RequestMapping(value="lightingIntel/selectAllByInchargeof", method=RequestMethod.GET)
	public List<LightingIntel> selectAllByInchargeof(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		return lightingIntelService.selectAllByInchargeof(lightingIntel.getInchargeof());
	}

	/**
	 * 根据分组/街道/地区查询 智慧照明设备信息
	 * @param lightingIntel 查询条件
	 * @return
	 */
	@RequestMapping(value="lightingIntel/selectAllByTeamId", method=RequestMethod.GET)
	public List<LightingIntel> selectAllByTeamId(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		return lightingIntelService.selectAllByTeamId(lightingIntel.getTeamid());
	}

	/**
	 * 根据设备负责人统计智慧照明设备数量
	 * @param lightingIntel 查询条件
	 * @return
	 */	
	@RequestMapping(value="lightingIntel/countLightingIntelNum", method=RequestMethod.GET)
	public Integer countLightingIntelNum(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		return lightingIntelService.countLightingIntelNum(lightingIntel.getInchargeof());
	}
	
	/**
	 * 根据设备负责人统计在线的智慧照明设备数量
	 * @param lightingIntel 查询条件
	 * @return
	 */	
	@RequestMapping(value="lightingIntel/countLightingIntelstatus", method=RequestMethod.GET)
	public Integer countLightingIntelstatus(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("inchargeof", lightingIntel.getInchargeof());
		param.put("intelstatus", "在线");
		param.put("dataLength", lightingIntel.getPageSize());
		return lightingIntelService.countLightingIntelstatus(param);
	}
	
	/**
	 * 根据设备负责人统计亮灯的智慧照明设备数量
	 * @param lightingIntel 查询条件
	 * @return
	 */	
	@RequestMapping(value="lightingIntel/countLightingStatus", method=RequestMethod.GET)
	public Integer countLightingStatus(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("inchargeof", lightingIntel.getInchargeof());
		param.put("intelstatus", "在线");
		param.put("dataLength", lightingIntel.getPageSize());
		return lightingIntelService.countLightingStatus(param);
	}
	
	/**
	 * 根据设备负责人统计智慧照明设备自上线以来的能耗
	 * @param lightingIntel 查询条件
	 * @return
	 */	
	@RequestMapping(value="lightingIntel/selectTotalPower", method=RequestMethod.GET)
	public Double selectTotalPower(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		return lightingIntelService.selectTotalPower(lightingIntel.getInchargeof());
	}
	
	/**
	 * 根据设备负责人统计智慧照明设备今日的能耗
	 * @param lightingIntel 查询条件
	 * @return
	 */	
	@RequestMapping(value="lightingIntel/selectTodayPower", method=RequestMethod.GET)
	public Double selectTodayPower(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		return lightingIntelService.selectTodayPower(lightingIntel.getInchargeof());
	}
	
	/**
	 * 根据设备负责人统计智慧照明设备今日能耗折线图、本周能耗折线图、本月能耗折线图
	 * @param lightingIntel 查询条件
	 * @return
	 */	
	@RequestMapping(value="lightingIntel/dayWeekMonthPower", method=RequestMethod.GET)
	public Double[] dayWeekMonthPower(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		return lightingIntelService.dayWeekMonthPower(lightingIntel.getDataType(), lightingIntel.getInchargeof());
	}
	
	/**
	 * 根据设备分组统计智慧照明设备最近7日能耗折线图
	 * @param lightingIntel 查询条件
	 * @return
	 */	
	@RequestMapping(value="lightingIntel/sevenDayPowerByTeam", method=RequestMethod.GET)
	public Double[] sevenDayPowerByTeam(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		return lightingIntelService.sevenDayPowerByTeam(lightingIntel.getTeamid());
	}
	
	/**
	 * 根据设备负责人统计智慧照明设备最近7日能耗折线图
	 * @param lightingIntel 查询条件
	 * @return
	 */	
	@RequestMapping(value="lightingIntel/sevenDayPowerByIncharge", method=RequestMethod.GET)
	public Double[] sevenDayPowerByIncharge(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		return lightingIntelService.sevenDayPowerByIncharge(lightingIntel.getInchargeof());
	}
	
	
	
	
	

//	
//	/**
//	 * （粤控）显示所有在线的智慧照明设备
//	 * @param lightingIntel 查询条件
//	 * @return
//	 */
//	@RequestMapping(value="lightingIntelControl/getAll", method=RequestMethod.GET)
//	public List<String> lightingIntelControlGetAll() {
//		List<String> result = new LinkedList<String>();
//		for (String key : CommonVar.Light.keySet()) {
//			result.add(key);
//		}
//		return result;
//	}
//	
//	/**
//	 * （粤控）根据adr标识控制智慧照明设备开灯
//	 * @param lightingIntel 查询条件
//	 * @return
//	 */
//	@RequestMapping(value="lightingIntelControl/turnOn", method=RequestMethod.GET)
//	public String lightingIntelControlTurnOn(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
//		return CommonVar.setLightOperate(lightingIntel.getIpdress(), "开灯");
//	}
//	
//	/**
//	 * （粤控）根据adr标识控制智慧照明设备开灯
//	 * @param lightingIntel 查询条件
//	 * @return
//	 */
//	@RequestMapping(value="lightingIntelControl/turnOff", method=RequestMethod.GET)
//	public String lightingIntelControlTurnOff(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
//		return CommonVar.setLightOperate(lightingIntel.getIpdress(), "关灯");
//	}

	
	
	
	
	

	
	/**
	 * 根据条件分页查询智慧照明设备运转历史记录
	 * @param lightingIntelRecord 查询条件
	 * @return
	 */
	@RequestMapping(value="lightingIntelrecord/getByPage", method=RequestMethod.GET)
	public List<LightingIntelRecord> selectLightingIntelByPage(@ModelAttribute("lightingIntelrecord") LightingIntelRecord lightingIntelRecord) {
		return lightingIntelRecordService.selectLightingIntelByPage(lightingIntelRecord);
	}

	/**
	 * 添加智慧照明设备运转历史记录
	 * @param lightingIntelRecord 查询条件
	 * @return
	 */
	@RequestMapping(value="lightingIntelrecord/add", method=RequestMethod.POST)
	public boolean addLightingIntelRecord(HttpSession session, @ModelAttribute("lightingIntelrecord") LightingIntelRecord lightingIntelrecord) {
		return lightingIntelRecordService.insert(lightingIntelrecord);
	}

	/**
	 * 删除智慧照明设备运转历史记录
	 * @param lightingIntelRecord 查询条件
	 * @return
	 */
	@RequestMapping(value="lightingIntelrecord/deleteByCreatedate", method=RequestMethod.POST)
	public boolean deleteByCreatedate(@ModelAttribute("lightingIntelrecord") LightingIntelRecord lightingIntelrecord) {
		return lightingIntelRecordService.deleteByCreatedate(lightingIntelrecord.getCreatedate());
	}

	
	
	
	


	/**
	 * 根据主键获取照明策略
	 * @param lightingIntelRecord 查询条件
	 * @return
	 */
	@RequestMapping(value="lightStrategy/getById", method=RequestMethod.GET)
	public LightStrategy selectLightStrategyById(@ModelAttribute("lightStrategy") LightStrategy lightStrategy) {
		return lightStrategyService.selectByPrimaryKey(lightStrategy.getId());
	}


	/**
	 * 根据条件分页查询照明策略
	 * @param lightingIntelRecord 查询条件
	 * @return
	 */
	@RequestMapping(value="lightStrategy/getByPage", method=RequestMethod.GET)
	public List<LightStrategy> selectLightStrategyByPage(@ModelAttribute("lightStrategy") LightStrategy lightStrategy) {
		return lightStrategyService.selectLightStrategyByPage(lightStrategy);
	}

	/**
	 * 添加照明策略
	 * @param lightingIntelRecord 查询条件
	 * @return
	 */
	@RequestMapping(value="lightStrategy/add", method=RequestMethod.POST)
	public boolean addLightStrategy(HttpSession session, @ModelAttribute("lightStrategy") LightStrategy lightStrategy) {
		return lightStrategyService.insert(lightStrategy);
	}

	/**
	 * 删除照明策略
	 * @param lightingIntelRecord 查询条件
	 * @return
	 */
	@RequestMapping(value="lightStrategy/deleteById", method=RequestMethod.POST)
	public boolean deleteById(@ModelAttribute("lightStrategy") LightStrategy lightStrategy) {
		boolean result = lightStrategyService.deleteByPrimaryKey(lightStrategy.getId());
		lightTeamService.updateByStrategyid(lightStrategy.getId());
		return result;
	}

	/**
	 * 修改照明策略
	 * @param lightingIntelRecord 查询条件
	 * @return
	 */
	@RequestMapping(value="lightStrategy/update", method=RequestMethod.POST)
	public boolean updateLightStrategy(HttpSession session, @ModelAttribute("lightStrategy") LightStrategy lightStrategy) {
		return lightStrategyService.updateByPrimaryKeySelective(lightStrategy);
	}

	/**
	 * 根据智慧照明设备主键获取该设备采用的照明策略
	 * @param lightingIntelRecord 查询条件
	 * @return
	 */
	@RequestMapping(value="lightStrategy/selectByLightId", method=RequestMethod.GET)
	public LightStrategy selectByLightId(HttpSession session, @ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
		return lightStrategyService.selectByLightId(lightingIntel.getId());
	}
	
	
	
	
	
	
	


	/**
	 * 根据主键获取照明分组
	 * @param lightingIntelRecord 查询条件
	 * @return
	 */
	@RequestMapping(value="lightTeam/getById", method=RequestMethod.GET)
	public LightTeam selectLightTeamById(@ModelAttribute("lightTeam") LightTeam lightTeam) {
		return lightTeamService.selectByPrimaryKey(lightTeam.getId());
	}
	
	/**
	 * 根据条件分页查询照明分组
	 * @param lightingIntelRecord 查询条件
	 * @return
	 */
	@RequestMapping(value="lightTeam/getByPage", method=RequestMethod.GET)
	public List<LightTeam> selectLightTeamByPage(@ModelAttribute("lightTeam") LightTeam lightTeam) {
		return lightTeamService.selectLightTeamByPage(lightTeam);
	}
	
	/**
	 * 添加照明分组
	 * @param lightingIntelRecord 查询条件
	 * @return
	 */
	@RequestMapping(value="lightTeam/add", method=RequestMethod.POST)
	public boolean addLightTeam(HttpSession session, @ModelAttribute("lightTeam") LightTeam lightTeam) {
		return lightTeamService.insert(lightTeam);
	}
	
	/**
	 * 根据主键删除照明分组
	 * @param lightingIntelRecord 查询条件
	 * @return
	 */
	@RequestMapping(value="lightTeam/deleteById", method=RequestMethod.POST)
	public boolean deleteLightTeamById(@ModelAttribute("lightTeam") LightTeam lightTeam) {
		boolean result = lightTeamService.deleteByPrimaryKey(lightTeam.getId());
		lightingIntelService.updateByTeamid(lightTeam.getId());
		return result;
	}
	
	/**
	 * 修改照明分组
	 * @param lightingIntelRecord 查询条件
	 * @return
	 */
	@RequestMapping(value="lightTeam/update", method=RequestMethod.POST)
	public boolean updateLightTeam(HttpSession session, @ModelAttribute("lightTeam") LightTeam lightTeam) {
		return lightTeamService.updateByPrimaryKeySelective(lightTeam);
	}
	
	
	
	
	
	
	
	
	

    public String processHystrix_light(@ModelAttribute("lightingIntel") LightingIntel lightingIntel) {
        return "照明组件负载过大，暂停服务";
    }

	
}
