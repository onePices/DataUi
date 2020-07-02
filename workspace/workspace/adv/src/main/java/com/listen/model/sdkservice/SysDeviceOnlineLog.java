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
/*    */ public class SysDeviceOnlineLog
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private Integer devLogId;
/*    */   private String devCode;
/*    */   private String devMac;
/*    */   private long lastOnlineDate;
/*    */   private String remark;
/*    */   
/* 23 */   public Integer getDevLogId() { return this.devLogId; }
/*    */ 
/*    */   
/* 26 */   public void setDevLogId(Integer devLogId) { this.devLogId = devLogId; }
/*    */ 
/*    */   
/* 29 */   public String getDevCode() { return this.devCode; }
/*    */ 
/*    */   
/* 32 */   public void setDevCode(String devCode) { this.devCode = devCode; }
/*    */ 
/*    */   
/* 35 */   public String getDevMac() { return this.devMac; }
/*    */ 
/*    */   
/* 38 */   public void setDevMac(String devMac) { this.devMac = devMac; }
/*    */ 
/*    */   
/* 41 */   public long getLastOnlineDate() { return this.lastOnlineDate; }
/*    */ 
/*    */   
/* 44 */   public void setLastOnlineDate(long lastOnlineDate) { this.lastOnlineDate = lastOnlineDate; }
/*    */ 
/*    */   
/* 47 */   public String getRemark() { return this.remark; }
/*    */ 
/*    */   
/* 50 */   public void setRemark(String remark) { this.remark = remark; }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     return "SysDeviceOnlineLog [devLogId=" + this.devLogId + ", devCode=" + 
/* 55 */       this.devCode + ", devMac=" + this.devMac + ", lastOnlineDate=" + 
/* 56 */       this.lastOnlineDate + ", remark=" + this.remark + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\sdkservice\SysDeviceOnlineLog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */