/*    */ package com.listen.controller;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.i18n.LocaleContextHolder;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMethod;
/*    */ import org.springframework.web.servlet.i18n.SessionLocaleResolver;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ public class GlobalController
/*    */ {
/*    */   @Autowired
/*    */   private SessionLocaleResolver localeResolver;
/*    */   
/*    */   @RequestMapping(value = {"/changeLang"}, method = {RequestMethod.GET})
/*    */   public String test(HttpServletRequest request, String langType, HttpServletResponse response) {
/* 23 */     if (langType.equals("CN")) {
/* 24 */       Locale locale = new Locale("zh", "CN");
/* 25 */       request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
/* 26 */       this.localeResolver.setLocale(request, response, locale);
/* 27 */     } else if (langType.equals("US")) {
/* 28 */       Locale locale = new Locale("en", "US");
/* 29 */       request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
/* 30 */       this.localeResolver.setLocale(request, response, locale);
/*    */     } else {
/* 32 */       request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, LocaleContextHolder.getLocale());
/*    */     } 
/* 34 */     return "forward:/sdkOperationMain";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\controller\GlobalController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */