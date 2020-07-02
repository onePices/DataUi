/*    */ package com.listen.thread;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThreadContext
/*    */ {
/* 13 */   private List<CardInfoThread> cardInfoThread = new ArrayList<>();
/*    */ 
/*    */   
/* 16 */   public List<CardInfoThread> getCardInfoThread() { return this.cardInfoThread; }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public void setCardInfoThread(CardInfoThread cIT) { this.cardInfoThread.add(cIT); }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\thread\ThreadContext.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */