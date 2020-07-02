/*    */ package com.listen.util;
/*    */ 
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ThreadUtil
/*    */   extends Thread
/*    */ {
/* 11 */   private long waitTime = 1000L;
/*    */   
/* 13 */   private Logger log = Logger.getLogger(ThreadUtil.class);
/*    */   
/*    */   private boolean stop = true;
/*    */   
/*    */   public void run() {
/*    */     try {
/* 19 */       while (this.stop) {
/* 20 */         doWork();
/*    */       }
/* 22 */     } catch (Exception e) {
/* 23 */       e.printStackTrace();
/* 24 */       this.log.error("多线程运行异常", e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract void doWork();
/*    */   
/* 31 */   public long getWaitTime() { return this.waitTime; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public void setWaitTime(long waitTime) { this.waitTime = waitTime; }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public boolean isStop() { return this.stop; }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public void setStop(boolean stop) { this.stop = stop; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\ThreadUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */