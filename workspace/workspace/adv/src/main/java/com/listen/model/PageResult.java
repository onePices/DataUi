/*     */ package com.listen.model;
/*     */ 
/*     */ import com.github.pagehelper.PageInfo;
/*     */ import com.listen.util.PageUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PageResult<T>
/*     */ {
/*     */   private Integer nowPage;
/*     */   private Integer pageSize;
/*     */   private Integer startRow;
/*     */   private Integer endRow;
/*     */   private long records;
/*     */   private Integer page;
/*     */   private Integer total;
/*     */   private String orderColumn;
/*     */   private String orderTurn;
/*  47 */   private List<T> rows = new ArrayList<>();
/*     */   
/*     */   public PageResult() {
/*  50 */     this.nowPage = Integer.valueOf(1);
/*  51 */     this.pageSize = Integer.valueOf(10);
/*  52 */     this.orderTurn = "ASC";
/*     */   }
/*     */ 
/*     */   
/*     */   public PageResult(Integer nowPage, Integer pageSize, String orderColumn) {
/*  57 */     this.nowPage = nowPage;
/*  58 */     this.pageSize = pageSize;
/*  59 */     this.startRow = Integer.valueOf((nowPage.intValue() > 0) ? ((nowPage.intValue() - 1) * pageSize.intValue()) : 0);
/*  60 */     this.endRow = Integer.valueOf(nowPage.intValue() * pageSize.intValue());
/*  61 */     this.orderColumn = PageUtil.transformPropertyToColumn(orderColumn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PageResult(Integer nowPage, Integer pageSize, String orderColumn, String orderTurn) {
/*  67 */     this.nowPage = nowPage;
/*  68 */     this.pageSize = pageSize;
/*  69 */     this.startRow = Integer.valueOf((nowPage.intValue() > 0) ? ((nowPage.intValue() - 1) * pageSize.intValue()) : 0);
/*  70 */     this.endRow = Integer.valueOf(nowPage.intValue() * pageSize.intValue());
/*  71 */     this.orderColumn = PageUtil.transformPropertyToColumn(orderColumn);
/*  72 */     this.orderTurn = orderTurn;
/*     */   }
/*     */ 
/*     */   
/*  76 */   public Integer getNowPage() { return this.nowPage; }
/*     */ 
/*     */ 
/*     */   
/*  80 */   public void setNowPage(Integer nowPage) { this.nowPage = nowPage; }
/*     */ 
/*     */ 
/*     */   
/*  84 */   public Integer getPageSize() { return this.pageSize; }
/*     */ 
/*     */ 
/*     */   
/*  88 */   public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public String getOrderColumn() { return this.orderColumn; }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public void setOrderColumn(String orderColumn) { this.orderColumn = orderColumn; }
/*     */ 
/*     */ 
/*     */   
/* 100 */   public String getOrderTurn() { return this.orderTurn; }
/*     */ 
/*     */ 
/*     */   
/* 104 */   public void setOrderTurn(String orderTurn) { this.orderTurn = orderTurn; }
/*     */ 
/*     */ 
/*     */   
/* 108 */   public long getRecords() { return this.records; }
/*     */ 
/*     */ 
/*     */   
/* 112 */   public void setRecords(long l) { this.records = l; }
/*     */ 
/*     */ 
/*     */   
/* 116 */   public Integer getTotal() { return this.total; }
/*     */ 
/*     */ 
/*     */   
/* 120 */   public void setTotal(Integer total) { this.total = total; }
/*     */ 
/*     */ 
/*     */   
/* 124 */   public Integer getStartRow() { return this.startRow; }
/*     */ 
/*     */ 
/*     */   
/* 128 */   public void setStartRow(Integer startRow) { this.startRow = startRow; }
/*     */ 
/*     */ 
/*     */   
/* 132 */   public Integer getEndRow() { return this.endRow; }
/*     */ 
/*     */ 
/*     */   
/* 136 */   public void setEndRow(Integer endRow) { this.endRow = endRow; }
/*     */ 
/*     */ 
/*     */   
/* 140 */   public List<T> getRows() { return this.rows; }
/*     */ 
/*     */ 
/*     */   
/* 144 */   public void setRows(List<T> rows) { this.rows = rows; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 149 */   public Integer getPage() { return this.page; }
/*     */ 
/*     */ 
/*     */   
/* 153 */   public void setPage(Integer page) { this.page = page; }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 158 */     return "Page [nowPage=" + this.nowPage + ", pageSize=" + this.pageSize + ", startRow=" + this.startRow + ", endRow=" + this.endRow + 
/* 159 */       ", records=" + this.records + ", total=" + this.total + ", orderColumn=" + this.orderColumn + ", orderTurn=" + 
/* 160 */       this.orderTurn + ", rows=" + this.rows + "]";
/*     */   }
/*     */ 
/*     */   
/*     */   public PageResult(PageInfo<T> info) {
/* 165 */     if (info.getTotal() > 0L) {
/* 166 */       this.rows = info.getList();
/* 167 */       this.total = Integer.valueOf((int)info.getTotal());
/* 168 */       this.pageSize = Integer.valueOf(info.getPageSize());
/* 169 */       this.startRow = Integer.valueOf(info.getStartRow());
/* 170 */       this.nowPage = Integer.valueOf(info.getPageNum());
/* 171 */       this.page = Integer.valueOf(info.getPages());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\model\PageResult.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */