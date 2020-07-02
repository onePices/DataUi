/*    */ package com.listen.util;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.apache.shiro.crypto.hash.Md5Hash;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MD5
/*    */ {
/*    */   public static String getMd5Hash(String password, String salt) {
/* 11 */     Md5Hash md5Hash = new Md5Hash(password, salt, 2);
/* 12 */     return md5Hash.toString();
/*    */   }
/*    */   
/*    */   public static String getSalt() {
/* 16 */     Random r = new Random();
/* 17 */     StringBuilder sb = new StringBuilder(16);
/* 18 */     sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
/* 19 */     int len = sb.length();
/* 20 */     if (len < 16) {
/* 21 */       for (int i = 0; i < 16 - len; i++) {
/* 22 */         sb.append("0");
/*    */       }
/*    */     }
/* 25 */     String salt = sb.toString();
/* 26 */     return salt;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {}
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\MD5.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */