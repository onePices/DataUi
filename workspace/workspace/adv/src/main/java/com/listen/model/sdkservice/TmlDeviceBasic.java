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
/*    */ public class TmlDeviceBasic
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private Integer devId;
/*    */   private String devCode;
/*    */   private String devMac;
/*    */   private Long lastOnlineDate;
/*    */   private Long createDate;
/*    */   private String devType;
/*    */   
/* 24 */   public Integer getDevId() { return this.devId; }
/*    */ 
/*    */   
/* 27 */   public void setDevId(Integer devId) { this.devId = devId; }
/*    */ 
/*    */   
/* 30 */   public String getDevCode() { return this.devCode; }
/*    */ 
/*    */   
/* 33 */   public void setDevCode(String devCode) { this.devCode = devCode; }
/*    */ 
/*    */   
/* 36 */   public String getDevMac() { return this.devMac; }
/*    */ 
/*    */   
/* 39 */   public void setDevMac(String devMac) { this.devMac = devMac; }
/*    */ 
/*    */   
/* 42 */   public Long getLastOnlineDate() { return this.lastOnlineDate; }
/*    */ 
/*    */   
/* 45 */   public void setLastOnlineDate(Long lastOnlineDate) { this.lastOnlineDate = lastOnlineDate; }
/*    */ 
/*    */   
/* 48 */   public Long getCreateDate() { return this.createDate; }
/*    */ 
/*    */   
/* 51 */   public void setCreateDate(Long createDate) { this.createDate = createDate; }
/*    */ 
/*    */   
/* 54 */   public String getDevType() { return this.devType; }
/*    */ 
/*    */   
/* 57 */   public void setDevType(String devType) { this.devType = devType; }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     return "TmlDeviceBasic [devId=" + this.devId + ", devCode=" + this.devCode + 
/* 62 */       ", devMac=" + this.devMac + ", lastOnlineDate=" + this.lastOnlineDate + 
/* 63 */       ", createDate=" + this.createDate + ", devType=" + this.devType + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\sdkservice\TmlDeviceBasic.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */