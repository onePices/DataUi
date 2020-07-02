/*    */ package com.listen.model.sdkservice;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SysTaskExpireLog
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private Integer expireLogId;
/*    */   private String devCode;
/*    */   private Integer taskId;
/*    */   private Integer taskType;
/*    */   private String taskName;
/*    */   private String taskContent;
/*    */   private Long createDate;
/*    */   private Long expireDate;
/*    */   private String remark;
/*    */   
/* 27 */   public Integer getExpireLogId() { return this.expireLogId; }
/*    */ 
/*    */   
/* 30 */   public void setExpireLogId(Integer expireLogId) { this.expireLogId = expireLogId; }
/*    */ 
/*    */   
/* 33 */   public String getDevCode() { return this.devCode; }
/*    */ 
/*    */   
/* 36 */   public void setDevCode(String devCode) { this.devCode = devCode; }
/*    */ 
/*    */   
/* 39 */   public Integer getTaskType() { return this.taskType; }
/*    */ 
/*    */   
/* 42 */   public void setTaskType(Integer taskType) { this.taskType = taskType; }
/*    */ 
/*    */   
/* 45 */   public String isTaskName() { return this.taskName; }
/*    */ 
/*    */   
/* 48 */   public void setTaskName(String taskName) { this.taskName = taskName; }
/*    */ 
/*    */   
/* 51 */   public String getTaskContent() { return this.taskContent; }
/*    */ 
/*    */   
/* 54 */   public void setTaskContent(String taskContent) { this.taskContent = taskContent; }
/*    */ 
/*    */   
/* 57 */   public Long getCreateDate() { return this.createDate; }
/*    */ 
/*    */   
/* 60 */   public void setCreateDate(Long createDate) { this.createDate = createDate; }
/*    */ 
/*    */   
/* 63 */   public Long getExpireDate() { return this.expireDate; }
/*    */ 
/*    */   
/* 66 */   public void setExpireDate(Long expireDate) { this.expireDate = expireDate; }
/*    */ 
/*    */   
/* 69 */   public Integer getTaskId() { return this.taskId; }
/*    */ 
/*    */   
/* 72 */   public void setTaskId(Integer taskId) { this.taskId = taskId; }
/*    */ 
/*    */   
/* 75 */   public String getRemark() { return this.remark; }
/*    */ 
/*    */   
/* 78 */   public void setRemark(String remark) { this.remark = remark; }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     return "SysTaskExpireLog [expireLogId=" + this.expireLogId + ", devCode=" + 
/* 83 */       this.devCode + ", taskId=" + this.taskId + ", taskType=" + this.taskType + 
/* 84 */       ", taskName=" + this.taskName + ", taskContent=" + this.taskContent + 
/* 85 */       ", createDate=" + this.createDate + ", expireDate=" + this.expireDate + 
/* 86 */       ", remark=" + this.remark + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\sdkservice\SysTaskExpireLog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */