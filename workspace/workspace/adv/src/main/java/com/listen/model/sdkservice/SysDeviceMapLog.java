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
/*    */ public class SysDeviceMapLog
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private Integer devMapId;
/*    */   private String devCode;
/*    */   private String imageName;
/*    */   private String imageUrl;
/*    */   private String createBy;
/*    */   private Long createDate;
/*    */   private String updateBy;
/*    */   private Long updateDate;
/*    */   
/* 26 */   public Integer getDevMapId() { return this.devMapId; }
/*    */ 
/*    */   
/* 29 */   public void setDevMapId(Integer devMapId) { this.devMapId = devMapId; }
/*    */ 
/*    */   
/* 32 */   public String getDevCode() { return this.devCode; }
/*    */ 
/*    */   
/* 35 */   public void setDevCode(String devCode) { this.devCode = devCode; }
/*    */ 
/*    */   
/* 38 */   public String getImageName() { return this.imageName; }
/*    */ 
/*    */   
/* 41 */   public void setImageName(String imageName) { this.imageName = imageName; }
/*    */ 
/*    */   
/* 44 */   public String getImageUrl() { return this.imageUrl; }
/*    */ 
/*    */   
/* 47 */   public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
/*    */ 
/*    */   
/* 50 */   public String getCreateBy() { return this.createBy; }
/*    */ 
/*    */   
/* 53 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*    */ 
/*    */   
/* 56 */   public Long getCreateDate() { return this.createDate; }
/*    */ 
/*    */   
/* 59 */   public void setCreateDate(Long createDate) { this.createDate = createDate; }
/*    */ 
/*    */   
/* 62 */   public String getUpdateBy() { return this.updateBy; }
/*    */ 
/*    */   
/* 65 */   public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
/*    */ 
/*    */   
/* 68 */   public Long getUpdateDate() { return this.updateDate; }
/*    */ 
/*    */   
/* 71 */   public void setUpdateDate(Long updateDate) { this.updateDate = updateDate; }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     return "SysDeviceMap [devMapId=" + this.devMapId + ", devCode=" + this.devCode + 
/* 76 */       ", imageName=" + this.imageName + ", imageUrl=" + this.imageUrl + 
/* 77 */       ", createBy=" + this.createBy + ", createDate=" + this.createDate + 
/* 78 */       ", updateBy=" + this.updateBy + ", updateDate=" + this.updateDate + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\sdkservice\SysDeviceMapLog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */