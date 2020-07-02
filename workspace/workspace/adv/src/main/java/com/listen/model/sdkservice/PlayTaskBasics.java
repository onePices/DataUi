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
/*     */ public class PlayTaskBasics
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*     */   private Integer taskId;
/*     */   private String devCode;
/*     */   private String devType;
/*     */   private Integer groupId;
/*     */   private Integer taskType;
/*     */   private Integer taskStatus;
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
/*     */   
/*  36 */   public Integer getTaskId() { return this.taskId; }
/*     */ 
/*     */   
/*  39 */   public void setTaskId(Integer taskId) { this.taskId = taskId; }
/*     */ 
/*     */   
/*  42 */   public String getDevCode() { return this.devCode; }
/*     */ 
/*     */   
/*  45 */   public void setDevCode(String devCode) { this.devCode = devCode; }
/*     */ 
/*     */   
/*  48 */   public String getDevType() { return this.devType; }
/*     */ 
/*     */   
/*  51 */   public void setDevType(String devType) { this.devType = devType; }
/*     */ 
/*     */   
/*  54 */   public Integer getGroupId() { return this.groupId; }
/*     */ 
/*     */   
/*  57 */   public void setGroupId(Integer groupId) { this.groupId = groupId; }
/*     */ 
/*     */   
/*  60 */   public Integer getTaskType() { return this.taskType; }
/*     */ 
/*     */   
/*  63 */   public void setTaskType(Integer taskType) { this.taskType = taskType; }
/*     */ 
/*     */   
/*  66 */   public String getTaskPercent() { return this.taskPercent; }
/*     */ 
/*     */   
/*  69 */   public void setTaskPercent(String taskPercent) { this.taskPercent = taskPercent; }
/*     */ 
/*     */   
/*  72 */   public String getTaskDownloadRate() { return this.taskDownloadRate; }
/*     */ 
/*     */   
/*  75 */   public void setTaskDownloadRate(String taskDownloadRate) { this.taskDownloadRate = taskDownloadRate; }
/*     */ 
/*     */   
/*  78 */   public String getTaskContent() { return this.taskContent; }
/*     */ 
/*     */   
/*  81 */   public void setTaskContent(String taskContent) { this.taskContent = taskContent; }
/*     */ 
/*     */   
/*  84 */   public Long getTaskExpireDate() { return this.taskExpireDate; }
/*     */ 
/*     */   
/*  87 */   public void setTaskExpireDate(Long taskExpireDate) { this.taskExpireDate = taskExpireDate; }
/*     */ 
/*     */   
/*  90 */   public String getTaskRemarks() { return this.taskRemarks; }
/*     */ 
/*     */   
/*  93 */   public void setTaskRemarks(String taskRemarks) { this.taskRemarks = taskRemarks; }
/*     */ 
/*     */   
/*  96 */   public String getCustCode() { return this.custCode; }
/*     */ 
/*     */   
/*  99 */   public void setCustCode(String custCode) { this.custCode = custCode; }
/*     */ 
/*     */   
/* 102 */   public String getCreateBy() { return this.createBy; }
/*     */ 
/*     */   
/* 105 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*     */ 
/*     */   
/* 108 */   public Long getCreateDate() { return this.createDate; }
/*     */ 
/*     */   
/* 111 */   public void setCreateDate(Long createDate) { this.createDate = createDate; }
/*     */ 
/*     */   
/* 114 */   public String getUpdateBy() { return this.updateBy; }
/*     */ 
/*     */   
/* 117 */   public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
/*     */ 
/*     */   
/* 120 */   public Long getUpdateDate() { return this.updateDate; }
/*     */ 
/*     */   
/* 123 */   public void setUpdateDate(Long updateDate) { this.updateDate = updateDate; }
/*     */ 
/*     */   
/* 126 */   public Integer getTaskStatus() { return this.taskStatus; }
/*     */ 
/*     */   
/* 129 */   public void setTaskStatus(Integer taskStatus) { this.taskStatus = taskStatus; }
/*     */ 
/*     */   
/* 132 */   public Integer getTaskTimes() { return this.taskTimes; }
/*     */ 
/*     */   
/* 135 */   public void setTaskTimes(Integer taskTimes) { this.taskTimes = taskTimes; }
/*     */ 
/*     */   
/* 138 */   public String getCustNo() { return this.custNo; }
/*     */ 
/*     */   
/* 141 */   public void setCustNo(String custNo) { this.custNo = custNo; }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 145 */     return "PlayTaskBasics [taskId=" + this.taskId + ", devCode=" + this.devCode + 
/* 146 */       ", devType=" + this.devType + ", groupId=" + this.groupId + 
/* 147 */       ", taskType=" + this.taskType + ", taskStatus=" + this.taskStatus + 
/* 148 */       ", taskTimes=" + this.taskTimes + ", taskPercent=" + this.taskPercent + 
/* 149 */       ", taskDownloadRate=" + this.taskDownloadRate + ", taskContent=" + 
/* 150 */       this.taskContent + ", taskExpireDate=" + this.taskExpireDate + 
/* 151 */       ", taskRemarks=" + this.taskRemarks + ", custNo=" + this.custNo + 
/* 152 */       ", custCode=" + this.custCode + ", createBy=" + this.createBy + 
/* 153 */       ", createDate=" + this.createDate + ", updateBy=" + this.updateBy + 
/* 154 */       ", updateDate=" + this.updateDate + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\sdkservice\PlayTaskBasics.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */