/*    */ package com.listen.model;
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
/*    */ public class SystemConfig
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long configId;
/*    */   private String configCode;
/*    */   private Long waitTime;
/*    */   private Integer pageSize;
/*    */   private Integer threadCount;
/*    */   private String threadContent;
/*    */   
/* 29 */   public Long getConfigId() { return this.configId; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public void setConfigId(Long configId) { this.configId = configId; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public String getConfigCode() { return this.configCode; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setConfigCode(String configCode) { this.configCode = configCode; }
/*    */ 
/*    */ 
/*    */   
/* 45 */   public Long getWaitTime() { return this.waitTime; }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public void setWaitTime(Long waitTime) { this.waitTime = waitTime; }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public Integer getPageSize() { return this.pageSize; }
/*    */ 
/*    */ 
/*    */   
/* 57 */   public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public Integer getThreadCount() { return this.threadCount; }
/*    */ 
/*    */ 
/*    */   
/* 65 */   public void setThreadCount(Integer threadCount) { this.threadCount = threadCount; }
/*    */ 
/*    */ 
/*    */   
/* 69 */   public String getThreadContent() { return this.threadContent; }
/*    */ 
/*    */ 
/*    */   
/* 73 */   public void setThreadContent(String threadContent) { this.threadContent = threadContent; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\SystemConfig.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */