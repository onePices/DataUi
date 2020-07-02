/*     */ package com.listen.model.query;
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
/*     */ public class TmlDeviceBasic
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int devId;
/*     */   private String devName;
/*     */   private int devWidth;
/*     */   private int devHeight;
/*     */   private String devCode;
/*     */   private String devIp;
/*     */   private int devPort;
/*     */   private String devMac;
/*     */   private byte devInstallType;
/*     */   private byte devControlType;
/*     */   private byte devKernelType;
/*     */   private String devKemelVersion;
/*     */   private String devType;
/*     */   private String devCurrentVersion;
/*     */   private byte devVersionValue;
/*     */   private String devControlName;
/*     */   private byte devAuditStatus;
/*     */   private byte devState;
/*     */   private String devLongitudeValue;
/*     */   private String devLatitudeValue;
/*     */   private int plyStrategyId;
/*     */   private String devAddress;
/*     */   private String custCode;
/*     */   private byte devSoftwareUpdate;
/*     */   private byte screenType;
/*     */   private String cpu;
/*     */   private String hdd;
/*     */   private String memory;
/*     */   private String operatingLoad;
/*     */   private String licenseCode;
/*     */   private String tranCode;
/*     */   private long lastOnlineDate;
/*     */   private String remarks;
/*     */   private String custNo;
/*     */   private Integer vendorId;
/*     */   private String devEquipmentType;
/*     */   private String createBy;
/*     */   private long createDate;
/*     */   private String updateBy;
/*     */   private long updateDate;
/*     */   
/*  57 */   public int getDevId() { return this.devId; }
/*     */ 
/*     */   
/*  60 */   public void setDevId(int devId) { this.devId = devId; }
/*     */ 
/*     */   
/*  63 */   public String getDevName() { return this.devName; }
/*     */ 
/*     */   
/*  66 */   public void setDevName(String devName) { this.devName = devName; }
/*     */ 
/*     */   
/*  69 */   public int getDevWidth() { return this.devWidth; }
/*     */ 
/*     */   
/*  72 */   public void setDevWidth(int devWidth) { this.devWidth = devWidth; }
/*     */ 
/*     */   
/*  75 */   public int getDevHeight() { return this.devHeight; }
/*     */ 
/*     */   
/*  78 */   public void setDevHeight(int devHeight) { this.devHeight = devHeight; }
/*     */ 
/*     */   
/*  81 */   public String getDevCode() { return this.devCode; }
/*     */ 
/*     */   
/*  84 */   public void setDevCode(String devCode) { this.devCode = devCode; }
/*     */ 
/*     */   
/*  87 */   public String getDevIp() { return this.devIp; }
/*     */ 
/*     */   
/*  90 */   public void setDevIp(String devIp) { this.devIp = devIp; }
/*     */ 
/*     */   
/*  93 */   public int getDevPort() { return this.devPort; }
/*     */ 
/*     */   
/*  96 */   public void setDevPort(int devPort) { this.devPort = devPort; }
/*     */ 
/*     */   
/*  99 */   public String getDevMac() { return this.devMac; }
/*     */ 
/*     */   
/* 102 */   public void setDevMac(String devMac) { this.devMac = devMac; }
/*     */ 
/*     */   
/* 105 */   public byte getDevInstallType() { return this.devInstallType; }
/*     */ 
/*     */   
/* 108 */   public void setDevInstallType(byte devInstallType) { this.devInstallType = devInstallType; }
/*     */ 
/*     */   
/* 111 */   public byte getDevControlType() { return this.devControlType; }
/*     */ 
/*     */   
/* 114 */   public void setDevControlType(byte devControlType) { this.devControlType = devControlType; }
/*     */ 
/*     */   
/* 117 */   public byte getDevKernelType() { return this.devKernelType; }
/*     */ 
/*     */   
/* 120 */   public void setDevKernelType(byte devKernelType) { this.devKernelType = devKernelType; }
/*     */ 
/*     */   
/* 123 */   public String getDevKemelVersion() { return this.devKemelVersion; }
/*     */ 
/*     */   
/* 126 */   public void setDevKemelVersion(String devKemelVersion) { this.devKemelVersion = devKemelVersion; }
/*     */ 
/*     */   
/* 129 */   public String getDevType() { return this.devType; }
/*     */ 
/*     */   
/* 132 */   public void setDevType(String devType) { this.devType = devType; }
/*     */ 
/*     */   
/* 135 */   public String getDevCurrentVersion() { return this.devCurrentVersion; }
/*     */ 
/*     */   
/* 138 */   public void setDevCurrentVersion(String devCurrentVersion) { this.devCurrentVersion = devCurrentVersion; }
/*     */ 
/*     */   
/* 141 */   public byte getDevVersionValue() { return this.devVersionValue; }
/*     */ 
/*     */   
/* 144 */   public void setDevVersionValue(byte devVersionValue) { this.devVersionValue = devVersionValue; }
/*     */ 
/*     */   
/* 147 */   public String getDevControlName() { return this.devControlName; }
/*     */ 
/*     */   
/* 150 */   public void setDevControlName(String devControlName) { this.devControlName = devControlName; }
/*     */ 
/*     */   
/* 153 */   public byte getDevAuditStatus() { return this.devAuditStatus; }
/*     */ 
/*     */   
/* 156 */   public void setDevAuditStatus(byte devAuditStatus) { this.devAuditStatus = devAuditStatus; }
/*     */ 
/*     */   
/* 159 */   public byte getDevState() { return this.devState; }
/*     */ 
/*     */   
/* 162 */   public void setDevState(byte devState) { this.devState = devState; }
/*     */ 
/*     */   
/* 165 */   public String getDevLongitudeValue() { return this.devLongitudeValue; }
/*     */ 
/*     */   
/* 168 */   public void setDevLongitudeValue(String devLongitudeValue) { this.devLongitudeValue = devLongitudeValue; }
/*     */ 
/*     */   
/* 171 */   public String getDevLatitudeValue() { return this.devLatitudeValue; }
/*     */ 
/*     */   
/* 174 */   public void setDevLatitudeValue(String devLatitudeValue) { this.devLatitudeValue = devLatitudeValue; }
/*     */ 
/*     */   
/* 177 */   public int getPlyStrategyId() { return this.plyStrategyId; }
/*     */ 
/*     */   
/* 180 */   public void setPlyStrategyId(int plyStrategyId) { this.plyStrategyId = plyStrategyId; }
/*     */ 
/*     */   
/* 183 */   public String getDevAddress() { return this.devAddress; }
/*     */ 
/*     */   
/* 186 */   public void setDevAddress(String devAddress) { this.devAddress = devAddress; }
/*     */ 
/*     */   
/* 189 */   public String getCustCode() { return this.custCode; }
/*     */ 
/*     */   
/* 192 */   public void setCustCode(String custCode) { this.custCode = custCode; }
/*     */ 
/*     */   
/* 195 */   public byte getDevSoftwareUpdate() { return this.devSoftwareUpdate; }
/*     */ 
/*     */   
/* 198 */   public void setDevSoftwareUpdate(byte devSoftwareUpdate) { this.devSoftwareUpdate = devSoftwareUpdate; }
/*     */ 
/*     */   
/* 201 */   public byte getScreenType() { return this.screenType; }
/*     */ 
/*     */   
/* 204 */   public void setScreenType(byte screenType) { this.screenType = screenType; }
/*     */ 
/*     */   
/* 207 */   public String getCpu() { return this.cpu; }
/*     */ 
/*     */   
/* 210 */   public void setCpu(String cpu) { this.cpu = cpu; }
/*     */ 
/*     */   
/* 213 */   public String getHdd() { return this.hdd; }
/*     */ 
/*     */   
/* 216 */   public void setHdd(String hdd) { this.hdd = hdd; }
/*     */ 
/*     */   
/* 219 */   public String getMemory() { return this.memory; }
/*     */ 
/*     */   
/* 222 */   public void setMemory(String memory) { this.memory = memory; }
/*     */ 
/*     */   
/* 225 */   public String getOperatingLoad() { return this.operatingLoad; }
/*     */ 
/*     */   
/* 228 */   public void setOperatingLoad(String operatingLoad) { this.operatingLoad = operatingLoad; }
/*     */ 
/*     */   
/* 231 */   public String getLicenseCode() { return this.licenseCode; }
/*     */ 
/*     */   
/* 234 */   public void setLicenseCode(String licenseCode) { this.licenseCode = licenseCode; }
/*     */ 
/*     */   
/* 237 */   public String getTranCode() { return this.tranCode; }
/*     */ 
/*     */   
/* 240 */   public void setTranCode(String tranCode) { this.tranCode = tranCode; }
/*     */ 
/*     */   
/* 243 */   public long getLastOnlineDate() { return this.lastOnlineDate; }
/*     */ 
/*     */   
/* 246 */   public void setLastOnlineDate(long lastOnlineDate) { this.lastOnlineDate = lastOnlineDate; }
/*     */ 
/*     */   
/* 249 */   public String getRemarks() { return this.remarks; }
/*     */ 
/*     */   
/* 252 */   public void setRemarks(String remarks) { this.remarks = remarks; }
/*     */ 
/*     */   
/* 255 */   public String getCustNo() { return this.custNo; }
/*     */ 
/*     */   
/* 258 */   public void setCustNo(String custNo) { this.custNo = custNo; }
/*     */ 
/*     */   
/* 261 */   public Integer getVendorId() { return this.vendorId; }
/*     */ 
/*     */   
/* 264 */   public void setVendorId(Integer vendorId) { this.vendorId = vendorId; }
/*     */ 
/*     */   
/* 267 */   public String getDevEquipmentType() { return this.devEquipmentType; }
/*     */ 
/*     */   
/* 270 */   public void setDevEquipmentType(String devEquipmentType) { this.devEquipmentType = devEquipmentType; }
/*     */ 
/*     */   
/* 273 */   public String getCreateBy() { return this.createBy; }
/*     */ 
/*     */   
/* 276 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*     */ 
/*     */   
/* 279 */   public long getCreateDate() { return this.createDate; }
/*     */ 
/*     */   
/* 282 */   public void setCreateDate(long createDate) { this.createDate = createDate; }
/*     */ 
/*     */   
/* 285 */   public String getUpdateBy() { return this.updateBy; }
/*     */ 
/*     */   
/* 288 */   public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
/*     */ 
/*     */   
/* 291 */   public long getUpdateDate() { return this.updateDate; }
/*     */ 
/*     */   
/* 294 */   public void setUpdateDate(long updateDate) { this.updateDate = updateDate; }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\query\TmlDeviceBasic.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */