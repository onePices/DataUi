/*     */ package com.listen.service.impl;
/*     */ 
/*     */ import com.listen.dao.SysUserDao;
/*     */ import com.listen.model.ResultBean;
/*     */ import com.listen.model.SysUserDto;
/*     */ import com.listen.model.sdkservice.ConfigService;
/*     */ import com.listen.model.user.SysUser;
/*     */ import com.listen.service.SysUserService;
/*     */ import com.listen.util.ConfigUtil;
/*     */ import com.listen.util.DESCription;
/*     */ import com.listen.util.LanguagesPropertyUtil;
/*     */ import com.listen.util.MD5;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.util.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class SysUserServiceImpl
/*     */   implements SysUserService
/*     */ {
/*  30 */   private static final Logger log = Logger.getLogger(SysUserServiceImpl.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Resource
/*     */   private SysUserDao sysUserMapper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultBean logIn(HttpSession session, String userName, String password) {
/*  43 */     ResultBean rb = new ResultBean();
/*     */     try {
/*  45 */       SysUser sysUser = this.sysUserMapper.getSysUser(userName);
/*     */       
/*  47 */       if (sysUser.getPassword().equals(MD5.getMd5Hash(password, sysUser.getUserSalt()))) {
/*  48 */         SysUserDto sysUserDto = new SysUserDto();
/*  49 */         sysUserDto.setUserName(userName);
/*  50 */         session.setAttribute("currentUser", sysUserDto);
/*  51 */         session.setMaxInactiveInterval(1800);
/*  52 */         rb.setFlag(Integer.valueOf(1));
/*  53 */         rb.setValue("sdkOperationMain");
/*  54 */         return rb;
/*     */       } 
/*  56 */       rb.setMsg(LanguagesPropertyUtil.getPropertyOnSystemLocale("login.acount.errorPwd"));
/*     */     }
/*  58 */     catch (Exception e) {
/*  59 */       log.info(e.getMessage());
/*  60 */       rb.setFlag(Integer.valueOf(0));
/*  61 */       rb.setMsg("haveing.error");
/*     */     } 
/*  63 */     return rb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultBean updatepassword(HttpServletRequest request, String oldPassword, String newPassword) {
/*  73 */     ResultBean rb = new ResultBean();
/*     */     try {
/*  75 */       if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
/*  76 */         rb.setFlag(Integer.valueOf(0));
/*  77 */         rb.setMsg("message.dateNull");
/*  78 */         return rb;
/*     */       } 
/*  80 */       SysUserDto sysUserDto = (SysUserDto)request.getSession().getAttribute("currentUser");
/*  81 */       SysUser sysUser = this.sysUserMapper.getSysUser(sysUserDto.getUserName());
/*  82 */       String desKey = getDESKey(request);
/*  83 */       oldPassword = DESCription.decryption(oldPassword, desKey);
/*  84 */       if (!oldPassword.equals(null) && !newPassword.equals(null)) {
/*  85 */         if (sysUser.getPassword().equals(MD5.getMd5Hash(oldPassword, sysUser.getUserSalt()))) {
/*  86 */           newPassword = DESCription.decryption(newPassword, desKey);
/*  87 */           newPassword = MD5.getMd5Hash(newPassword, sysUser.getUserSalt());
/*  88 */           this.sysUserMapper.updatePassword(sysUser.getUserId(), newPassword);
/*  89 */           rb.setFlag(Integer.valueOf(1));
/*  90 */           rb.setMsg("password.updateSuccess");
/*     */         } else {
/*  92 */           rb.setFlag(Integer.valueOf(0));
/*  93 */           rb.setMsg("password.error");
/*     */         } 
/*     */       }
/*  96 */     } catch (Exception e) {
/*  97 */       log.info(e.getMessage());
/*  98 */       rb.setFlag(Integer.valueOf(0));
/*  99 */       rb.setMsg("haveing.error");
/*     */     } 
/* 101 */     return rb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultBean logOut(HttpServletRequest request) {
/* 112 */     ResultBean rb = new ResultBean();
/*     */     try {
/* 114 */       SysUserDto sysUserDto = (SysUserDto)request.getSession().getAttribute("currentUser");
/* 115 */       if (sysUserDto != null) {
/* 116 */         request.getSession().removeAttribute("currentUser");
/*     */       }
/* 118 */       rb.setFlag(Integer.valueOf(-1));
/* 119 */       rb.setMsg("logout.success");
/* 120 */     } catch (Exception e) {
/* 121 */       log.info(e.getMessage());
/* 122 */       rb.setFlag(Integer.valueOf(0));
/* 123 */       rb.setMsg("haveing.error");
/*     */     } 
/* 125 */     return rb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDESKey(HttpServletRequest request) {
/* 133 */     HttpSession session = request.getSession();
/* 134 */     if (session != null) {
/* 135 */       return (String)session.getAttribute("DES_KEY");
/*     */     }
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultBean getConfigureList() {
/* 145 */     ResultBean rb = new ResultBean();
/*     */     try {
/* 147 */       List<ConfigService> conflist = new ArrayList<>();
/* 148 */       ConfigService conf = new ConfigService();
/* 149 */       conf.setDefaultSourcePath(ConfigUtil.getProperties("default.source.path"));
/* 150 */       conf.setDefaultCallbackJs(ConfigUtil.getProperties("default.callbackJs"));
/* 151 */       conf.setDefaultTomcatPath(ConfigUtil.getProperties("default.tomcatPath"));
/* 152 */       conf.setDefaultLogTimeOut(ConfigUtil.getProperties("default.logTimeOut"));
/* 153 */       conf.setDefaultTaskZipTimeOut(ConfigUtil.getProperties("default.taskZipTimeOut"));
/* 154 */       conf.setDefaultHeartbeatSaveUrl(ConfigUtil.getProperties("default.heartbeatSaveUrl"));
/* 155 */       conf.setDefaultTaskSaveUrl(ConfigUtil.getProperties("default.taskSaveUrl"));
/* 156 */       conf.setDefaultAlarmSaveUrl(ConfigUtil.getProperties("default.alarmSaveUrl"));
/* 157 */       conf.setDefaultInputPath(ConfigUtil.getProperties("default.input.path"));
/* 158 */       conf.setDefaultGetPictureTimeOut(ConfigUtil.getProperties("default.getPicture.timeOut"));
/* 159 */       conf.setDefaultMapLogPath(ConfigUtil.getProperties("default.mapLog.path"));
/* 160 */       conf.setDefaultMapImageTimeOut(ConfigUtil.getProperties("default.mapImageTimeOut"));
/* 161 */       conf.setDefaultHeartbeatSwitch(ConfigUtil.getProperties("default.heartbeat.switch"));
/* 162 */       conf.setDefaultHeartbeatRecord(ConfigUtil.getProperties("default.heartbeat.record"));
/* 163 */       conf.setDefaultUpdatetaskSwitch(ConfigUtil.getProperties("default.updatetask.switch"));
/* 164 */       conf.setDefaultUpdatetaskRecord(ConfigUtil.getProperties("default.updatetask.record"));
/* 165 */       conf.setDefaultStoragePosition(ConfigUtil.getProperties("default.storagePosition"));
/* 166 */       conf.setDefaultSendTaskUrl(ConfigUtil.getProperties("default.sendTaskUrl"));
/* 167 */       conflist.add(conf);
/* 168 */       rb.setValue(conflist);
/* 169 */       rb.setFlag(Integer.valueOf(1));
/* 170 */       rb.setMsg("message.searchS");
/* 171 */     } catch (Exception e) {
/* 172 */       log.info(e.getMessage());
/* 173 */       rb.setFlag(Integer.valueOf(0));
/* 174 */       rb.setMsg("message.searchF");
/*     */     } 
/* 176 */     return rb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultBean saveOrUpdateConfigure(ConfigService configService) {
/* 183 */     ResultBean rb = new ResultBean();
/* 184 */     boolean updateResult = false;
/*     */     try {
/* 186 */       if (configService.getDefaultSourcePath() != null && !configService.getDefaultSourcePath().equals(ConfigUtil.getProperties("default.source.path"))) {
/* 187 */         ConfigUtil.updateProperties("default.source.path", configService.getDefaultSourcePath());
/* 188 */         updateResult = true;
/*     */       } 
/* 190 */       if (configService.getDefaultCallbackJs() != null && !configService.getDefaultCallbackJs().equals(ConfigUtil.getProperties("default.callbackJs"))) {
/* 191 */         ConfigUtil.updateProperties("default.callbackJs", configService.getDefaultCallbackJs());
/* 192 */         updateResult = true;
/*     */       } 
/* 194 */       if (configService.getDefaultTomcatPath() != null && !configService.getDefaultTomcatPath().equals(ConfigUtil.getProperties("default.tomcatPath"))) {
/* 195 */         ConfigUtil.updateProperties("default.tomcatPath", configService.getDefaultTomcatPath());
/* 196 */         updateResult = true;
/*     */       } 
/* 198 */       if (configService.getDefaultLogTimeOut() != null && !configService.getDefaultLogTimeOut().equals(ConfigUtil.getProperties("default.logTimeOut"))) {
/* 199 */         ConfigUtil.updateProperties("default.logTimeOut", configService.getDefaultLogTimeOut());
/* 200 */         updateResult = true;
/*     */       } 
/* 202 */       if (configService.getDefaultTaskZipTimeOut() != null && !configService.getDefaultTaskZipTimeOut().equals(ConfigUtil.getProperties("default.taskZipTimeOut"))) {
/* 203 */         ConfigUtil.updateProperties("default.taskZipTimeOut", configService.getDefaultTaskZipTimeOut());
/* 204 */         updateResult = true;
/*     */       } 
/* 206 */       if (configService.getDefaultHeartbeatSaveUrl() != null && !configService.getDefaultHeartbeatSaveUrl().equals(ConfigUtil.getProperties("default.heartbeatSaveUrl"))) {
/* 207 */         ConfigUtil.updateProperties("default.heartbeatSaveUrl", configService.getDefaultHeartbeatSaveUrl());
/* 208 */         updateResult = true;
/*     */       } 
/* 210 */       if (configService.getDefaultTaskSaveUrl() != null && !configService.getDefaultTaskSaveUrl().equals(ConfigUtil.getProperties("default.taskSaveUrl"))) {
/* 211 */         ConfigUtil.updateProperties("default.taskSaveUrl", configService.getDefaultTaskSaveUrl());
/* 212 */         updateResult = true;
/*     */       } 
/* 214 */       if (configService.getDefaultAlarmSaveUrl() != null && !configService.getDefaultAlarmSaveUrl().equals(ConfigUtil.getProperties("default.alarmSaveUrl"))) {
/* 215 */         ConfigUtil.updateProperties("default.alarmSaveUrl", configService.getDefaultAlarmSaveUrl());
/* 216 */         updateResult = true;
/*     */       } 
/* 218 */       if (configService.getDefaultInputPath() != null && !configService.getDefaultInputPath().equals(ConfigUtil.getProperties("default.input.path"))) {
/* 219 */         ConfigUtil.updateProperties("default.input.path", configService.getDefaultInputPath());
/* 220 */         updateResult = true;
/*     */       } 
/* 222 */       if (configService.getDefaultGetPictureTimeOut() != null && !configService.getDefaultGetPictureTimeOut().equals(ConfigUtil.getProperties("default.getPicture.timeOut"))) {
/* 223 */         ConfigUtil.updateProperties("default.getPicture.timeOut", configService.getDefaultGetPictureTimeOut());
/* 224 */         updateResult = true;
/*     */       } 
/* 226 */       if (configService.getDefaultMapLogPath() != null && !configService.getDefaultMapLogPath().equals(ConfigUtil.getProperties("default.mapLog.path"))) {
/* 227 */         ConfigUtil.updateProperties("default.mapLog.path", configService.getDefaultMapLogPath());
/* 228 */         updateResult = true;
/*     */       } 
/* 230 */       if (configService.getDefaultMapImageTimeOut() != null && !configService.getDefaultMapImageTimeOut().equals(ConfigUtil.getProperties("default.mapImageTimeOut"))) {
/* 231 */         ConfigUtil.updateProperties("default.mapImageTimeOut", configService.getDefaultMapImageTimeOut());
/* 232 */         updateResult = true;
/*     */       } 
/* 234 */       if (configService.getDefaultHeartbeatSwitch() != null && !configService.getDefaultHeartbeatSwitch().equals(ConfigUtil.getProperties("default.heartbeat.switch"))) {
/* 235 */         ConfigUtil.updateProperties("default.heartbeat.switch", configService.getDefaultHeartbeatSwitch());
/* 236 */         updateResult = true;
/*     */       } 
/* 238 */       if (configService.getDefaultHeartbeatRecord() != null && !configService.getDefaultHeartbeatRecord().equals(ConfigUtil.getProperties("default.heartbeat.record"))) {
/* 239 */         ConfigUtil.updateProperties("default.heartbeat.record", configService.getDefaultHeartbeatRecord());
/* 240 */         updateResult = true;
/*     */       } 
/* 242 */       if (configService.getDefaultUpdatetaskSwitch() != null && !configService.getDefaultUpdatetaskSwitch().equals(ConfigUtil.getProperties("default.updatetask.switch"))) {
/* 243 */         ConfigUtil.updateProperties("default.updatetask.switch", configService.getDefaultUpdatetaskSwitch());
/* 244 */         updateResult = true;
/*     */       } 
/* 246 */       if (configService.getDefaultUpdatetaskRecord() != null && !configService.getDefaultUpdatetaskRecord().equals(ConfigUtil.getProperties("default.updatetask.record"))) {
/* 247 */         ConfigUtil.updateProperties("default.updatetask.record", configService.getDefaultUpdatetaskRecord());
/* 248 */         updateResult = true;
/*     */       } 
/* 250 */       if (configService.getDefaultStoragePosition() != null && !configService.getDefaultStoragePosition().equals(ConfigUtil.getProperties("default.storagePosition"))) {
/* 251 */         ConfigUtil.updateProperties("default.storagePosition", configService.getDefaultStoragePosition());
/* 252 */         updateResult = true;
/*     */       } 
/* 254 */       if (configService.getDefaultSendTaskUrl() != null && !configService.getDefaultSendTaskUrl().equals(ConfigUtil.getProperties("default.sendTaskUrl"))) {
/* 255 */         ConfigUtil.updateProperties("default.sendTaskUrl", configService.getDefaultSendTaskUrl());
/* 256 */         updateResult = true;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 261 */       rb.setFlag(Integer.valueOf(1));
/* 262 */       rb.setMsg("having.success");
/* 263 */     } catch (Exception e) {
/* 264 */       log.info(e.getMessage());
/* 265 */       rb.setFlag(Integer.valueOf(0));
/* 266 */       rb.setMsg("haveing.error");
/*     */     } 
/* 268 */     return rb;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\service\impl\SysUserServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */