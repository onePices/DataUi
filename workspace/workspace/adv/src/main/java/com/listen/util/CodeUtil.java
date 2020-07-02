/*     */ package com.listen.util;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CodeUtil
/*     */ {
/*  11 */   private static Random rand = new Random();
/*  12 */   private static char[] arr = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized String getCustCode() {
/*  18 */     String str = (new StringBuilder(String.valueOf(System.currentTimeMillis() / 10000000L))).toString();
/*  19 */     int ran = rand.nextInt(1000);
/*  20 */     String a = "C" + str + ran;
/*  21 */     if (a.length() == 10) {
/*  22 */       return a;
/*     */     }
/*  24 */     return getCustCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized String getDevCode() {
/*  33 */     String str = (new StringBuilder(String.valueOf(System.currentTimeMillis() / 10000000L))).toString();
/*  34 */     int ran = rand.nextInt(1000);
/*  35 */     String flag = "";
/*  36 */     for (int i = 0; i < 6; i++) {
/*  37 */       int index = rand.nextInt(26);
/*  38 */       flag = String.valueOf(flag) + arr[index];
/*     */     } 
/*  40 */     String a = "D" + str + flag + ran;
/*  41 */     if (a.length() == 16) {
/*  42 */       return a;
/*     */     }
/*  44 */     return getDevCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized String getLicenseCode() {
/*  53 */     String str = (new StringBuilder(String.valueOf(System.currentTimeMillis() / 10000000L))).toString();
/*  54 */     String flag = "";
/*  55 */     for (int i = 0; i < 12; i++) {
/*  56 */       int index = rand.nextInt(26);
/*  57 */       flag = String.valueOf(flag) + arr[index];
/*     */     } 
/*  59 */     int ran = rand.nextInt(10000);
/*  60 */     String a = String.valueOf(str) + flag + ran;
/*  61 */     if (a.length() == 22) {
/*  62 */       return a;
/*     */     }
/*  64 */     return getLicenseCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized String getTranCode() {
/*  72 */     String str = (new StringBuilder(String.valueOf(System.currentTimeMillis() / 10000000L))).toString();
/*  73 */     String flag = "";
/*  74 */     for (int i = 0; i < 12; i++) {
/*  75 */       int index = rand.nextInt(26);
/*  76 */       flag = String.valueOf(flag) + arr[index];
/*     */     } 
/*  78 */     int ran = rand.nextInt(10000);
/*  79 */     String a = String.valueOf(str) + ran + flag;
/*  80 */     if (a.length() == 22) {
/*  81 */       return a;
/*     */     }
/*  83 */     return getTranCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized String getSixCode() {
/*  93 */     String str = (new StringBuilder(String.valueOf(System.currentTimeMillis() / 100L))).toString();
/*  94 */     str = str.substring(str.length() - 5, str.length() - 1);
/*  95 */     int ran = rand.nextInt(100);
/*  96 */     String a = String.valueOf(str) + ran;
/*  97 */     if (a.length() == 6) {
/*  98 */       return a;
/*     */     }
/* 100 */     return getSixCode();
/*     */   }
/*     */   
/*     */   public static void main(String[] args) throws ParseException {}
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\CodeUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */