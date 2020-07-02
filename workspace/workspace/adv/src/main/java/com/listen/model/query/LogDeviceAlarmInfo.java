/*     */ package com.listen.model.query;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LogDeviceAlarmInfo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int logAlarmId;
/*     */   private String devCode;
/*     */   private String tranCode;
/*     */   private String devType;
/*     */   private String devTypeName;
/*     */   private String devEquipmentType;
/*     */   private String devLongitudeValue;
/*     */   
/*  19 */   public int getLogAlarmId() { return this.logAlarmId; }
/*     */   private String devLatitudeValue; private String alarmCode; private String alarmType; private String alarmContent; private String devAlarmArea; private long updateDatetime; private String custNo; private String custCode;
/*     */   
/*  22 */   public void setLogAlarmId(int logAlarmId) { this.logAlarmId = logAlarmId; }
/*     */ 
/*     */   
/*  25 */   public String getDevCode() { return this.devCode; }
/*     */ 
/*     */   
/*  28 */   public void setDevCode(String devCode) { this.devCode = devCode; }
/*     */ 
/*     */   
/*  31 */   public String getTranCode() { return this.tranCode; }
/*     */ 
/*     */   
/*  34 */   public void setTranCode(String tranCode) { this.tranCode = tranCode; }
/*     */ 
/*     */   
/*  37 */   public String getDevType() { return this.devType; }
/*     */ 
/*     */   
/*  40 */   public void setDevType(String devType) { this.devType = devType; }
/*     */ 
/*     */   
/*  43 */   public String getDevTypeName() { return this.devTypeName; }
/*     */ 
/*     */   
/*  46 */   public void setDevTypeName(String devTypeName) { this.devTypeName = devTypeName; }
/*     */ 
/*     */   
/*  49 */   public String getDevEquipmentType() { return this.devEquipmentType; }
/*     */ 
/*     */   
/*  52 */   public void setDevEquipmentType(String devEquipmentType) { this.devEquipmentType = devEquipmentType; }
/*     */ 
/*     */   
/*  55 */   public String getDevLongitudeValue() { return this.devLongitudeValue; }
/*     */ 
/*     */   
/*  58 */   public void setDevLongitudeValue(String devLongitudeValue) { this.devLongitudeValue = devLongitudeValue; }
/*     */ 
/*     */   
/*  61 */   public String getDevLatitudeValue() { return this.devLatitudeValue; }
/*     */ 
/*     */   
/*  64 */   public void setDevLatitudeValue(String devLatitudeValue) { this.devLatitudeValue = devLatitudeValue; }
/*     */ 
/*     */   
/*  67 */   public String getAlarmCode() { return this.alarmCode; }
/*     */ 
/*     */   
/*  70 */   public void setAlarmCode(String alarmCode) { this.alarmCode = alarmCode; }
/*     */ 
/*     */   
/*  73 */   public String getAlarmContent() { return this.alarmContent; }
/*     */ 
/*     */   
/*  76 */   public void setAlarmContent(String alarmContent) { this.alarmContent = alarmContent; }
/*     */ 
/*     */   
/*  79 */   public String getDevAlarmArea() { return this.devAlarmArea; }
/*     */ 
/*     */   
/*  82 */   public void setDevAlarmArea(String devAlarmArea) { this.devAlarmArea = devAlarmArea; }
/*     */ 
/*     */   
/*  85 */   public long getUpdateDatetime() { return this.updateDatetime; }
/*     */ 
/*     */   
/*  88 */   public void setUpdateDatetime(long updateDatetime) { this.updateDatetime = updateDatetime; }
/*     */ 
/*     */   
/*  91 */   public String getCustNo() { return this.custNo; }
/*     */ 
/*     */   
/*  94 */   public void setCustNo(String custNo) { this.custNo = custNo; }
/*     */ 
/*     */ 
/*     */   
/*  98 */   public String getCustCode() { return this.custCode; }
/*     */ 
/*     */   
/* 101 */   public void setCustCode(String custCode) { this.custCode = custCode; }
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
/* 117 */   public String getAlarmType() { return this.alarmType; }
/*     */ 
/*     */   
/* 120 */   public void setAlarmType(String alarmType) { this.alarmType = alarmType; }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\query\LogDeviceAlarmInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */