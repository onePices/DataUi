/*    */ package com.listen.model;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FileBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -8933331496600261099L;
/*    */   private String filePath;
/*    */   private boolean dirFlag;
/*    */   private boolean isServer;
/*    */   
/* 29 */   public String getFilePath() { return this.filePath; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public void setFilePath(String filePath) { this.filePath = filePath; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public boolean isDirFlag() { return this.dirFlag; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setDirFlag(boolean dirFlag) { this.dirFlag = dirFlag; }
/*    */ 
/*    */ 
/*    */   
/*    */   public FileBean() {}
/*    */ 
/*    */   
/*    */   public FileBean(String filePath, boolean dirFlag, boolean sources) {
/* 49 */     this.dirFlag = dirFlag;
/* 50 */     this.filePath = filePath;
/* 51 */     this.isServer = sources;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 55 */     int prime = 31;
/* 56 */     int result = 1;
/* 57 */     result = 31 * result + ((this.filePath == null) ? 0 : this.filePath.hashCode());
/* 58 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 64 */     if (this == obj) {
/* 65 */       return true;
/*    */     }
/* 67 */     if (obj == null) {
/* 68 */       return false;
/*    */     }
/* 70 */     if (getClass() != obj.getClass()) {
/* 71 */       return false;
/*    */     }
/* 73 */     FileBean other = (FileBean)obj;
/* 74 */     if (this.filePath == null) {
/* 75 */       if (other.filePath != null)
/* 76 */         return false; 
/* 77 */     } else if (!this.filePath.equals(other.filePath)) {
/* 78 */       return false;
/* 79 */     }  return true;
/*    */   }
/*    */   
/* 82 */   public boolean isServer() { return this.isServer; }
/*    */ 
/*    */ 
/*    */   
/* 86 */   public void setServer(boolean isServer) { this.isServer = isServer; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\FileBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */