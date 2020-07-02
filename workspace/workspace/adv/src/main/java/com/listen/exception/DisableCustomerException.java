/*    */ package com.listen.exception;
/*    */ 
/*    */ import org.apache.shiro.authc.AccountException;
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
/*    */ public class DisableCustomerException
/*    */   extends AccountException
/*    */ {
/*    */   public DisableCustomerException() {}
/*    */   
/* 22 */   public DisableCustomerException(String message) { super(message); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public DisableCustomerException(Throwable cause) { super(cause); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public DisableCustomerException(String message, Throwable cause) { super(message, cause); }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\exception\DisableCustomerException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */