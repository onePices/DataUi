/*     */ package com.listen.controller;
/*     */ 
/*     */ import com.listen.model.ResponseModel;
/*     */ import com.listen.model.ResultBean;
/*     */ import com.listen.model.TmlDeviceMapImage;
/*     */ import com.listen.model.query.DeviceQuery;
/*     */ import com.listen.model.task.PlayTaskBasicVo;
/*     */ import com.listen.service.AuthService;
/*     */ import com.listen.service.CardService;
/*     */ import com.listen.util.ConfigUtil;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestMethod;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @RequestMapping({"/sdkNew/"})
/*     */ @Controller
/*     */ public class ListenSdkOperateController
/*     */   extends BaseController
/*     */ {
/*     */   @Resource
/*     */   private CardService cardService;
/*     */   @Resource
/*     */   private AuthService authService;
/*     */   
/*     */   @RequestMapping({"/deviceMain"})
/*     */   public String deviceMain(HttpServletRequest request, Model model) {
/*  38 */     List<DeviceQuery> list = this.cardService.getDevList();
/*  39 */     model.addAttribute("devList", (list != null && list.size() > 0) ? list : null);
/*  40 */     List<TmlDeviceMapImage> mapList = this.cardService.getMapDevList();
/*  41 */     model.addAttribute("mapDevList", (mapList != null && mapList.size() > 0) ? mapList : null);
/*  42 */     return "device/device";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/getDeviceList"})
/*     */   @ResponseBody
/*     */   public ResultBean getDevList(Integer pageNo, Integer pageSize, String devCodeOrMac) {
/*  55 */     ResultBean rb = this.cardService.getDevList(pageNo, pageSize, devCodeOrMac);
/*  56 */     return rb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/getMapDevList"})
/*     */   @ResponseBody
/*     */   public ResultBean getMapDevList(Integer pageNo, Integer pageSize, String devCode) {
/*  69 */     ResultBean rb = this.cardService.getMapDevList(pageNo, pageSize, devCode);
/*  70 */     return rb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/deviceHeartBeatList"})
/*     */   public String deviceHeartBeatList(HttpServletRequest request, Model model) {
/*  80 */     List<DeviceQuery> list = this.cardService.getDeviceHeartBeatList();
/*  81 */     model.addAttribute("onlineDevList", (list != null && list.size() > 0) ? list : null);
/*  82 */     return "device/deviceHeartBeatList";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/getDeviceHeartBeatList"})
/*     */   @ResponseBody
/*     */   public ResultBean getdeviceHeartBeatList(Integer pageNo, Integer pageSize, String devCodeOrMac) {
/*  95 */     ResultBean rb = this.cardService.getDeviceHeartBeatList(pageNo, pageSize, devCodeOrMac);
/*  96 */     return rb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping({"/deviceTaskUpdateList"})
/*     */   public String deviceTaskUpdateList(HttpServletRequest request, Model model) {
/* 107 */     List<PlayTaskBasicVo> list = this.cardService.getDeviceTaskUpdateList();
/* 108 */     model.addAttribute("taskUpdateList", (list != null && list.size() > 0) ? list : null);
/* 109 */     return "device/deviceTaskUpdateList";
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
/*     */   @RequestMapping({"/getDeviceTaskUpdateList"})
/*     */   @ResponseBody
/*     */   public ResultBean deviceTaskUpdateList(Integer pageNo, Integer pageSize, String devCode) {
/* 123 */     ResultBean rb = this.cardService.getDeviceTaskUpdateList(pageNo, pageSize, devCode);
/* 124 */     return rb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/lsPublishProgram.fgl"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public ResponseModel saveAndSendProgram(HttpServletRequest request, String devCode, String strProgramList) {
/* 137 */     String defaultStoragePosition = ConfigUtil.getProperties("default.storagePosition");
/* 138 */     ResponseModel rm = null;
/* 139 */     if (defaultStoragePosition.equals("1")) {
/* 140 */       rm = this.authService.saveAndSendProgram(devCode, strProgramList);
/* 141 */     } else if (defaultStoragePosition.equals("2")) {
/* 142 */       rm = this.authService.saveAndSendProgramNew(devCode, strProgramList);
/*     */     } 
/* 144 */     return rm;
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
/*     */   @RequestMapping(value = {"/lsPublishTask.fgl"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/* 157 */   public ResponseModel saveAndSendTask(HttpServletRequest request, String devCode, Integer type, String content) { return this.authService.saveAndSendTask(devCode, type, content); }
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
/*     */   @RequestMapping(value = {"/updateData.fgl"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/* 171 */   public ResponseModel updateData(HttpServletRequest request, String devCode, String devType, String devDataType, String dataInfo) { return this.authService.updateData(devCode, devType, devDataType, dataInfo); }
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
/*     */   @RequestMapping(value = {"/addMapDevice.fgl"}, method = {RequestMethod.POST})
/* 183 */   public ResponseModel addMapDevice(HttpServletRequest request, String devCode, String imageName) { return this.authService.addMapDevice(devCode, imageName); }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\controller\ListenSdkOperateController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */