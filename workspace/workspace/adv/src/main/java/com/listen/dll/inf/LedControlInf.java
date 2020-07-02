/*    */ package com.listen.dll.inf;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.win32.StdCallLibrary;
/*    */ import java.util.Vector;
/*    */ 
/*    */ 
/*    */ public interface LedControlInf
/*    */   extends StdCallLibrary
/*    */ {
/* 11 */   public static final Class c = LedControlInf.class;
/*    */ 
/*    */   
/* 14 */   public static final String strDllPath = String.valueOf(c.getClassLoader().getResource("").getPath().substring(1).replace("/", "\\")) + "dll\\DllEx_0116";
/*    */   
/* 16 */   public static final LedControlInf INSTANCE = (LedControlInf)Native.loadLibrary(strDllPath, c);
/*    */   
/*    */   int RtfConvertBmp2_1(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7);
/*    */   
/*    */   int RtfConvertBmp3(String paramString, int paramInt1, int paramInt2, int paramInt3, Vector<String> paramVector, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8);
/*    */   
/*    */   int RtfConvertBmp3_1(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8);
/*    */   
/*    */   int AdjustTime(String paramString);
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\dll\inf\LedControlInf.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */