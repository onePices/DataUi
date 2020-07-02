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
/*     */ public class TmlDeviceBasicQuery
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
/*     */   private Integer groupId;
/*     */   
/*  58 */   public int getDevId() { return this.devId; }
/*     */ 
/*     */   
/*  61 */   public void setDevId(int devId) { this.devId = devId; }
/*     */ 
/*     */   
/*  64 */   public String getDevName() { return this.devName; }
/*     */ 
/*     */   
/*  67 */   public void setDevName(String devName) { this.devName = devName; }
/*     */ 
/*     */   
/*  70 */   public int getDevWidth() { return this.devWidth; }
/*     */ 
/*     */   
/*  73 */   public void setDevWidth(int devWidth) { this.devWidth = devWidth; }
/*     */ 
/*     */   
/*  76 */   public int getDevHeight() { return this.devHeight; }
/*     */ 
/*     */   
/*  79 */   public void setDevHeight(int devHeight) { this.devHeight = devHeight; }
/*     */ 
/*     */   
/*  82 */   public String getDevCode() { return this.devCode; }
/*     */ 
/*     */   
/*  85 */   public void setDevCode(String devCode) { this.devCode = devCode; }
/*     */ 
/*     */   
/*  88 */   public String getDevIp() { return this.devIp; }
/*     */ 
/*     */   
/*  91 */   public void setDevIp(String devIp) { this.devIp = devIp; }
/*     */ 
/*     */   
/*  94 */   public int getDevPort() { return this.devPort; }
/*     */ 
/*     */   
/*  97 */   public void setDevPort(int devPort) { this.devPort = devPort; }
/*     */ 
/*     */   
/* 100 */   public String getDevMac() { return this.devMac; }
/*     */ 
/*     */   
/* 103 */   public void setDevMac(String devMac) { this.devMac = devMac; }
/*     */ 
/*     */   
/* 106 */   public byte getDevInstallType() { return this.devInstallType; }
/*     */ 
/*     */   
/* 109 */   public void setDevInstallType(byte devInstallType) { this.devInstallType = devInstallType; }
/*     */ 
/*     */   
/* 112 */   public byte getDevControlType() { return this.devControlType; }
/*     */ 
/*     */   
/* 115 */   public void setDevControlType(byte devControlType) { this.devControlType = devControlType; }
/*     */ 
/*     */   
/* 118 */   public byte getDevKernelType() { return this.devKernelType; }
/*     */ 
/*     */   
/* 121 */   public void setDevKernelType(byte devKernelType) { this.devKernelType = devKernelType; }
/*     */ 
/*     */   
/* 124 */   public String getDevKemelVersion() { return this.devKemelVersion; }
/*     */ 
/*     */   
/* 127 */   public void setDevKemelVersion(String devKemelVersion) { this.devKemelVersion = devKemelVersion; }
/*     */ 
/*     */   
/* 130 */   public String getDevType() { return this.devType; }
/*     */ 
/*     */   
/* 133 */   public void setDevType(String devType) { this.devType = devType; }
/*     */ 
/*     */   
/* 136 */   public String getDevCurrentVersion() { return this.devCurrentVersion; }
/*     */ 
/*     */   
/* 139 */   public void setDevCurrentVersion(String devCurrentVersion) { this.devCurrentVersion = devCurrentVersion; }
/*     */ 
/*     */   
/* 142 */   public byte getDevVersionValue() { return this.devVersionValue; }
/*     */ 
/*     */   
/* 145 */   public void setDevVersionValue(byte devVersionValue) { this.devVersionValue = devVersionValue; }
/*     */ 
/*     */   
/* 148 */   public String getDevControlName() { return this.devControlName; }
/*     */ 
/*     */   
/* 151 */   public void setDevControlName(String devControlName) { this.devControlName = devControlName; }
/*     */ 
/*     */   
/* 154 */   public byte getDevAuditStatus() { return this.devAuditStatus; }
/*     */ 
/*     */   
/* 157 */   public void setDevAuditStatus(byte devAuditStatus) { this.devAuditStatus = devAuditStatus; }
/*     */ 
/*     */   
/* 160 */   public byte getDevState() { return this.devState; }
/*     */ 
/*     */   
/* 163 */   public void setDevState(byte devState) { this.devState = devState; }
/*     */ 
/*     */   
/* 166 */   public String getDevLongitudeValue() { return this.devLongitudeValue; }
/*     */ 
/*     */   
/* 169 */   public void setDevLongitudeValue(String devLongitudeValue) { this.devLongitudeValue = devLongitudeValue; }
/*     */ 
/*     */   
/* 172 */   public String getDevLatitudeValue() { return this.devLatitudeValue; }
/*     */ 
/*     */   
/* 175 */   public void setDevLatitudeValue(String devLatitudeValue) { this.devLatitudeValue = devLatitudeValue; }
/*     */ 
/*     */   
/* 178 */   public int getPlyStrategyId() { return this.plyStrategyId; }
/*     */ 
/*     */   
/* 181 */   public void setPlyStrategyId(int plyStrategyId) { this.plyStrategyId = plyStrategyId; }
/*     */ 
/*     */   
/* 184 */   public String getDevAddress() { return this.devAddress; }
/*     */ 
/*     */   
/* 187 */   public void setDevAddress(String devAddress) { this.devAddress = devAddress; }
/*     */ 
/*     */   
/* 190 */   public String getCustCode() { return this.custCode; }
/*     */ 
/*     */   
/* 193 */   public void setCustCode(String custCode) { this.custCode = custCode; }
/*     */ 
/*     */   
/* 196 */   public byte getDevSoftwareUpdate() { return this.devSoftwareUpdate; }
/*     */ 
/*     */   
/* 199 */   public void setDevSoftwareUpdate(byte devSoftwareUpdate) { this.devSoftwareUpdate = devSoftwareUpdate; }
/*     */ 
/*     */   
/* 202 */   public byte getScreenType() { return this.screenType; }
/*     */ 
/*     */   
/* 205 */   public void setScreenType(byte screenType) { this.screenType = screenType; }
/*     */ 
/*     */   
/* 208 */   public String getCpu() { return this.cpu; }
/*     */ 
/*     */   
/* 211 */   public void setCpu(String cpu) { this.cpu = cpu; }
/*     */ 
/*     */   
/* 214 */   public String getHdd() { return this.hdd; }
/*     */ 
/*     */   
/* 217 */   public void setHdd(String hdd) { this.hdd = hdd; }
/*     */ 
/*     */   
/* 220 */   public String getMemory() { return this.memory; }
/*     */ 
/*     */   
/* 223 */   public void setMemory(String memory) { this.memory = memory; }
/*     */ 
/*     */   
/* 226 */   public String getOperatingLoad() { return this.operatingLoad; }
/*     */ 
/*     */   
/* 229 */   public void setOperatingLoad(String operatingLoad) { this.operatingLoad = operatingLoad; }
/*     */ 
/*     */   
/* 232 */   public String getLicenseCode() { return this.licenseCode; }
/*     */ 
/*     */   
/* 235 */   public void setLicenseCode(String licenseCode) { this.licenseCode = licenseCode; }
/*     */ 
/*     */   
/* 238 */   public String getTranCode() { return this.tranCode; }
/*     */ 
/*     */   
/* 241 */   public void setTranCode(String tranCode) { this.tranCode = tranCode; }
/*     */ 
/*     */   
/* 244 */   public long getLastOnlineDate() { return this.lastOnlineDate; }
/*     */ 
/*     */   
/* 247 */   public void setLastOnlineDate(long lastOnlineDate) { this.lastOnlineDate = lastOnlineDate; }
/*     */ 
/*     */   
/* 250 */   public String getRemarks() { return this.remarks; }
/*     */ 
/*     */   
/* 253 */   public void setRemarks(String remarks) { this.remarks = remarks; }
/*     */ 
/*     */   
/* 256 */   public String getCustNo() { return this.custNo; }
/*     */ 
/*     */   
/* 259 */   public void setCustNo(String custNo) { this.custNo = custNo; }
/*     */ 
/*     */   
/* 262 */   public Integer getVendorId() { return this.vendorId; }
/*     */ 
/*     */   
/* 265 */   public void setVendorId(Integer vendorId) { this.vendorId = vendorId; }
/*     */ 
/*     */   
/* 268 */   public String getDevEquipmentType() { return this.devEquipmentType; }
/*     */ 
/*     */   
/* 271 */   public void setDevEquipmentType(String devEquipmentType) { this.devEquipmentType = devEquipmentType; }
/*     */ 
/*     */   
/* 274 */   public String getCreateBy() { return this.createBy; }
/*     */ 
/*     */   
/* 277 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*     */ 
/*     */   
/* 280 */   public long getCreateDate() { return this.createDate; }
/*     */ 
/*     */   
/* 283 */   public void setCreateDate(long createDate) { this.createDate = createDate; }
/*     */ 
/*     */   
/* 286 */   public String getUpdateBy() { return this.updateBy; }
/*     */ 
/*     */   
/* 289 */   public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
/*     */ 
/*     */   
/* 292 */   public long getUpdateDate() { return this.updateDate; }
/*     */ 
/*     */   
/* 295 */   public void setUpdateDate(long updateDate) { this.updateDate = updateDate; }
/*     */ 
/*     */   
/* 298 */   public Integer getGroupId() { return this.groupId; }
/*     */ 
/*     */   
/* 301 */   public void setGroupId(Integer groupId) { this.groupId = groupId; }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\query\TmlDeviceBasicQuery.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */