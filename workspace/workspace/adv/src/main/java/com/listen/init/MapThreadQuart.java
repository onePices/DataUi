/*    */ package com.listen.init;
/*    */ 
/*    */ import com.listen.service.MapCardService;
/*    */ import com.listen.thread.CardInfoThread;
/*    */ import com.listen.thread.ThreadContext;
/*    */ import com.listen.util.ConfigUtil;
/*    */ import javax.annotation.Resource;
///*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.stereotype.Component;
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
/*    */ @Component
/*    */ public class MapThreadQuart
/*    */   extends ThreadContext
/*    */ {
/*    */   @Resource
/*    */   private MapCardService mapCardService;
///* 26 */   private Logger log = Logger.getLogger(MapThreadQuart.class);
/*    */   
/*    */   public void execute() {
/*    */     try {
/* 30 */       if (!checkThreadIsAlive()) {
/* 31 */         CardInfoThread c = new CardInfoThread();
/* 32 */         String waitTime = ConfigUtil.getProperties("default.getPicture.timeOut");
/* 33 */         if (waitTime != null) {
/* 34 */           c.setWaitTime((Integer.valueOf(waitTime).intValue() * 1000));
/*    */         } else {
/*    */           
/* 37 */           c.setWaitTime(10000L);
/*    */         } 
/* 39 */         c.setMapCardService(this.mapCardService);
/* 40 */         c.start();
/* 41 */         setCardInfoThread(c);
/*    */       } 
/* 43 */     } catch (Exception e) {
/* 44 */       e.printStackTrace();
///* 45 */       this.log.error("守护线程 异常", e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkThreadIsAlive() {
/* 51 */     boolean flag = true;
/* 52 */     if (getCardInfoThread().size() > 0) {
/* 53 */       for (int i = 0; i < getCardInfoThread().size(); i++) {
/* 54 */         if (!((CardInfoThread)getCardInfoThread().get(i)).isAlive()) {
/* 55 */           flag = false;
/*    */         }
/*    */       } 
/*    */     }
/* 59 */     if (!flag) {
/* 60 */       getCardInfoThread().clear();
/*    */     }
/* 62 */     return flag;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\init\MapThreadQuart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */