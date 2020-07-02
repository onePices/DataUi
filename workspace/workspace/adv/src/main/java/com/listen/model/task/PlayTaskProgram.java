/*     */ package com.listen.model.task;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class PlayTaskProgram
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2791103380784807941L;
/*     */   private Integer programId;
/*     */   private String programName;
/*     */   private Integer programSort;
/*     */   private String programStarttime;
/*     */   private String programEndtime;
/*     */   private String planDay;
/*     */   private String programUrl;
/*     */   private String createBy;
/*     */   private Long createDate;
/*     */   private String updateBy;
/*     */   private Long updateDate;
/*     */   
/*     */   public PlayTaskProgram() {}
/*     */   
/*     */   public PlayTaskProgram(Integer programId, String programName, Integer programSort, String programStarttime, String programEndtime, String programUrl) {
/*  49 */     this.programId = programId;
/*  50 */     this.programName = programName;
/*  51 */     this.programSort = programSort;
/*  52 */     this.programStarttime = programStarttime;
/*  53 */     this.programEndtime = programEndtime;
/*  54 */     this.programUrl = programUrl;
/*     */   }
/*     */ 
/*     */   
/*  58 */   public Integer getProgramId() { return this.programId; }
/*     */ 
/*     */ 
/*     */   
/*  62 */   public void setProgramId(Integer programId) { this.programId = programId; }
/*     */ 
/*     */ 
/*     */   
/*  66 */   public String getProgramName() { return this.programName; }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public void setProgramName(String programName) { this.programName = programName; }
/*     */ 
/*     */ 
/*     */   
/*  74 */   public Integer getProgramSort() { return this.programSort; }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void setProgramSort(Integer programSort) { this.programSort = programSort; }
/*     */ 
/*     */ 
/*     */   
/*  82 */   public String getProgramStarttime() { return this.programStarttime; }
/*     */ 
/*     */ 
/*     */   
/*  86 */   public void setProgramStarttime(String programStarttime) { this.programStarttime = programStarttime; }
/*     */ 
/*     */ 
/*     */   
/*  90 */   public String getProgramEndtime() { return this.programEndtime; }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public void setProgramEndtime(String programEndtime) { this.programEndtime = programEndtime; }
/*     */ 
/*     */ 
/*     */   
/*  98 */   public String getProgramUrl() { return this.programUrl; }
/*     */ 
/*     */ 
/*     */   
/* 102 */   public void setProgramUrl(String programUrl) { this.programUrl = programUrl; }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public String getPlanDay() { return this.planDay; }
/*     */ 
/*     */ 
/*     */   
/* 110 */   public void setPlanDay(String planDay) { this.planDay = planDay; }
/*     */ 
/*     */ 
/*     */   
/* 114 */   public String getCreateBy() { return this.createBy; }
/*     */ 
/*     */ 
/*     */   
/* 118 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*     */ 
/*     */ 
/*     */   
/* 122 */   public Long getCreateDate() { return this.createDate; }
/*     */ 
/*     */ 
/*     */   
/* 126 */   public void setCreateDate(Long createDate) { this.createDate = createDate; }
/*     */ 
/*     */ 
/*     */   
/* 130 */   public String getUpdateBy() { return this.updateBy; }
/*     */ 
/*     */ 
/*     */   
/* 134 */   public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
/*     */ 
/*     */ 
/*     */   
/* 138 */   public Long getUpdateDate() { return this.updateDate; }
/*     */ 
/*     */ 
/*     */   
/* 142 */   public void setUpdateDate(Long updateDate) { this.updateDate = updateDate; }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\task\PlayTaskProgram.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */