/*     */ package com.listen.model.task;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayTaskBasic
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int taskId;
/*     */   private String devCode;
/*     */   private String devType;
/*     */   private int groupId;
/*     */   private int taskType;
/*     */   private byte taskStatus;
/*     */   private int taskTimes;
/*     */   private String taskPercent;
/*     */   
/*  23 */   public int getTaskId() { return this.taskId; }
/*     */   private String taskDownloadRate; private String taskContent; private long taskExpireDate; private String custNo; private String taskRemarks; private String createBy; private Long createDate; private String updateBy; private Long updateDate;
/*     */   
/*  26 */   public void setTaskId(int taskId) { this.taskId = taskId; }
/*     */ 
/*     */   
/*  29 */   public String getDevCode() { return this.devCode; }
/*     */ 
/*     */   
/*  32 */   public void setDevCode(String devCode) { this.devCode = devCode; }
/*     */ 
/*     */   
/*  35 */   public String getDevType() { return this.devType; }
/*     */ 
/*     */   
/*  38 */   public void setDevType(String devType) { this.devType = devType; }
/*     */ 
/*     */   
/*  41 */   public int getGroupId() { return this.groupId; }
/*     */ 
/*     */   
/*  44 */   public void setGroupId(int groupId) { this.groupId = groupId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public int getTaskType() { return this.taskType; }
/*     */ 
/*     */   
/*  56 */   public void setTaskType(int taskType) { this.taskType = taskType; }
/*     */ 
/*     */   
/*  59 */   public byte getTaskStatus() { return this.taskStatus; }
/*     */ 
/*     */   
/*  62 */   public void setTaskStatus(byte taskStatus) { this.taskStatus = taskStatus; }
/*     */ 
/*     */   
/*  65 */   public int getTaskTimes() { return this.taskTimes; }
/*     */ 
/*     */   
/*  68 */   public void setTaskTimes(int taskTimes) { this.taskTimes = taskTimes; }
/*     */ 
/*     */   
/*  71 */   public String getTaskContent() { return this.taskContent; }
/*     */ 
/*     */   
/*  74 */   public void setTaskContent(String taskContent) { this.taskContent = taskContent; }
/*     */ 
/*     */   
/*  77 */   public long getTaskExpireDate() { return this.taskExpireDate; }
/*     */ 
/*     */   
/*  80 */   public void setTaskExpireDate(long taskExpireDate) { this.taskExpireDate = taskExpireDate; }
/*     */ 
/*     */   
/*  83 */   public String getCustNo() { return this.custNo; }
/*     */ 
/*     */   
/*  86 */   public void setCustNo(String custNo) { this.custNo = custNo; }
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
/* 128 */   public String getTaskDownloadRate() { return this.taskDownloadRate; }
/*     */ 
/*     */   
/* 131 */   public void setTaskDownloadRate(String taskDownloadRate) { this.taskDownloadRate = taskDownloadRate; }
/*     */ 
/*     */   
/* 134 */   public String getTaskPercent() { return this.taskPercent; }
/*     */ 
/*     */   
/* 137 */   public void setTaskPercent(String taskPercent) { this.taskPercent = taskPercent; }
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
/* 154 */   public String getTaskRemarks() { return this.taskRemarks; }
/*     */ 
/*     */   
/* 157 */   public void setTaskRemarks(String taskRemarks) { this.taskRemarks = taskRemarks; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public String getCreateBy() { return this.createBy; }
/*     */ 
/*     */   
/* 167 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*     */ 
/*     */   
/* 170 */   public Long getCreateDate() { return this.createDate; }
/*     */ 
/*     */   
/* 173 */   public void setCreateDate(Long createDate) { this.createDate = createDate; }
/*     */ 
/*     */   
/* 176 */   public String getUpdateBy() { return this.updateBy; }
/*     */ 
/*     */   
/* 179 */   public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
/*     */ 
/*     */   
/* 182 */   public Long getUpdateDate() { return this.updateDate; }
/*     */ 
/*     */   
/* 185 */   public void setUpdateDate(Long updateDate) { this.updateDate = updateDate; }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 189 */     return "PlayTaskBasic [taskId=" + this.taskId + ", devCode=" + this.devCode + ", devType=" + this.devType + ", groupId=" + 
/* 190 */       this.groupId + ", taskType=" + this.taskType + ", taskStatus=" + this.taskStatus + ", taskTimes=" + this.taskTimes + 
/* 191 */       ", taskPercent=" + this.taskPercent + ", taskDownloadRate=" + this.taskDownloadRate + ", taskContent=" + 
/* 192 */       this.taskContent + ", taskExpireDate=" + this.taskExpireDate + ", custNo=" + this.custNo + ", taskRemarks=" + 
/* 193 */       this.taskRemarks + ", createBy=" + this.createBy + ", createDate=" + this.createDate + ", updateBy=" + this.updateBy + 
/* 194 */       ", updateDate=" + this.updateDate + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\task\PlayTaskBasic.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */