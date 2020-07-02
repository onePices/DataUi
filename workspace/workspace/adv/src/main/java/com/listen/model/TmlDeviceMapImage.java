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
/*    */ 
/*    */ 
/*    */ public class TmlDeviceMapImage
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String devCode;
/*    */   private String imageName;
/*    */   private String imageUrl;
/*    */   private long createDate;
/*    */   private String createBy;
/*    */   private long updateDate;
/*    */   private String updateBy;
/*    */   
/* 32 */   public String getDevCode() { return this.devCode; }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public void setDevCode(String devCode) { this.devCode = devCode; }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public String getImageName() { return this.imageName; }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public void setImageName(String imageName) { this.imageName = imageName; }
/*    */ 
/*    */ 
/*    */   
/* 48 */   public String getImageUrl() { return this.imageUrl; }
/*    */ 
/*    */ 
/*    */   
/* 52 */   public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
/*    */ 
/*    */ 
/*    */   
/* 56 */   public long getCreateDate() { return this.createDate; }
/*    */ 
/*    */ 
/*    */   
/* 60 */   public void setCreateDate(long createDate) { this.createDate = createDate; }
/*    */ 
/*    */ 
/*    */   
/* 64 */   public String getCreateBy() { return this.createBy; }
/*    */ 
/*    */ 
/*    */   
/* 68 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*    */ 
/*    */ 
/*    */   
/* 72 */   public long getUpdateDate() { return this.updateDate; }
/*    */ 
/*    */ 
/*    */   
/* 76 */   public void setUpdateDate(long updateDate) { this.updateDate = updateDate; }
/*    */ 
/*    */ 
/*    */   
/* 80 */   public String getUpdateBy() { return this.updateBy; }
/*    */ 
/*    */ 
/*    */   
/* 84 */   public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 89 */     return "TmlDeviceMapImage [devCode=" + this.devCode + ", imageName=" + this.imageName + ", imageUrl=" + 
/* 90 */       this.imageUrl + ", createDate=" + this.createDate + ", createBy=" + this.createBy + ", updateDate=" + this.updateDate + 
/* 91 */       ", updateBy=" + this.updateBy + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\TmlDeviceMapImage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */