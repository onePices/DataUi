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
/*    */ public class UserAccountUnauditException
/*    */   extends AccountException
/*    */ {
/*    */   public UserAccountUnauditException() {}
/*    */   
/* 22 */   public UserAccountUnauditException(String message) { super(message); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public UserAccountUnauditException(Throwable cause) { super(cause); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public UserAccountUnauditException(String message, Throwable cause) { super(message, cause); }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\exception\UserAccountUnauditException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */