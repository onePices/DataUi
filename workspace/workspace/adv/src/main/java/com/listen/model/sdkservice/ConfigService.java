/*     */ package com.listen.model.sdkservice;
/*     */ 
/*     */ 
/*     */ public class ConfigService
/*     */ {
/*     */   private String defaultSourcePath;
/*     */   private String defaultCallbackJs;
/*     */   private String defaultTomcatPath;
/*     */   private String defaultLogTimeOut;
/*     */   private String defaultTaskZipTimeOut;
/*     */   private String defaultHeartbeatSaveUrl;
/*     */   private String defaultTaskSaveUrl;
/*     */   private String defaultAlarmSaveUrl;
/*     */   private String defaultInputPath;
/*     */   private String defaultGetPictureTimeOut;
/*     */   private String defaultMapLogPath;
/*     */   private String defaultMapImageTimeOut;
/*     */   private String defaultHeartbeatSwitch;
/*     */   private String defaultHeartbeatRecord;
/*     */   private String defaultUpdatetaskRecord;
/*     */   private String defaultUpdatetaskSwitch;
/*     */   private String defaultStoragePosition;
/*     */   private String defaultSendTaskUrl;
/*     */   
/*  25 */   public String getDefaultSourcePath() { return this.defaultSourcePath; }
/*     */ 
/*     */   
/*  28 */   public void setDefaultSourcePath(String defaultSourcePath) { this.defaultSourcePath = defaultSourcePath; }
/*     */ 
/*     */   
/*  31 */   public String getDefaultCallbackJs() { return this.defaultCallbackJs; }
/*     */ 
/*     */   
/*  34 */   public void setDefaultCallbackJs(String defaultCallbackJs) { this.defaultCallbackJs = defaultCallbackJs; }
/*     */ 
/*     */   
/*  37 */   public String getDefaultTomcatPath() { return this.defaultTomcatPath; }
/*     */ 
/*     */   
/*  40 */   public void setDefaultTomcatPath(String defaultTomcatPath) { this.defaultTomcatPath = defaultTomcatPath; }
/*     */ 
/*     */   
/*  43 */   public String getDefaultLogTimeOut() { return this.defaultLogTimeOut; }
/*     */ 
/*     */   
/*  46 */   public void setDefaultLogTimeOut(String defaultLogTimeOut) { this.defaultLogTimeOut = defaultLogTimeOut; }
/*     */ 
/*     */   
/*  49 */   public String getDefaultTaskZipTimeOut() { return this.defaultTaskZipTimeOut; }
/*     */ 
/*     */   
/*  52 */   public void setDefaultTaskZipTimeOut(String defaultTaskZipTimeOut) { this.defaultTaskZipTimeOut = defaultTaskZipTimeOut; }
/*     */ 
/*     */   
/*  55 */   public String getDefaultHeartbeatSaveUrl() { return this.defaultHeartbeatSaveUrl; }
/*     */ 
/*     */   
/*  58 */   public void setDefaultHeartbeatSaveUrl(String defaultHeartbeatSaveUrl) { this.defaultHeartbeatSaveUrl = defaultHeartbeatSaveUrl; }
/*     */ 
/*     */   
/*  61 */   public String getDefaultTaskSaveUrl() { return this.defaultTaskSaveUrl; }
/*     */ 
/*     */   
/*  64 */   public void setDefaultTaskSaveUrl(String defaultTaskSaveUrl) { this.defaultTaskSaveUrl = defaultTaskSaveUrl; }
/*     */ 
/*     */   
/*  67 */   public String getDefaultAlarmSaveUrl() { return this.defaultAlarmSaveUrl; }
/*     */ 
/*     */   
/*  70 */   public void setDefaultAlarmSaveUrl(String defaultAlarmSaveUrl) { this.defaultAlarmSaveUrl = defaultAlarmSaveUrl; }
/*     */ 
/*     */   
/*  73 */   public String getDefaultInputPath() { return this.defaultInputPath; }
/*     */ 
/*     */   
/*  76 */   public void setDefaultInputPath(String defaultInputPath) { this.defaultInputPath = defaultInputPath; }
/*     */ 
/*     */   
/*  79 */   public String getDefaultGetPictureTimeOut() { return this.defaultGetPictureTimeOut; }
/*     */ 
/*     */   
/*  82 */   public void setDefaultGetPictureTimeOut(String defaultGetPictureTimeOut) { this.defaultGetPictureTimeOut = defaultGetPictureTimeOut; }
/*     */ 
/*     */   
/*  85 */   public String getDefaultMapLogPath() { return this.defaultMapLogPath; }
/*     */ 
/*     */   
/*  88 */   public void setDefaultMapLogPath(String defaultMapLogPath) { this.defaultMapLogPath = defaultMapLogPath; }
/*     */ 
/*     */   
/*  91 */   public String getDefaultMapImageTimeOut() { return this.defaultMapImageTimeOut; }
/*     */ 
/*     */   
/*  94 */   public void setDefaultMapImageTimeOut(String defaultMapImageTimeOut) { this.defaultMapImageTimeOut = defaultMapImageTimeOut; }
/*     */ 
/*     */   
/*  97 */   public String getDefaultHeartbeatSwitch() { return this.defaultHeartbeatSwitch; }
/*     */ 
/*     */   
/* 100 */   public void setDefaultHeartbeatSwitch(String defaultHeartbeatSwitch) { this.defaultHeartbeatSwitch = defaultHeartbeatSwitch; }
/*     */ 
/*     */   
/* 103 */   public String getDefaultHeartbeatRecord() { return this.defaultHeartbeatRecord; }
/*     */ 
/*     */   
/* 106 */   public void setDefaultHeartbeatRecord(String defaultHeartbeatRecord) { this.defaultHeartbeatRecord = defaultHeartbeatRecord; }
/*     */ 
/*     */   
/* 109 */   public String getDefaultUpdatetaskRecord() { return this.defaultUpdatetaskRecord; }
/*     */ 
/*     */   
/* 112 */   public void setDefaultUpdatetaskRecord(String defaultUpdatetaskRecord) { this.defaultUpdatetaskRecord = defaultUpdatetaskRecord; }
/*     */ 
/*     */   
/* 115 */   public String getDefaultStoragePosition() { return this.defaultStoragePosition; }
/*     */ 
/*     */   
/* 118 */   public void setDefaultStoragePosition(String defaultStoragePosition) { this.defaultStoragePosition = defaultStoragePosition; }
/*     */ 
/*     */   
/* 121 */   public String getDefaultSendTaskUrl() { return this.defaultSendTaskUrl; }
/*     */ 
/*     */   
/* 124 */   public void setDefaultSendTaskUrl(String defaultSendTaskUrl) { this.defaultSendTaskUrl = defaultSendTaskUrl; }
/*     */ 
/*     */   
/* 127 */   public String getDefaultUpdatetaskSwitch() { return this.defaultUpdatetaskSwitch; }
/*     */ 
/*     */   
/* 130 */   public void setDefaultUpdatetaskSwitch(String defaultUpdatetaskSwitch) { this.defaultUpdatetaskSwitch = defaultUpdatetaskSwitch; }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 134 */     return "ConfigService [defaultSourcePath=" + this.defaultSourcePath + 
/* 135 */       ", defaultCallbackJs=" + this.defaultCallbackJs + 
/* 136 */       ", defaultTomcatPath=" + this.defaultTomcatPath + 
/* 137 */       ", defaultLogTimeOut=" + this.defaultLogTimeOut + 
/* 138 */       ", defaultTaskZipTimeOut=" + this.defaultTaskZipTimeOut + 
/* 139 */       ", defaultHeartbeatSaveUrl=" + this.defaultHeartbeatSaveUrl + 
/* 140 */       ", defaultTaskSaveUrl=" + this.defaultTaskSaveUrl + 
/* 141 */       ", defaultAlarmSaveUrl=" + this.defaultAlarmSaveUrl + 
/* 142 */       ", defaultInputPath=" + this.defaultInputPath + 
/* 143 */       ", defaultGetPictureTimeOut=" + this.defaultGetPictureTimeOut + 
/* 144 */       ", defaultMapLogPath=" + this.defaultMapLogPath + 
/* 145 */       ", defaultMapImageTimeOut=" + this.defaultMapImageTimeOut + 
/* 146 */       ", defaultHeartbeatSwitch=" + this.defaultHeartbeatSwitch + 
/* 147 */       ", defaultHeartbeatRecord=" + this.defaultHeartbeatRecord + 
/* 148 */       ", defaultUpdatetaskRecord=" + this.defaultUpdatetaskRecord + 
/* 149 */       ", defaultUpdatetaskSwitch=" + this.defaultUpdatetaskSwitch + 
/* 150 */       ", defaultStoragePosition=" + this.defaultStoragePosition + 
/* 151 */       ", defaultSendTaskUrl=" + this.defaultSendTaskUrl + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\sdkservice\ConfigService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */