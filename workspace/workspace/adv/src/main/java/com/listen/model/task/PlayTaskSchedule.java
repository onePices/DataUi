/*     */ package com.listen.model.task;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
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
/*     */ public class PlayTaskSchedule
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1889781091196030252L;
/*     */   private Integer scheduleId;
/*     */   private String scheduleName;
/*     */   private Long planStarttime;
/*     */   private Long planEndtime;
/*     */   private Integer scheduleType;
/*     */   private String createBy;
/*     */   private Long createDate;
/*     */   private String updateBy;
/*     */   private Long updateDate;
/*     */   private List<PlayTaskProgram> program;
/*     */   
/*  43 */   public Integer getScheduleId() { return this.scheduleId; }
/*     */ 
/*     */ 
/*     */   
/*  47 */   public void setScheduleId(Integer scheduleId) { this.scheduleId = scheduleId; }
/*     */ 
/*     */ 
/*     */   
/*  51 */   public String getScheduleName() { return this.scheduleName; }
/*     */ 
/*     */ 
/*     */   
/*  55 */   public void setScheduleName(String scheduleName) { this.scheduleName = scheduleName; }
/*     */ 
/*     */ 
/*     */   
/*  59 */   public Long getPlanStarttime() { return this.planStarttime; }
/*     */ 
/*     */ 
/*     */   
/*  63 */   public void setPlanStarttime(Long planStarttime) { this.planStarttime = planStarttime; }
/*     */ 
/*     */ 
/*     */   
/*  67 */   public Long getPlanEndtime() { return this.planEndtime; }
/*     */ 
/*     */ 
/*     */   
/*  71 */   public void setPlanEndtime(Long planEndtime) { this.planEndtime = planEndtime; }
/*     */ 
/*     */ 
/*     */   
/*  75 */   public List<PlayTaskProgram> getProgram() { return this.program; }
/*     */ 
/*     */ 
/*     */   
/*  79 */   public void setProgram(List<PlayTaskProgram> program) { this.program = program; }
/*     */ 
/*     */ 
/*     */   
/*  83 */   public Integer getScheduleType() { return this.scheduleType; }
/*     */ 
/*     */ 
/*     */   
/*  87 */   public void setScheduleType(Integer scheduleType) { this.scheduleType = scheduleType; }
/*     */ 
/*     */ 
/*     */   
/*  91 */   public String getCreateBy() { return this.createBy; }
/*     */ 
/*     */ 
/*     */   
/*  95 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public Long getCreateDate() { return this.createDate; }
/*     */ 
/*     */ 
/*     */   
/* 103 */   public void setCreateDate(Long createDate) { this.createDate = createDate; }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public String getUpdateBy() { return this.updateBy; }
/*     */ 
/*     */ 
/*     */   
/* 111 */   public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
/*     */ 
/*     */ 
/*     */   
/* 115 */   public Long getUpdateDate() { return this.updateDate; }
/*     */ 
/*     */ 
/*     */   
/* 119 */   public void setUpdateDate(Long updateDate) { this.updateDate = updateDate; }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\task\PlayTaskSchedule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */