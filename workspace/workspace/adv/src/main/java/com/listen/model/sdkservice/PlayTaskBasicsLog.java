/*     */ package com.listen.model.sdkservice;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayTaskBasicsLog
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*     */   private Integer taskLogId;
/*     */   private String devCode;
/*     */   private String devType;
/*     */   private Integer groupId;
/*     */   private Integer taskType;
/*     */   private byte taskStatus;
/*     */   private Integer taskTimes;
/*     */   private String taskPercent;
/*     */   private String taskDownloadRate;
/*     */   private String taskContent;
/*     */   private Long taskExpireDate;
/*     */   private String taskRemarks;
/*     */   private String custNo;
/*     */   private String custCode;
/*     */   private String createBy;
/*     */   private Long createDate;
/*     */   private String updateBy;
/*     */   private Long updateDate;
/*     */   private String devTranCode;
/*     */   private String devTaskStatusDis;
/*     */   private String strUpdateDateTime;
/*     */   
/*  39 */   public Integer getTaskLogId() { return this.taskLogId; }
/*     */ 
/*     */   
/*  42 */   public void setTaskLogId(Integer taskLogId) { this.taskLogId = taskLogId; }
/*     */ 
/*     */   
/*  45 */   public String getDevCode() { return this.devCode; }
/*     */ 
/*     */   
/*  48 */   public void setDevCode(String devCode) { this.devCode = devCode; }
/*     */ 
/*     */   
/*  51 */   public String getDevType() { return this.devType; }
/*     */ 
/*     */   
/*  54 */   public void setDevType(String devType) { this.devType = devType; }
/*     */ 
/*     */   
/*  57 */   public Integer getGroupId() { return this.groupId; }
/*     */ 
/*     */   
/*  60 */   public void setGroupId(Integer groupId) { this.groupId = groupId; }
/*     */ 
/*     */   
/*  63 */   public Integer getTaskType() { return this.taskType; }
/*     */ 
/*     */   
/*  66 */   public void setTaskType(Integer taskType) { this.taskType = taskType; }
/*     */ 
/*     */   
/*  69 */   public String getTaskPercent() { return this.taskPercent; }
/*     */ 
/*     */   
/*  72 */   public void setTaskPercent(String taskPercent) { this.taskPercent = taskPercent; }
/*     */ 
/*     */   
/*  75 */   public String getTaskDownloadRate() { return this.taskDownloadRate; }
/*     */ 
/*     */   
/*  78 */   public void setTaskDownloadRate(String taskDownloadRate) { this.taskDownloadRate = taskDownloadRate; }
/*     */ 
/*     */   
/*  81 */   public String getTaskContent() { return this.taskContent; }
/*     */ 
/*     */   
/*  84 */   public void setTaskContent(String taskContent) { this.taskContent = taskContent; }
/*     */ 
/*     */   
/*  87 */   public Long getTaskExpireDate() { return this.taskExpireDate; }
/*     */ 
/*     */   
/*  90 */   public void setTaskExpireDate(Long taskExpireDate) { this.taskExpireDate = taskExpireDate; }
/*     */ 
/*     */   
/*  93 */   public String getTaskRemarks() { return this.taskRemarks; }
/*     */ 
/*     */   
/*  96 */   public void setTaskRemarks(String taskRemarks) { this.taskRemarks = taskRemarks; }
/*     */ 
/*     */   
/*  99 */   public String getCustCode() { return this.custCode; }
/*     */ 
/*     */   
/* 102 */   public void setCustCode(String custCode) { this.custCode = custCode; }
/*     */ 
/*     */   
/* 105 */   public String getCreateBy() { return this.createBy; }
/*     */ 
/*     */   
/* 108 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*     */ 
/*     */   
/* 111 */   public Long getCreateDate() { return this.createDate; }
/*     */ 
/*     */   
/* 114 */   public void setCreateDate(Long createDate) { this.createDate = createDate; }
/*     */ 
/*     */   
/* 117 */   public String getUpdateBy() { return this.updateBy; }
/*     */ 
/*     */   
/* 120 */   public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
/*     */ 
/*     */   
/* 123 */   public Long getUpdateDate() { return this.updateDate; }
/*     */ 
/*     */   
/* 126 */   public void setUpdateDate(Long updateDate) { this.updateDate = updateDate; }
/*     */ 
/*     */   
/* 129 */   public String getDevTranCode() { return this.devTranCode; }
/*     */ 
/*     */   
/* 132 */   public void setDevTranCode(String devTranCode) { this.devTranCode = devTranCode; }
/*     */ 
/*     */   
/* 135 */   public String getDevTaskStatusDis() { return this.devTaskStatusDis; }
/*     */ 
/*     */   
/* 138 */   public void setDevTaskStatusDis(String devTaskStatusDis) { this.devTaskStatusDis = devTaskStatusDis; }
/*     */ 
/*     */   
/* 141 */   public String getStrUpdateDateTime() { return this.strUpdateDateTime; }
/*     */ 
/*     */   
/* 144 */   public void setStrUpdateDateTime(String strUpdateDateTime) { this.strUpdateDateTime = strUpdateDateTime; }
/*     */ 
/*     */   
/* 147 */   public byte getTaskStatus() { return this.taskStatus; }
/*     */ 
/*     */   
/* 150 */   public void setTaskStatus(byte taskStatus) { this.taskStatus = taskStatus; }
/*     */ 
/*     */   
/* 153 */   public Integer getTaskTimes() { return this.taskTimes; }
/*     */ 
/*     */   
/* 156 */   public void setTaskTimes(Integer taskTimes) { this.taskTimes = taskTimes; }
/*     */ 
/*     */   
/* 159 */   public String getCustNo() { return this.custNo; }
/*     */ 
/*     */   
/* 162 */   public void setCustNo(String custNo) { this.custNo = custNo; }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 166 */     return "PlayTaskBasicsLog [taskLogId=" + this.taskLogId + ", devCode=" + 
/* 167 */       this.devCode + ", devType=" + this.devType + ", groupId=" + this.groupId + 
/* 168 */       ", taskType=" + this.taskType + ", taskStatus=" + this.taskStatus + 
/* 169 */       ", taskTimes=" + this.taskTimes + ", taskPercent=" + this.taskPercent + 
/* 170 */       ", taskDownloadRate=" + this.taskDownloadRate + ", taskContent=" + 
/* 171 */       this.taskContent + ", taskExpireDate=" + this.taskExpireDate + 
/* 172 */       ", taskRemarks=" + this.taskRemarks + ", custNo=" + this.custNo + 
/* 173 */       ", custCode=" + this.custCode + ", createBy=" + this.createBy + 
/* 174 */       ", createDate=" + this.createDate + ", updateBy=" + this.updateBy + 
/* 175 */       ", updateDate=" + this.updateDate + ", devTranCode=" + this.devTranCode + 
/* 176 */       ", devTaskStatusDis=" + this.devTaskStatusDis + 
/* 177 */       ", strUpdateDateTime=" + this.strUpdateDateTime + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\sdkservice\PlayTaskBasicsLog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */