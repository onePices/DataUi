/*     */ package com.listen.controller;
/*     */ 
/*     */ import com.listen.model.ResponseModel;
/*     */ import com.listen.model.TmlDeviceMapImage;
/*     */ import com.listen.model.query.DeviceQuery;
/*     */ import com.listen.model.query.LogDeviceAlarmInfo;
/*     */ import com.listen.model.query.TmlDeviceBasicVo;
/*     */ import com.listen.model.task.PlayTaskBasicVo;
/*     */ import com.listen.service.CardService;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
///*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestMethod;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/v1.0.0/"})
/*     */ public class CardController
/*     */ {
/*     */   @Resource
/*     */   private CardService cardService;
///*  35 */   private Logger log = Logger.getLogger(CardController.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ResponseBody
/*     */   @RequestMapping(value = {"updateTaskStatusCallBack.php"}, method = {RequestMethod.POST})
/*     */   public ResponseModel updateTaskStatusCallBack(String taskInfo) {
///*  44 */     this.log.info("updateTaskStatusCallBack =" + taskInfo);
/*     */     
/*  46 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ResponseBody
/*     */   @RequestMapping(value = {"deviceHeartBeatCallBack.php"}, method = {RequestMethod.POST})
/*     */   public ResponseModel deviceHeartBeatCallBack(String heartBeatInfo) {
///*  56 */     this.log.info("deviceHeartBeatCallBack =" + heartBeatInfo);
/*     */     
/*  58 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ResponseBody
/*     */   @RequestMapping(value = {"deviceCommitAlarmCallBack.php"}, method = {RequestMethod.POST})
/*     */   public ResponseModel deviceCommitAlarmCallBack(String alarmInfo) {
///*  68 */     this.log.info("deviceCommitAlarmCallBack =" + alarmInfo);
/*     */     
/*  70 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"getLog.php"}, method = {RequestMethod.GET})
/*  81 */   public void getLogs(HttpServletRequest request, HttpServletResponse response, Integer count) { this.cardService.getLogs(request, response, count); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ResponseBody
/*     */   @RequestMapping(value = {"vertifyNetwork.php"}, method = {RequestMethod.POST})
/*  92 */   public ResponseModel vertifyNetwork(String devIp, String devMac) { return this.cardService.vertifyNetwork(devIp, devMac); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"getDeviceProgramTask.php"}, method = {RequestMethod.GET})
/*     */   @ResponseBody
/*     */   public ResponseModel getCardProgramInfo(String custCode, String custNo, String devTranCode, String devCode, String devIp, String devMac, String devTaskStatus) {
/* 104 */     ResponseModel b = this.cardService.getCardProgramInfo(custCode, custNo, devCode, devIp, devMac, devTaskStatus, devTranCode);
/* 105 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"getDeviceDirectiveTask.php"}, method = {RequestMethod.GET})
/*     */   @ResponseBody
/*     */   public ResponseModel getCardIndexInfo(String custCode, String custNo, String devTranCode, String devCode, String devIp, String devMac, String devTaskStatus) {
/* 117 */     ResponseModel b = this.cardService.getCardIndexInfo(custCode, custNo, devCode, devIp, devMac, devTaskStatus, devTranCode);
/* 118 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"getUpdateSoftwareUpdateTask.php"}, method = {RequestMethod.GET})
/*     */   @ResponseBody
/*     */   public ResponseModel getCardUpdateInfo(String custCode, String custNo, String devTranCode, String devCode, String devIp, String devMac, String devTaskStatus) {
/* 130 */     ResponseModel b = this.cardService.getCardUpdateInfo(custCode, custNo, devCode, devIp, devMac, devTaskStatus, devTranCode);
/* 131 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ResponseBody
/*     */   @RequestMapping(value = {"updateTaskStatus.php"}, method = {RequestMethod.POST})
/* 146 */   public ResponseModel updateTaskStatus(PlayTaskBasicVo sysTaskBasicVo) { return this.cardService.updateTaskStatus(sysTaskBasicVo); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"heartbeat.php"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/* 158 */   public ResponseModel addHeartbeatData(TmlDeviceBasicVo tmlDeviceBasicVo) { return this.cardService.addHeartbeatData(tmlDeviceBasicVo); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ResponseBody
/*     */   @RequestMapping(value = {"alarmCommit.php"}, method = {RequestMethod.POST})
/* 175 */   public ResponseModel deviceAlarmCommit(LogDeviceAlarmInfo logDeviceAlarmInfo) { return this.cardService.deviceAlarmCommit(logDeviceAlarmInfo); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ResponseBody
/*     */   @RequestMapping(value = {"updateDownloadSchedule.php"}, method = {RequestMethod.POST})
/*     */   public ResponseModel updateDownloadSchedule(String custCode, String custNo, String taskId, String scheeduleValue, String downloadRate, String devTranCode) {
/* 193 */     return this.cardService.updateDownloadSchedule(custCode, custNo, taskId, 
/* 194 */         scheeduleValue, downloadRate, devTranCode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"deviceMain"})
/*     */   public String deviceMain(Model model) {
/* 203 */     List<DeviceQuery> list = this.cardService.getDevList();
/* 204 */     model.addAttribute("devList", (list != null && list.size() > 0) ? list : null);
/* 205 */     List<TmlDeviceMapImage> mapList = this.cardService.getMapDevList();
/* 206 */     model.addAttribute("mapDevList", (mapList != null && mapList.size() > 0) ? mapList : null);
/* 207 */     return "device";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"getCardInfo.php"}, method = {RequestMethod.GET})
/*     */   @ResponseBody
/*     */   public ResponseModel getCardInfo(String custCode, String custNo, String devTranCode, String devCode, String devIp, String devMac, String devTaskStatus) {
/* 219 */     ResponseModel b = this.cardService.getCardInfo(custCode, custNo, devCode, devIp, devMac, devTaskStatus, devTranCode);
/* 220 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @ResponseBody
/*     */   @RequestMapping(value = {"uploadFile.php"}, method = {RequestMethod.POST})
/*     */   public ResponseModel uploadFile(HttpServletRequest request, String strDevMac, @RequestParam("file") MultipartFile file, String opType, String fileName) throws ServletException, IOException {
/* 238 */     ResponseModel appResponseResult = this.cardService.uploadFile(request, strDevMac, file, opType, fileName);
/* 239 */     return appResponseResult;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"deviceHeartBeatList"})
/*     */   public String deviceHeartBeatList(Model model) {
/* 250 */     List<DeviceQuery> list = this.cardService.getDeviceHeartBeatList();
/* 251 */     model.addAttribute("onlineDevList", (list != null && list.size() > 0) ? list : null);
/* 252 */     return "deviceHeartBeatList";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"deviceTaskUpdateList"})
/*     */   public String deviceTaskUpdateList(Model model) {
/* 263 */     List<PlayTaskBasicVo> list = this.cardService.getDeviceTaskUpdateList();
/* 264 */     model.addAttribute("taskUpdateList", (list != null && list.size() > 0) ? list : null);
/* 265 */     return "deviceTaskUpdateList";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\controller\CardController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */