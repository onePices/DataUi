/*    */ package com.listen.controller;
/*    */ 
/*    */ import com.listen.exception.DisableCustomerException;
/*    */ import com.listen.model.ResultBean;
/*    */ import com.listen.service.SysUserService;
/*    */ import com.listen.util.DESCription;
/*    */ import com.listen.util.LanguagesPropertyUtil;
/*    */ import javax.annotation.Resource;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.apache.shiro.SecurityUtils;
/*    */ import org.apache.shiro.authc.ExpiredCredentialsException;
/*    */ import org.apache.shiro.authc.IncorrectCredentialsException;
/*    */ import org.apache.shiro.authc.UnknownAccountException;
/*    */ import org.apache.shiro.subject.Subject;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.util.StringUtils;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/sdkNew/"})
/*    */ public class LoginController
/*    */   extends BaseController
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Resource
/*    */   private SysUserService sysUserService;
/*    */   
/*    */   @RequestMapping({"/loginIn"})
/*    */   @ResponseBody
/*    */   public ResultBean loginIn(HttpServletRequest request, String username, String password, Model model, HttpSession session, String token) {
/* 45 */     ResultBean rb = new ResultBean();
/* 46 */     rb.setFlag(Integer.valueOf(0));
/* 47 */     Subject subject = SecurityUtils.getSubject();
/* 48 */     boolean flag = false;
/* 49 */     if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
/* 50 */       rb.setMsg(LanguagesPropertyUtil.getPropertyOnSystemLocale("login.empty"));
/* 51 */       rb.setValue("login");
/* 52 */       return rb;
/*    */     } 
/* 54 */     if (!flag) {
/* 55 */       String desKey = getDESKey(request);
/* 56 */       if (desKey == null) {
/* 57 */         rb.setMsg(LanguagesPropertyUtil.getPropertyOnSystemLocale("login.desKeyEmpty"));
/* 58 */         rb.setValue("login");
/* 59 */         return rb;
/*    */       } 
/*    */       try {
/* 62 */         username = DESCription.decryption(username, desKey);
/* 63 */         password = DESCription.decryption(password, desKey);
/* 64 */       } catch (Exception e1) {
/* 65 */         e1.printStackTrace();
/*    */       } 
/*    */     } 
/*    */     try {
/* 69 */       rb = this.sysUserService.logIn(session, username, password);
/* 70 */       if (rb.getFlag().equals(Integer.valueOf(1))) {
/* 71 */         return rb;
/*    */       }
/* 73 */     } catch (IncorrectCredentialsException e) {
/* 74 */       rb.setMsg(LanguagesPropertyUtil.getPropertyOnSystemLocale("login.acount.errorPwd"));
/* 75 */     } catch (ExpiredCredentialsException e) {
/* 76 */       rb.setMsg(LanguagesPropertyUtil.getPropertyOnSystemLocale("login.acount.expired"));
/* 77 */     } catch (UnknownAccountException e) {
/* 78 */       rb.setMsg(LanguagesPropertyUtil.getPropertyOnSystemLocale("login.unknownAccount"));
/* 79 */     } catch (DisableCustomerException e) {
/* 80 */       rb.setMsg(LanguagesPropertyUtil.getPropertyOnSystemLocale("login.custDisable"));
/*    */     } 
/* 82 */     rb.setValue("login");
/* 83 */     subject.logout();
/* 84 */     return rb;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @ResponseBody
/*    */   @RequestMapping({"/logOut"})
/*    */   public ResultBean logOut(HttpServletRequest request) {
/* 96 */     ResultBean rb = this.sysUserService.logOut(request);
/* 97 */     return rb;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\controller\LoginController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */