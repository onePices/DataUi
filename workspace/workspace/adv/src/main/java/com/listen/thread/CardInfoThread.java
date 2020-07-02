/*    */ package com.listen.thread;
/*    */ 
/*    */ import com.listen.service.MapCardService;
/*    */ import com.listen.util.ConfigUtil;
/*    */ import com.listen.util.ThreadUtil;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CardInfoThread
/*    */   extends ThreadUtil
/*    */ {
/*    */   private MapCardService mapCardService;
/* 16 */   private Logger log = Logger.getLogger(CardInfoThread.class);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doWork() {
/*    */     try {
/* 23 */       String defaultQuery = ConfigUtil.getProperties("default.storagePosition");
/* 24 */       if (defaultQuery.equals("1")) {
/* 25 */         this.mapCardService.saveMapImageInfo();
/* 26 */       } else if (defaultQuery.equals("2")) {
/* 27 */         this.mapCardService.saveNewMapImageInfo();
/*    */       } 
/* 29 */       sleep(getWaitTime());
/* 30 */     } catch (Exception e) {
/* 31 */       this.log.error("信息更新线程异常", e);
/* 32 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 37 */   public MapCardService getMapCardService() { return this.mapCardService; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setMapCardService(MapCardService mapCardService) { this.mapCardService = mapCardService; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\thread\CardInfoThread.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */