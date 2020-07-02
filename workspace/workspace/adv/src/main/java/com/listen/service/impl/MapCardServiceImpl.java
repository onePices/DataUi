/*     */ package com.listen.service.impl;
/*     */ 
/*     */ import com.fasterxml.jackson.databind.ObjectMapper;
/*     */ import com.listen.dao.SysDeviceMapDao;
/*     */ import com.listen.dao.SysDeviceMapLogDao;
/*     */ import com.listen.model.ResultBean;
/*     */ import com.listen.model.TmlDeviceMapImage;
/*     */ import com.listen.model.sdkservice.SysDeviceMap;
/*     */ import com.listen.model.sdkservice.SysDeviceMapLog;
/*     */ import com.listen.redis.RedisUtil;
/*     */ import com.listen.service.MapCardService;
/*     */ import com.listen.util.ConfigUtil;
/*     */ import com.listen.util.DateUtil;
/*     */ import com.listen.util.FileUtil;
/*     */ import com.listen.util.StringUtil;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.interceptor.TransactionAspectSupport;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ public class MapCardServiceImpl
/*     */   implements MapCardService
/*     */ {
/*  34 */   private static Logger log = Logger.getLogger(CardServiceImpl.class);
/*     */   
/*  36 */   private static ObjectMapper mapper = new ObjectMapper();
/*     */ 
/*     */ 
/*     */   
/*     */   @Resource
/*     */   private SysDeviceMapDao sysDeviceMapMapper;
/*     */ 
/*     */ 
/*     */   
/*     */   @Resource
/*     */   private SysDeviceMapLogDao sysDeviceMapLogMapper;
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPicturePath(String devCode, HttpServletRequest request) {
/*  51 */     ResultBean rb = new ResultBean();
/*  52 */     String result = "";
/*     */     try {
///*  54 */       if (StringUtil.isEmpty(devCode)) {
///*  55 */         rb.setFlag(Integer.valueOf(0));
///*  56 */         rb.setMsg("请求参数devCode为空");
///*     */       } else {
///*     */         
///*  59 */         String defaultQuery = ConfigUtil.getProperties("default.storagePosition");
///*  60 */         String key = "MapImage" + devCode;
///*  61 */         if (defaultQuery.equals("1")) {
///*  62 */           String imageUrl = RedisUtil.getValue(key);
///*  63 */           rb.setValue(imageUrl);
///*  64 */         } else if (defaultQuery.equals("2")) {
///*  65 */           SysDeviceMap sysDeviceMap = this.sysDeviceMapMapper.getMapByDevCode(key);
///*  66 */           if (sysDeviceMap != null) {
///*  67 */             rb.setValue(sysDeviceMap.getImageUrl());
///*     */           } else {
///*  69 */             rb.setValue(null);
///*     */           } 
///*     */         } 
///*  72 */         rb.setFlag(Integer.valueOf(1));
///*  73 */         rb.setMsg("请求成功");
///*  74 */         String jsonpCallback = request.getParameter("jsonpCallback");
///*  75 */         result = String.valueOf(jsonpCallback) + "(" + mapper.writeValueAsString(rb) + ");";
///*  76 */         result = URLDecoder.decode(result, "utf-8");
///*     */       } 
/*  78 */     } catch (Exception e) {
/*  79 */       log.error("获取图片：", e);
/*  80 */       e.printStackTrace();
/*  81 */       rb.setFlag(Integer.valueOf(0));
/*  82 */       rb.setMsg("获取图片失败");
/*     */     } 
/*  84 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveMapImageInfo() {
/*     */     try {
/*  90 */       Map<String, String> map = FileUtil.getImageUrl();
/*  91 */       Set<Map.Entry<String, String>> entry = map.entrySet();
/*  92 */       TmlDeviceMapImage devMap = null;
///*  93 */       Set<String> set = RedisUtil.getAllInfo("mapDevList");
///*  94 */       if (set != null && set.size() > 0) {
///*  95 */         for (String content : set) {
///*  96 */           devMap = (TmlDeviceMapImage)mapper.readValue(content, TmlDeviceMapImage.class);
///*  97 */           if (entry != null && entry.size() > 0) {
///*  98 */             for (Map.Entry<String, String> e : entry) {
///*  99 */               if (((String)e.getKey()).equals(devMap.getImageName())) {
///* 100 */                 devMap.setImageUrl(e.getValue());
///* 101 */                 devMap.setUpdateBy(e.getKey());
///* 102 */                 devMap.setUpdateDate(DateUtil.getNowTimeStamp().longValue());
///* 103 */                 RedisUtil.addValue("MapImage" + devMap.getDevCode(), devMap.getImageUrl());
///*     */                 break;
///*     */               } 
///*     */             } 
///* 107 */             RedisUtil.addInfo("mapDevList", mapper.writeValueAsString(devMap));
///* 108 */             RedisUtil.delInfo("mapDevList", content);
///*     */           } 
///*     */         } 
///*     */       }
/* 112 */     } catch (Exception e) {
/* 113 */       log.error("获取图片：", e);
/* 114 */       e.printStackTrace();
/* 115 */       TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveNewMapImageInfo() {
/*     */     try {
/* 126 */       Map<String, String> map = FileUtil.getImageUrl();
/* 127 */       Set<Map.Entry<String, String>> entry = map.entrySet();
/* 128 */       List<SysDeviceMapLog> loglist = this.sysDeviceMapLogMapper.getDevMapLogList(null);
/* 129 */       if (loglist.size() > 0) {
/* 130 */         for (SysDeviceMapLog log : loglist) {
/* 131 */           if (entry != null && entry.size() > 0) {
/* 132 */             for (Map.Entry<String, String> e : entry) {
/* 133 */               if (((String)e.getKey()).equals(log.getImageName())) {
/* 134 */                 log.setImageUrl(e.getValue());
/* 135 */                 log.setUpdateBy(e.getKey());
/* 136 */                 log.setUpdateDate(DateUtil.getNowTimeStamp());
/* 137 */                 String devCodeName = "MapImage" + log.getDevCode();
/* 138 */                 String imageUrl = log.getImageUrl();
/* 139 */                 SysDeviceMap sysDeviceMap = this.sysDeviceMapMapper.getMapByDevCode(devCodeName);
/* 140 */                 if (sysDeviceMap == null) {
/* 141 */                   this.sysDeviceMapMapper.insertDeviceMap(devCodeName, imageUrl); break;
/*     */                 } 
/* 143 */                 this.sysDeviceMapMapper.updateDeviceMap(devCodeName, imageUrl);
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */             
/* 149 */             this.sysDeviceMapLogMapper.deleteByPrimaryKey(log.getDevMapId());
/* 150 */             log.setDevMapId(null);
/* 151 */             this.sysDeviceMapLogMapper.insert(log);
/*     */           } 
/*     */         } 
/*     */       }
/* 155 */     } catch (Exception e) {
/* 156 */       MapCardServiceImpl.log.error("获取图片：", e);
/* 157 */       e.printStackTrace();
/* 158 */       TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\service\impl\MapCardServiceImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */