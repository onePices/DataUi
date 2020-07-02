/*     */ package com.listen.util;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipOutputStream;
/*     */ import org.apache.log4j.Logger;
/*     */ import sun.misc.BASE64Decoder;
/*     */ import sun.misc.BASE64Encoder;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileUtil
/*     */ {
/*  26 */   private static Logger log = Logger.getLogger(FileUtil.class);
/*     */   
/*     */   private static String SdkLogPath;
/*     */   
/*     */   private static String tomLogPath;
/*     */   
/*     */   private static String envPath;
/*  33 */   private static String SAVE_FILE_PATH = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   private static String INPUT_FILE_PATH = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   private static String IMAGE_PATH = "mapImages";
/*     */   
/*     */   static  {
/*  46 */     initMethod();
/*  47 */     getThePath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void getThePath() {
/*  54 */     String str = System.getenv(ConfigUtil.getProperties("default.tomcatPath"));
/*  55 */     File tom = new File(str);
/*     */     
/*  57 */     envPath = str;
/*  58 */     File[] fileList = tom.listFiles();
/*  59 */     if (fileList != null && fileList.length > 0) {
/*  60 */       byte b; int i; File[] arrayOfFile; for (i = (arrayOfFile = fileList).length, b = 0; b < i; ) { File file = arrayOfFile[b];
/*  61 */         if ("logs".equals(file.getName())) {
/*  62 */           tomLogPath = String.valueOf(file.getPath()) + File.separator;
/*     */         }
/*  64 */         if ("log_listenSdkService".equals(file.getName())) {
/*  65 */           SdkLogPath = String.valueOf(file.getPath()) + File.separator;
/*     */         }
/*     */         b++; }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void initMethod() {
/*  73 */     SAVE_FILE_PATH = ConfigUtil.getProperties("default.source.path");
/*  74 */     INPUT_FILE_PATH = ConfigUtil.getProperties("default.input.path");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String fileToString(String filePath) {
/*  84 */     String fileStr = null;
/*  85 */     FileInputStream fis = null;
/*  86 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/*     */     try {
/*  88 */       File file = new File(filePath);
/*  89 */       if (!file.exists()) {
/*  90 */         throw new FileNotFoundException();
/*     */       }
/*  92 */       fis = new FileInputStream(file);
/*  93 */       byte[] buffer = new byte[4096];
/*  94 */       int n = 0;
/*  95 */       while ((n = fis.read(buffer)) != -1) {
/*  96 */         out.write(buffer, 0, n);
/*     */       }
/*  98 */       fileStr = (new BASE64Encoder()).encodeBuffer(out.toByteArray());
/*     */     
/*     */     }
/* 101 */     catch (Exception e) {
/* 102 */       e.printStackTrace();
/* 103 */       log.error("文件工具类-文件转字符串", e);
/*     */     } finally {
/*     */       try {
/* 106 */         if (fis != null) {
/* 107 */           fis.close();
/*     */         }
/* 109 */         if (out != null) {
/* 110 */           out.close();
/*     */         }
/* 112 */       } catch (Exception e2) {
/* 113 */         e2.printStackTrace();
/* 114 */         log.error("文件工具类-文件转字符串-流关闭", e2);
/*     */       } 
/*     */     } 
/* 117 */     return fileStr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void stringSaveAsFile(String fileStr, String outFilePath) {
/* 126 */     InputStream is = null;
/* 127 */     FileOutputStream fos = null;
/*     */     try {
/* 129 */       File file = new File(outFilePath);
/* 130 */       if (!file.getParentFile().exists()) {
/* 131 */         file.mkdirs();
/*     */       }
/* 133 */       is = new ByteArrayInputStream(fileStr.getBytes());
/* 134 */       BASE64Decoder decoder = new BASE64Decoder();
/* 135 */       fos = new FileOutputStream(file);
/* 136 */       byte[] buffer = decoder.decodeBuffer(is);
/* 137 */       fos.write(buffer, 0, buffer.length);
/* 138 */     } catch (Exception e) {
/* 139 */       e.printStackTrace();
/* 140 */       log.error("文件工具类-字符串存为文件", e);
/* 141 */       throw new RuntimeException(e);
/*     */     } finally {
/*     */       try {
/* 144 */         if (is != null) {
/* 145 */           is.close();
/*     */         }
/* 147 */         if (fos != null) {
/* 148 */           fos.close();
/*     */         }
/* 150 */       } catch (Exception e2) {
/* 151 */         e2.printStackTrace();
/* 152 */         log.error("文件工具类-字符串存为文件-流关闭", e2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void cleanLog() {
/* 161 */     if (StringUtil.isEmpty(tomLogPath) || StringUtil.isEmpty(SdkLogPath)) {
/* 162 */       getThePath();
/*     */     }
/* 164 */     cleanLog(tomLogPath, Long.parseLong(ConfigUtil.getProperties("default.logTimeOut")));
/* 165 */     cleanLog(SdkLogPath, Long.parseLong(ConfigUtil.getProperties("default.logTimeOut")));
/* 166 */     cleanLog(ConfigUtil.getProperties("default.mapLog.path"), 1L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void cleanLog(String path, long day) {
/* 173 */     if (StringUtil.isEmpty(path)) {
/*     */       return;
/*     */     }
/* 176 */     File file = new File(path);
/* 177 */     long nowTime = System.currentTimeMillis();
/*     */     
/* 179 */     File[] list = file.listFiles();
/* 180 */     if (list != null && list.length > 0) {
/* 181 */       byte b; int i; File[] arrayOfFile; for (i = (arrayOfFile = list).length, b = 0; b < i; ) { File f = arrayOfFile[b];
/* 182 */         long time = f.lastModified();
/* 183 */         boolean flag = (nowTime - time > 86400000L * day);
/* 184 */         if (flag) {
/* 185 */           f.delete();
/*     */         }
/*     */         b++; }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void cleanTaskZip() {
/* 195 */     String path = String.valueOf(ConfigUtil.getProperties("default.source.path")) + "job" + File.separator;
/* 196 */     cleanTaskZip(new File(path));
/*     */   }
/*     */   
/*     */   private static void cleanTaskZip(File file) {
/* 200 */     long day = Long.parseLong(ConfigUtil.getProperties("default.taskZipTimeOut"));
/* 201 */     long nowTime = System.currentTimeMillis();
/* 202 */     long time = file.lastModified();
/* 203 */     boolean flag = (nowTime - time > 86400000L * day);
/* 204 */     if (file.isDirectory())
/* 205 */     { File[] fileList = file.listFiles();
/* 206 */       if (fileList != null && fileList.length > 0) {
/* 207 */         byte b; int i; File[] arrayOfFile; for (i = (arrayOfFile = fileList).length, b = 0; b < i; ) { File f = arrayOfFile[b];
/* 208 */           if (f.isDirectory()) {
/* 209 */             cleanTaskZip(f);
/*     */           } else {
/* 211 */             long timeUp = f.lastModified();
/* 212 */             boolean bool = (nowTime - timeUp > 86400000L * day);
/* 213 */             if (f.exists() && bool) f.delete(); 
/*     */           }  b++; }
/*     */       
/*     */       } 
/* 217 */       if (file.exists() && flag) file.delete();
/*     */        }
/* 219 */     else if (file.exists() && flag) { file.delete(); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void zipLogFile(ZipOutputStream out, Integer count) throws Exception {
/* 229 */     if (StringUtil.isEmpty(tomLogPath) || StringUtil.isEmpty(SdkLogPath)) {
/* 230 */       getThePath();
/*     */     }
/* 232 */     count = Integer.valueOf((count == null || count.intValue() < 1) ? 1 : count.intValue());
/* 233 */     zipLogFile(out, tomLogPath, count, envPath);
/* 234 */     zipLogFile(out, SdkLogPath, count, envPath);
/* 235 */     out.flush();
/* 236 */     out.close();
/*     */   }
/*     */   public static void zipLogFile(ZipOutputStream out, String path, Integer count, String envPath) throws Exception {
/* 239 */     if (StringUtil.isEmpty(path)) {
/*     */       return;
/*     */     }
/* 242 */     File file = new File(path);
/* 243 */     if (file.isDirectory()) {
/* 244 */       File[] fileList = file.listFiles();
/* 245 */       if (fileList != null && fileList.length > 0) {
/* 246 */         byte b; int i; File[] arrayOfFile; for (i = (arrayOfFile = fileList).length, b = 0; b < i; ) { File f = arrayOfFile[b];
/* 247 */           if (f.exists())
/* 248 */             recursionZip(out, f, path.replace(String.valueOf(envPath) + File.separator, ""), count); 
/*     */           b++; }
/*     */       
/*     */       } 
/*     */     } else {
/* 253 */       recursionZip(out, file, "", false, count);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void recursionZip(ZipOutputStream zipOut, File file, String baseDir, Integer count) throws Exception {
/* 258 */     if (file.isDirectory()) {
/* 259 */       File[] files = file.listFiles();
/* 260 */       if (files != null && files.length > 0) {
/* 261 */         byte b; int i; File[] arrayOfFile; for (i = (arrayOfFile = files).length, b = 0; b < i; ) { File fileSec = arrayOfFile[b];
/* 262 */           recursionZip(zipOut, fileSec, String.valueOf(baseDir) + file.getName() + File.separator, count); b++; }
/*     */       
/*     */       } 
/*     */     } else {
/* 266 */       long nowTime = System.currentTimeMillis();
/* 267 */       long time = file.lastModified();
/* 268 */       boolean flag = (nowTime - time <= 86400000L * count.intValue());
/* 269 */       if (flag) {
/* 270 */         byte[] buf = new byte[1024];
/* 271 */         FileInputStream input = new FileInputStream(file);
/* 272 */         zipOut.putNextEntry(new ZipEntry(String.valueOf(baseDir) + file.getName()));
/*     */         int len;
/* 274 */         while ((len = input.read(buf)) != -1) {
/* 275 */           zipOut.write(buf, 0, len);
/*     */         }
/* 277 */         input.close();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void recursionZip(ZipOutputStream zipOut, File file, String baseDir, boolean isdir, Integer count) throws Exception {
/* 283 */     if (file.isDirectory()) {
/* 284 */       File[] files = file.listFiles(); byte b; int i; File[] arrayOfFile;
/* 285 */       for (i = (arrayOfFile = files).length, b = 0; b < i; ) { File fileSec = arrayOfFile[b];
/* 286 */         recursionZip(zipOut, fileSec, String.valueOf(baseDir) + file.getName() + File.separator, isdir, count); b++; }
/*     */     
/*     */     } else {
/* 289 */       long nowTime = System.currentTimeMillis();
/* 290 */       long time = file.lastModified();
/* 291 */       boolean flag = (nowTime - time <= 86400000L * count.intValue());
/* 292 */       if (flag) {
/* 293 */         byte[] buf = new byte[1024];
/* 294 */         InputStream input = new FileInputStream(file);
/* 295 */         if (isdir) {
/* 296 */           zipOut.putNextEntry(new ZipEntry(baseDir));
/*     */         } else {
/* 298 */           zipOut.putNextEntry(new ZipEntry(file.getName()));
/*     */         } 
/*     */         int len;
/* 301 */         while ((len = input.read(buf)) != -1) {
/* 302 */           zipOut.write(buf, 0, len);
/*     */         }
/* 304 */         input.close();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static synchronized Map<String, String> getImageUrl() {
/* 310 */     if (INPUT_FILE_PATH == null || SAVE_FILE_PATH == null) {
/* 311 */       initMethod();
/*     */     }
/* 313 */     Map<String, String> map = new HashMap<>();
/* 314 */     List<File> delFile = new LinkedList<>();
/* 315 */     File file = new File(INPUT_FILE_PATH);
/* 316 */     File[] fileList = file.listFiles();
/* 317 */     if (fileList != null && fileList.length > 0) {
/* 318 */       File timeFile = ((File[])fileList.clone())[0];
/* 319 */       String path = String.valueOf(SAVE_FILE_PATH) + IMAGE_PATH + File.separator + timeFile.getName() + File.separator;
/* 320 */       createDirc(path);
/* 321 */       File[] imageList = timeFile.listFiles();
/* 322 */       if (imageList != null && imageList.length > 0 && 
/* 323 */         isContainIt(imageList, "end.txt")) {
/* 324 */         byte b; int i; File[] arrayOfFile; for (i = (arrayOfFile = imageList).length, b = 0; b < i; ) { File image = arrayOfFile[b];
/* 325 */           String inputStr = image.getPath();
/* 326 */           String outputStr = String.valueOf(path) + image.getName();
/* 327 */           fileChannelCopy(inputStr, outputStr);
/* 328 */           if (!image.getName().contains("end.txt") && image.getName().contains("."))
/* 329 */             map.put(image.getName().substring(0, image.getName().lastIndexOf(".")), outputStr.replace(SAVE_FILE_PATH, "")); 
/* 330 */           delFile.add(image);
/*     */           b++; }
/*     */       
/*     */       } 
/*     */     } 
/* 335 */     deleteFile(delFile);
/* 336 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isContainIt(File[] list, String name) {
/* 346 */     boolean flag = false; byte b; int i; File[] arrayOfFile;
/* 347 */     for (i = (arrayOfFile = list).length, b = 0; b < i; ) { File file = arrayOfFile[b];
/* 348 */       if (name.equalsIgnoreCase(file.getName()))
/* 349 */         flag = true; 
/*     */       b++; }
/*     */     
/* 352 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void fileChannelCopy(String input, String output) {
/* 361 */     FileInputStream fis = null;
/* 362 */     FileOutputStream fos = null;
/* 363 */     FileChannel in = null;
/* 364 */     FileChannel out = null;
/*     */     try {
/* 366 */       File inputFile = new File(input);
/* 367 */       File outputFile = new File(output);
/* 368 */       fis = new FileInputStream(inputFile);
/* 369 */       fos = new FileOutputStream(outputFile);
/* 370 */       in = fis.getChannel();
/* 371 */       out = fos.getChannel();
/* 372 */       in.transferTo(0L, in.size(), out);
/* 373 */     } catch (IOException e) {
/* 374 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 377 */         if (fis != null) {
/* 378 */           fis.close();
/*     */         }
/* 380 */         if (in != null) {
/* 381 */           in.close();
/*     */         }
/* 383 */         if (fos != null) {
/* 384 */           fos.close();
/*     */         }
/* 386 */         if (out != null) {
/* 387 */           out.close();
/*     */         }
/* 389 */       } catch (IOException e) {
/* 390 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void createDirc(String str) {
/* 400 */     File fileP = new File(str);
/* 401 */     if (!fileP.exists()) {
/* 402 */       fileP.mkdirs();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void deleteFile(List<File> list) {
/* 410 */     File parentFile = null;
/* 411 */     boolean flag = true;
/* 412 */     for (File file : list) {
/* 413 */       if (file.exists()) file.delete(); 
/* 414 */       if (flag) {
/* 415 */         parentFile = file.getParentFile();
/* 416 */         flag = false;
/*     */       } 
/*     */     } 
/* 419 */     if (parentFile != null && parentFile.exists()) parentFile.delete(); 
/*     */   }
/*     */   
/*     */   public static void cleanMapImage() {
/* 423 */     String path = String.valueOf(ConfigUtil.getProperties("default.source.path")) + File.separator + IMAGE_PATH;
/* 424 */     if (StringUtil.isEmpty(path)) {
/*     */       return;
/*     */     }
/* 427 */     File file = new File(path);
/* 428 */     long nowTime = System.currentTimeMillis();
/*     */     
/* 430 */     long day = Long.parseLong(ConfigUtil.getProperties("default.mapImageTimeOut"));
/* 431 */     File[] list = file.listFiles();
/* 432 */     if (list != null && list.length > 0) {
/* 433 */       byte b; int i; File[] arrayOfFile; for (i = (arrayOfFile = list).length, b = 0; b < i; ) { File f = arrayOfFile[b];
/* 434 */         long time = f.lastModified();
/* 435 */         if (f.isDirectory()) {
/* 436 */           File[] fileList = f.listFiles();
/* 437 */           if (fileList != null && fileList.length > 0) {
/* 438 */             int num = fileList.length; byte b1; int j; File[] arrayOfFile1;
/* 439 */             for (j = (arrayOfFile1 = fileList).length, b1 = 0; b1 < j; ) { File ff = arrayOfFile1[b1];
/* 440 */               long tim = ff.lastModified();
/* 441 */               boolean arr = (nowTime - tim > 3600000L * day);
/* 442 */               if (arr) {
/* 443 */                 ff.delete();
/* 444 */                 num--;
/*     */               }  b1++; }
/*     */             
/* 447 */             if (num == 0) {
/* 448 */               f.delete();
/*     */             }
/*     */           } else {
/* 451 */             f.delete();
/*     */           } 
/*     */         } else {
/* 454 */           boolean flag = (nowTime - time > 3600000L * day);
/* 455 */           if (flag)
/* 456 */             f.delete(); 
/*     */         } 
/*     */         b++; }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void main(String[] args) throws Exception {}
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\FileUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */