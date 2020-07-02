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
/*    */ public class SysDeviceMap
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private String devCodeName;
/*    */   private String imageUrl;
/*    */   
/* 20 */   public String getDevCodeName() { return this.devCodeName; }
/*    */ 
/*    */   
/* 23 */   public void setDevCodeName(String devCodeName) { this.devCodeName = devCodeName; }
/*    */ 
/*    */   
/* 26 */   public String getImageUrl() { return this.imageUrl; }
/*    */ 
/*    */   
/* 29 */   public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     return "SysDeviceMap [devCodeName=" + this.devCodeName + ", imageUrl=" + 
/* 34 */       this.imageUrl + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\sdkservice\SysDeviceMap.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */