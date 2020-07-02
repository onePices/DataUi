/*    */ package com.listen.util;
/*    */ 
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Locale;
/*    */ import java.util.Properties;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.shiro.SecurityUtils;
/*    */ import org.springframework.web.servlet.i18n.SessionLocaleResolver;
/*    */ 
/*    */ 
/*    */ public class LanguagesPropertyUtil
/*    */ {
/* 15 */   private static final Logger logger = Logger.getLogger(LanguagesPropertyUtil.class);
/*    */   private static Properties propsUS;
/*    */   private static Properties propsCN;
/*    */   
/*    */   static  {
/* 20 */     loadProps();
/*    */   }
/*    */   
/*    */   private static synchronized void loadProps() {
/* 24 */     propsUS = new Properties();
/* 25 */     propsCN = new Properties();
/* 26 */     InputStream in = null;
/*    */     try {
/* 28 */       in = LanguagesPropertyUtil.class.getClassLoader().getResourceAsStream("languages/language_en_US.properties");
/* 29 */       propsUS.load(in);
/* 30 */       in = LanguagesPropertyUtil.class.getClassLoader().getResourceAsStream("languages/language_zh_CN.properties");
/* 31 */       propsCN.load(in);
/* 32 */     } catch (FileNotFoundException e) {
/* 33 */       logger.error("国际化文件未找到");
/* 34 */     } catch (IOException e) {
/* 35 */       logger.error("出现IOException");
/*    */     } finally {
/*    */       try {
/* 38 */         if (in != null) {
/* 39 */           in.close();
/*    */         }
/* 41 */       } catch (IOException e) {
/* 42 */         logger.error("国际化文件流关闭出现异常");
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String getLocale() {
/* 48 */     Object object = SecurityUtils.getSubject().getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
/* 49 */     if (object != null) {
/* 50 */       Locale locale = (Locale)object;
/* 51 */       return locale.getCountry();
/*    */     } 
/* 53 */     return "CN";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 58 */   public static String getPropertyOnSystemLocale(String key) { return getProperty(key, getLocale()); }
/*    */ 
/*    */   
/*    */   public static String getProperty(String key, String locale) {
/* 62 */     if (key != null && !"".equals(key)) {
/* 63 */       if (propsUS == null || propsCN == null) {
/* 64 */         loadProps();
/*    */       }
/* 66 */       if (locale.equalsIgnoreCase("US")) {
/* 67 */         return (propsUS.getProperty(key) == null || "".equals(propsUS.getProperty(key))) ? key : propsUS.getProperty(key);
/*    */       }
/* 69 */       return (propsCN.getProperty(key) == null || "".equals(propsCN.getProperty(key))) ? key : propsCN.getProperty(key);
/*    */     } 
/*    */     
/* 72 */     return key;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\LanguagesPropertyUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */