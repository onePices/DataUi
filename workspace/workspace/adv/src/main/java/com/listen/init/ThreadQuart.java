/*    */ package com.listen.init;
/*    */ 
/*    */ import com.listen.util.FileUtil;
///*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class ThreadQuart
/*    */ {
///* 15 */   private Logger log = Logger.getLogger(ThreadQuart.class);
/*    */   public void execute() {
/*    */     try {
/* 18 */       FileUtil.cleanLog();
/* 19 */       FileUtil.cleanTaskZip();
/* 20 */       FileUtil.cleanMapImage();
/* 21 */     } catch (Exception e) {
/* 22 */       e.printStackTrace();
///* 23 */       this.log.error("删除日志异常", e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\init\ThreadQuart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */