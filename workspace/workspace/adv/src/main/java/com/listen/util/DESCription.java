/*     */ package com.listen.util;
/*     */ 
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.spec.InvalidKeySpecException;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.SecretKeyFactory;
/*     */ import javax.crypto.spec.DESKeySpec;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DESCription
/*     */ {
/*     */   private static final String DES_ALGORITHM = "DES";
/*     */   
/*     */   public static String encryption(String plainData, String secretKey) throws Exception {
/*  33 */     Cipher cipher = null;
/*     */     try {
/*  35 */       cipher = Cipher.getInstance("DES");
/*  36 */       cipher.init(1, generateKey(secretKey));
/*     */     }
/*  38 */     catch (NoSuchAlgorithmException e) {
/*  39 */       e.printStackTrace();
/*  40 */     } catch (NoSuchPaddingException e) {
/*  41 */       e.printStackTrace();
/*  42 */     } catch (InvalidKeyException invalidKeyException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  50 */       byte[] buf = cipher.doFinal(plainData.getBytes());
/*     */       
/*  52 */       return 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 146 */         Base64Utils.encode(buf);
/*     */     } catch (IllegalBlockSizeException e) {
/*     */       e.printStackTrace();
/*     */       throw new Exception("IllegalBlockSizeException", e);
/*     */     } catch (BadPaddingException e) {
/*     */       e.printStackTrace();
/*     */       throw new Exception("BadPaddingException", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String decryption(String secretData, String secretKey) throws Exception {
/*     */     Cipher cipher = null;
/*     */     try {
/*     */       cipher = Cipher.getInstance("DES");
/*     */       cipher.init(2, generateKey(secretKey));
/*     */     } catch (NoSuchAlgorithmException e) {
/*     */       e.printStackTrace();
/*     */       throw new Exception("NoSuchAlgorithmException", e);
/*     */     } catch (NoSuchPaddingException e) {
/*     */       e.printStackTrace();
/*     */       throw new Exception("NoSuchPaddingException", e);
/*     */     } catch (InvalidKeyException e) {
/*     */       e.printStackTrace();
/*     */       throw new Exception("InvalidKeyException", e);
/*     */     } 
/*     */     
/* 177 */     try { byte[] buf = cipher.doFinal(Base64Utils.decode(secretData.toCharArray())); return new String(buf); } catch (IllegalBlockSizeException e) { e.printStackTrace(); throw new Exception("IllegalBlockSizeException", e); } catch (BadPaddingException e) { e.printStackTrace(); throw new Exception("BadPaddingException", e); } 
/* 178 */   } private static SecretKey generateKey(String secretKey) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException { SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); DESKeySpec keySpec = new DESKeySpec(secretKey.getBytes()); keyFactory.generateSecret(keySpec); return keyFactory.generateSecret(keySpec); } private static class Base64Utils { private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray(); private static byte[] codes = new byte[256]; private static byte[] decode(char[] data) { int len = (data.length + 3) / 4 * 3;
/* 179 */       if (data.length > 0 && data[data.length - 1] == '=')
/* 180 */         len--; 
/* 181 */       if (data.length > 1 && data[data.length - 2] == '=')
/* 182 */         len--; 
/* 183 */       byte[] out = new byte[len];
/* 184 */       int shift = 0;
/* 185 */       int accum = 0;
/* 186 */       int index = 0;
/* 187 */       for (int ix = 0; ix < data.length; ix++) {
/* 188 */         int value = codes[data[ix] & 0xFF];
/* 189 */         if (value >= 0) {
/* 190 */           accum <<= 6;
/* 191 */           shift += 6;
/* 192 */           accum |= value;
/* 193 */           if (shift >= 8) {
/* 194 */             shift -= 8;
/* 195 */             out[index++] = (byte)(accum >> shift & 0xFF);
/*     */           } 
/*     */         } 
/*     */       } 
/* 199 */       if (index != out.length)
/* 200 */         throw new Error("miscalculated data length!"); 
/* 201 */       return out; }
/*     */     static  { for (int i = 0; i < 256; i++) codes[i] = -1;  for (int i = 65; i <= 90; i++)
/*     */         codes[i] = (byte)(i - 65);  for (int i = 97; i <= 122; i++)
/*     */         codes[i] = (byte)(26 + i - 97);  for (int i = 48; i <= 57; i++)
/*     */         codes[i] = (byte)(52 + i - 48);  codes[43] = 62; codes[47] = 63; } private static String encode(byte[] data) { char[] out = new char[(data.length + 2) / 3 * 4]; for (int i = 0, index = 0; i < data.length; i += 3, index += 4) { boolean quad = false; boolean trip = false; int val = 0xFF & data[i]; val <<= 8; if (i + 1 < data.length) { val |= 0xFF & data[i + 1]; trip = true; }  val <<= 8; if (i + 2 < data.length) { val |= 0xFF & data[i + 2]; quad = true; }  out[index + 3] = alphabet[quad ? (val & 0x3F) : 64]; val >>= 6; out[index + 2] = alphabet[trip ? (val & 0x3F) : 64]; val >>= 6; out[index + 1] = alphabet[val & 0x3F]; val >>= 6; out[index + 0] = alphabet[val & 0x3F]; }  return new String(out); } }
/* 206 */    public static void main(String[] args) throws Exception { String decrypt1 = decryption("5eZ5hD9gQ+8=", "D155969RHHTKG990");
/* 207 */     System.out.println("解密后：" + decrypt1); }
/*     */ 
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\DESCription.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */