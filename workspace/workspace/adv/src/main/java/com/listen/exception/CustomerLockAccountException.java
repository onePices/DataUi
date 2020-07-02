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
/*    */ public class CustomerLockAccountException
/*    */   extends AccountException
/*    */ {
/*    */   public CustomerLockAccountException() {}
/*    */   
/* 22 */   public CustomerLockAccountException(String message) { super(message); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public CustomerLockAccountException(Throwable cause) { super(cause); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public CustomerLockAccountException(String message, Throwable cause) { super(message, cause); }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\exception\CustomerLockAccountException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */