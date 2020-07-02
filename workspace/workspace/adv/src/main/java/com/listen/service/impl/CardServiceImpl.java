/*      */ package com.listen.service.impl;
/*      */ 
/*      */ import com.fasterxml.jackson.databind.ObjectMapper;
/*      */ import com.github.pagehelper.PageHelper;
/*      */ import com.github.pagehelper.PageInfo;
/*      */ import com.listen.dao.PlayTaskBasicsLogDao;
/*      */ import com.listen.dao.SysDeviceMapLogDao;
/*      */ import com.listen.dao.SysDeviceOnlineLogDao;
/*      */ import com.listen.dao.SysTaskExpireLogDao;
/*      */ import com.listen.dao.TmlDeviceBasicDao;
/*      */ import com.listen.model.PageResult;
/*      */ import com.listen.model.ResponseModel;
/*      */ import com.listen.model.ResultBean;
/*      */ import com.listen.model.TmlDeviceMapImage;
/*      */ import com.listen.model.query.DeviceQuery;
/*      */ import com.listen.model.query.LogDeviceAlarmInfo;
/*      */ import com.listen.model.query.TaskInfo;
/*      */ import com.listen.model.query.TmlDeviceBasicVo;
/*      */ import com.listen.model.sdkservice.PlayTaskBasicsLog;
/*      */ import com.listen.model.sdkservice.SysDeviceMapLog;
/*      */ import com.listen.model.sdkservice.SysDeviceOnlineLog;
/*      */ import com.listen.model.sdkservice.SysTaskExpireLog;
/*      */ import com.listen.model.sdkservice.TmlDeviceBasic;
/*      */ import com.listen.model.task.PlayTaskBasicVo;
/*      */ import com.listen.redis.RedisUtil;
/*      */ import com.listen.service.CardService;
/*      */ import com.listen.util.ConfigUtil;
/*      */ import com.listen.util.DateUtil;
/*      */ import com.listen.util.FileUtil;
/*      */ import com.listen.util.HttpClientUtil;
/*      */ import com.listen.util.IndentityUtil;
/*      */ import com.listen.util.StringUtil;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.text.DecimalFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.zip.ZipOutputStream;
/*      */ import javax.annotation.Resource;
/*      */ import javax.servlet.ServletException;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import javax.servlet.http.HttpServletResponse;
/*      */ import org.apache.commons.io.FileUtils;
/*      */ import org.apache.log4j.Logger;
/*      */ import org.springframework.stereotype.Service;
/*      */ import org.springframework.web.multipart.MultipartFile;
/*      */ 
/*      */ @Service
/*      */ public class CardServiceImpl
/*      */   implements CardService
/*      */ {
/*      */   private Logger log;
/*      */   
/*      */   public CardServiceImpl() {
/*   62 */     this.log = Logger.getLogger(CardServiceImpl.class);
/*   63 */     this.mapper = new ObjectMapper();
/*      */   }
/*      */ 
/*      */   
/*      */   private ObjectMapper mapper;
/*      */   
/*      */   @Resource
/*      */   private SysTaskExpireLogDao sysTaskExpireLogMapper;
/*      */   
/*      */   @Resource
/*      */   private TmlDeviceBasicDao tmlDeviceBasicMapper;
/*      */   
/*      */   @Resource
/*      */   private SysDeviceOnlineLogDao sysDeviceOnlineMapper;
/*      */   
/*      */   @Resource
/*      */   private PlayTaskBasicsLogDao playTaskBasicsLogMapper;
/*      */   
/*      */   @Resource
/*      */   private SysDeviceMapLogDao sysDeviceMapLogMapper;
/*      */   
/*      */   public void getLogs(HttpServletRequest request, HttpServletResponse response, Integer count) {
/*   85 */     ZipOutputStream out = null;
/*      */     try {
/*   87 */       String zipName = new String("tomcatAndSdkLog.zip".getBytes(), "ISO-8859-1");
/*   88 */       response.setContentType("APPLICATION/OCTET-STREAM");
/*   89 */       response.setHeader("Content-Disposition", "attachment; filename=" + zipName);
/*   90 */       out = new ZipOutputStream((OutputStream)response.getOutputStream());
/*   91 */       FileUtil.zipLogFile(out, count);
/*   92 */       response.flushBuffer();
/*   93 */     } catch (Exception e) {
/*   94 */       e.printStackTrace();
/*   95 */       this.log.error("getLogError", e);
/*      */     } finally {
/*      */       try {
/*   98 */         if (out != null) {
/*   99 */           out.close();
/*      */         }
/*  101 */       } catch (Exception e) {
/*  102 */         e.printStackTrace();
/*  103 */         this.log.error("getLogError", e);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResponseModel vertifyNetwork(String devIp, String devMac) {
/*  113 */     this.log.error("devIp: " + devIp + " ,devMac: " + devMac);
/*  114 */     HashMap<String, String> hm = new HashMap<>();
/*  115 */     ResponseModel rm = new ResponseModel();
/*  116 */     rm.setResult("ok");
/*  117 */     hm.put("tips", "注册成功");
/*  118 */     return rm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResponseModel getCardProgramInfo(String custCode, String custNo, String devCode, String devIp, String devMac, String devTaskStatus, String devTranCode) {
/*  129 */     ResponseModel rm = new ResponseModel();
/*  130 */     Map<String, Object> map = new HashMap<>();
/*  131 */     List<TaskInfo> taskList = new ArrayList<>();
/*      */     try {
/*  133 */       if (StringUtil.isEmpty(devCode)) {
/*  134 */         map.put("errorCode", "-0001");
/*  135 */         map.put("errorText", "参数为空");
/*  136 */         rm.setResult("fail");
/*  137 */         rm.setResponse(map);
/*  138 */         return rm;
/*      */       } 
/*      */       
/*  141 */       String defaultStoragePosition = ConfigUtil.getProperties("default.storagePosition");
/*  142 */       if (defaultStoragePosition.equals("1")) {
///*  143 */         List<String> list = RedisUtil.getAndRmCode(String.valueOf(devCode) + "_programTask");
				   List<String> list = null;
/*  144 */         if (list != null && list.size() > 0) {
/*  145 */           for (int i = 0; i < list.size(); i++) {
/*  146 */             TaskInfo taskInfo = (TaskInfo)this.mapper.readValue(list.get(i), TaskInfo.class);
/*  147 */             taskList.add(taskInfo);
/*      */           } 
/*      */         }
/*  150 */       } else if (defaultStoragePosition.equals("2")) {
/*      */         
/*  152 */         List<SysTaskExpireLog> list = this.sysTaskExpireLogMapper.getSysTaskExpireLog(String.valueOf(devCode) + "_programTask", DateUtil.getNowTimeStamp().longValue(), DateUtil.getAppointTime(1));
/*  153 */         if (list != null && list.size() > 0) {
/*  154 */           for (int i = 0; i < list.size(); i++) {
/*  155 */             TaskInfo taskInfo = (TaskInfo)this.mapper.readValue(((SysTaskExpireLog)list.get(i)).getTaskContent(), TaskInfo.class);
/*  156 */             taskList.add(taskInfo);
/*      */             
/*  158 */             this.sysTaskExpireLogMapper.deleteByPrimaryKey(((SysTaskExpireLog)list.get(i)).getExpireLogId());
/*      */           } 
/*      */         }
/*      */       } 
/*  162 */       map.put("tips", "获取任务成功");
/*  163 */       map.put("taskInfo", taskList);
/*  164 */       rm.setResult("ok");
/*  165 */       rm.setResponse(map);
/*  166 */     } catch (Exception e) {
/*  167 */       e.printStackTrace();
/*  168 */       this.log.error("设备获取节目任务异常", e);
/*  169 */       map.put("errorCode", "-2000");
/*  170 */       map.put("errorText", "获取任务失败");
/*  171 */       rm.setResult("fail");
/*  172 */       rm.setResponse(map);
/*      */     } 
/*  174 */     return rm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResponseModel getCardIndexInfo(String custCode, String custNo, String devCode, String devIp, String devMac, String devTaskStatus, String devTranCode) {
/*  185 */     ResponseModel rm = new ResponseModel();
/*  186 */     Map<String, Object> map = new HashMap<>();
/*  187 */     List<TaskInfo> taskList = new ArrayList<>();
/*      */     
/*      */     try {
/*  190 */       if (StringUtil.isEmpty(devCode)) {
/*  191 */         map.put("errorCode", "-0001");
/*  192 */         map.put("errorText", "参数为空");
/*  193 */         rm.setResult("fail");
/*  194 */         rm.setResponse(map);
/*  195 */         return rm;
/*      */       } 
/*      */       
/*  198 */       String defaultStoragePosition = ConfigUtil.getProperties("default.storagePosition");
/*  199 */       if (defaultStoragePosition.equals("1")) {
///*  200 */         List<String> list = RedisUtil.getAndRmCode(String.valueOf(devCode) + "_indexTask");
					List<String> list = null;
/*  201 */         if (list != null && list.size() > 0) {
/*  202 */           for (int i = 0; i < list.size(); i++) {
/*  203 */             TaskInfo taskInfo = (TaskInfo)this.mapper.readValue(list.get(i), TaskInfo.class);
/*  204 */             taskList.add(taskInfo);
/*      */           } 
/*      */         }
/*  207 */       } else if (defaultStoragePosition.equals("2")) {
/*      */         
/*  209 */         List<SysTaskExpireLog> list = this.sysTaskExpireLogMapper.getSysTaskExpireLog(String.valueOf(devCode) + "_indexTask", DateUtil.getNowTimeStamp().longValue(), DateUtil.getAppointTime(1));
/*  210 */         if (list != null && list.size() > 0) {
/*  211 */           for (int i = 0; i < list.size(); i++) {
/*  212 */             TaskInfo taskInfo = (TaskInfo)this.mapper.readValue(((SysTaskExpireLog)list.get(i)).getTaskContent(), TaskInfo.class);
/*  213 */             taskList.add(taskInfo);
/*      */             
/*  215 */             this.sysTaskExpireLogMapper.deleteByPrimaryKey(((SysTaskExpireLog)list.get(i)).getExpireLogId());
/*      */           } 
/*      */         }
/*      */       } 
/*  219 */       map.put("tips", "获取任务成功");
/*  220 */       map.put("taskInfo", taskList);
/*  221 */       rm.setResult("ok");
/*  222 */       rm.setResponse(map);
/*  223 */     } catch (Exception e) {
/*  224 */       e.printStackTrace();
/*  225 */       this.log.error("设备获取指令任务异常", e);
/*  226 */       map.put("errorCode", "-2000");
/*  227 */       map.put("errorText", "获取任务失败");
/*  228 */       rm.setResult("fail");
/*  229 */       rm.setResponse(map);
/*      */     } 
/*  231 */     return rm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResponseModel getCardUpdateInfo(String custCode, String custNo, String devCode, String devIp, String devMac, String devTaskStatus, String devTranCode) {
/*  242 */     ResponseModel rm = new ResponseModel();
/*  243 */     Map<String, Object> map = new HashMap<>();
/*  244 */     List<TaskInfo> taskList = new ArrayList<>();
/*      */     try {
/*  246 */       if (StringUtil.isEmpty(devCode)) {
/*  247 */         map.put("errorCode", "-0001");
/*  248 */         map.put("errorText", "参数为空");
/*  249 */         rm.setResult("fail");
/*  250 */         rm.setResponse(map);
/*  251 */         return rm;
/*      */       } 
/*      */       
/*  254 */       String defaultStoragePosition = ConfigUtil.getProperties("default.storagePosition");
/*  255 */       if (defaultStoragePosition.equals("1")) {
///*  256 */         List<String> list = RedisUtil.getAndRmCode(String.valueOf(devCode) + "_updateTask");
					List<String> list = null;
/*  257 */         if (list != null && list.size() > 0) {
/*  258 */           for (int i = 0; i < list.size(); i++) {
/*  259 */             TaskInfo taskInfo = (TaskInfo)this.mapper.readValue(list.get(i), TaskInfo.class);
/*  260 */             taskList.add(taskInfo);
/*      */           } 
/*      */         }
/*  263 */       } else if (defaultStoragePosition.equals("2")) {
/*      */         
/*  265 */         List<SysTaskExpireLog> list = this.sysTaskExpireLogMapper.getSysTaskExpireLog(String.valueOf(devCode) + "_updateTask", DateUtil.getNowTimeStamp().longValue(), DateUtil.getAppointTime(1));
/*  266 */         if (list != null && list.size() > 0) {
/*  267 */           for (int i = 0; i < list.size(); i++) {
/*  268 */             TaskInfo taskInfo = (TaskInfo)this.mapper.readValue(((SysTaskExpireLog)list.get(i)).getTaskContent(), TaskInfo.class);
/*  269 */             taskList.add(taskInfo);
/*      */             
/*  271 */             this.sysTaskExpireLogMapper.deleteByPrimaryKey(((SysTaskExpireLog)list.get(i)).getExpireLogId());
/*      */           } 
/*      */         }
/*      */       } 
/*  275 */       map.put("tips", "获取任务成功");
/*  276 */       map.put("taskInfo", taskList);
/*  277 */       rm.setResult("ok");
/*  278 */       rm.setResponse(map);
/*  279 */     } catch (Exception e) {
/*  280 */       e.printStackTrace();
/*  281 */       this.log.error("设备获取升级任务异常", e);
/*  282 */       map.put("errorCode", "-2000");
/*  283 */       map.put("errorText", "获取任务失败");
/*  284 */       rm.setResult("fail");
/*  285 */       rm.setResponse(map);
/*      */     } 
/*  287 */     return rm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResponseModel addHeartbeatData(TmlDeviceBasicVo tmlDeviceBasicVo) {
/*  295 */     ResponseModel rm = new ResponseModel();
/*  296 */     Map<String, Object> map = new HashMap<>();
/*  297 */     String retReDisValue = null;
/*  298 */     String devCodeR = null;
/*      */ 
/*      */     
/*      */     try {
/*  302 */       if (tmlDeviceBasicVo.getDevMac() == null) {
/*  303 */         this.log.error("设备Mac为空!!!");
/*  304 */         map.put("errorCode", "-7001");
/*  305 */         map.put("errorText", "MAC地址为空");
/*  306 */         rm.setResult("fail");
/*  307 */         rm.setResponse(map);
/*      */       } else {
/*  309 */         if (!StringUtil.isEmpty(tmlDeviceBasicVo.getDevCode()) && !StringUtil.isEmpty(ConfigUtil.getProperties("default.heartbeatSaveUrl"))) {
/*  310 */           HttpClientUtil.getInstance().sendHttpPost(ConfigUtil.getProperties("default.heartbeatSaveUrl"), 
/*  311 */               "heartBeatInfo=" + this.mapper.writeValueAsString(tmlDeviceBasicVo).replace("%", ""));
/*      */         }
/*      */         
/*  314 */         String defaultStoragePosition = ConfigUtil.getProperties("default.storagePosition");
/*  315 */         String devCode = tmlDeviceBasicVo.getDevCode();
/*      */         
/*  317 */         if (defaultStoragePosition.equals("1")) {
/*      */           
///*  319 */           retReDisValue = RedisUtil.getValue(tmlDeviceBasicVo.getDevMac());
					retReDisValue = null;
/*      */ 
/*      */           
/*  322 */           if (retReDisValue != null && retReDisValue.equals("-9000")) {
/*  323 */             map.put("errorCode", "-7002");
/*  324 */             map.put("errorText", "心跳失败（数据库连接异常）");
/*  325 */             rm.setResult("fail");
/*  326 */             rm.setResponse(map);
/*  327 */             return rm;
/*      */           } 
/*  329 */           devCodeR = retReDisValue;
/*  330 */           if (devCodeR == null) {
/*      */             
/*  332 */             this.log.error("设备注册[Mac= " + tmlDeviceBasicVo.getDevMac() + "]");
/*  333 */             devCode = getCreateDevCode();
///*  334 */             RedisUtil.addValue(tmlDeviceBasicVo.getDevMac(), devCode);
/*  335 */             DeviceQuery devList = new DeviceQuery(devCode, tmlDeviceBasicVo.getDevMac());
///*  336 */             RedisUtil.addInfo("deviceList", this.mapper.writeValueAsString(devList));
/*      */           } else {
/*      */             
/*  339 */             this.log.error("设备已注册[devCode=" + devCode + "，devCodeR=" + devCodeR + "，Mac= " + tmlDeviceBasicVo.getDevMac() + "]");
/*  340 */             devCode = devCodeR;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  375 */         else if (defaultStoragePosition.equals("2")) {
/*      */ 
/*      */           
/*  378 */           TmlDeviceBasic tmlDeviceBasic1 = this.tmlDeviceBasicMapper.getDeviceByMac(tmlDeviceBasicVo.getDevMac());
/*  379 */           if (tmlDeviceBasic1 != null) {
/*      */             
/*  381 */             devCode = tmlDeviceBasic1.getDevCode();
/*      */           } else {
/*      */             
/*  384 */             this.log.error("设备注册[Mac= " + tmlDeviceBasicVo.getDevMac() + "]");
/*  385 */             devCode = getNewCreateDevCode();
/*  386 */             TmlDeviceBasic tmlDeviceBasic = new TmlDeviceBasic();
/*  387 */             tmlDeviceBasic.setDevCode(devCode);
/*  388 */             tmlDeviceBasic.setDevMac(tmlDeviceBasicVo.getDevMac());
/*  389 */             tmlDeviceBasic.setCreateDate(DateUtil.getNowTimeStamp());
/*  390 */             tmlDeviceBasic.setLastOnlineDate(DateUtil.getNowTimeStamp());
/*  391 */             this.tmlDeviceBasicMapper.insertSelective(tmlDeviceBasic);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  397 */         String heartbeatSwitch = ConfigUtil.getProperties("default.heartbeat.switch");
/*  398 */         if (heartbeatSwitch != null && heartbeatSwitch.equals("1") && 
/*  399 */           devCode != "") {
/*  400 */           int heartBeatRecordCount = 5;
/*  401 */           String tempValue = ConfigUtil.getProperties("default.heartbeat.record");
/*  402 */           if (tempValue != null && !tempValue.equals("")) {
/*  403 */             heartBeatRecordCount = Integer.valueOf(tempValue).intValue();
/*      */           }
/*      */           
/*  406 */           if (defaultStoragePosition.equals("1")) {
///*  407 */             Set<String> set = RedisUtil.getAllInfo("onlineDevList");
/*  407 */             Set<String> set = null;
/*  408 */             if (set != null && set.size() >= heartBeatRecordCount) {
///*  409 */               RedisUtil.delKey("onlineDevList");
/*      */             }
/*  411 */             DeviceQuery onlineDevList = new DeviceQuery(devCode, tmlDeviceBasicVo.getDevMac(), DateUtil.format(new Date()));
/*  412 */             String str = this.mapper.writeValueAsString(onlineDevList);
///*  413 */             RedisUtil.addInfo("onlineDevList", str);
/*      */           } 
/*  415 */           if (defaultStoragePosition.equals("2")) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  420 */             SysDeviceOnlineLog sysDeviceOnline = new SysDeviceOnlineLog();
/*  421 */             sysDeviceOnline.setDevCode(devCode);
/*  422 */             sysDeviceOnline.setDevMac(tmlDeviceBasicVo.getDevMac());
/*  423 */             sysDeviceOnline.setLastOnlineDate(DateUtil.getNowTimeStamp().longValue());
/*  424 */             sysDeviceOnline.setRemark(tmlDeviceBasicVo.getRemarks());
/*  425 */             this.sysDeviceOnlineMapper.insertSelective(sysDeviceOnline);
/*      */           } 
/*      */         } 
/*      */         
/*  429 */         map.put("tips", "心跳成功");
/*  430 */         map.put("devCode", devCode);
/*  431 */         map.put("dateTimeValue", String.valueOf(DateUtil.getNowTimeStamp()));
/*  432 */         map.put("licenseCode", "99990000000000000000");
/*  433 */         rm.setResult("ok");
/*  434 */         rm.setResponse(map);
/*      */       } 
/*  436 */     } catch (Exception e) {
/*  437 */       e.printStackTrace();
/*  438 */       this.log.error("心跳异常：", e);
/*  439 */       map.put("errorCode", "-7000");
/*  440 */       map.put("errorText", "心跳失败");
/*  441 */       rm.setResult("fail");
/*  442 */       rm.setResponse(map);
/*      */     } 
/*  444 */     return rm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String getCreateDevCode() {
/*  453 */     String devCode = null;
///*  454 */     String str = RedisUtil.getValue("deviceCount");
				String str = null;
/*  455 */     int count = Integer.parseInt((str == null || "".equals(str)) ? "0" : str);
///*  456 */     RedisUtil.addValue("deviceCount", String.valueOf(count + 1));
/*  457 */     devCode = IndentityUtil.createIndentityValue(count, "QCP");
///*  458 */     String tempDevCode = RedisUtil.getValue(devCode);
///*  459 */     if (tempDevCode != null || !StringUtil.isEmpty(tempDevCode))
///*      */     {
///*  461 */       return getCreateDevCode();
///*      */     }
///*  463 */     RedisUtil.addValue(devCode, String.valueOf(count + 1));
/*  464 */     return devCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String getNewCreateDevCode() {
/*  472 */     String devCode = null;
/*  473 */     TmlDeviceBasic tmlDeviceBasic = this.tmlDeviceBasicMapper.getLastDevice();
/*  474 */     if (tmlDeviceBasic != null) {
/*  475 */       devCode = IndentityUtil.createIndentityValue(tmlDeviceBasic.getDevId().intValue(), "QCP");
/*      */     } else {
/*  477 */       devCode = IndentityUtil.createIndentityValue(0, "QCP");
/*      */     } 
/*  479 */     return devCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResponseModel deviceAlarmCommit(LogDeviceAlarmInfo logDeviceAlarmInfo) {
/*  487 */     ResponseModel rm = new ResponseModel();
/*  488 */     Map<String, Object> map = new HashMap<>();
/*      */     try {
/*  490 */       logDeviceAlarmInfo.setUpdateDatetime(DateUtil.getNowTimeStamp().longValue());
/*  491 */       if (!StringUtil.isEmpty(ConfigUtil.getProperties("default.alarmSaveUrl"))) {
/*  492 */         HttpClientUtil.getInstance().sendHttpPost(ConfigUtil.getProperties("default.alarmSaveUrl"), 
/*  493 */             "alarmInfo=" + this.mapper.writeValueAsString(logDeviceAlarmInfo).replace("%", ""));
/*      */       }
/*  495 */       map.put("tips", "报警成功");
/*  496 */       rm.setResult("ok");
/*  497 */       rm.setResponse(map);
/*  498 */     } catch (Exception e) {
/*  499 */       e.printStackTrace();
/*  500 */       this.log.error("报警异常：", e);
/*  501 */       map.put("errorCode", "-8000");
/*  502 */       map.put("errorText", "报警失败");
/*  503 */       rm.setResult("fail");
/*  504 */       rm.setResponse(map);
/*      */     } 
/*  506 */     return rm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResponseModel updateTaskStatus(PlayTaskBasicVo sysTaskBasicVo) {
/*  514 */     ResponseModel rm = new ResponseModel();
/*  515 */     Map<String, Object> map = new HashMap<>();
/*  516 */     String strTempRedisKey = null;
/*      */     try {
/*  518 */       System.out.println("result data =[" + sysTaskBasicVo.toString() + "]");
/*      */       
/*  520 */       sysTaskBasicVo.setUpdateBy(sysTaskBasicVo.getDevCode());
/*  521 */       sysTaskBasicVo.setUpdateDate(DateUtil.getNowTimeStamp());
/*  522 */       sysTaskBasicVo.setStrUpdateDateTime(DateUtil.format(new Date()));
/*  523 */       sysTaskBasicVo.setTaskTimes(1);
/*      */       
/*  525 */       if (sysTaskBasicVo.getTaskStatus() == 1) {
/*  526 */         sysTaskBasicVo.setDevTaskStatusDis("Processing");
/*  527 */       } else if (sysTaskBasicVo.getTaskStatus() == 2) {
/*  528 */         sysTaskBasicVo.setDevTaskStatusDis("successful");
/*  529 */       } else if (sysTaskBasicVo.getTaskStatus() == 3) {
/*  530 */         sysTaskBasicVo.setDevTaskStatusDis("failure");
/*      */       } 
/*      */       
/*  533 */       String defaultStoragePosition = ConfigUtil.getProperties("default.storagePosition");
/*  534 */       if (sysTaskBasicVo.getTaskId() != 0 && !StringUtil.isEmpty(ConfigUtil.getProperties("default.taskSaveUrl"))) {
/*  535 */         HttpClientUtil.getInstance().sendHttpPost(ConfigUtil.getProperties("default.taskSaveUrl"), 
/*  536 */             "taskInfo=" + this.mapper.writeValueAsString(sysTaskBasicVo).replace("%", ""));
/*      */         
/*  538 */         if (sysTaskBasicVo.getTaskType() == 505 && sysTaskBasicVo.getTaskStatus() == 2) {
/*  539 */           strTempRedisKey = String.valueOf((sysTaskBasicVo.getDevCode() != null) ? sysTaskBasicVo.getDevCode() : "") + "_cardInfo";
/*  540 */           if (defaultStoragePosition.equals("1")) {
///*  541 */             RedisUtil.delKey(strTempRedisKey);
/*  542 */           } else if (defaultStoragePosition.equals("2")) {
/*  543 */             this.sysTaskExpireLogMapper.delSysTaskExpireLogByCode(strTempRedisKey);
/*      */           } 
/*      */         } 
/*      */       } 
/*  547 */       map.put("tips", "更新任务成功");
/*  548 */       rm.setResult("ok");
/*  549 */       rm.setResponse(map);
/*      */ 
/*      */       
/*  552 */       String taskUpdateSwitch = ConfigUtil.getProperties("default.updatetask.switch");
/*  553 */       if (taskUpdateSwitch != null && taskUpdateSwitch.equals("1")) {
/*      */         
/*  555 */         int taskUpdateRecordCount = 5;
/*  556 */         String tempValue = ConfigUtil.getProperties("default.updatetask.record");
/*  557 */         if (tempValue != null && !tempValue.equals("")) {
/*  558 */           taskUpdateRecordCount = Integer.valueOf(tempValue).intValue();
/*      */         }
/*  560 */         if (defaultStoragePosition.equals("1")) {
///*  561 */           Set<String> set = RedisUtil.getAllInfo("taskUpdateList");
///*  562 */           if (set != null && set.size() >= taskUpdateRecordCount) {
///*  563 */             RedisUtil.delKey("taskUpdateList");
///*      */           }
///*  565 */           String str = this.mapper.writeValueAsString(sysTaskBasicVo);
///*  566 */           RedisUtil.addInfo("taskUpdateList", str);
/*  567 */         } else if (defaultStoragePosition.equals("2")) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  572 */           PlayTaskBasicsLog playTaskBasicsLog = new PlayTaskBasicsLog();
/*  573 */           playTaskBasicsLog.setDevCode(sysTaskBasicVo.getDevCode());
/*  574 */           playTaskBasicsLog.setDevType(sysTaskBasicVo.getDevType());
/*  575 */           playTaskBasicsLog.setGroupId(Integer.valueOf(sysTaskBasicVo.getGroupId()));
/*  576 */           playTaskBasicsLog.setTaskType(Integer.valueOf(sysTaskBasicVo.getTaskType()));
/*  577 */           playTaskBasicsLog.setTaskStatus(sysTaskBasicVo.getTaskStatus());
/*  578 */           playTaskBasicsLog.setTaskTimes(Integer.valueOf(sysTaskBasicVo.getTaskTimes()));
/*  579 */           playTaskBasicsLog.setTaskPercent(sysTaskBasicVo.getTaskPercent());
/*  580 */           playTaskBasicsLog.setTaskDownloadRate(sysTaskBasicVo.getTaskDownloadRate());
/*  581 */           playTaskBasicsLog.setTaskContent(sysTaskBasicVo.getTaskContent());
/*  582 */           playTaskBasicsLog.setTaskExpireDate(Long.valueOf(sysTaskBasicVo.getTaskExpireDate()));
/*  583 */           playTaskBasicsLog.setTaskRemarks(sysTaskBasicVo.getTaskRemarks());
/*  584 */           playTaskBasicsLog.setCustNo(sysTaskBasicVo.getCustNo());
/*  585 */           playTaskBasicsLog.setCustCode(sysTaskBasicVo.getCustCode());
/*  586 */           playTaskBasicsLog.setCreateBy(sysTaskBasicVo.getCreateBy());
/*  587 */           playTaskBasicsLog.setCreateDate(sysTaskBasicVo.getCreateDate());
/*  588 */           playTaskBasicsLog.setUpdateBy(sysTaskBasicVo.getDevCode());
/*  589 */           playTaskBasicsLog.setUpdateDate(DateUtil.getNowTimeStamp());
/*  590 */           playTaskBasicsLog.setDevTranCode(sysTaskBasicVo.getDevTranCode());
/*  591 */           playTaskBasicsLog.setDevTaskStatusDis(sysTaskBasicVo.getDevTaskStatusDis());
/*  592 */           playTaskBasicsLog.setStrUpdateDateTime(sysTaskBasicVo.getStrUpdateDateTime());
/*  593 */           this.playTaskBasicsLogMapper.insertSelective(playTaskBasicsLog);
/*      */         }
/*      */       
/*      */       } 
/*  597 */     } catch (Exception e) {
/*  598 */       e.printStackTrace();
/*  599 */       this.log.error("更新任务处理状态：", e);
/*  600 */       map.put("errorCode", "-2001");
/*  601 */       map.put("errorText", "更新任务失败");
/*  602 */       rm.setResult("fail");
/*  603 */       rm.setResponse(map);
/*      */     } 
/*  605 */     return rm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResponseModel updateDownloadSchedule(String custCode, String custNo, String taskId, String scheeduleValue, String downloadRate, String devTranCode) {
/*  614 */     ResponseModel rm = new ResponseModel();
/*  615 */     Map<String, Object> map = new HashMap<>();
/*      */     try {
/*  617 */       PlayTaskBasicVo sysTaskBasicVo = new PlayTaskBasicVo();
/*  618 */       sysTaskBasicVo.setTaskId(Integer.valueOf(taskId).intValue());
/*  619 */       sysTaskBasicVo.setTaskPercent(scheeduleValue);
/*  620 */       sysTaskBasicVo.setTaskStatus((byte)1);
/*  621 */       sysTaskBasicVo.setDevTaskStatusDis("Processing");
/*  622 */       sysTaskBasicVo.setTaskDownloadRate(String.valueOf(downloadRate) + " KB/S");
/*  623 */       sysTaskBasicVo.setUpdateDate(DateUtil.getNowTimeStamp());
/*  624 */       sysTaskBasicVo.setStrUpdateDateTime(DateUtil.format(new Date()));
/*  625 */       if (sysTaskBasicVo.getTaskId() != 0 && !StringUtil.isEmpty(ConfigUtil.getProperties("default.taskSaveUrl"))) {
/*  626 */         HttpClientUtil.getInstance().sendHttpPost(ConfigUtil.getProperties("default.taskSaveUrl"), 
/*  627 */             "taskInfo=" + this.mapper.writeValueAsString(sysTaskBasicVo).replace("%", ""));
/*      */       }
/*  629 */       map.put("tips", "任务下载进度更新成功");
/*  630 */       rm.setResult("ok");
/*  631 */       rm.setResponse(map);
/*      */ 
/*      */       
/*  634 */       String taskUpdateSwitch = ConfigUtil.getProperties("default.updatetask.switch");
/*  635 */       if (taskUpdateSwitch != null && taskUpdateSwitch.equals("1")) {
/*      */         
/*  637 */         int taskUpdateRecordCount = 5;
/*  638 */         String tempValue = ConfigUtil.getProperties("default.updatetask.record");
/*  639 */         if (tempValue != null && !tempValue.equals("")) {
/*  640 */           taskUpdateRecordCount = Integer.valueOf(tempValue).intValue();
/*      */         }
/*      */         
/*  643 */         String defaultStoragePosition = ConfigUtil.getProperties("default.storagePosition");
/*  644 */         if (defaultStoragePosition.equals("1")) {
///*  645 */           Set<String> set = RedisUtil.getAllInfo("taskUpdateList");
///*  646 */           if (set != null && set.size() >= taskUpdateRecordCount) {
///*  647 */             RedisUtil.delKey("taskUpdateList");
///*      */           }
///*  649 */           String str = this.mapper.writeValueAsString(sysTaskBasicVo);
///*  650 */           RedisUtil.addInfo("taskUpdateList", str);
/*  651 */         } else if (defaultStoragePosition.equals("2")) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  656 */           PlayTaskBasicsLog playTaskBasicsLog = new PlayTaskBasicsLog();
/*  657 */           playTaskBasicsLog.setTaskPercent(sysTaskBasicVo.getTaskPercent());
/*  658 */           playTaskBasicsLog.setTaskStatus((byte)1);
/*  659 */           playTaskBasicsLog.setDevTaskStatusDis(sysTaskBasicVo.getDevTaskStatusDis());
/*  660 */           playTaskBasicsLog.setTaskDownloadRate(String.valueOf(downloadRate) + " KB/S");
/*  661 */           playTaskBasicsLog.setUpdateDate(DateUtil.getNowTimeStamp());
/*  662 */           playTaskBasicsLog.setStrUpdateDateTime(DateUtil.format(new Date()));
/*  663 */           this.playTaskBasicsLogMapper.insertSelective(playTaskBasicsLog);
/*      */         } 
/*      */       } 
/*  666 */     } catch (Exception e) {
/*  667 */       e.printStackTrace();
/*  668 */       this.log.error("更新任务下载进度：", e);
/*  669 */       map.put("errorCode", "-2002");
/*  670 */       map.put("errorText", "任务下载进度更新失败");
/*  671 */       rm.setResult("fail");
/*  672 */       rm.setResponse(map);
/*      */     } 
/*  674 */     return rm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<DeviceQuery> getDevList() {
/*  683 */     List<DeviceQuery> list = null;
/*      */     try {
/*  685 */       list = getDevListFromRedis();
/*  686 */     } catch (Exception e) {
/*  687 */       e.printStackTrace();
/*  688 */       this.log.error("查询设备列表：", e);
/*      */     } 
/*  690 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   private List<DeviceQuery> getDevListFromRedis() throws Exception {
/*  695 */     List<DeviceQuery> list = new ArrayList<>();
///*  696 */     Set<String> set = RedisUtil.getAllInfo("deviceList");
///*  697 */     if (set != null && set.size() > 0) {
///*  698 */       for (String str : set) {
///*  699 */         DeviceQuery device = (DeviceQuery)this.mapper.readValue(str, DeviceQuery.class);
///*  700 */         list.add(device);
///*      */       } 
///*      */     }
/*  703 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<TmlDeviceMapImage> getMapDevList() {
/*  708 */     List<TmlDeviceMapImage> list = null;
/*      */     try {
/*  710 */       list = getMapDevListFromRedis();
/*  711 */     } catch (Exception e) {
/*  712 */       e.printStackTrace();
/*  713 */       this.log.error("查询设备列表：", e);
/*      */     } 
/*  715 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   private List<TmlDeviceMapImage> getMapDevListFromRedis() throws Exception {
/*  720 */     List<TmlDeviceMapImage> list = new ArrayList<>();
///*  721 */     Set<String> set = RedisUtil.getAllInfo("mapDevList");
///*  722 */     if (set != null && set.size() > 0) {
///*  723 */       for (String str : set) {
///*  724 */         TmlDeviceMapImage device = (TmlDeviceMapImage)this.mapper.readValue(str, TmlDeviceMapImage.class);
///*  725 */         list.add(device);
///*      */       } 
///*      */     }
/*  728 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResponseModel getCardInfo(String custCode, String custNo, String devCode, String devIp, String devMac, String devTaskStatus, String devTranCode) {
/*  737 */     ResponseModel rm = new ResponseModel();
/*  738 */     Map<String, Object> map = new HashMap<>();
/*  739 */     List<TaskInfo> taskList = new ArrayList<>();
/*      */     try {
/*  741 */       if (StringUtil.isEmpty(devCode)) {
/*  742 */         map.put("errorCode", "-0001");
/*  743 */         map.put("errorText", "参数为空");
/*  744 */         rm.setResult("fail");
/*  745 */         rm.setResponse(map);
/*  746 */         return rm;
/*      */       } 
/*      */       
/*  749 */       String defaultStoragePosition = ConfigUtil.getProperties("default.storagePosition");
/*  750 */       if (defaultStoragePosition.equals("1")) {
///*  751 */         String str = RedisUtil.getValue(String.valueOf(devCode) + "_cardInfo");
					String str = null;
/*  752 */         if (!StringUtil.isEmpty(str)) {
/*  753 */           TaskInfo taskInfo = (TaskInfo)this.mapper.readValue(str, TaskInfo.class);
/*  754 */           taskList.add(taskInfo);
/*      */         } 
/*  756 */       } else if (defaultStoragePosition.equals("2")) {
/*  757 */         List<SysTaskExpireLog> logList = this.sysTaskExpireLogMapper.getSysTaskExpireLog(String.valueOf(devCode) + "_cardInfo", 0L, 0L);
/*  758 */         if (logList.size() > 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  763 */           TaskInfo taskInfo = (TaskInfo)this.mapper.readValue(((SysTaskExpireLog)logList.get(0)).getTaskContent(), TaskInfo.class);
/*  764 */           taskList.add(taskInfo);
/*      */         } 
/*      */       } 
/*  767 */       map.put("tips", "获取更新数据成功");
/*  768 */       map.put("taskInfo", taskList);
/*  769 */       rm.setResult("ok");
/*  770 */       rm.setResponse(map);
/*  771 */     } catch (Exception e) {
/*  772 */       e.printStackTrace();
/*  773 */       this.log.error("设备获取更新数据", e);
/*  774 */       map.put("errorCode", "-6000");
/*  775 */       map.put("errorText", "获取更新数据失败");
/*  776 */       rm.setResult("fail");
/*  777 */       rm.setResponse(map);
/*      */     } 
/*  779 */     return rm;
/*      */   }
/*      */   
/*      */   public static void main(String[] args) {
/*  783 */     for (int i = 28; i <= 338; i++) {
/*      */       
/*  785 */       DecimalFormat df = new DecimalFormat("0000");
///*  786 */       RedisUtil.delKey("QC20190220" + df.format(i));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResponseModel uploadFile(HttpServletRequest request, String strDevMac, MultipartFile uploadFile, String opType, String fileName) throws ServletException, IOException {
/*  800 */     ResponseModel appResponseResult = new ResponseModel();
/*  801 */     HashMap<String, Object> hm = new HashMap<>();
/*  802 */     String uploadUrl = null;
/*      */ 
/*      */     
/*      */     try {
/*  806 */       if (opType.equals("1")) {
/*      */         
/*  808 */         uploadUrl = String.valueOf(ConfigUtil.getProperties("default.source.path")) + "/commonLog";
/*      */       }
/*  810 */       else if (opType.equals("2")) {
/*      */         
/*  812 */         uploadUrl = String.valueOf(ConfigUtil.getProperties("default.source.path")) + "/screenShots";
/*      */       }
/*  814 */       else if (opType.equals("3")) {
/*      */         
/*  816 */         uploadUrl = String.valueOf(ConfigUtil.getProperties("default.source.path")) + "/playData";
/*      */       }
/*  818 */       else if (opType.equals("4")) {
/*      */         
/*  820 */         uploadUrl = String.valueOf(ConfigUtil.getProperties("default.source.path")) + "/photograph";
/*      */       } 
/*      */ 
/*      */       
/*  824 */       File toFilePath = new File(String.valueOf(uploadUrl) + "/" + strDevMac.replace(":", "") + "/" + fileName);
/*      */       
/*  826 */       byte[] uploadFileBytes = uploadFile.getBytes();
/*      */       
/*  828 */       FileUtils.writeByteArrayToFile(toFilePath, uploadFileBytes);
/*      */       
/*  830 */       appResponseResult.setResult("ok");
/*  831 */       hm.put("tips", "文件上传成功");
/*  832 */       appResponseResult.setResponse(hm);
/*      */     }
/*  834 */     catch (Exception ex) {
/*  835 */       this.log.error("file Upload！", ex);
/*  836 */       hm.put("errorCode", "-9000");
/*  837 */       hm.put("errorText", "文件上传失败");
/*  838 */       appResponseResult.setResult("fail");
/*  839 */       appResponseResult.setResponse(hm);
/*      */     } 
/*      */     
/*  842 */     this.log.info(appResponseResult);
/*  843 */     return appResponseResult;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<DeviceQuery> getDeviceHeartBeatList() {
/*  851 */     List<DeviceQuery> list = null;
/*      */     try {
/*  853 */       list = getDevHeartBeatListFromRedis();
/*  854 */     } catch (Exception e) {
/*  855 */       e.printStackTrace();
/*  856 */       this.log.error("查询设备心跳流水列表：", e);
/*      */     } 
/*  858 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List<DeviceQuery> getDevHeartBeatListFromRedis() throws Exception {
/*  867 */     List<DeviceQuery> list = new ArrayList<>();
///*  868 */     Set<String> set = RedisUtil.getAllInfo("onlineDevList");
				Set<String> set = null;
/*  869 */     if (set != null && set.size() > 0) {
/*  870 */       for (String str : set) {
/*  871 */         DeviceQuery device = (DeviceQuery)this.mapper.readValue(str, DeviceQuery.class);
/*  872 */         list.add(device);
/*  873 */         Collections.sort(list, new Comparator<DeviceQuery>()
/*      */             {
/*      */               public int compare(DeviceQuery o1, DeviceQuery o2) {
/*  876 */                 if (o1.getLastOnlineTime().compareTo(o2.getLastOnlineTime()) < 0) {
/*  877 */                   return 1;
/*      */                 }
/*  879 */                 if (o1.getLastOnlineTime().equals(o2.getLastOnlineTime())) {
/*  880 */                   return 0;
/*      */                 }
/*  882 */                 return -1;
/*      */               }
/*      */             });
/*      */       } 
/*      */     }
/*  887 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<PlayTaskBasicVo> getDeviceTaskUpdateList() {
/*  896 */     List<PlayTaskBasicVo> list = null;
/*      */     try {
/*  898 */       list = getDevTaskUpdateListFromRedis();
/*  899 */     } catch (Exception e) {
/*  900 */       e.printStackTrace();
/*  901 */       this.log.error("查询更新任务状态流水列表：", e);
/*      */     } 
/*  903 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List<PlayTaskBasicVo> getDevTaskUpdateListFromRedis() throws Exception {
/*  912 */     List<PlayTaskBasicVo> list = new ArrayList<>();
///*  913 */     Set<String> set = RedisUtil.getAllInfo("taskUpdateList");
				Set<String> set = null;
/*  914 */     if (set != null && set.size() > 0) {
/*  915 */       for (String str : set) {
/*  916 */         PlayTaskBasicVo device = (PlayTaskBasicVo)this.mapper.readValue(str, PlayTaskBasicVo.class);
/*  917 */         list.add(device);
/*  918 */         Collections.sort(list, new Comparator<PlayTaskBasicVo>()
/*      */             {
/*      */               public int compare(PlayTaskBasicVo o1, PlayTaskBasicVo o2) {
/*  921 */                 if (o1.getStrUpdateDateTime().compareTo(o2.getStrUpdateDateTime()) < 0) {
/*  922 */                   return 1;
/*      */                 }
/*  924 */                 if (o1.getStrUpdateDateTime().equals(o2.getStrUpdateDateTime())) {
/*  925 */                   return 0;
/*      */                 }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  933 */                 return -1;
/*      */               }
/*      */             });
/*      */       } 
/*      */     }
/*  938 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultBean getDevList(Integer pageNo, Integer pageSize, String devCodeOrMac) {
/*  948 */     ResultBean rb = new ResultBean();
/*      */     try {
/*  950 */       PageHelper.startPage(pageNo.intValue(), pageSize.intValue());
/*  951 */       List<TmlDeviceBasic> devcieList = this.tmlDeviceBasicMapper.getTmlDeviceBasic(devCodeOrMac);
/*  952 */       PageInfo<TmlDeviceBasic> info = new PageInfo(devcieList);
/*  953 */       rb.setFlag(Integer.valueOf(1));
/*  954 */       rb.setValue(new PageResult(info));
/*  955 */       rb.setMsg("message.searchS");
/*  956 */     } catch (Exception e) {
/*  957 */       rb.setFlag(Integer.valueOf(0));
/*  958 */       rb.setMsg("message.searchF");
/*  959 */       this.log.error("查询设备信息：", e);
/*      */     } 
/*  961 */     return rb;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultBean getMapDevList(Integer pageNo, Integer pageSize, String devCode) {
/*  970 */     ResultBean rb = new ResultBean();
/*      */     try {
/*  972 */       PageHelper.startPage(pageNo.intValue(), pageSize.intValue());
/*  973 */       List<SysDeviceMapLog> mapLogList = this.sysDeviceMapLogMapper.getDevMapLogList(devCode);
/*  974 */       PageInfo<SysDeviceMapLog> info = new PageInfo(mapLogList);
/*  975 */       rb.setFlag(Integer.valueOf(1));
/*  976 */       rb.setValue(new PageResult(info));
/*  977 */       rb.setMsg("message.searchS");
/*  978 */     } catch (Exception e) {
/*  979 */       rb.setFlag(Integer.valueOf(0));
/*  980 */       rb.setMsg("message.searchF");
/*  981 */       this.log.error("查询设备和地图绑定的信息日志：", e);
/*      */     } 
/*  983 */     return rb;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultBean getDeviceHeartBeatList(Integer pageNo, Integer pageSize, String devCodeOrMac) {
/*  993 */     ResultBean rb = new ResultBean();
/*      */     try {
/*  995 */       PageHelper.startPage(pageNo.intValue(), pageSize.intValue());
/*  996 */       List<SysDeviceOnlineLog> onlinelist = this.sysDeviceOnlineMapper.getSysDeviceOnlineLog2(devCodeOrMac);
/*  997 */       PageInfo<SysDeviceOnlineLog> info = new PageInfo(onlinelist);
/*  998 */       rb.setFlag(Integer.valueOf(1));
/*  999 */       rb.setValue(new PageResult(info));
/* 1000 */       rb.setMsg("message.searchS");
/* 1001 */     } catch (Exception e) {
/* 1002 */       rb.setFlag(Integer.valueOf(0));
/* 1003 */       rb.setMsg("message.searchF");
/* 1004 */       this.log.error("查询设备心跳流水列表 ：", e);
/*      */     } 
/* 1006 */     return rb;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResultBean getDeviceTaskUpdateList(Integer pageNo, Integer pageSize, String devCode) {
/* 1017 */     ResultBean rb = new ResultBean();
/*      */     try {
/* 1019 */       PageHelper.startPage(pageNo.intValue(), pageSize.intValue());
/* 1020 */       List<PlayTaskBasicsLog> loglist = this.playTaskBasicsLogMapper.getPlayTaskBasicsLog2(devCode);
/* 1021 */       PageInfo<PlayTaskBasicsLog> info = new PageInfo(loglist);
/* 1022 */       rb.setFlag(Integer.valueOf(1));
/* 1023 */       rb.setValue(new PageResult(info));
/* 1024 */       rb.setMsg("message.searchS");
/* 1025 */     } catch (Exception e) {
/* 1026 */       rb.setFlag(Integer.valueOf(0));
/* 1027 */       rb.setMsg("message.searchF");
/* 1028 */       this.log.error("查询设备心跳流水列表 ：", e);
/*      */     } 
/* 1030 */     return rb;
/*      */   }
/*      */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\service\impl\CardServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */