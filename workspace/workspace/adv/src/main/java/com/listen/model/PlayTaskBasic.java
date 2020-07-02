/*     */ package com.listen.model;
/*     */ 
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
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
/*     */ 
/*     */ 
/*     */ public class PlayTaskBasic
/*     */ {
/*     */   @Id
/*     */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*     */   private Integer taskId;
/*     */   private String devCode;
/*     */   private String devType;
/*     */   private Integer groupId;
/*     */   private Integer taskType;
/*     */   private Integer taskStatus;
/*     */   private Integer taskTimes;
/*     */   private String taskContent;
/*     */   private String createBy;
/*     */   private long createDate;
/*     */   private String updateBy;
/*     */   private long updateDate;
/*     */   private long taskExpireDate;
/*     */   private String taskPercent;
/*     */   private String custNo;
/*     */   private String taskRemarks;
/*     */   private String taskDownloadRate;
/*     */   
/*  51 */   public Integer getTaskId() { return this.taskId; }
/*     */ 
/*     */ 
/*     */   
/*  55 */   public void setTaskId(Integer taskId) { this.taskId = taskId; }
/*     */ 
/*     */ 
/*     */   
/*  59 */   public String getDevCode() { return this.devCode; }
/*     */ 
/*     */ 
/*     */   
/*  63 */   public void setDevCode(String devCode) { this.devCode = devCode; }
/*     */ 
/*     */ 
/*     */   
/*  67 */   public String getDevType() { return this.devType; }
/*     */ 
/*     */ 
/*     */   
/*  71 */   public void setDevType(String devType) { this.devType = devType; }
/*     */ 
/*     */ 
/*     */   
/*  75 */   public Integer getGroupId() { return this.groupId; }
/*     */ 
/*     */ 
/*     */   
/*  79 */   public void setGroupId(Integer groupId) { this.groupId = groupId; }
/*     */ 
/*     */ 
/*     */   
/*  83 */   public Integer getTaskType() { return this.taskType; }
/*     */ 
/*     */ 
/*     */   
/*  87 */   public void setTaskType(Integer taskType) { this.taskType = taskType; }
/*     */ 
/*     */ 
/*     */   
/*  91 */   public Integer getTaskStatus() { return this.taskStatus; }
/*     */ 
/*     */ 
/*     */   
/*  95 */   public void setTaskStatus(Integer taskStatus) { this.taskStatus = taskStatus; }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public Integer getTaskTimes() { return this.taskTimes; }
/*     */ 
/*     */ 
/*     */   
/* 103 */   public void setTaskTimes(Integer taskTimes) { this.taskTimes = taskTimes; }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public String getTaskContent() { return this.taskContent; }
/*     */ 
/*     */ 
/*     */   
/* 111 */   public void setTaskContent(String taskContent) { this.taskContent = taskContent; }
/*     */ 
/*     */ 
/*     */   
/* 115 */   public String getCreateBy() { return this.createBy; }
/*     */ 
/*     */ 
/*     */   
/* 119 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*     */ 
/*     */ 
/*     */   
/* 123 */   public long getCreateDate() { return this.createDate; }
/*     */ 
/*     */ 
/*     */   
/* 127 */   public void setCreateDate(long createDate) { this.createDate = createDate; }
/*     */ 
/*     */ 
/*     */   
/* 131 */   public String getUpdateBy() { return this.updateBy; }
/*     */ 
/*     */ 
/*     */   
/* 135 */   public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
/*     */ 
/*     */ 
/*     */   
/* 139 */   public long getUpdateDate() { return this.updateDate; }
/*     */ 
/*     */ 
/*     */   
/* 143 */   public void setUpdateDate(long updateDate) { this.updateDate = updateDate; }
/*     */ 
/*     */ 
/*     */   
/* 147 */   public long getTaskExpireDate() { return this.taskExpireDate; }
/*     */ 
/*     */ 
/*     */   
/* 151 */   public void setTaskExpireDate(long taskExpireDate) { this.taskExpireDate = taskExpireDate; }
/*     */ 
/*     */ 
/*     */   
/* 155 */   public String getTaskPercent() { return this.taskPercent; }
/*     */ 
/*     */ 
/*     */   
/* 159 */   public void setTaskPercent(String taskPercent) { this.taskPercent = taskPercent; }
/*     */ 
/*     */ 
/*     */   
/* 163 */   public String getCustNo() { return this.custNo; }
/*     */ 
/*     */ 
/*     */   
/* 167 */   public void setCustNo(String custNo) { this.custNo = custNo; }
/*     */ 
/*     */ 
/*     */   
/* 171 */   public String getTaskRemarks() { return this.taskRemarks; }
/*     */ 
/*     */ 
/*     */   
/* 175 */   public void setTaskRemarks(String taskRemarks) { this.taskRemarks = taskRemarks; }
/*     */ 
/*     */ 
/*     */   
/* 179 */   public String getTaskDownloadRate() { return this.taskDownloadRate; }
/*     */ 
/*     */ 
/*     */   
/* 183 */   public void setTaskDownloadRate(String taskDownloadRate) { this.taskDownloadRate = taskDownloadRate; }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\PlayTaskBasic.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */