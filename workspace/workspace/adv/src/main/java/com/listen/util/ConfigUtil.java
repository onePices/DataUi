/*    */ package com.listen.util;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Properties;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConfigUtil
/*    */ {
/*    */   private static Properties prop;
/* 19 */   private static Logger log = Logger.getLogger(ConfigUtil.class);
/*    */   
/*    */   static  {
/* 22 */     loadProp();
/*    */   }
/*    */   
/*    */   private static synchronized void loadProp() {
/* 26 */     prop = new Properties();
/* 27 */     InputStream in = null;
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 32 */       String path = ConfigUtil.class.getClassLoader().getResource("config.properties").getPath();
/* 33 */       InputStream is = new FileInputStream(path);
/* 34 */       prop.load(is);
/* 35 */     } catch (Exception e) {
/* 36 */       log.error("加载配置文件异常", e);
/*    */     } finally {
/*    */       try {
/* 39 */         if (in != null) {
/* 40 */           in.close();
/*    */         }
/* 42 */       } catch (IOException e) {
/* 43 */         log.error("配置文件流关闭出现异常", e);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String getProperties(String key) {
/* 49 */     if (prop == null) {
/* 50 */       loadProp();
/*    */     }
/* 52 */     if (key != null && !"".equals(key)) {
/* 53 */       return prop.getProperty(key);
/*    */     }
/* 55 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void updateProperties(String key, String value) {
/* 60 */     if (prop == null) {
/* 61 */       loadProp();
/*    */     }
/*    */     try {
/* 64 */       prop.setProperty(key, value);
/* 65 */       String path = ConfigUtil.class.getClassLoader().getResource("config.properties").getPath();
/* 66 */       FileOutputStream oFile = new FileOutputStream(path);
/* 67 */       prop.store(oFile, "");
/* 68 */     } catch (Exception e) {
/* 69 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\ConfigUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */