/*    */ package com.listen.controller.user;
/*    */ 
/*    */ import com.listen.controller.BaseController;
/*    */ import com.listen.model.ResultBean;
/*    */ import com.listen.model.sdkservice.ConfigService;
/*    */ import com.listen.service.SysUserService;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.ResponseBody;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @RequestMapping({"/user/"})
/*    */ @Controller
/*    */ public class SysUserController
/*    */   extends BaseController
/*    */ {
/*    */   @Autowired
/*    */   private SysUserService sysUserService;
/*    */   
/*    */   @ResponseBody
/*    */   @RequestMapping({"/updatePassword"})
/* 30 */   public ResultBean updatepassword(HttpServletRequest request, String oldPassword, String newPassword) { return this.sysUserService.updatepassword(request, oldPassword, newPassword); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @ResponseBody
/*    */   @RequestMapping({"/getConfigureList"})
/* 39 */   public ResultBean getConfigureList() { return this.sysUserService.getConfigureList(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @ResponseBody
/*    */   @RequestMapping({"/saveOrUpdateConfigure"})
/* 48 */   public ResultBean saveOrUpdateConfigure(ConfigService configService) { return this.sysUserService.saveOrUpdateConfigure(configService); }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\controlle\\user\SysUserController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */