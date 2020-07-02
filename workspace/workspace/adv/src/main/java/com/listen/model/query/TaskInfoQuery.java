/*     */ package com.listen.model.query;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TaskInfoQuery
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer taskId;
/*     */   private Integer taskType;
/*     */   private String taskContent;
/*     */   private String devCode;
/*     */   private String devType;
/*     */   private Integer groupId;
/*     */   private Integer taskStatus;
/*     */   private Integer taskTimes;
/*     */   private String taskPercent;
/*     */   private String taskDownloadRate;
/*     */   private String taskExpireDate;
/*     */   private String custNo;
/*     */   private String taskRemarks;
/*     */   private String createBy;
/*     */   private long createDate;
/*     */   private String updateBy;
/*     */   private long updateDate;
/*     */   private String custCode;
/*     */   private String devTranCode;
/*     */   
/*  35 */   public String getDevCode() { return this.devCode; }
/*     */ 
/*     */ 
/*     */   
/*  39 */   public void setDevCode(String devCode) { this.devCode = devCode; }
/*     */ 
/*     */ 
/*     */   
/*  43 */   public String getDevType() { return this.devType; }
/*     */ 
/*     */ 
/*     */   
/*  47 */   public void setDevType(String devType) { this.devType = devType; }
/*     */ 
/*     */ 
/*     */   
/*  51 */   public Integer getGroupId() { return this.groupId; }
/*     */ 
/*     */ 
/*     */   
/*  55 */   public void setGroupId(Integer groupId) { this.groupId = groupId; }
/*     */ 
/*     */ 
/*     */   
/*  59 */   public Integer getTaskStatus() { return this.taskStatus; }
/*     */ 
/*     */ 
/*     */   
/*  63 */   public void setTaskStatus(Integer taskStatus) { this.taskStatus = taskStatus; }
/*     */ 
/*     */ 
/*     */   
/*  67 */   public Integer getTaskTimes() { return this.taskTimes; }
/*     */ 
/*     */ 
/*     */   
/*  71 */   public void setTaskTimes(Integer taskTimes) { this.taskTimes = taskTimes; }
/*     */ 
/*     */ 
/*     */   
/*  75 */   public String getTaskPercent() { return this.taskPercent; }
/*     */ 
/*     */ 
/*     */   
/*  79 */   public void setTaskPercent(String taskPercent) { this.taskPercent = taskPercent; }
/*     */ 
/*     */ 
/*     */   
/*  83 */   public String getTaskDownloadRate() { return this.taskDownloadRate; }
/*     */ 
/*     */ 
/*     */   
/*  87 */   public void setTaskDownloadRate(String taskDownloadRate) { this.taskDownloadRate = taskDownloadRate; }
/*     */ 
/*     */ 
/*     */   
/*  91 */   public String getTaskExpireDate() { return this.taskExpireDate; }
/*     */ 
/*     */ 
/*     */   
/*  95 */   public void setTaskExpireDate(String taskExpireDate) { this.taskExpireDate = taskExpireDate; }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public String getCustNo() { return this.custNo; }
/*     */ 
/*     */ 
/*     */   
/* 103 */   public void setCustNo(String custNo) { this.custNo = custNo; }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public String getTaskRemarks() { return this.taskRemarks; }
/*     */ 
/*     */ 
/*     */   
/* 111 */   public void setTaskRemarks(String taskRemarks) { this.taskRemarks = taskRemarks; }
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
/* 147 */   public String getCustCode() { return this.custCode; }
/*     */ 
/*     */ 
/*     */   
/* 151 */   public void setCustCode(String custCode) { this.custCode = custCode; }
/*     */ 
/*     */ 
/*     */   
/* 155 */   public String getDevTranCode() { return this.devTranCode; }
/*     */ 
/*     */ 
/*     */   
/* 159 */   public void setDevTranCode(String devTranCode) { this.devTranCode = devTranCode; }
/*     */ 
/*     */ 
/*     */   
/*     */   public TaskInfoQuery() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public TaskInfoQuery(Integer taskId, Integer taskType, String taskContent) {
/* 168 */     this.taskId = taskId;
/* 169 */     this.taskType = taskType;
/* 170 */     this.taskContent = taskContent;
/*     */   }
/*     */ 
/*     */   
/* 174 */   public Integer getTaskId() { return this.taskId; }
/*     */ 
/*     */   
/* 177 */   public void setTaskId(Integer taskId) { this.taskId = taskId; }
/*     */ 
/*     */   
/* 180 */   public Integer getTaskType() { return this.taskType; }
/*     */ 
/*     */   
/* 183 */   public void setTaskType(Integer taskType) { this.taskType = taskType; }
/*     */ 
/*     */   
/* 186 */   public String getTaskContent() { return this.taskContent; }
/*     */ 
/*     */   
/* 189 */   public void setTaskContent(String taskContent) { this.taskContent = taskContent; }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\query\TaskInfoQuery.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */