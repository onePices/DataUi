/*    */ package com.listen.model.query;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TmlDeviceBasicVo
/*    */   extends TmlDeviceBasic
/*    */   implements Serializable {
/*    */   private static final long serialVersionUID = 1220881027871224796L;
/*    */   private int devBrightness;
/*    */   private int devVolice;
/*    */   private String devHumidity;
/*    */   private String enHumidity;
/*    */   private String devTemperature;
/*    */   private String enTemperature;
/*    */   private String devNetStatus;
/*    */   private String devConnectstatus;
/*    */   private String devCurrentVersion;
/*    */   private String devRunStatus;
/*    */   
/* 20 */   public int getDevBrightness() { return this.devBrightness; }
/*    */ 
/*    */   
/* 23 */   public void setDevBrightness(int devBrightness) { this.devBrightness = devBrightness; }
/*    */ 
/*    */   
/* 26 */   public int getDevVolice() { return this.devVolice; }
/*    */ 
/*    */   
/* 29 */   public void setDevVolice(int devVolice) { this.devVolice = devVolice; }
/*    */ 
/*    */   
/* 32 */   public String getDevHumidity() { return this.devHumidity; }
/*    */ 
/*    */   
/* 35 */   public void setDevHumidity(String devHumidity) { this.devHumidity = devHumidity; }
/*    */ 
/*    */   
/* 38 */   public String getEnHumidity() { return this.enHumidity; }
/*    */ 
/*    */   
/* 41 */   public void setEnHumidity(String enHumidity) { this.enHumidity = enHumidity; }
/*    */ 
/*    */   
/* 44 */   public String getDevTemperature() { return this.devTemperature; }
/*    */ 
/*    */   
/* 47 */   public void setDevTemperature(String devTemperature) { this.devTemperature = devTemperature; }
/*    */ 
/*    */   
/* 50 */   public String getEnTemperature() { return this.enTemperature; }
/*    */ 
/*    */   
/* 53 */   public void setEnTemperature(String enTemperature) { this.enTemperature = enTemperature; }
/*    */ 
/*    */   
/* 56 */   public String getDevNetStatus() { return this.devNetStatus; }
/*    */ 
/*    */   
/* 59 */   public void setDevNetStatus(String devNetStatus) { this.devNetStatus = devNetStatus; }
/*    */ 
/*    */   
/* 62 */   public String getDevConnectstatus() { return this.devConnectstatus; }
/*    */ 
/*    */   
/* 65 */   public void setDevConnectstatus(String devConnectstatus) { this.devConnectstatus = devConnectstatus; }
/*    */ 
/*    */   
/* 68 */   public String getDevCurrentVersion() { return this.devCurrentVersion; }
/*    */ 
/*    */   
/* 71 */   public void setDevCurrentVersion(String devCurrentVersion) { this.devCurrentVersion = devCurrentVersion; }
/*    */ 
/*    */   
/* 74 */   public String getDevRunStatus() { return this.devRunStatus; }
/*    */ 
/*    */   
/* 77 */   public void setDevRunStatus(String devRunStatus) { this.devRunStatus = devRunStatus; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\query\TmlDeviceBasicVo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */