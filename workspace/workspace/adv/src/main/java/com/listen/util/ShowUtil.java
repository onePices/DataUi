/*     */ package com.listen.util;
/*     */ 
/*     */ import com.listen.model.FileBean;
/*     */ import com.listen.model.TextImageModel;
/*     */ import com.listen.model.task.PlayModel;
/*     */ import com.listen.model.task.PlayTaskChannel;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipOutputStream;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.util.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShowUtil
/*     */ {
/*  33 */   private static Logger log = Logger.getLogger(ShowUtil.class);
/*     */ 
/*     */   
/*     */   public static Set<FileBean> createFile(StringBuffer strProgramId, StringBuffer strProgramName, String jsonData, String filePath, String uuid, String custNo, PlayModel pm, Integer taskId) {
/*  37 */     String timeStamp = DateUtil.format(new Date(), "yyyyMM");
/*     */     
/*  39 */     String tempPath = String.valueOf(filePath) + "listenService" + File.separator + "temp" + File.separator + custNo + File.separator + timeStamp + File.separator + taskId + File.separator;
/*  40 */     String tImgPath = "text" + File.separator + "temp" + File.separator + timeStamp + File.separator + taskId + File.separator;
/*  41 */     JSONObject json = JSONObject.fromObject(jsonData);
/*  42 */     String pageResolution = json.getString("pageResolution");
/*  43 */     String pageStr = json.getString("page");
/*  44 */     String resolution = getResolution(pageResolution);
/*  45 */     String js = "var pageList ={\"pageResolution\":[{\"resolution\":\"" + resolution + "\"}],";
/*     */     
/*  47 */     String playTimes = json.getString("playTimes");
/*  48 */     if (json.containsKey("playTimes")) {
/*  49 */       js = String.valueOf(js) + "\"playTimes\":\"" + playTimes + "\",";
/*     */     }
/*  51 */     String playTimesFlag = json.getString("playTimesFlag");
/*  52 */     if (json.containsKey("playTimesFlag")) {
/*  53 */       js = String.valueOf(js) + "\"playTimesFlag\":\"" + playTimesFlag + "\",";
/*     */     }
/*  55 */     String page = "\"page\":[";
/*     */ 
/*     */     
/*  58 */     strProgramId.append((json.getString("programId") == "" || json.getString("programId") == null) ? "0" : Integer.valueOf(json.getString("programId")));
/*  59 */     strProgramName.append(json.getString("programName"));
/*     */ 
/*     */     
/*  62 */     String strProgramPlanInfo = null;
/*  63 */     if (json.containsKey("programPlan")) {
/*  64 */       strProgramPlanInfo = getProgramPlanInfo(json.getString("programPlan"));
/*     */     }
/*  66 */     Set<FileBean> setFile = getTxtData(pageStr, tempPath, uuid, String.valueOf(js) + page, pm, filePath, tImgPath, strProgramPlanInfo);
/*  67 */     return setFile;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getProgramPlanInfo(String data) {
/*  72 */     JSONArray programPlanArray = JSONArray.fromObject(data);
/*  73 */     String strProgramPlan = programPlanArray.getString(0);
/*  74 */     return strProgramPlan;
/*     */   }
/*     */   
/*     */   public static String getResolution(String data) {
/*  78 */     JSONArray pageResolution = JSONArray.fromObject(data);
/*  79 */     String pageRes = pageResolution.getString(0);
/*  80 */     JSONObject res = JSONObject.fromObject(pageRes);
/*  81 */     String resolution = res.getString("resolution");
/*  82 */     return resolution;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Set<FileBean> getTxtData(String pageStr, String tempPath, String uuid, String content, PlayModel pm, String filePath, String tImgPath, String strProgramPlanInfo) {
/*  87 */     Set<FileBean> set = new HashSet<>();
/*     */     
/*  89 */     Set<String> urlSet = new HashSet<>();
/*     */     
/*  91 */     List<TextImageModel> textList = new ArrayList<>();
/*     */     
/*  93 */     Map<String, String> map = new HashMap<>();
/*  94 */     JSONArray pageArr = JSONArray.fromObject(pageStr);
/*  95 */     long mediaSize = 0L;
/*  96 */     int num = 1;
/*  97 */     String pageJS = "";
/*  98 */     for (Object pageObj : pageArr) {
/*  99 */       JSONObject page = JSONObject.fromObject(pageObj);
/* 100 */       String pagTime = page.getString("pagTime");
/* 101 */       String areaListStr = page.getString("areaList");
/* 102 */       JSONObject areaList = JSONObject.fromObject(areaListStr);
/* 103 */       String mediaEleStr = areaList.getString("mediaEle");
/* 104 */       JSONArray mediaEleArr = JSONArray.fromObject(mediaEleStr);
/* 105 */       for (Object mediaEleObj : mediaEleArr) {
/* 106 */         JSONObject mediaEle = JSONObject.fromObject(mediaEleObj);
/* 107 */         String type = mediaEle.getString("type");
/* 108 */         if ("1".equals(type) || "0".equals(type) || "5".equals(type) || "10".equals(type)) {
/* 109 */           String srcGroupStr = mediaEle.getString("srcGroup");
/* 110 */           JSONArray srcGroupArr = JSONArray.fromObject(srcGroupStr);
/* 111 */           for (Object srcGroupObj : srcGroupArr) {
/* 112 */             JSONObject srcGroup = JSONObject.fromObject(srcGroupObj);
/* 113 */             String size = srcGroup.getString("size");
/* 114 */             String src = srcGroup.getString("src");
/* 115 */             urlSet.add(src);
/* 116 */             mediaSize += Long.parseLong(size);
/* 117 */             updateUrl(src, map, type);
/*     */           }  continue;
/*     */         } 
/* 120 */         if ("2".equals(type)) {
/* 121 */           String textGroupStr = mediaEle.getString("textGroup");
/* 122 */           JSONArray textGroupArr = JSONArray.fromObject(textGroupStr);
/*     */           
/* 124 */           String fontSize = mediaEle.getString("fontSize");
/*     */           
/* 126 */           String fontColor = mediaEle.getString("fontColor");
/*     */           
/* 128 */           String fontFamily = mediaEle.getString("fontFamily");
/* 129 */           String textY = mediaEle.getString("top");
/* 130 */           String textX = mediaEle.getString("left");
/*     */           
/* 132 */           String textWidth = mediaEle.getString("width");
/*     */           
/* 134 */           String textHeight = mediaEle.getString("height");
/* 135 */           String siderType = mediaEle.getString("siderType");
/* 136 */           String background = mediaEle.getString("background");
/* 137 */           String textShow = mediaEle.getString("textShow");
/* 138 */           String textAlign = mediaEle.getString("textAlign");
/* 139 */           String fontStyle = mediaEle.getString("fontStyle");
/* 140 */           String fontWeight = mediaEle.getString("fontWeight");
/* 141 */           String textDecoration = mediaEle.getString("textDecoration");
/* 142 */           String textid = mediaEle.getString("id");
/* 143 */           String textStyle = getTextStyle(fontStyle, fontWeight, textDecoration);
/* 144 */           int lineId = 1;
/* 145 */           for (Object textGroupObj : textGroupArr) {
/* 146 */             List<String> tempList = new ArrayList<>();
/* 147 */             JSONObject textGroup = JSONObject.fromObject(textGroupObj);
/* 148 */             String text = textGroup.getString("text");
/* 149 */             if (textid != null && !textid.equals("")) {
/* 150 */               lineId = Integer.valueOf(textid).intValue();
/*     */             }
/* 152 */             Text2ImagesUtil.text2Img4File(fontSize, fontColor, fontFamily, textY, textX, textWidth, textHeight, tempList, String.valueOf(filePath) + tImgPath + lineId + File.separator, text, siderType, background, textShow, textAlign, textStyle);
/*     */             
/* 154 */             if (tempList.size() > 0) {
/* 155 */               TextImageModel tim = new TextImageModel();
/* 156 */               tim.setDomainId(textid);
/* 157 */               tim.setImageList(tempList);
/* 158 */               textList.add(tim);
/* 159 */               for (String textImage : tempList) {
/* 160 */                 set.add(new FileBean(textImage.replaceFirst(filePath, ""), true, true));
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 167 */       Set<String> keys = map.keySet();
/* 168 */       for (String key : keys) {
/* 169 */         areaListStr = areaListStr.replaceAll(key, map.get(key));
/*     */       }
/*     */       
/* 172 */       String jsPath = String.valueOf(tempPath) + uuid + "_" + num + ".js";
/* 173 */       String jsName = String.valueOf(uuid) + "_" + num + ".js";
/* 174 */       set.add(new FileBean(jsPath.replace(filePath, ""), false, false));
/* 175 */       String str = "var picDate=" + areaListStr + ";" + "var imgList=" + JSONArray.fromObject(textList).toString().replace(filePath, "") + ";var picList=[];var pptList=[];var wordList=[];" + ConfigUtil.getProperties("default.callbackJs");
/* 176 */       writeToJS(str, jsPath);
/* 177 */       num++;
/* 178 */       if ("".equals(pageJS)) {
/* 179 */         pageJS = "{\"jsonData\":\"" + jsName + "\",\"pagTime\":" + pagTime + "}";
/*     */       } else {
/* 181 */         pageJS = ",{\"jsonData\":\"" + jsName + "\",\"pagTime\":" + pagTime + "}";
/*     */       } 
/* 183 */       content = String.valueOf(content) + pageJS;
/*     */     } 
/* 185 */     content = String.valueOf(content) + "]};function callbackPL(){return pageList;}";
/* 186 */     String pagePath = String.valueOf(tempPath) + "pageList.js";
/* 187 */     set.add(new FileBean(pagePath.replace(filePath, ""), false, false));
/* 188 */     writeToJS(content, pagePath);
/* 189 */     String textPath = String.valueOf(tempPath) + uuid + ".txt";
/* 190 */     set.add(new FileBean(textPath.replace(filePath, ""), false, false));
/* 191 */     writeToText(urlSet, textPath);
/* 192 */     pm.setSize(Long.valueOf(mediaSize));
/* 193 */     set.add(new FileBean("modol", true, true));
/*     */ 
/*     */     
/* 196 */     if (strProgramPlanInfo != null && strProgramPlanInfo.length() > 0) {
/* 197 */       String strProgramPlanPath = String.valueOf(tempPath) + "programPlayPlan.js";
/* 198 */       set.add(new FileBean(strProgramPlanPath.replace(filePath, ""), false, false));
/* 199 */       writeToJS(strProgramPlanInfo, strProgramPlanPath);
/*     */     } 
/*     */     
/* 202 */     return set;
/*     */   }
/*     */   
/*     */   public static void writeToJS(String str, String jsPath) {
/* 206 */     FileOutputStream fos = null;
/* 207 */     BufferedWriter bw = null;
/*     */     try {
/* 209 */       File file = new File(jsPath);
/* 210 */       if (!file.getParentFile().exists()) {
/* 211 */         file.getParentFile().mkdirs();
/*     */       }
/* 213 */       if (!file.exists()) {
/* 214 */         file.createNewFile();
/*     */       }
/* 216 */       fos = new FileOutputStream(file);
/* 217 */       bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
/* 218 */       bw.write(str);
/* 219 */     } catch (Exception e) {
/* 220 */       e.printStackTrace();
/* 221 */       log.error("writeToJS生成文件异常", e);
/*     */     } finally {
/*     */       try {
/* 224 */         if (bw != null) {
/* 225 */           bw.close();
/*     */         }
/* 227 */         if (fos != null) {
/* 228 */           fos.close();
/*     */         }
/* 230 */       } catch (Exception e) {
/* 231 */         e.printStackTrace();
/* 232 */         log.error("writeToJS流关闭异常", e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public static void writeToText(Set<String> urlSet, String textPath) {
/* 237 */     FileOutputStream fos = null;
/* 238 */     BufferedWriter bw = null;
/*     */     try {
/* 240 */       File file = new File(textPath);
/* 241 */       if (!file.getParentFile().exists()) {
/* 242 */         file.getParentFile().mkdirs();
/*     */       }
/* 244 */       if (!file.exists()) {
/* 245 */         file.createNewFile();
/*     */       }
/* 247 */       fos = new FileOutputStream(file);
/* 248 */       bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
/* 249 */       for (String str : urlSet) {
/* 250 */         bw.write(str);
/* 251 */         bw.newLine();
/*     */       } 
/* 253 */     } catch (Exception e) {
/* 254 */       e.printStackTrace();
/* 255 */       log.error("writeToText生成文件异常", e);
/*     */     } finally {
/*     */       try {
/* 258 */         if (bw != null) {
/* 259 */           bw.close();
/*     */         }
/* 261 */         if (fos != null) {
/* 262 */           fos.close();
/*     */         }
/* 264 */       } catch (Exception e) {
/* 265 */         e.printStackTrace();
/* 266 */         log.error("writeToText流关闭异常", e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String getTextStyle(String fontStyle, String fontWeight, String textDecoration) {
/* 272 */     String style = "";
/* 273 */     if (!StringUtils.isEmpty(fontStyle)) {
/* 274 */       style = String.valueOf(style) + "italic,";
/*     */     }
/* 276 */     if (!StringUtils.isEmpty(fontWeight)) {
/* 277 */       style = String.valueOf(style) + "bold,";
/*     */     }
/* 279 */     if (!StringUtils.isEmpty(textDecoration)) {
/* 280 */       if (textDecoration.equalsIgnoreCase("underline")) {
/* 281 */         style = String.valueOf(style) + "underline,";
/*     */       } else {
/* 283 */         style = String.valueOf(style) + "line-through,";
/*     */       } 
/*     */     }
/* 286 */     if (!StringUtils.isEmpty(style)) {
/* 287 */       style = style.substring(0, style.length() - 1);
/*     */     }
/* 289 */     return style;
/*     */   }
/*     */   
/*     */   public static void createZipFile(ZipOutputStream zipOut, Set<FileBean> setFile, String filePath) throws Exception {
/* 293 */     for (FileBean fb : setFile) {
/* 294 */       File file = new File(String.valueOf(filePath) + fb.getFilePath());
/* 295 */       if (file.isDirectory()) {
/* 296 */         File[] files = file.listFiles(); byte b; int i; File[] arrayOfFile;
/* 297 */         for (i = (arrayOfFile = files).length, b = 0; b < i; ) { File fileSe = arrayOfFile[b];
/* 298 */           if (fileSe.exists())
/* 299 */             recursionZip(zipOut, fileSe, "");  b++; }
/*     */         
/*     */         continue;
/*     */       } 
/* 303 */       recursionZip(zipOut, file, fb.getFilePath(), fb.isDirFlag());
/*     */     } 
/*     */     
/* 306 */     zipOut.flush();
/* 307 */     zipOut.close();
/*     */   }
/*     */   private static void recursionZip(ZipOutputStream zipOut, File file, String baseDir) throws Exception {
/* 310 */     if (file.isDirectory()) {
/* 311 */       File[] files = file.listFiles(); byte b; int i; File[] arrayOfFile;
/* 312 */       for (i = (arrayOfFile = files).length, b = 0; b < i; ) { File fileSec = arrayOfFile[b];
/* 313 */         recursionZip(zipOut, fileSec, String.valueOf(baseDir) + file.getName() + File.separator); b++; }
/*     */     
/*     */     } else {
/* 316 */       byte[] buf = new byte[1024];
/* 317 */       FileInputStream input = new FileInputStream(file);
/* 318 */       zipOut.putNextEntry(new ZipEntry(String.valueOf(baseDir) + file.getName()));
/*     */       int len;
/* 320 */       while ((len = input.read(buf)) != -1) {
/* 321 */         zipOut.write(buf, 0, len);
/*     */       }
/* 323 */       input.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void recursionZip(ZipOutputStream zipOut, File file, String baseDir, boolean isdir) throws Exception {
/* 328 */     if (file.isDirectory()) {
/* 329 */       File[] files = file.listFiles(); byte b; int i; File[] arrayOfFile;
/* 330 */       for (i = (arrayOfFile = files).length, b = 0; b < i; ) { File fileSec = arrayOfFile[b];
/* 331 */         recursionZip(zipOut, fileSec, String.valueOf(baseDir) + file.getName() + File.separator, isdir); b++; }
/*     */     
/*     */     } else {
/* 334 */       byte[] buf = new byte[1024];
/* 335 */       InputStream input = new FileInputStream(file);
/* 336 */       if (isdir) {
/*     */         
/* 338 */         zipOut.putNextEntry(new ZipEntry(baseDir));
/*     */       } else {
/* 340 */         zipOut.putNextEntry(new ZipEntry(file.getName()));
/*     */       } 
/*     */       int len;
/* 343 */       while ((len = input.read(buf)) != -1) {
/* 344 */         zipOut.write(buf, 0, len);
/*     */       }
/* 346 */       input.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void savePlayTaskModelTxt(PlayTaskChannel ptc, String textPath, PlayModel pm) {
/* 351 */     JSONObject json = new JSONObject();
/* 352 */     json.put("playModel", Integer.valueOf(1));
/* 353 */     json.put("channel", JSONObject.fromObject(ptc));
/* 354 */     json.put("size", pm.getSize());
/* 355 */     writeToJS(json.toString(), textPath);
/*     */   }
/*     */   
/*     */   public static void updateUrl(String src, Map<String, String> map, String type) {
/* 359 */     int index = (src.lastIndexOf("\\") > src.lastIndexOf("/")) ? src.lastIndexOf("\\") : src.lastIndexOf("/");
/* 360 */     String fileName = src.substring(index);
/* 361 */     String url = "";
/* 362 */     if ("0".equals(type)) {
/* 363 */       url = "video" + File.separator + fileName;
/* 364 */     } else if ("1".equals(type)) {
/* 365 */       url = "picture" + File.separator + fileName;
/* 366 */     } else if ("10".equals(type)) {
/* 367 */       url = "audio" + File.separator + fileName;
/* 368 */     } else if ("5".equals(type)) {
/* 369 */       if (fileName.contains(".gif") || fileName.contains(".png") || fileName.contains(".jpeg") || fileName.contains(".jpg") || fileName.contains(".bmp")) {
/* 370 */         url = "picture" + File.separator + fileName;
/*     */       } else {
/* 372 */         url = "video" + File.separator + fileName;
/*     */       } 
/*     */     } 
/* 375 */     map.put(src, url);
/*     */   }
/*     */   
/*     */   public static void deleteTempFile(File file) {
/* 379 */     if (file.isDirectory())
/* 380 */     { File[] fileList = file.listFiles();
/* 381 */       if (fileList != null && fileList.length > 0) {
/* 382 */         byte b; int i; File[] arrayOfFile; for (i = (arrayOfFile = fileList).length, b = 0; b < i; ) { File f = arrayOfFile[b];
/* 383 */           if (f.isDirectory())
/* 384 */           { deleteTempFile(f); }
/*     */           
/* 386 */           else if (f.exists()) { f.delete(); }
/*     */            b++; }
/*     */       
/*     */       } 
/* 390 */       file.delete(); }
/*     */     
/* 392 */     else if (file.exists()) { file.delete(); }
/*     */     
/* 394 */     File parentFile = file.getParentFile();
/* 395 */     if (!"temp".equals(parentFile.getName()))
/* 396 */       deleteTempFile(parentFile); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\ShowUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */