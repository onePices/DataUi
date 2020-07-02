/*    */ package com.listen.util;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.util.Date;
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
/*    */ public class IndentityUtil
/*    */ {
/*    */   private static final String SYS_NO_FORMAT = "0000";
/*    */   private static final String SYS_NO_FORMAT_TEST = "000000000000";
/*    */   
/*    */   public static String createIndentityValue(int sysValue, String sysType) {
/* 23 */     String retValue = null;
/*    */     
/* 25 */     String strPrefix = "LE";
/*    */     
/* 27 */     if (sysType != null && sysType.length() >= 2) {
/* 28 */       strPrefix = sysType.substring(0, 2);
/*    */     }
/* 30 */     DecimalFormat df = new DecimalFormat("0000");
/*    */     
/* 32 */     sysValue++;
/*    */     
/* 34 */     retValue = String.valueOf(strPrefix) + DateUtil.formatDate(new Date()).replace("-", "") + df.format(sysValue);
/*    */     
/* 36 */     return retValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String createIndentityValueByTest(int sysValue, String sysType) {
/* 46 */     String retValue = null;
/*    */     
/* 48 */     String strPrefix = "LE";
/*    */     
/* 50 */     if (sysType != null && sysType.length() >= 2) {
/* 51 */       strPrefix = sysType.substring(0, 2);
/*    */     }
/*    */     
/* 54 */     DecimalFormat df = new DecimalFormat("000000000000");
/*    */     
/* 56 */     sysValue++;
/*    */     
/* 58 */     retValue = String.valueOf(strPrefix) + DateUtil.formatDate(new Date()).replace("-", "") + df.format(sysValue);
/*    */     
/* 60 */     return retValue;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\IndentityUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */