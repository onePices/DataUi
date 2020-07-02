/*    */ package com.listen.util;
/*    */ 
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ToolsUtil
/*    */ {
/*    */   public static boolean isChinese(char c) {
/* 13 */     Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
/* 14 */     if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || 
/* 15 */       ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || 
/* 16 */       ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || 
/* 17 */       ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
/* 18 */       return true;
/*    */     }
/* 20 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isChinese(String strName) {
/* 25 */     char[] ch = strName.toCharArray();
/* 26 */     for (int i = 0; i < ch.length; i++) {
/* 27 */       char c = ch[i];
/* 28 */       if (isChinese(c)) {
/* 29 */         return true;
/*    */       }
/*    */     } 
/* 32 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isChineseByREG(String str) {
/* 37 */     if (str == null) {
/* 38 */       return false;
/*    */     }
/* 40 */     Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
/* 41 */     return pattern.matcher(str.trim()).find();
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isChineseByName(String str) {
/* 46 */     if (str == null) {
/* 47 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 51 */     String reg = "\\p{InCJK Unified Ideographs}&&\\P{Cn}";
/* 52 */     Pattern pattern = Pattern.compile(reg);
/* 53 */     return pattern.matcher(str.trim()).find();
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\ToolsUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */