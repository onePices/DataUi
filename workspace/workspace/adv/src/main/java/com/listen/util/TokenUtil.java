/*    */ package com.listen.util;
/*    */ 
/*    */ import java.security.MessageDigest;
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
/*    */ 
/*    */ public class TokenUtil
/*    */ {
/*    */   public static String createToken(String devNo) throws Exception {
/* 21 */     MessageDigest md5 = null;
/*    */     try {
/* 23 */       md5 = MessageDigest.getInstance("MD5");
/* 24 */     } catch (Exception e) {
/* 25 */       e.printStackTrace();
/* 26 */       return "";
/*    */     } 
/*    */     
/* 29 */     byte[] byteArray = (String.valueOf(devNo) + DateUtil.formatDate(new Date()).replace("-", "")).getBytes("UTF-8");
/* 30 */     byte[] md5Bytes = md5.digest(byteArray);
/* 31 */     StringBuffer hexValue = new StringBuffer();
/* 32 */     for (int i = 0; i < md5Bytes.length; i++) {
/* 33 */       int val = md5Bytes[i] & 0xFF;
/* 34 */       if (val < 16) {
/* 35 */         hexValue.append("0");
/*    */       }
/* 37 */       hexValue.append(Integer.toHexString(val));
/*    */     } 
/* 39 */     return hexValue.toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean IsExistTokenExpire(String devNo, String tranCode) throws Exception {
/* 50 */     MessageDigest md5 = null;
/*    */     try {
/* 52 */       md5 = MessageDigest.getInstance("MD5");
/* 53 */     } catch (Exception e) {
/*    */       
/* 55 */       e.printStackTrace();
/* 56 */       return true;
/*    */     } 
/*    */     
/* 59 */     byte[] byteArray = (String.valueOf(devNo) + DateUtil.formatDate(new Date()).replace("-", "")).getBytes("UTF-8");
/* 60 */     byte[] md5Bytes = md5.digest(byteArray);
/* 61 */     StringBuffer hexValue = new StringBuffer();
/* 62 */     for (int i = 0; i < md5Bytes.length; i++) {
/* 63 */       int val = md5Bytes[i] & 0xFF;
/* 64 */       if (val < 16) {
/* 65 */         hexValue.append("0");
/*    */       }
/* 67 */       hexValue.append(Integer.toHexString(val));
/*    */     } 
/* 69 */     return !hexValue.toString().equals(tranCode);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) throws Exception {
/* 78 */     String str = createToken("listen");
/* 79 */     System.out.println(str);
/* 80 */     String arr = "ed331e6fc132dd0b268cb2b1fe65cc94";
/* 81 */     boolean b = IsExistTokenExpire("sxjtyd", arr);
/* 82 */     System.out.println(b);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\TokenUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */