/*     */ package com.listen.util;
/*     */ 
/*     */ import com.fasterxml.jackson.core.JsonProcessingException;
/*     */ import com.fasterxml.jackson.databind.ObjectMapper;
/*     */ import com.listen.model.PlayTaskBasic;
/*     */ import com.listen.model.query.TaskInfo;
/*     */ import com.listen.model.sdkservice.PlayTaskBasics;
/*     */ import com.listen.model.sdkservice.SysTaskExpireLog;
/*     */ import com.listen.redis.RedisUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TaskUtil
/*     */ {
/*     */   public static void addTaskToRedis(PlayTaskBasic ptb, String devCode) throws JsonProcessingException {
/*  18 */     TaskInfo info = new TaskInfo(ptb.getTaskId(), ptb.getTaskType(), ptb.getTaskContent());
/*  19 */     ObjectMapper mapper = new ObjectMapper();
/*  20 */     int type = ptb.getTaskType().intValue();
/*  21 */     if (type == 60 || type == 65 || type == 70 || type == 230) {
///*  22 */       RedisUtil.setLPush(String.valueOf(devCode) + "_updateTask", new String[] { mapper.writeValueAsString(info) });
///*  23 */       RedisUtil.disableTime(String.valueOf(devCode) + "_updateTask", 86400);
/*  24 */     } else if (type == 135 || type == 140 || type == 150 || type == 160 || type == 170) {
///*  25 */       RedisUtil.setLPush(String.valueOf(devCode) + "_programTask", new String[] { mapper.writeValueAsString(info) });
///*  26 */       RedisUtil.disableTime(String.valueOf(devCode) + "_programTask", 86400);
/*  27 */     } else if (type == 80) {
/*  28 */       info.setTaskContent(DateUtil.getNowTimeStamp().toString());
///*  29 */       RedisUtil.setLPush(String.valueOf(devCode) + "_indexTask", new String[] { mapper.writeValueAsString(info) });
///*  30 */       RedisUtil.disableTime(String.valueOf(devCode) + "_indexTask", 86400);
/*  31 */     } else if (type == 505) {
///*  32 */       RedisUtil.addValue(String.valueOf(devCode) + "_cardInfo", mapper.writeValueAsString(info));
/*     */     } else {
/*     */       
///*  35 */       RedisUtil.setLPush(String.valueOf(devCode) + "_indexTask", new String[] { mapper.writeValueAsString(info) });
///*  36 */       RedisUtil.disableTime(String.valueOf(devCode) + "_indexTask", 86400);
/*     */     } 
///*  38 */     RedisUtil.setLPush("task_" + devCode, new String[] { mapper.writeValueAsString(ptb) });
///*  39 */     RedisUtil.disableTime("task_" + devCode, 86400);
/*     */   }
/*     */   
/*     */   public static PlayTaskBasic getTaskBasic(String devCode, String content, String devType, Integer taskType) {
///*  43 */     String taskId = RedisUtil.getValue("taskId_SDK");
///*  44 */     Integer id = Integer.valueOf(0);
///*  45 */     if (taskId == null || "".equals(taskId)) {
///*  46 */       id = Integer.valueOf(0);
///*     */     } else {
///*  48 */       id = Integer.valueOf((Integer.valueOf(taskId).intValue() >= 0) ? Integer.valueOf(taskId).intValue() : 0);
///*     */     } 
/*  50 */     PlayTaskBasic task = new PlayTaskBasic();
///*  51 */     task.setCreateBy(devCode);
///*  52 */     task.setCreateDate(DateUtil.getNowTimeStamp().longValue());
///*  53 */     task.setUpdateBy(devCode);
///*  54 */     task.setUpdateDate(DateUtil.getNowTimeStamp().longValue());
///*  55 */     task.setCustNo("B");
///*  56 */     task.setDevCode(devCode);
///*  57 */     task.setDevType(("".equals(devType) || devType == null) ? "QCP" : devType);
///*  58 */     task.setGroupId(Integer.valueOf(0));
///*  59 */     task.setTaskContent(content);
///*  60 */     task.setTaskExpireDate(DateUtil.getNowTimeStamp().longValue());
///*  61 */     task.setTaskStatus(Integer.valueOf(0));
///*  62 */     task.setTaskType(taskType);
///*  63 */     task.setTaskTimes(Integer.valueOf(0));
///*  64 */     task.setTaskId(Integer.valueOf(id.intValue() + 1));
///*  65 */     RedisUtil.addValue("taskId_SDK", String.valueOf(task.getTaskId()));
/*  66 */     return task;
/*     */   }
/*     */ 
/*     */   
/*     */   public static SysTaskExpireLog addTaskToRedisNew(PlayTaskBasics ptb, String devCode) throws JsonProcessingException {
/*  71 */     TaskInfo info = new TaskInfo(ptb.getTaskId(), ptb.getTaskType(), ptb.getTaskContent());
/*  72 */     SysTaskExpireLog expireLog = new SysTaskExpireLog();
/*  73 */     expireLog.setTaskId(ptb.getTaskId());
/*  74 */     expireLog.setTaskType(ptb.getTaskType());
/*  75 */     expireLog.setDevCode(devCode);
/*  76 */     expireLog.setCreateDate(DateUtil.getNowTimeStamp());
/*  77 */     ObjectMapper mapper = new ObjectMapper();
/*  78 */     int type = ptb.getTaskType().intValue();
/*  79 */     if (type == 60 || type == 65 || type == 70 || type == 230) {
/*  80 */       expireLog.setTaskName("updateTask");
/*  81 */       expireLog.setRemark(String.valueOf(devCode) + "_updateTask");
/*  82 */       expireLog.setTaskContent(mapper.writeValueAsString(info));
/*  83 */       expireLog.setExpireDate(Long.valueOf(DateUtil.getAppointTime(1)));
/*  84 */     } else if (type == 135 || type == 140 || type == 150 || type == 160 || type == 170) {
/*  85 */       expireLog.setTaskName("programTask");
/*  86 */       expireLog.setRemark(String.valueOf(devCode) + "_programTask");
/*  87 */       expireLog.setTaskContent(mapper.writeValueAsString(info));
/*  88 */       expireLog.setExpireDate(Long.valueOf(DateUtil.getAppointTime(1)));
/*  89 */     } else if (type == 80) {
/*  90 */       info.setTaskContent(DateUtil.getNowTimeStamp().toString());
/*  91 */       expireLog.setTaskName("indexTask");
/*  92 */       expireLog.setRemark(String.valueOf(devCode) + "_indexTask");
/*  93 */       expireLog.setTaskContent(mapper.writeValueAsString(info));
/*  94 */       expireLog.setExpireDate(Long.valueOf(DateUtil.getAppointTime(1)));
/*  95 */     } else if (type == 505) {
/*  96 */       expireLog.setTaskName("cardInfo");
/*  97 */       expireLog.setRemark(String.valueOf(devCode) + "_cardInfo");
/*  98 */       expireLog.setTaskContent(mapper.writeValueAsString(info));
/*     */     } else {
/* 100 */       expireLog.setTaskName("indexTask");
/* 101 */       expireLog.setRemark(String.valueOf(devCode) + "_indexTask");
/* 102 */       expireLog.setTaskContent(mapper.writeValueAsString(info));
/* 103 */       expireLog.setExpireDate(Long.valueOf(DateUtil.getAppointTime(1)));
/*     */     } 
/* 105 */     return expireLog;
/*     */   }
/*     */ 
/*     */   
/*     */   public static PlayTaskBasics getTaskBasics(String devCode, String content, String devType, Integer taskType) {
/* 110 */     PlayTaskBasics task = new PlayTaskBasics();
/* 111 */     task.setCreateBy(devCode);
/* 112 */     task.setCreateDate(DateUtil.getNowTimeStamp());
/* 113 */     task.setUpdateBy(devCode);
/* 114 */     task.setUpdateDate(DateUtil.getNowTimeStamp());
/* 115 */     task.setCustNo("B");
/* 116 */     task.setDevCode(devCode);
/* 117 */     task.setDevType(("".equals(devType) || devType == null) ? "QCP" : devType);
/* 118 */     task.setGroupId(Integer.valueOf(0));
/* 119 */     task.setTaskContent(content);
/* 120 */     task.setTaskExpireDate(DateUtil.getNowTimeStamp());
/* 121 */     task.setTaskStatus(Integer.valueOf(0));
/* 122 */     task.setTaskType(taskType);
/* 123 */     task.setTaskTimes(Integer.valueOf(0));
/* 124 */     return task;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\TaskUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */