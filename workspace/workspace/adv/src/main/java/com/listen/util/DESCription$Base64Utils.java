/*     */ package com.listen.util;
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
/*     */ class Base64Utils
/*     */ {
/* 126 */   private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
/* 127 */     .toCharArray();
/* 128 */   private static byte[] codes = new byte[256];
/*     */   
/*     */   static  {
/* 131 */     for (int i = 0; i < 256; i++)
/* 132 */       codes[i] = -1; 
/* 133 */     for (int i = 65; i <= 90; i++)
/* 134 */       codes[i] = (byte)(i - 65); 
/* 135 */     for (int i = 97; i <= 122; i++)
/* 136 */       codes[i] = (byte)(26 + i - 97); 
/* 137 */     for (int i = 48; i <= 57; i++)
/* 138 */       codes[i] = (byte)(52 + i - 48); 
/* 139 */     codes[43] = 62;
/* 140 */     codes[47] = 63;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String encode(byte[] data) {
/* 147 */     char[] out = new char[(data.length + 2) / 3 * 4];
/* 148 */     for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
/* 149 */       boolean quad = false;
/* 150 */       boolean trip = false;
/* 151 */       int val = 0xFF & data[i];
/* 152 */       val <<= 8;
/* 153 */       if (i + 1 < data.length) {
/* 154 */         val |= 0xFF & data[i + 1];
/* 155 */         trip = true;
/*     */       } 
/* 157 */       val <<= 8;
/* 158 */       if (i + 2 < data.length) {
/* 159 */         val |= 0xFF & data[i + 2];
/* 160 */         quad = true;
/*     */       } 
/* 162 */       out[index + 3] = alphabet[quad ? (val & 0x3F) : 64];
/* 163 */       val >>= 6;
/* 164 */       out[index + 2] = alphabet[trip ? (val & 0x3F) : 64];
/* 165 */       val >>= 6;
/* 166 */       out[index + 1] = alphabet[val & 0x3F];
/* 167 */       val >>= 6;
/* 168 */       out[index + 0] = alphabet[val & 0x3F];
/*     */     } 
/*     */     
/* 171 */     return new String(out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] decode(char[] data) {
/* 178 */     int len = (data.length + 3) / 4 * 3;
/* 179 */     if (data.length > 0 && data[data.length - 1] == '=')
/* 180 */       len--; 
/* 181 */     if (data.length > 1 && data[data.length - 2] == '=')
/* 182 */       len--; 
/* 183 */     byte[] out = new byte[len];
/* 184 */     int shift = 0;
/* 185 */     int accum = 0;
/* 186 */     int index = 0;
/* 187 */     for (int ix = 0; ix < data.length; ix++) {
/* 188 */       int value = codes[data[ix] & 0xFF];
/* 189 */       if (value >= 0) {
/* 190 */         accum <<= 6;
/* 191 */         shift += 6;
/* 192 */         accum |= value;
/* 193 */         if (shift >= 8) {
/* 194 */           shift -= 8;
/* 195 */           out[index++] = (byte)(accum >> shift & 0xFF);
/*     */         } 
/*     */       } 
/*     */     } 
/* 199 */     if (index != out.length)
/* 200 */       throw new Error("miscalculated data length!"); 
/* 201 */     return out;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\DESCription$Base64Utils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */