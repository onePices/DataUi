/*    */ package com.listen.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
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
/*    */ public class TextImageModel
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String domainId;
/*    */   private List<String> imageList;
/*    */   
/* 27 */   public String getDomainId() { return this.domainId; }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public void setDomainId(String domainId) { this.domainId = domainId; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public List<String> getImageList() { return this.imageList; }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public void setImageList(List<String> imageList) { this.imageList = imageList; }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\TextImageModel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */