/*    */ package com.listen.controller;
/*    */ 
/*    */ import com.listen.service.MapCardService;
/*    */ import javax.annotation.Resource;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMethod;
/*    */ import org.springframework.web.bind.annotation.ResponseBody;
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
/*    */ @Controller
/*    */ @RequestMapping({"/map/"})
/*    */ public class MapCardController
/*    */ {
/*    */   @Resource
/*    */   private MapCardService mapCardService;
/*    */   
/*    */   @RequestMapping(value = {"getPic.fgl"}, method = {RequestMethod.GET}, produces = {"text/html;charset=UTF-8"})
/*    */   @ResponseBody
/* 29 */   public String getPicturePath(String devCode, HttpServletRequest request) { return this.mapCardService.getPicturePath(devCode, request); }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\controller\MapCardController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */