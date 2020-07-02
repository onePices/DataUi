/*    */ package com.listen.model.task;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayTaskChannel
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4670194266255165280L;
/*    */   private Integer channelId;
/*    */   private String channelName;
/*    */   private String createBy;
/*    */   private Long createDate;
/*    */   private String updateBy;
/*    */   private Long updateDate;
/*    */   private List<PlayTaskSchedule> schedule;
/*    */   
/* 37 */   public Integer getChannelId() { return this.channelId; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setChannelId(Integer channelId) { this.channelId = channelId; }
/*    */ 
/*    */ 
/*    */   
/* 45 */   public String getChannelName() { return this.channelName; }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public void setChannelName(String channelName) { this.channelName = channelName; }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public List<PlayTaskSchedule> getSchedule() { return this.schedule; }
/*    */ 
/*    */ 
/*    */   
/* 57 */   public void setSchedule(List<PlayTaskSchedule> schedule) { this.schedule = schedule; }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public String getCreateBy() { return this.createBy; }
/*    */ 
/*    */ 
/*    */   
/* 65 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*    */ 
/*    */ 
/*    */   
/* 69 */   public Long getCreateDate() { return this.createDate; }
/*    */ 
/*    */ 
/*    */   
/* 73 */   public void setCreateDate(Long createDate) { this.createDate = createDate; }
/*    */ 
/*    */ 
/*    */   
/* 77 */   public String getUpdateBy() { return this.updateBy; }
/*    */ 
/*    */ 
/*    */   
/* 81 */   public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
/*    */ 
/*    */ 
/*    */   
/* 85 */   public Long getUpdateDate() { return this.updateDate; }
/*    */ 
/*    */ 
/*    */   
/* 89 */   public void setUpdateDate(Long updateDate) { this.updateDate = updateDate; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\task\PlayTaskChannel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */