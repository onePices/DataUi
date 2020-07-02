/*     */ package com.listen.util;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.NameValuePair;
/*     */ import org.apache.http.client.config.RequestConfig;
/*     */ import org.apache.http.client.entity.UrlEncodedFormEntity;
/*     */ import org.apache.http.client.methods.CloseableHttpResponse;
/*     */ import org.apache.http.client.methods.HttpGet;
/*     */ import org.apache.http.client.methods.HttpPost;
/*     */ import org.apache.http.client.methods.HttpUriRequest;
/*     */ import org.apache.http.entity.StringEntity;
/*     */ import org.apache.http.impl.client.CloseableHttpClient;
/*     */ import org.apache.http.impl.client.HttpClients;
		  import org.apache.http.message.BasicNameValuePair;
/*     */ import org.apache.http.util.EntityUtils;
/*     */ 
/*     */ public class HttpClientUtil {
/*  20 */   private RequestConfig requestConfig = RequestConfig.custom()
/*  21 */     .setSocketTimeout(15000)
/*  22 */     .setConnectTimeout(15000)
/*  23 */     .setConnectionRequestTimeout(15000)
/*  24 */     .build();
/*     */   
/*  26 */   private static HttpClientUtil instance = null;
/*     */   
/*     */   public static HttpClientUtil getInstance() {
/*  29 */     if (instance == null) {
/*  30 */       instance = new HttpClientUtil();
/*     */     }
/*  32 */     return instance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String sendHttpPost(String httpUrl, String params) {
/*  41 */     HttpPost httpPost = new HttpPost(httpUrl);
/*     */     
/*     */     try {
/*  44 */       StringEntity stringEntity = new StringEntity(params, "UTF-8");
/*  45 */       stringEntity.setContentType("application/x-www-form-urlencoded");
/*  46 */       httpPost.setEntity((HttpEntity)stringEntity);
/*  47 */     } catch (Exception e) {
/*  48 */       e.printStackTrace();
/*     */     } 
/*  50 */     return sendHttpPost(httpPost);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String sendHttpPost(String httpUrl, Map<String, String> maps) {
/*  59 */     HttpPost httpPost = new HttpPost(httpUrl);
/*     */     
/*  61 */     List<NameValuePair> nameValuePairs = new ArrayList<>();
/*  62 */     for (String key : maps.keySet()) {
/*  63 */       nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
/*     */     }
/*     */     try {
/*  66 */       httpPost.setEntity((HttpEntity)new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
/*  67 */     } catch (Exception e) {
/*  68 */       e.printStackTrace();
/*     */     } 
/*  70 */     return sendHttpPost(httpPost);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String sendHttpPost(HttpPost httpPost) {
/*  79 */     CloseableHttpClient httpClient = null;
/*  80 */     CloseableHttpResponse response = null;
/*  81 */     HttpEntity entity = null;
/*  82 */     String responseContent = null;
/*     */     
/*     */     try {
/*  85 */       httpClient = HttpClients.createDefault();
/*  86 */       httpPost.setConfig(this.requestConfig);
/*     */       
/*  88 */       response = httpClient.execute((HttpUriRequest)httpPost);
/*  89 */       entity = response.getEntity();
/*  90 */       responseContent = EntityUtils.toString(entity, "UTF-8");
/*  91 */     } catch (Exception e) {
/*  92 */       e.printStackTrace();
/*     */     } finally {
/*     */       
/*     */       try {
/*  96 */         if (response != null) {
/*  97 */           response.close();
/*     */         }
/*  99 */         if (httpClient != null) {
/* 100 */           httpClient.close();
/*     */         }
/* 102 */       } catch (IOException e) {
/* 103 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 106 */     return responseContent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String sendHttpGet(String httpUrl) {
/* 114 */     HttpGet httpGet = new HttpGet(httpUrl);
/* 115 */     return sendHttpGet(httpGet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String sendHttpsGet(String httpUrl, String params) {
/* 123 */     HttpGet httpGet = new HttpGet(httpUrl);
/* 124 */     httpGet.setHeader("", "");
/* 125 */     return sendHttpGet(httpGet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String sendHttpGet(HttpGet httpGet) {
/* 134 */     CloseableHttpClient httpClient = null;
/* 135 */     CloseableHttpResponse response = null;
/* 136 */     HttpEntity entity = null;
/* 137 */     String responseContent = null;
/*     */     
/*     */     try {
/* 140 */       httpClient = HttpClients.createDefault();
/* 141 */       httpGet.setConfig(this.requestConfig);
/*     */       
/* 143 */       response = httpClient.execute((HttpUriRequest)httpGet);
/* 144 */       entity = response.getEntity();
/* 145 */       responseContent = EntityUtils.toString(entity, "UTF-8");
/* 146 */     } catch (Exception e) {
/* 147 */       e.printStackTrace();
/*     */     } finally {
/*     */       
/*     */       try {
/* 151 */         if (response != null) {
/* 152 */           response.close();
/*     */         }
/* 154 */         if (httpClient != null) {
/* 155 */           httpClient.close();
/*     */         }
/* 157 */       } catch (IOException e) {
/* 158 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 161 */     return responseContent;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\HttpClientUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */