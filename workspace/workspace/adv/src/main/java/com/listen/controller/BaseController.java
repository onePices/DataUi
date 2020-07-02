/*    */ package com.listen.controller;
/*    */ 
/*    */ import com.listen.model.SysUserDto;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaseController
/*    */ {
/*    */   public SysUserDto getUser(HttpServletRequest request) {
/* 16 */     SysUserDto sysUserDto = (SysUserDto)request.getSession().getAttribute("currentUser");
/* 17 */     if (sysUserDto != null) {
/* 18 */       return sysUserDto;
/*    */     }
/* 20 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDESKey(HttpServletRequest request) {
/* 28 */     HttpSession session = request.getSession();
/* 29 */     if (session != null) {
/* 30 */       return (String)session.getAttribute("DES_KEY");
/*    */     }
/* 32 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\controller\BaseController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */