/*     */ package com.listen.filter;
/*     */ 
/*     */ import com.listen.model.ResultBean;
/*     */ import com.listen.util.CodeUtil;
/*     */ import com.listen.util.LanguagesPropertyUtil;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.List;
/*     */ import javax.servlet.ServletRequest;
/*     */ import javax.servlet.ServletResponse;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.codehaus.jackson.map.ObjectMapper;
/*     */ import org.springframework.web.servlet.HandlerInterceptor;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ import tk.mybatis.mapper.util.StringUtil;
/*     */ 
/*     */ 
/*     */ public class CheckSessionFilter
/*     */   implements HandlerInterceptor
/*     */ {
/*     */   private List<String> uncheckUrls;
/*     */   
/*     */   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
/*  25 */     Object o = request.getSession().getAttribute("currentUser");
/*  26 */     if (o != null) {
/*  27 */       return true;
/*     */     }
/*  29 */     String url = request.getRequestURI();
/*  30 */     String requestType = request.getHeader("X-Requested-With");
/*  31 */     if (this.uncheckUrls.contains(url) || isLetGo(this.uncheckUrls, url)) {
/*  32 */       return true;
/*     */     }
/*  34 */     String referer = request.getHeader("Referer");
/*  35 */     if (referer != null && referer.indexOf(request.getContextPath()) < 0) {
/*  36 */       ObjectMapper objectMapper = new ObjectMapper();
/*  37 */       response.setCharacterEncoding("UTF-8");
/*  38 */       response.setContentType("application/json; charset=utf-8");
/*  39 */       PrintWriter out = null;
/*     */       try {
/*  41 */         out = response.getWriter();
/*  42 */         ResultBean rb = new ResultBean();
/*  43 */         rb.setFlag(Integer.valueOf(-1));
/*  44 */         rb.setMsg("非法操作");
/*  45 */         out.append(objectMapper.writeValueAsString(rb));
/*  46 */       } catch (IOException e) {
/*  47 */         e.printStackTrace();
/*     */       } finally {
/*  49 */         if (out != null) {
/*  50 */           out.close();
/*     */         }
/*     */       } 
/*  53 */       return false;
/*     */     } 
/*  55 */     if (url.indexOf("materialUpLoad") > 0 || url.indexOf("Main") > 0 || this.uncheckUrls.contains(url) || requestType == null) {
/*  56 */       request.setAttribute("message", LanguagesPropertyUtil.getPropertyOnSystemLocale("login.timeout"));
/*  57 */       request.getSession().setAttribute("DES_KEY", CodeUtil.getDevCode());
/*  58 */       request.getRequestDispatcher("/page/login.jsp").forward((ServletRequest)request, (ServletResponse)response);
/*     */     } else {
/*  60 */       ObjectMapper objectMapper = new ObjectMapper();
/*  61 */       response.setCharacterEncoding("UTF-8");
/*  62 */       response.setContentType("application/json; charset=utf-8");
/*  63 */       PrintWriter out = null;
/*     */       try {
/*  65 */         out = response.getWriter();
/*  66 */         ResultBean rb = new ResultBean();
/*  67 */         rb.setFlag(Integer.valueOf(-1));
/*  68 */         rb.setMsg("login.timeout");
/*  69 */         out.append(objectMapper.writeValueAsString(rb));
/*  70 */       } catch (IOException e) {
/*  71 */         e.printStackTrace();
/*     */       } finally {
/*  73 */         if (out != null) {
/*  74 */           out.close();
/*     */         }
/*     */       } 
/*     */     } 
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isLetGo(List<String> uncheckUrls, String url) {
/*  85 */     if (StringUtil.isNotEmpty(url)) {
/*  86 */       url = url.replace("\\", "/");
/*  87 */       int index = -1;
/*  88 */       for (String path : uncheckUrls) {
/*  89 */         index = path.lastIndexOf("*");
/*  90 */         if (index != -1 && url.indexOf(path.substring(0, index)) != -1) {
/*  91 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public List<String> getUncheckUrls() { return this.uncheckUrls; }
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void setUncheckUrls(List<String> uncheckUrls) { this.uncheckUrls = uncheckUrls; }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\filter\CheckSessionFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */