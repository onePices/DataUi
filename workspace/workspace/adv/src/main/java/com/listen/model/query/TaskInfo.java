/*    */ package com.listen.model.query;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaskInfo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer taskId;
/*    */   private Integer taskType;
/*    */   private String taskContent;
/*    */   
/*    */   public TaskInfo() {}
/*    */   
/*    */   public TaskInfo(Integer taskId, Integer taskType, String taskContent) {
/* 25 */     this.taskId = taskId;
/* 26 */     this.taskType = taskType;
/* 27 */     this.taskContent = taskContent;
/*    */   }
/*    */ 
/*    */   
/* 31 */   public Integer getTaskId() { return this.taskId; }
/*    */ 
/*    */   
/* 34 */   public void setTaskId(Integer taskId) { this.taskId = taskId; }
/*    */ 
/*    */   
/* 37 */   public Integer getTaskType() { return this.taskType; }
/*    */ 
/*    */   
/* 40 */   public void setTaskType(Integer taskType) { this.taskType = taskType; }
/*    */ 
/*    */   
/* 43 */   public String getTaskContent() { return this.taskContent; }
/*    */ 
/*    */   
/* 46 */   public void setTaskContent(String taskContent) { this.taskContent = taskContent; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\query\TaskInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */