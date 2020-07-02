/*    */ package com.listen.model.task;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayModel
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4138862711443271670L;
/*    */   private Integer taskId;
/*    */   private Long size;
/*    */   
/* 26 */   public Integer getTaskId() { return this.taskId; }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public void setTaskId(Integer taskId) { this.taskId = taskId; }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public Long getSize() { return this.size; }
/*    */ 
/*    */ 
/*    */   
/* 38 */   public void setSize(Long size) { this.size = size; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\task\PlayModel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */