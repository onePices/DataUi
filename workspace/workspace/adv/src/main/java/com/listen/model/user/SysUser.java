/*    */ package com.listen.model.user;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SysUser
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer userId;
/*    */   private String userName;
/*    */   private String password;
/*    */   private String userSalt;
/*    */   
/* 17 */   public Integer getUserId() { return this.userId; }
/*    */ 
/*    */   
/* 20 */   public void setUserId(Integer userId) { this.userId = userId; }
/*    */ 
/*    */   
/* 23 */   public String getUserName() { return this.userName; }
/*    */ 
/*    */   
/* 26 */   public void setUserName(String userName) { this.userName = userName; }
/*    */ 
/*    */   
/* 29 */   public String getPassword() { return this.password; }
/*    */ 
/*    */   
/* 32 */   public void setPassword(String password) { this.password = password; }
/*    */ 
/*    */   
/* 35 */   public String getUserSalt() { return this.userSalt; }
/*    */ 
/*    */   
/* 38 */   public void setUserSalt(String userSalt) { this.userSalt = userSalt; }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     return "SysUser [userId=" + this.userId + ", userName=" + this.userName + 
/* 43 */       ", password=" + this.password + ", userSalt=" + this.userSalt + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\mode\\user\SysUser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */