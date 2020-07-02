/*    */ package com.listen.util;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExecuteTomcatUtil
/*    */ {
/*    */   private static final String startLocationUrl = "D://tomcat2/apache-tomcat-7.0.93-windows-x648/apache-tomcat-7.0.93/bin/startup.bat";
/*    */   private static final String stopLocationUrl = "D://tomcat2/apache-tomcat-7.0.93-windows-x648/apache-tomcat-7.0.93/bin/shutdown.bat";
/*    */   
/*    */   public static void executeCmd() {
/* 14 */     Runtime run = Runtime.getRuntime();
/*    */     try {
/* 16 */       run.exec("D://tomcat2/apache-tomcat-7.0.93-windows-x648/apache-tomcat-7.0.93/bin/shutdown.bat");
/* 17 */       Process ps = run.exec("D://tomcat2/apache-tomcat-7.0.93-windows-x648/apache-tomcat-7.0.93/bin/startup.bat");
/* 18 */       BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream(), "UTF-8"));
/*    */       String line;
/* 20 */       while ((line = br.readLine()) != null) {
/* 21 */         System.out.println("StartedLog==>" + line);
/*    */       }
/* 23 */       br.close();
/* 24 */     } catch (Exception e) {
/* 25 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\ExecuteTomcatUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */