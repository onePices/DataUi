/*    */ package com.listen.init;
/*    */ 
/*    */ import com.listen.service.MapCardService;
/*    */ import com.listen.thread.CardInfoThread;
/*    */ import com.listen.thread.ThreadContext;
/*    */ import com.listen.util.ConfigUtil;
/*    */ import javax.annotation.PostConstruct;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class CardInfoController
/*    */   extends ThreadContext
/*    */ {
/*    */   @Resource
/*    */   private MapCardService mapCardService;
/*    */   
/*    */   @PostConstruct
/*    */   public void init() {
/* 25 */     CardInfoThread c = new CardInfoThread();
/* 26 */     String waitTime = ConfigUtil.getProperties("default.getPicture.timeOut");
/* 27 */     if (waitTime != null) {
/* 28 */       c.setWaitTime((Integer.valueOf(waitTime).intValue() * 1000));
/*    */     } else {
/*    */       
/* 31 */       c.setWaitTime(10000L);
/*    */     } 
/* 33 */     c.setMapCardService(this.mapCardService);
/* 34 */     c.start();
/* 35 */     setCardInfoThread(c);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\init\CardInfoController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */