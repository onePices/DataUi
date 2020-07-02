/*    */ package com.listen.model.query;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DeviceQuery
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String devCode;
/*    */   private String devMac;
/*    */   private String lastOnlineTime;
/*    */   
/*    */   public DeviceQuery() {}
/*    */   
/*    */   public DeviceQuery(String devCode, String devMac) {
/* 20 */     this.devCode = devCode;
/* 21 */     this.devMac = devMac;
/*    */   }
/*    */ 
/*    */   
/*    */   public DeviceQuery(String devCode, String devMac, String lastOnlineTime) {
/* 26 */     this.devCode = devCode;
/* 27 */     this.devMac = devMac;
/* 28 */     this.lastOnlineTime = lastOnlineTime;
/*    */   }
/*    */   
/* 31 */   public String getDevCode() { return this.devCode; }
/*    */ 
/*    */   
/* 34 */   public void setDevCode(String devCode) { this.devCode = devCode; }
/*    */ 
/*    */   
/* 37 */   public String getDevMac() { return this.devMac; }
/*    */ 
/*    */   
/* 40 */   public void setDevMac(String devMac) { this.devMac = devMac; }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public String getLastOnlineTime() { return this.lastOnlineTime; }
/*    */ 
/*    */ 
/*    */   
/* 48 */   public void setLastOnlineTime(String lastOnlineTime) { this.lastOnlineTime = lastOnlineTime; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\query\DeviceQuery.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */