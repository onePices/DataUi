/*    */ package com.listen.controller;
/*    */ 
/*    */ import com.listen.model.ResponseModel;
/*    */ import com.listen.service.AuthService;
/*    */ import com.listen.util.ConfigUtil;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMethod;
/*    */ import org.springframework.web.bind.annotation.ResponseBody;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/sdk/"})
/*    */ public class AuthController
/*    */ {
/*    */   @Resource
/*    */   private AuthService authService;
/*    */   
/*    */   @RequestMapping(value = {"lsPublishProgram.fgl"}, method = {RequestMethod.POST})
/*    */   @ResponseBody
/*    */   public ResponseModel saveAndSendProgram(String devCode, String strProgramList) {
/* 33 */     String defaultStoragePosition = ConfigUtil.getProperties("default.storagePosition");
/* 34 */     ResponseModel rm = null;
/* 35 */     if (defaultStoragePosition.equals("1")) {
/* 36 */       rm = this.authService.saveAndSendProgram(devCode, strProgramList);
/* 37 */     } else if (defaultStoragePosition.equals("2")) {
/* 38 */       rm = this.authService.saveAndSendProgramNew(devCode, strProgramList);
/*    */     } 
/* 40 */     return rm;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @RequestMapping(value = {"lsPublishTask.fgl"}, method = {RequestMethod.POST})
/*    */   @ResponseBody
/* 53 */   public ResponseModel saveAndSendTask(String devCode, Integer type, String content) { return this.authService.saveAndSendTask(devCode, type, content); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @RequestMapping(value = {"updateData.fgl"}, method = {RequestMethod.POST})
/*    */   @ResponseBody
/* 67 */   public ResponseModel updateData(String devCode, String devType, String devDataType, String dataInfo) { return this.authService.updateData(devCode, devType, devDataType, dataInfo); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @ResponseBody
/*    */   @RequestMapping(value = {"getDeviceList.php"}, method = {RequestMethod.GET})
/* 77 */   public ResponseModel getDeviceList() { return this.authService.getDeviceList(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @ResponseBody
/*    */   @RequestMapping(value = {"addMapDevice.fgl"}, method = {RequestMethod.POST})
/* 89 */   public ResponseModel addMapDevice(String devCode, String imageName) { return this.authService.addMapDevice(devCode, imageName); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @ResponseBody
/*    */   @RequestMapping(value = {"getMapDeviceList.php"}, method = {RequestMethod.GET})
/* 99 */   public ResponseModel getMapDeviceList() { return this.authService.getMapDeviceList(); }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\controller\AuthController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */