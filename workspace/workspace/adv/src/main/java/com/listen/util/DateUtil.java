/*     */ package com.listen.util;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
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
/*     */ public class DateUtil
/*     */ {
/*     */   public static final String DAY_PATTERN = "yyyy-MM-dd";
/*     */   public static final String TIME_PATTERN = "HH:mm:ss";
/*     */   
/*     */   public static void main(String[] args) {}
/*     */   
/*  33 */   public static Long getNowTimeStamp() { return Long.valueOf((new Date()).getTime()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String timeStamp2Date(long timestamp, String pattern) {
/*  41 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
/*  42 */     Date date = new Date(timestamp);
/*  43 */     return simpleDateFormat.format(date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Long getTimeStamp(String time, String pattern) throws ParseException {
/*  51 */     Date date = (new SimpleDateFormat(pattern)).parse(time);
/*  52 */     long unixTimestamp = date.getTime();
/*  53 */     return Long.valueOf(unixTimestamp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String format(Date date) {
/*  64 */     SimpleDateFormat formatTool = new SimpleDateFormat();
/*  65 */     formatTool.applyPattern("yyyy-MM-dd HH:mm:ss");
/*  66 */     return formatTool.format(date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatDate(Date date) {
/*  76 */     SimpleDateFormat formatTool = new SimpleDateFormat();
/*  77 */     formatTool.applyPattern("yyyy-MM-dd");
/*  78 */     return formatTool.format(date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatTime(Date date) {
/*  88 */     SimpleDateFormat formatTool = new SimpleDateFormat();
/*  89 */     formatTool.applyPattern("HH:mm:ss");
/*  90 */     return formatTool.format(date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String format(Date date, String pattern) {
/* 102 */     SimpleDateFormat formatTool = new SimpleDateFormat();
/* 103 */     formatTool.applyPattern(pattern);
/* 104 */     return formatTool.format(date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Date parse(String dateValue) throws ParseException {
/* 116 */     SimpleDateFormat formatTool = new SimpleDateFormat();
/* 117 */     formatTool.applyPattern("yyyy-MM-dd HH:mm:ss");
/* 118 */     return formatTool.parse(dateValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Date parseDate(String dateValue) throws ParseException {
/* 130 */     SimpleDateFormat formatTool = new SimpleDateFormat();
/* 131 */     formatTool.applyPattern("yyyy-MM-dd");
/* 132 */     return formatTool.parse(dateValue);
/*     */   }
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
/*     */   public static Date parse(String dateValue, String pattern) throws ParseException {
/* 147 */     SimpleDateFormat formatTool = new SimpleDateFormat();
/* 148 */     formatTool.applyPattern(pattern);
/* 149 */     return formatTool.parse(dateValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public static Date getEarliest(Date date) { return parseTime(date, 0, 0, 0, 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   public static Date getLastest(Date date) { return parseTime(date, 23, 59, 59, 999); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getMonthLastDay(int year, int month) {
/* 180 */     Calendar a = Calendar.getInstance();
/* 181 */     a.set(1, year);
/* 182 */     a.set(2, month - 1);
/* 183 */     a.set(5, 1);
/* 184 */     a.roll(5, -1);
/* 185 */     int maxDate = a.get(5);
/* 186 */     return maxDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Date add(Date date, int num) {
/* 197 */     Calendar a = Calendar.getInstance();
/* 198 */     a.setTime(date);
/* 199 */     a.add(5, num);
/* 200 */     return a.getTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNotEmpty(Date date) {
/* 210 */     if (date != null) {
/* 211 */       Calendar a = Calendar.getInstance();
/* 212 */       a.setTime(date);
/* 213 */       return (a.get(1) != 1970);
/*     */     } 
/* 215 */     return Boolean.FALSE.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getHours(Date date) {
/* 225 */     Calendar a = Calendar.getInstance();
/* 226 */     a.setTime(date);
/* 227 */     return a.get(11);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getDay(Date date) {
/* 237 */     Calendar a = Calendar.getInstance();
/* 238 */     a.setTime(date);
/* 239 */     return a.get(7);
/*     */   }
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
/*     */   public static Date parseTime(Date date, int hourOfDay, int minute, int second, int milliSecond) {
/* 259 */     Calendar cal = Calendar.getInstance();
/* 260 */     cal.setTime(date);
/* 261 */     setCalendarTime(cal, hourOfDay, minute, second, milliSecond);
/* 262 */     return cal.getTime();
/*     */   }
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
/*     */   public static Date parseTime(Date date, String timeDetail) {
/* 276 */     List<String> strList = new ArrayList<>();
/* 277 */     strList.addAll(Arrays.asList(timeDetail.split(":")));
/*     */     
/* 279 */     while (strList.size() < 4) {
/* 280 */       strList.add("0");
/*     */     }
/* 282 */     return parseTime(date, Integer.parseInt(strList.get(0)), 
/* 283 */         Integer.parseInt(strList.get(1)), 
/* 284 */         Integer.parseInt(strList.get(2)), 
/* 285 */         Integer.parseInt(strList.get(3)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isAfterTime(String time) {
/* 296 */     Date date = parseTime(new Date(), time);
/* 297 */     return date.after(new Date());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isBeforeTime(String time) {
/* 308 */     Date date = parseTime(new Date(), time);
/* 309 */     return date.before(new Date());
/*     */   }
/*     */ 
/*     */   
/*     */   private static void setCalendarTime(Calendar cal, int hourOfDay, int minute, int second, int milliSecond) {
/* 314 */     cal.set(11, hourOfDay);
/* 315 */     cal.set(12, minute);
/* 316 */     cal.set(13, second);
/* 317 */     cal.set(14, milliSecond);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getAppointTime(int num) {
/* 324 */     Calendar ca = Calendar.getInstance();
/* 325 */     ca.add(5, num);
/* 326 */     return ca.getTime().getTime();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\DateUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */