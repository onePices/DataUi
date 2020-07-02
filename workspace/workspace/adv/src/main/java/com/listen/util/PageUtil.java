/*    */ package com.listen.util;
/*    */ 
/*    */ import com.github.pagehelper.Page;
/*    */ import com.listen.model.PageResult;
/*    */ import java.util.List;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PageUtil
/*    */ {
/*    */   public static PageResult toPagedResult(List datas) {
/* 16 */     PageResult result = new PageResult();
/* 17 */     if (datas instanceof PageResult) {
/* 18 */       Page page = (Page)datas;
/* 19 */       result.setNowPage(Integer.valueOf(page.getPageNum()));
/* 20 */       result.setPageSize(Integer.valueOf(page.getPageSize()));
/* 21 */       result.setRows(page.getResult());
/* 22 */       result.setRecords(page.getTotal());
/* 23 */       result.setTotal(Integer.valueOf(page.getPages()));
/*    */     } else {
/* 25 */       Page page = (Page)datas;
/* 26 */       result.setNowPage(Integer.valueOf(page.getPageNum()));
/* 27 */       result.setPageSize(Integer.valueOf(page.getPageSize()));
/* 28 */       result.setRows(page.getResult());
/* 29 */       result.setRecords(page.getTotal());
/* 30 */       result.setTotal(Integer.valueOf(page.getPages()));
/*    */     } 
/*    */     
/* 33 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String transformPropertyToColumn(String property) {
/* 44 */     if (property != null && !property.trim().isEmpty()) {
/* 45 */       Pattern p = Pattern.compile("[A-Z]");
/* 46 */       Matcher m = p.matcher(property);
/* 47 */       while (m.find()) {
/* 48 */         String a = m.group();
/* 49 */         property = property.replaceAll(a, "_" + a.toLowerCase());
/* 50 */         m = p.matcher(property);
/*    */       } 
/* 52 */       return property.toUpperCase();
/*    */     } 
/* 54 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\PageUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */