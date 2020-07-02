/*     */ package com.listen.service.impl;
/*     */ 
/*     */ import com.fasterxml.jackson.core.JsonProcessingException;
/*     */ import com.fasterxml.jackson.databind.ObjectMapper;
/*     */ import com.listen.dao.PlayTaskBasicsDao;
/*     */ import com.listen.dao.PlayTaskBasicsLogDao;
/*     */ import com.listen.dao.SysDeviceMapLogDao;
/*     */ import com.listen.dao.SysTaskExpireLogDao;
/*     */ import com.listen.dao.TmlDeviceBasicDao;
/*     */ import com.listen.model.FileBean;
/*     */ import com.listen.model.PlayTaskBasic;
/*     */ import com.listen.model.ResponseModel;
/*     */ import com.listen.model.TmlDeviceMapImage;
/*     */ import com.listen.model.query.DeviceQuery;
/*     */ import com.listen.model.sdkservice.PlayTaskBasics;
/*     */ import com.listen.model.sdkservice.PlayTaskBasicsLog;
/*     */ import com.listen.model.sdkservice.SysDeviceMapLog;
/*     */ import com.listen.model.sdkservice.SysTaskExpireLog;
/*     */ import com.listen.model.sdkservice.TmlDeviceBasic;
/*     */ import com.listen.model.task.PlayModel;
/*     */ import com.listen.model.task.PlayTaskBasicVo;
/*     */ import com.listen.model.task.PlayTaskChannel;
/*     */ import com.listen.model.task.PlayTaskProgram;
/*     */ import com.listen.model.task.PlayTaskSchedule;
/*     */ import com.listen.service.AuthService;
/*     */ import com.listen.util.ConfigUtil;
/*     */ import com.listen.util.DateUtil;
/*     */ import com.listen.util.HttpClientUtil;
/*     */ import com.listen.util.ShowUtil;
/*     */ import com.listen.util.StringUtil;
/*     */ import com.listen.util.TaskUtil;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.zip.ZipOutputStream;
/*     */ import javax.annotation.Resource;
///*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.interceptor.TransactionAspectSupport;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class AuthServiceImpl
/*     */   implements AuthService
/*     */ {
///*  60 */   private Logger log = Logger.getLogger(AuthServiceImpl.class);
/*  61 */   private ObjectMapper mapper = new ObjectMapper();
/*     */ 
/*     */   
/*     */   @Resource
/*     */   private SysDeviceMapLogDao sysDeviceMapLogMapper;
/*     */ 
/*     */   
/*     */   @Resource
/*     */   private PlayTaskBasicsDao playTaskBasicsMapper;
/*     */ 
/*     */   
/*     */   @Resource
/*     */   private PlayTaskBasicsLogDao playTaskBasicsLogMapper;
/*     */ 
/*     */   
/*     */   @Resource
/*     */   private SysTaskExpireLogDao sysTaskExpireLogMapper;
/*     */ 
/*     */   
/*     */   @Resource
/*     */   private TmlDeviceBasicDao tmlDeviceBasicMapper;
/*     */ 
/*     */ 
/*     */   
/*     */   public ResponseModel saveAndSendProgram(String devCode, String strProgramList) {
/*  86 */     ResponseModel rm = new ResponseModel();
/*  87 */     Map<String, Object> map = new HashMap<>();
/*  88 */     int programId = 0;
/*  89 */     StringBuffer strProgramName = new StringBuffer();
/*  90 */     StringBuffer strProgramID = new StringBuffer();
/*     */     
/*     */     try {
/*  93 */       if (StringUtil.isEmpty(devCode) || StringUtil.isEmpty(strProgramList)) {
/*  94 */         map.put("errorCode", "-0001");
/*  95 */         map.put("errorText", "参数为空");
/*  96 */         rm.setResult("fail");
/*  97 */         rm.setResponse(map);
/*  98 */         return rm;
/*     */       } 
/*     */       
/* 101 */       PlayTaskBasic ptb = TaskUtil.getTaskBasic(devCode, "", "", Integer.valueOf(150));
/* 102 */       String timePath = DateUtil.format(new Date(), "yyyyMM");
/* 103 */       String filePath = ConfigUtil.getProperties("default.source.path");
/* 104 */       String uuid = String.valueOf(UUID.randomUUID().toString()) + "_" + ptb.getTaskId();
/*     */       
/* 106 */       String zipPath = "job" + File.separator + devCode + File.separator + timePath + File.separator + ptb.getTaskId();
/* 107 */       File zipDir = new File(String.valueOf(filePath) + zipPath);
/* 108 */       if (!zipDir.exists()) {
/* 109 */         zipDir.mkdirs();
/*     */       }
/* 111 */       PlayModel pm = new PlayModel();
/* 112 */       pm.setSize(Long.valueOf(0L));
/* 113 */       pm.setTaskId(ptb.getTaskId());
/* 114 */       File zipFile = new File(String.valueOf(filePath) + zipPath + File.separator + uuid + ".zip");
/* 115 */       ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
/*     */       
/* 117 */       Set<FileBean> setFile = ShowUtil.createFile(strProgramID, strProgramName, strProgramList, filePath, uuid, devCode, pm, ptb.getTaskId());
/*     */       
/* 119 */       ShowUtil.createZipFile(zipOut, setFile, filePath);
/* 120 */       programId = Integer.valueOf(strProgramID.toString()).intValue();
/* 121 */       PlayTaskProgram ptp = new PlayTaskProgram(Integer.valueOf(programId), strProgramName.toString(), Integer.valueOf(1), "0", "0", String.valueOf(zipPath) + File.separator + uuid + ".zip");
/* 122 */       ptp.setCreateBy(devCode);
/* 123 */       ptp.setCreateDate(DateUtil.getNowTimeStamp());
/* 124 */       ptp.setUpdateBy(devCode);
/* 125 */       ptp.setUpdateDate(DateUtil.getNowTimeStamp());
/* 126 */       List<PlayTaskProgram> list = new ArrayList<>();
/* 127 */       list.add(ptp);
/* 128 */       PlayTaskSchedule pts = new PlayTaskSchedule();
/* 129 */       pts.setPlanEndtime(Long.valueOf(0L));
/* 130 */       pts.setPlanStarttime(Long.valueOf(0L));
/* 131 */       pts.setScheduleId(Integer.valueOf(0));
/* 132 */       pts.setScheduleName("");
/* 133 */       pts.setScheduleType(Integer.valueOf(0));
/* 134 */       pts.setProgram(list);
/* 135 */       List<PlayTaskSchedule> ptsList = new ArrayList<>();
/* 136 */       ptsList.add(pts);
/* 137 */       PlayTaskChannel ptc = new PlayTaskChannel();
/* 138 */       ptc.setChannelId(Integer.valueOf(0));
/* 139 */       ptc.setChannelName("");
/* 140 */       ptc.setSchedule(ptsList);
/*     */       
/* 142 */       File zipEndFile = new File(String.valueOf(filePath) + zipPath + File.separator + uuid + ".zip");
/* 143 */       pm.setSize(Long.valueOf(pm.getSize().longValue() + zipEndFile.length()));
/* 144 */       ShowUtil.savePlayTaskModelTxt(ptc, String.valueOf(filePath) + zipPath + File.separator + uuid + ".txt", pm);
/* 145 */       String txtContent = String.valueOf(zipPath) + File.separator + uuid + ".txt";
/* 146 */       ptb.setTaskContent(txtContent);
/* 147 */       TaskUtil.addTaskToRedis(ptb, devCode);
/* 148 */       map.put("tips", "下发节目成功");
/* 149 */       rm.setResult("ok");
/* 150 */       rm.setResponse(map);
/*     */       
/* 152 */       String tempJsPath = String.valueOf(filePath) + "listenService" + File.separator + "temp" + File.separator + devCode + File.separator + timePath + File.separator + ptb.getTaskId() + File.separator;
/* 153 */       ShowUtil.deleteTempFile(new File(tempJsPath));
/* 154 */       String tempTextPicPath = String.valueOf(filePath) + "text" + File.separator + "temp" + File.separator + timePath + File.separator + ptb.getTaskId() + File.separator;
/* 155 */       ShowUtil.deleteTempFile(new File(tempTextPicPath));
/* 156 */       saveTask(ptb);
/* 157 */     } catch (Exception e) {
/* 159 */       e.printStackTrace();
/* 160 */       map.put("errorCode", "-4000");
/* 161 */       map.put("errorText", "下发节目失败");
/* 162 */       rm.setResult("fail");
/* 163 */       rm.setResponse(map);
/*     */     } 
/* 165 */     return rm;
/*     */   }
/*     */ 
/*     */   
/*     */   private void saveTask(PlayTaskBasic task) throws JsonProcessingException {
/* 170 */     if (!StringUtil.isEmpty(ConfigUtil.getProperties("default.taskSaveUrl"))) {
/* 171 */       HttpClientUtil.getInstance().sendHttpPost(ConfigUtil.getProperties("default.taskSaveUrl"), 
/* 172 */           "taskInfo=" + this.mapper.writeValueAsString(task));
/*     */     }
/*     */ 
/*     */     
/* 176 */     String taskUpdateSwitch = ConfigUtil.getProperties("default.updatetask.switch");
/* 177 */     if (taskUpdateSwitch != null && taskUpdateSwitch.equals("1")) {
/*     */       
/* 179 */       int taskUpdateRecordCount = 5;
/* 180 */       String tempValue = ConfigUtil.getProperties("default.updatetask.record");
/* 181 */       if (tempValue != null && !tempValue.equals("")) {
/* 182 */         taskUpdateRecordCount = Integer.valueOf(tempValue).intValue();
/*     */       }
///* 184 */       Set<String> set = RedisUtil.getAllInfo("taskUpdateList");
///* 185 */       if (set != null && set.size() >= taskUpdateRecordCount) {
///* 186 */         RedisUtil.delKey("taskUpdateList");
///*     */       }
/* 188 */       PlayTaskBasicVo sysTaskBasicVo = new PlayTaskBasicVo();
/* 189 */       sysTaskBasicVo.setTaskId(task.getTaskId().intValue());
/* 190 */       sysTaskBasicVo.setDevCode(task.getDevCode());
/* 191 */       sysTaskBasicVo.setTaskType(task.getTaskType().intValue());
/* 192 */       sysTaskBasicVo.setTaskStatus((byte)0);
/* 193 */       sysTaskBasicVo.setDevTaskStatusDis("Pending");
/* 194 */       sysTaskBasicVo.setUpdateBy(task.getUpdateBy());
/* 195 */       sysTaskBasicVo.setUpdateDate(DateUtil.getNowTimeStamp());
/* 196 */       sysTaskBasicVo.setStrUpdateDateTime(DateUtil.format(new Date()));
/* 197 */       String str = this.mapper.writeValueAsString(sysTaskBasicVo);
///* 198 */       RedisUtil.addInfo("taskUpdateList", str);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResponseModel saveAndSendProgramNew(String devCode, String strProgramList) {
/* 210 */     ResponseModel rm = new ResponseModel();
/* 211 */     Map<String, Object> map = new HashMap<>();
/* 212 */     int programId = 0;
/* 213 */     StringBuffer strProgramName = new StringBuffer();
/* 214 */     StringBuffer strProgramID = new StringBuffer();
/*     */     
/*     */     try {
/* 217 */       if (StringUtil.isEmpty(devCode) || StringUtil.isEmpty(strProgramList)) {
/* 218 */         map.put("errorCode", "-0001");
/* 219 */         map.put("errorText", "参数为空");
/* 220 */         rm.setResult("fail");
/* 221 */         rm.setResponse(map);
/* 222 */         return rm;
/*     */       } 
/*     */       
/* 225 */       PlayTaskBasics ptbs = TaskUtil.getTaskBasics(devCode, "", "", Integer.valueOf(150));
/* 226 */       this.playTaskBasicsMapper.insertSelective(ptbs);
/*     */       
/* 228 */       String timePath = DateUtil.format(new Date(), "yyyyMM");
/* 229 */       String filePath = ConfigUtil.getProperties("default.source.path");
/* 230 */       String uuid = String.valueOf(UUID.randomUUID().toString()) + "_" + ptbs.getTaskId();
/*     */       
/* 232 */       String zipPath = "job" + File.separator + devCode + File.separator + timePath + File.separator + ptbs.getTaskId();
/* 233 */       File zipDir = new File(String.valueOf(filePath) + zipPath);
/* 234 */       if (!zipDir.exists()) {
/* 235 */         zipDir.mkdirs();
/*     */       }
/* 237 */       PlayModel pm = new PlayModel();
/* 238 */       pm.setSize(Long.valueOf(0L));
/* 239 */       pm.setTaskId(ptbs.getTaskId());
/* 240 */       File zipFile = new File(String.valueOf(filePath) + zipPath + File.separator + uuid + ".zip");
/* 241 */       ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
/*     */       
/* 243 */       Set<FileBean> setFile = ShowUtil.createFile(strProgramID, strProgramName, strProgramList, filePath, uuid, devCode, pm, ptbs.getTaskId());
/*     */       
/* 245 */       ShowUtil.createZipFile(zipOut, setFile, filePath);
/* 246 */       programId = Integer.valueOf(strProgramID.toString()).intValue();
/* 247 */       PlayTaskProgram ptp = new PlayTaskProgram(Integer.valueOf(programId), strProgramName.toString(), Integer.valueOf(1), "0", "0", String.valueOf(zipPath) + File.separator + uuid + ".zip");
/* 248 */       ptp.setCreateBy(devCode);
/* 249 */       ptp.setCreateDate(DateUtil.getNowTimeStamp());
/* 250 */       ptp.setUpdateBy(devCode);
/* 251 */       ptp.setUpdateDate(DateUtil.getNowTimeStamp());
/* 252 */       List<PlayTaskProgram> list = new ArrayList<>();
/* 253 */       list.add(ptp);
/* 254 */       PlayTaskSchedule pts = new PlayTaskSchedule();
/* 255 */       pts.setPlanEndtime(Long.valueOf(0L));
/* 256 */       pts.setPlanStarttime(Long.valueOf(0L));
/* 257 */       pts.setScheduleId(Integer.valueOf(0));
/* 258 */       pts.setScheduleName("");
/* 259 */       pts.setScheduleType(Integer.valueOf(0));
/* 260 */       pts.setProgram(list);
/* 261 */       List<PlayTaskSchedule> ptsList = new ArrayList<>();
/* 262 */       ptsList.add(pts);
/* 263 */       PlayTaskChannel ptc = new PlayTaskChannel();
/* 264 */       ptc.setChannelId(Integer.valueOf(0));
/* 265 */       ptc.setChannelName("");
/* 266 */       ptc.setSchedule(ptsList);
/*     */       
/* 268 */       File zipEndFile = new File(String.valueOf(filePath) + zipPath + File.separator + uuid + ".zip");
/* 269 */       pm.setSize(Long.valueOf(pm.getSize().longValue() + zipEndFile.length()));
/* 270 */       ShowUtil.savePlayTaskModelTxt(ptc, String.valueOf(filePath) + zipPath + File.separator + uuid + ".txt", pm);
/* 271 */       String txtContent = String.valueOf(zipPath) + File.separator + uuid + ".txt";
/* 272 */       ptbs.setTaskContent(txtContent);
/* 273 */       this.playTaskBasicsMapper.updateByPrimaryKeySelective(ptbs);
/* 274 */       SysTaskExpireLog expirelog1 = TaskUtil.addTaskToRedisNew(ptbs, devCode);
/* 275 */       this.sysTaskExpireLogMapper.insertSelective(expirelog1);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 280 */       map.put("tips", "下发节目成功");
/* 281 */       rm.setResult("ok");
/* 282 */       rm.setResponse(map);
/*     */       
/* 284 */       String tempJsPath = String.valueOf(filePath) + "listenService" + File.separator + "temp" + File.separator + devCode + File.separator + timePath + File.separator + ptbs.getTaskId() + File.separator;
/* 285 */       ShowUtil.deleteTempFile(new File(tempJsPath));
/* 286 */       String tempTextPicPath = String.valueOf(filePath) + "text" + File.separator + "temp" + File.separator + timePath + File.separator + ptbs.getTaskId() + File.separator;
/* 287 */       ShowUtil.deleteTempFile(new File(tempTextPicPath));
/* 288 */       saveTaskNew(ptbs);
/* 289 */     } catch (Exception e) {
/* 291 */       TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
/* 292 */       e.printStackTrace();
/* 293 */       map.put("errorCode", "-4000");
/* 294 */       map.put("errorText", "下发节目失败");
/* 295 */       rm.setResult("fail");
/* 296 */       rm.setResponse(map);
/*     */     } 
/* 298 */     return rm;
/*     */   }
/*     */ 
/*     */   
/*     */   private void saveTaskNew(PlayTaskBasics task) throws JsonProcessingException {
/* 303 */     if (!StringUtil.isEmpty(ConfigUtil.getProperties("default.taskSaveUrl"))) {
/* 304 */       HttpClientUtil.getInstance().sendHttpPost(ConfigUtil.getProperties("default.taskSaveUrl"), 
/* 305 */           "taskInfo=" + this.mapper.writeValueAsString(task));
/*     */     }
/*     */     
/* 308 */     String taskUpdateSwitch = ConfigUtil.getProperties("default.updatetask.switch");
/* 309 */     if (taskUpdateSwitch != null && taskUpdateSwitch.equals("1")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 319 */       PlayTaskBasicsLog playTaskBasicsLog = new PlayTaskBasicsLog();
/* 320 */       playTaskBasicsLog.setDevCode(task.getDevCode());
/* 321 */       playTaskBasicsLog.setTaskType(task.getTaskType());
/* 322 */       playTaskBasicsLog.setTaskStatus((byte)0);
/* 323 */       playTaskBasicsLog.setDevTaskStatusDis("Pending");
/* 324 */       playTaskBasicsLog.setUpdateBy(task.getUpdateBy());
/* 325 */       playTaskBasicsLog.setUpdateDate(DateUtil.getNowTimeStamp());
/* 326 */       playTaskBasicsLog.setStrUpdateDateTime(DateUtil.format(new Date()));
/* 327 */       this.playTaskBasicsLogMapper.insertSelective(playTaskBasicsLog);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResponseModel updateData(String devCode, String devType, String devDataType, String dataInfo) {
/* 334 */     ResponseModel rm = new ResponseModel();
/* 335 */     Map<String, String> map = new HashMap<>();
/* 336 */     String defaultStoragePosition = ConfigUtil.getProperties("default.storagePosition");
/*     */     
/*     */     try {
/* 339 */       if (StringUtil.isEmpty(devCode) || StringUtil.isEmpty(devType) || StringUtil.isEmpty(devDataType) || StringUtil.isEmpty(dataInfo)) {
/* 340 */         map.put("errorCode", "-0001");
/* 341 */         map.put("errorText", "参数为空");
/* 342 */         rm.setResult("fail");
/* 343 */         rm.setResponse(map);
/* 344 */         return rm;
/*     */       } 
/* 346 */       String content = "";
/* 347 */       if ("3".equals(devDataType)) {
/*     */         
/* 349 */         content = download(dataInfo);
/*     */       } else {
/* 351 */         content = dataInfo;
/*     */       } 
/*     */       
/* 354 */       if (defaultStoragePosition.equals("1")) {
/* 355 */         PlayTaskBasic detail = TaskUtil.getTaskBasic(devCode, content, devType, Integer.valueOf(505));
/* 356 */         TaskUtil.addTaskToRedis(detail, devCode);
/* 357 */         saveTask(detail);
/* 358 */       } else if (defaultStoragePosition.equals("2")) {
/* 359 */         PlayTaskBasics ptbs = TaskUtil.getTaskBasics(devCode, content, devType, Integer.valueOf(505));
/* 360 */         this.playTaskBasicsMapper.insertSelective(ptbs);
/*     */         
/* 362 */         String devCodeName = String.valueOf(devCode) + "_cardInfo";
/* 363 */         SysTaskExpireLog log = this.sysTaskExpireLogMapper.getLogByDevCodeName(devCodeName);
/* 364 */         if (log == null) {
/* 365 */           SysTaskExpireLog expirelog1 = TaskUtil.addTaskToRedisNew(ptbs, devCode);
/* 366 */           this.sysTaskExpireLogMapper.insertSelective(expirelog1);
/*     */         } else {
/* 368 */           SysTaskExpireLog expirelog1 = TaskUtil.addTaskToRedisNew(ptbs, devCode);
/* 369 */           expirelog1.setExpireLogId(log.getExpireLogId());
/* 370 */           this.sysTaskExpireLogMapper.updateByPrimaryKey(expirelog1);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 375 */         saveTaskNew(ptbs);
/*     */       } 
/* 377 */       map.put("tips", "数据更新成功");
/* 378 */       rm.setResult("ok");
/* 379 */       rm.setResponse(map);
/* 380 */     } catch (Exception e) {
/* 381 */       e.printStackTrace();
///* 382 */       this.log.error("数据更新", e);
/* 383 */       if (defaultStoragePosition.equals("2")) {
/* 384 */         TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
/*     */       }
/* 386 */       map.put("errorCode", "-1000");
/* 387 */       map.put("errorText", "数据更新失败");
/* 388 */       rm.setResult("fail");
/* 389 */       rm.setResponse(map);
/*     */     } 
/* 391 */     return rm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String download(String urlString) {
/* 400 */     InputStream is = null;
/* 401 */     OutputStream os = null;
/* 402 */     String savePath = null;
/*     */     try {
/* 404 */       String type = urlString.substring(urlString.lastIndexOf("\\."), urlString.length());
/* 405 */       String path = String.valueOf(ConfigUtil.getProperties("default.source.path")) + "/cardRes/";
/* 406 */       savePath = String.valueOf(path) + UUID.randomUUID() + DateUtil.getNowTimeStamp() + type;
/*     */       
/* 408 */       URL url = new URL(urlString);
/*     */       
/* 410 */       URLConnection con = url.openConnection();
/*     */       
/* 412 */       con.setConnectTimeout(5000);
/*     */       
/* 414 */       is = con.getInputStream();
/*     */ 
/*     */       
/* 417 */       byte[] bs = new byte[2048];
/*     */ 
/*     */ 
/*     */       
/* 421 */       File sf = new File(path);
/* 422 */       if (!sf.exists()) {
/* 423 */         sf.mkdirs();
/*     */       }
/* 425 */       os = new FileOutputStream(savePath);
/*     */       int len;
/* 427 */       while ((len = is.read(bs)) != -1) {
/* 428 */         os.write(bs, 0, len);
/*     */       }
/* 430 */     } catch (Exception e) {
/* 431 */       e.printStackTrace();
///* 432 */       this.log.error("保存文件异常");
/*     */     } finally {
/*     */       
/*     */       try {
/* 436 */         if (os != null) {
/* 437 */           os.close();
/*     */         }
/* 439 */         if (is != null) {
/* 440 */           is.close();
/*     */         }
/* 442 */       } catch (Exception e) {
/* 443 */         e.printStackTrace();
///* 444 */         this.log.error("关闭流异常");
/*     */       } 
/*     */     } 
/* 447 */     return savePath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResponseModel saveAndSendTask(String devCode, Integer type, String content) {
/* 459 */     ResponseModel rm = new ResponseModel();
/* 460 */     Map<String, String> map = new HashMap<>();
/* 461 */     String defaultStoragePosition = ConfigUtil.getProperties("default.storagePosition");
/*     */     try {
/* 463 */       boolean flag = true;
/*     */ 
/*     */       
/* 466 */       if (!StringUtil.isEmpty(content) || type.intValue() == 10 || type.intValue() == 20 || type.intValue() == 40 || type.intValue() == 50 || type.intValue() == 60 || type.intValue() == 65 || type.intValue() == 70 || type.intValue() == 230 || type.intValue() == 80 || type.intValue() == 180 || type.intValue() == 190 || type.intValue() == 110 || type.intValue() == 400) {
/* 467 */         flag = false;
/*     */       }
/*     */       
/* 470 */       if (StringUtil.isEmpty(devCode) || flag || type.intValue() == 0 || type == null) {
/* 471 */         map.put("errorCode", "-0001");
/* 472 */         map.put("errorText", "参数为空");
/* 473 */         rm.setResult("fail");
/* 474 */         rm.setResponse(map);
/* 475 */         return rm;
/*     */       } 
/*     */       
/* 478 */       if (defaultStoragePosition.equals("1")) {
/* 479 */         PlayTaskBasic detail = TaskUtil.getTaskBasic(devCode, content, "", type);
/* 480 */         TaskUtil.addTaskToRedis(detail, devCode);
/* 481 */         saveTask(detail);
/* 482 */       } else if (defaultStoragePosition.equals("2")) {
/* 483 */         PlayTaskBasics ptbs = TaskUtil.getTaskBasics(devCode, content, "", type);
/* 484 */         this.playTaskBasicsMapper.insertSelective(ptbs);
/* 485 */         SysTaskExpireLog expirelog1 = TaskUtil.addTaskToRedisNew(ptbs, devCode);
/* 486 */         this.sysTaskExpireLogMapper.insertSelective(expirelog1);
/*     */ 
/*     */         
/* 489 */         saveTaskNew(ptbs);
/*     */       } 
/* 491 */       map.put("tips", "下发任务成功");
/* 492 */       rm.setResult("ok");
/* 493 */       rm.setResponse(map);
/* 494 */     } catch (Exception e) {
/* 495 */       e.printStackTrace();
///* 496 */       this.log.error("下发一般任务", e);
/* 497 */       if (defaultStoragePosition.equals("2")) {
/* 498 */         TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
/*     */       }
/* 500 */       map.put("errorCode", "-3000");
/* 501 */       map.put("errorText", "下发任务失败");
/* 502 */       rm.setResult("fail");
/* 503 */       rm.setResponse(map);
/*     */     } 
/* 505 */     return rm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResponseModel getDeviceList() {
/* 529 */     ResponseModel rm = new ResponseModel();
/* 530 */     Map<String, Object> map = new HashMap<>();
/*     */     
/*     */     try {
/* 533 */       String defaultStoragePosition = ConfigUtil.getProperties("default.storagePosition");
/* 534 */       if (defaultStoragePosition.equals("1")) {
/* 535 */         map.put("records", getDevListFromRedis());
/* 536 */       } else if (defaultStoragePosition.equals("2")) {
/* 537 */         List<TmlDeviceBasic> tblist = this.tmlDeviceBasicMapper.getTmlDeviceBasic(null);
/* 538 */         map.put("records", tblist);
/*     */       } 
/* 540 */       map.put("tips", "获取设备信息成功");
/* 541 */       rm.setResult("ok");
/* 542 */       rm.setResponse(map);
/* 543 */     } catch (Exception e) {
/* 544 */       e.printStackTrace();
///* 545 */       this.log.error("查询设备列表：", e);
/* 546 */       map.put("errorCode", "-5000");
/* 547 */       map.put("errorText", "获取设备信息失败");
/* 548 */       rm.setResult("fail");
/* 549 */       rm.setResponse(map);
/*     */     } 
/* 551 */     return rm;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<DeviceQuery> getDevListFromRedis() throws Exception {
/* 556 */     List<DeviceQuery> list = new ArrayList<>();
///* 557 */     Set<String> set = RedisUtil.getAllInfo("deviceList");
///* 558 */     if (set != null && set != null && set.size() > 0) {
///* 559 */       for (String str : set) {
///* 560 */         DeviceQuery device = (DeviceQuery)this.mapper.readValue(str, DeviceQuery.class);
///* 561 */         list.add(device);
///*     */       } 
///*     */     }
/* 564 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResponseModel addMapDevice(String devCode, String imageName) {
/* 569 */     ResponseModel rm = new ResponseModel();
/* 570 */     Map<String, Object> map = new HashMap<>();
/*     */     try {
/* 572 */       if (StringUtil.isEmpty(devCode) || StringUtil.isEmpty(imageName)) {
/* 573 */         map.put("errorCode", "-0001");
/* 574 */         map.put("errorText", "参数为空");
/* 575 */         rm.setResult("fail");
/* 576 */         rm.setResponse(map);
/* 577 */         return rm;
/*     */       } 
/*     */       
/* 580 */       String defaultStoragePosition = ConfigUtil.getProperties("default.storagePosition");
/* 581 */       if (defaultStoragePosition.equals("1")) {
/* 582 */         TmlDeviceMapImage dev = getTmlDeviceMapImage(devCode, imageName);
///* 583 */         RedisUtil.addInfo("mapDevList", this.mapper.writeValueAsString(dev));
/* 584 */       } else if (defaultStoragePosition.equals("2")) {
/* 585 */         SysDeviceMapLog devlog = getSysDeviceMapLog(devCode, imageName);
/* 586 */         this.sysDeviceMapLogMapper.insert(devlog);
/*     */       } 
/* 588 */       map.put("tips", "绑定设备和图片成功");
/* 589 */       rm.setResult("ok");
/* 590 */       rm.setResponse(map);
/* 591 */     } catch (Exception e) {
/* 592 */       e.printStackTrace();
///* 593 */       this.log.error("绑定设备和图片：", e);
/* 594 */       map.put("errorCode", "-5001");
/* 595 */       map.put("errorText", "绑定设备和图片失败");
/* 596 */       rm.setResult("fail");
/* 597 */       rm.setResponse(map);
/*     */     } 
/* 599 */     return rm;
/*     */   }
/*     */   
/*     */   private TmlDeviceMapImage getTmlDeviceMapImage(String devCode, String imageName) throws Exception {
/* 603 */     TmlDeviceMapImage dev = isDevExist(devCode);
/* 604 */     if (dev == null) {
/* 605 */       dev = new TmlDeviceMapImage();
/* 606 */       dev.setCreateBy("admin");
/* 607 */       dev.setCreateDate(DateUtil.getNowTimeStamp().longValue());
/* 608 */       dev.setDevCode(devCode);
/* 609 */       dev.setImageName(imageName);
/* 610 */       dev.setImageUrl("");
/* 611 */       dev.setUpdateBy("admin");
/* 612 */       dev.setUpdateDate(DateUtil.getNowTimeStamp().longValue());
/*     */     } else {
/* 614 */       dev.setImageName(imageName);
/* 615 */       dev.setImageUrl("");
/* 616 */       dev.setUpdateBy("admin");
/* 617 */       dev.setUpdateDate(DateUtil.getNowTimeStamp().longValue());
/*     */     } 
/* 619 */     return dev;
/*     */   }
/*     */   
/*     */   private TmlDeviceMapImage isDevExist(String devCode) throws Exception {
/* 623 */     List<TmlDeviceMapImage> list = getMapDevListFromRedis();
/* 624 */     for (TmlDeviceMapImage dev : list) {
/* 625 */       if (devCode.equals(dev.getDevCode())) {
///* 626 */         RedisUtil.delInfo("mapDevList", this.mapper.writeValueAsString(dev));
/* 627 */         return dev;
/*     */       } 
/*     */     } 
/* 630 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResponseModel getMapDeviceList() {
/* 635 */     ResponseModel rm = new ResponseModel();
/* 636 */     Map<String, Object> map = new HashMap<>();
/*     */     
/*     */     try {
/* 639 */       String defaultStoragePosition = ConfigUtil.getProperties("default.storagePosition");
/* 640 */       if (defaultStoragePosition.equals("1")) {
/* 641 */         map.put("records", getMapDevListFromRedis());
/* 642 */       } else if (defaultStoragePosition.equals("2")) {
/* 643 */         List<SysDeviceMapLog> loglist = this.sysDeviceMapLogMapper.getDevMapLogList(null);
/* 644 */         map.put("records", loglist);
/*     */       } 
/* 646 */       map.put("tips", "获取设备信息成功");
/* 647 */       rm.setResult("ok");
/* 648 */       rm.setResponse(map);
/* 649 */     } catch (Exception e) {
/* 650 */       e.printStackTrace();
///* 651 */       this.log.error("查询设备列表：", e);
/* 652 */       map.put("errorCode", "-5000");
/* 653 */       map.put("errorText", "获取设备信息失败");
/* 654 */       rm.setResult("fail");
/* 655 */       rm.setResponse(map);
/*     */     } 
/* 657 */     return rm;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<TmlDeviceMapImage> getMapDevListFromRedis() throws Exception {
/* 662 */     List<TmlDeviceMapImage> list = new ArrayList<>();
///* 663 */     Set<String> set = RedisUtil.getAllInfo("mapDevList");
///* 664 */     if (set != null && set != null && set.size() > 0) {
///* 665 */       for (String str : set) {
///* 666 */         TmlDeviceMapImage device = (TmlDeviceMapImage)this.mapper.readValue(str, TmlDeviceMapImage.class);
///* 667 */         list.add(device);
///*     */       } 
///*     */     }
/* 670 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   private SysDeviceMapLog getSysDeviceMapLog(String devCode, String imageName) throws Exception {
/* 675 */     List<SysDeviceMapLog> devMapLogList = this.sysDeviceMapLogMapper.getDevMapLogList(null);
/* 676 */     for (SysDeviceMapLog dev : devMapLogList) {
/* 677 */       if (devCode.equals(dev.getDevCode())) {
/* 678 */         this.sysDeviceMapLogMapper.deleteByPrimaryKey(dev.getDevMapId());
/* 679 */         dev.setImageName(imageName);
/* 680 */         dev.setImageUrl("");
/* 681 */         dev.setUpdateBy("admin");
/* 682 */         dev.setUpdateDate(DateUtil.getNowTimeStamp());
/* 683 */         return dev;
/*     */       } 
/*     */     } 
/* 686 */     SysDeviceMapLog maplog = new SysDeviceMapLog();
/* 687 */     maplog.setCreateBy("admin");
/* 688 */     maplog.setCreateDate(DateUtil.getNowTimeStamp());
/* 689 */     maplog.setDevCode(devCode);
/* 690 */     maplog.setImageName(imageName);
/* 691 */     maplog.setImageUrl("");
/* 692 */     maplog.setUpdateBy("admin");
/* 693 */     maplog.setUpdateDate(DateUtil.getNowTimeStamp());
/* 694 */     return maplog;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\service\impl\AuthServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */