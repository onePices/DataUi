/*    */ package com.listen.controller;
/*    */ 
/*    */ import com.listen.util.CodeUtil;
/*    */ import com.listen.util.ConfigUtil;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ public class MainController
/*    */   extends BaseController
/*    */ {
/*    */   @RequestMapping({"/"})
/*    */   public String loginMain(HttpServletRequest request) {
/* 21 */     request.getSession().setAttribute("DES_KEY", CodeUtil.getDevCode());
/* 22 */     return "login";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @RequestMapping({"/sdkOperationMain"})
/*    */   public String sdkOperationMain(HttpServletRequest request) {
/* 30 */     request.getSession().removeAttribute("sendTaskUrl");
/* 31 */     request.getSession().setAttribute("sendTaskUrl", ConfigUtil.getProperties("default.sendTaskUrl"));
/* 32 */     request.getSession().removeAttribute("storagePosition");
/* 33 */     request.getSession().setAttribute("storagePosition", ConfigUtil.getProperties("default.storagePosition"));
/* 34 */     return "index";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @RequestMapping({"/deviceListMain"})
/* 42 */   public String deviceListMain() { return "database/device"; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @RequestMapping({"/deviceHeartBeatListMain"})
/* 50 */   public String deviceHeartBeatListMain() { return "database/deviceHeartBeatList"; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @RequestMapping({"/deviceTaskUpdateListMain"})
/* 58 */   public String deviceTaskUpdateListMain() { return "database/deviceTaskUpdateList"; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @RequestMapping({"/updatePasswordMain"})
/*    */   public String updatePasswordMain(HttpServletRequest request) {
/* 68 */     request.getSession().setAttribute("DES_KEY", CodeUtil.getDevCode());
/* 69 */     return "updatePassword";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @RequestMapping({"/configureServiceMain"})
/*    */   public String configureServiceMain(HttpServletRequest request) {
/* 79 */     request.getSession().setAttribute("DES_KEY", CodeUtil.getDevCode());
/* 80 */     return "configureService";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\controller\MainController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */