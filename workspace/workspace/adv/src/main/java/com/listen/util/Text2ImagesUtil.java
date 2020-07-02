/*     */ package com.listen.util;
/*     */ 
///*     */ import com.listen.card.common.utils.FileUtil;
///*     */ import com.listen.card.common.utils.RichTextUtil;
///*     */ import com.listen.card.entity.q3.AreaBackground;
///*     */ import com.listen.card.entity.q3.RichTextArea;
///*     */ import com.listen.card.entity.q3.Richtext;
/*     */ import com.listen.dll.inf.LedControlInf;
///*     */ import com.lowagie.text.Chunk;
///*     */ import com.lowagie.text.Document;
///*     */ import com.lowagie.text.Element;
///*     */ import com.lowagie.text.Font;
///*     */ import com.lowagie.text.pdf.BaseFont;
///*     */ import com.lowagie.text.rtf.RtfWriter2;
/*     */ import java.awt.Color;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Text2ImagesUtil
/*     */ {
/*  30 */   private static final Logger log = Logger.getLogger(Text2ImagesUtil.class);
/*     */   
/*  32 */   private static String tempName = "temp.rtf";
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
/*     */   public static void text2Img4File(String fontSize, String fontColor, String fontFamily, String textY, String textX, String textWidth, String textHeight, List<String> fileList, String filePath, String text, String scrollerType, String backColor, String textShow, String textAlign, String textStyle) {
/*  53 */     File file = new File(filePath);
/*  54 */     if (!file.exists()) {
/*  55 */       file.mkdirs();
/*     */     }
/*  57 */     if (fontFamily.toLowerCase().indexOf("padauk") != -1 || fontFamily.toLowerCase().indexOf("mmrtext") != -1 || fontFamily.toLowerCase().indexOf("mmrtextb") != -1 || fontFamily.toLowerCase().indexOf("myanmar1") != -1 || fontFamily.toLowerCase().indexOf("myanmar3") != -1) {
/*     */       
/*  59 */       myanmarText2Rtf(filePath, text, fontSize, fontFamily, fontColor, textStyle);
/*  60 */       myanmarRtf2Images(textY, textX, filePath, textWidth, textHeight, fileList, scrollerType, backColor, textShow, textAlign, null);
/*     */     }
/*  62 */     else if (fontFamily.toLowerCase().indexOf("digital") != -1) {
/*     */       
/*  64 */       othersText2Rtf(filePath, text, fontSize, fontFamily, fontColor, textStyle);
/*  65 */       othersRtf2Images(textY, textX, filePath, textWidth, textHeight, fileList, scrollerType, backColor, textShow, textAlign, null);
/*     */     }
/*     */     else {
/*     */       
/*  69 */       text2Rtf(filePath, text, fontSize, fontFamily, fontColor, textStyle);
/*  70 */       rtf2Images(textY, textX, filePath, textWidth, textHeight, fileList, scrollerType, backColor, textShow, textAlign, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void othersRtf2Images(String textY, String textX, String filePath, String textWidth, String textHeight, List<String> fileList, String scrollerType, String backColor, String textShow, String textAlign, String path) {
///*  77 */     RichTextArea richTextArea = new RichTextArea();
///*  78 */     richTextArea.setArea_x(Integer.valueOf(textX).intValue());
///*  79 */     richTextArea.setArea_y(Integer.valueOf(textY).intValue());
///*  80 */     richTextArea.setArea_width(Integer.valueOf(textWidth).intValue());
///*  81 */     richTextArea.setArea_height(Integer.valueOf(textHeight).intValue());
///*  82 */     richTextArea.setArea_ID("1");
/*     */     
///*  84 */     AreaBackground area_background = new AreaBackground();
///*  85 */     area_background.setArea_background_color((backColor == null || backColor.equals("")) ? "000000" : backColor.substring(1, 7));
///*  86 */     area_background.setArea_background_mode(0);
///*  87 */     richTextArea.setArea_background(area_background);
/*     */     
///*  89 */     Richtext aimRichtext = new Richtext();
/*     */     
///*  91 */     if (Integer.valueOf(scrollerType).intValue() == 2 || Integer.valueOf(scrollerType).intValue() == 4) {
///*  92 */       aimRichtext.setEffect_in(7);
///*     */     } else {
///*  94 */       aimRichtext.setEffect_in(0);
///*     */     } 
///*  96 */     int type = 0;
///*  97 */     if (path != null && path.trim() != "") {
///*  98 */       aimRichtext.setRtfFileFullPath(path);
///*  99 */       type = 2;
///*     */     } else {
///* 101 */       aimRichtext.setRtfFileFullPath(String.valueOf(filePath) + tempName);
///* 102 */       type = 1;
///*     */     } 
///*     */     
///* 105 */     if (textAlign.equals("center")) {
///*     */       
///* 107 */       aimRichtext.setText_Align(1);
///* 108 */     } else if (textAlign.equals("right")) {
///*     */       
///* 110 */       aimRichtext.setText_Align(2);
///*     */     } else {
///*     */       
///* 113 */       aimRichtext.setText_Align(0);
///*     */     } 
///*     */ 
///*     */     
///* 117 */     aimRichtext.setText_lineShowFlag((new StringBuilder(String.valueOf(Integer.valueOf(textShow).intValue() + 1))).toString());
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */     
///* 123 */     RichTextUtil.createRichTextPictures(richTextArea, aimRichtext, fileList, filePath, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void myanmarRtf2Images(String textY, String textX, String filePath, String textWidth, String textHeight, List<String> fileList, String scrollerType, String backColor, String textShow, String textAlign, String path) {
///* 130 */     RichTextArea richTextArea = new RichTextArea();
///* 131 */     richTextArea.setArea_x(Integer.valueOf(textX).intValue());
///* 132 */     richTextArea.setArea_y(Integer.valueOf(textY).intValue());
///* 133 */     richTextArea.setArea_width(Integer.valueOf(textWidth).intValue());
///* 134 */     richTextArea.setArea_height(Integer.valueOf(textHeight).intValue());
///* 135 */     richTextArea.setArea_ID("1");
///*     */     
///* 137 */     AreaBackground area_background = new AreaBackground();
///* 138 */     area_background.setArea_background_color((backColor == null || backColor.equals("")) ? "000000" : backColor.substring(1, 7));
///* 139 */     area_background.setArea_background_mode(0);
///* 140 */     richTextArea.setArea_background(area_background);
///*     */     
///* 142 */     Richtext aimRichtext = new Richtext();
///*     */     
///* 144 */     if (Integer.valueOf(scrollerType).intValue() == 2 || Integer.valueOf(scrollerType).intValue() == 4) {
///* 145 */       aimRichtext.setEffect_in(7);
///*     */     } else {
///* 147 */       aimRichtext.setEffect_in(0);
///*     */     } 
///* 149 */     int type = 0;
///* 150 */     if (path != null && path.trim() != "") {
///* 151 */       aimRichtext.setRtfFileFullPath(path);
///* 152 */       type = 2;
///*     */     } else {
///* 154 */       aimRichtext.setRtfFileFullPath(String.valueOf(filePath) + tempName);
///* 155 */       type = 1;
///*     */     } 
///*     */     
///* 158 */     if (textAlign.equals("center")) {
///*     */       
///* 160 */       aimRichtext.setText_Align(1);
///* 161 */     } else if (textAlign.equals("right")) {
///*     */       
///* 163 */       aimRichtext.setText_Align(2);
///*     */     } else {
///*     */       
///* 166 */       aimRichtext.setText_Align(0);
///*     */     } 
///*     */ 
///*     */     
///* 170 */     aimRichtext.setText_lineShowFlag((new StringBuilder(String.valueOf(Integer.valueOf(textShow).intValue() + 1))).toString());
///*     */ 
///*     */ 
///*     */ 
///*     */     
///* 175 */     int retValue = FileUtil.deleteFileList(filePath, "temp_");
///*     */     
///* 177 */     retValue = LedControlInf.INSTANCE.RtfConvertBmp3_1(aimRichtext.getRtfFileFullPath(), 0, richTextArea.getArea_width(), richTextArea.getArea_height(), Integer.valueOf(textShow).intValue(), 1, richTextArea.getArea_width(), richTextArea.getArea_height(), 0);
///*     */     
///* 179 */     if (retValue > 0) {
///*     */       
///* 181 */       System.out.println("rtf2Images is success!!,retValue=[" + retValue + "]");
///*     */       
///* 183 */       if (filePath != null) {
///*     */         
///* 185 */         File file = new File(filePath);
///*     */         
///* 187 */         String[] strFileList = file.list();
///*     */         
///* 189 */         if (strFileList != null)
///*     */         {
///* 191 */           for (int i = 0; i <= strFileList.length - 1; i++) {
///*     */             
///* 193 */             if (strFileList[i].indexOf(".bmp") != -1 && strFileList[i].startsWith("temp")) {
///* 194 */               fileList.add(String.valueOf(filePath) + strFileList[i]);
///*     */             }
///*     */           } 
///*     */         }
///*     */       } 
///*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void rtf2Images(String textY, String textX, String filePath, String textWidth, String textHeight, List<String> fileList, String scrollerType, String backColor, String textShow, String textAlign, String path) {
///* 204 */     RichTextArea richTextArea = new RichTextArea();
///* 205 */     richTextArea.setArea_x(Integer.valueOf(textX).intValue());
///* 206 */     richTextArea.setArea_y(Integer.valueOf(textY).intValue());
///* 207 */     richTextArea.setArea_width(Integer.valueOf(textWidth).intValue());
///* 208 */     richTextArea.setArea_height(Integer.valueOf(textHeight).intValue());
///* 209 */     richTextArea.setArea_ID("1");
///*     */     
///* 211 */     AreaBackground area_background = new AreaBackground();
///* 212 */     area_background.setArea_background_color((backColor == null || backColor.equals("")) ? "000000" : backColor.substring(1, 7));
///* 213 */     area_background.setArea_background_mode(0);
///* 214 */     richTextArea.setArea_background(area_background);
///*     */     
///* 216 */     Richtext aimRichtext = new Richtext();
///*     */     
///* 218 */     if (Integer.valueOf(scrollerType).intValue() == 2 || Integer.valueOf(scrollerType).intValue() == 4) {
///* 219 */       aimRichtext.setEffect_in(7);
///*     */     } else {
///* 221 */       aimRichtext.setEffect_in(0);
///*     */     } 
///* 223 */     int type = 0;
///* 224 */     if (path != null && path.trim() != "") {
///* 225 */       aimRichtext.setRtfFileFullPath(path);
///* 226 */       type = 2;
///*     */     } else {
///* 228 */       aimRichtext.setRtfFileFullPath(String.valueOf(filePath) + tempName);
///* 229 */       type = 1;
///*     */     } 
///*     */     
///* 232 */     if (textAlign.equals("center")) {
///*     */       
///* 234 */       aimRichtext.setText_Align(1);
///* 235 */     } else if (textAlign.equals("right")) {
///*     */       
///* 237 */       aimRichtext.setText_Align(2);
///*     */     } else {
///*     */       
///* 240 */       aimRichtext.setText_Align(0);
///*     */     } 
///*     */ 
///*     */     
///* 244 */     aimRichtext.setText_lineShowFlag((new StringBuilder(String.valueOf(Integer.valueOf(textShow).intValue() + 1))).toString());
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */     
///* 250 */     RichTextUtil.createRichTextPictures(richTextArea, aimRichtext, fileList, filePath, type);
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
/*     */   public static void text2Rtf(String filePath, String text, String fontSize, String fontFamily, String fontColor, String textStyle) {
/*     */     try {
///* 264 */       Document document = new Document();
///* 265 */       RtfWriter2.getInstance(document, new FileOutputStream(String.valueOf(filePath) + tempName));
///* 266 */       document.open();
///*     */       
///* 268 */       BaseFont bfTimesFont = BaseFont.createFont(String.valueOf(ConfigUtil.getProperties("default.source.path")) + "Fonts" + File.separator + "times.ttf", 
///* 269 */           "Identity-H", false);
///*     */       
///* 271 */       Font timesFont = new Font(bfTimesFont, Integer.valueOf(fontSize).intValue());
///* 272 */       timesFont.setColor(toColorFromString(fontColor));
///*     */       
///* 274 */       if (!StringUtils.isEmpty(textStyle)) {
///* 275 */         timesFont.setStyle(textStyle);
///*     */       }
///* 277 */       BaseFont bfSongTiFont = BaseFont.createFont(String.valueOf(ConfigUtil.getProperties("default.source.path")) + "Fonts" + File.separator + "simsun.ttc,0", 
///* 278 */           "Identity-H", false);
///* 279 */       Font songFont = new Font(bfSongTiFont, Integer.valueOf(fontSize).intValue());
///* 280 */       songFont.setColor(toColorFromString(fontColor));
///*     */       
///* 282 */       if (!StringUtils.isEmpty(textStyle)) {
///* 283 */         songFont.setStyle(textStyle);
///*     */       }
///* 285 */       char[] ch = text.toCharArray();
///* 286 */       for (int i = 0; i < ch.length; i++) {
///* 287 */         char c = ch[i];
///* 288 */         Chunk chunk = new Chunk();
///* 289 */         chunk.append(String.valueOf(c));
///* 290 */         if (!ToolsUtil.isChinese(c)) {
///* 291 */           chunk.setFont(timesFont);
///*     */         } else {
///* 293 */           chunk.setFont(songFont);
///*     */         } 
///* 295 */         document.add((Element)chunk);
///*     */       } 
///* 297 */       document.close();
/* 298 */     } catch (Exception e) {
/*     */       
/* 300 */       e.printStackTrace();
/* 301 */       log.error("text to rtf", e);
/*     */     } 
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
/*     */   public static void myanmarText2Rtf(String filePath, String text, String fontSize, String fontFamily, String fontColor, String textStyle) {
/*     */     try {
///* 316 */       Document document = new Document();
///* 317 */       RtfWriter2.getInstance(document, new FileOutputStream(String.valueOf(filePath) + tempName));
///* 318 */       document.open();
///*     */       
///* 320 */       BaseFont bfTimesFont = BaseFont.createFont(String.valueOf(ConfigUtil.getProperties("default.source.path")) + "Fonts" + File.separator + fontFamily + ".ttf", 
///* 321 */           "Identity-H", false);
///*     */       
///* 323 */       Font timesFont = new Font(bfTimesFont, Integer.valueOf(fontSize).intValue());
///* 324 */       timesFont.setColor(toColorFromString(fontColor));
///*     */       
///* 326 */       if (!StringUtils.isEmpty(textStyle)) {
///* 327 */         timesFont.setStyle(textStyle);
///*     */       }
///* 329 */       BaseFont bfSongTiFont = BaseFont.createFont(String.valueOf(ConfigUtil.getProperties("default.source.path")) + "Fonts" + File.separator + "simsun.ttc,0", 
///* 330 */           "Identity-H", false);
///*     */       
///* 332 */       Font songFont = new Font(bfSongTiFont, Integer.valueOf(fontSize).intValue());
///* 333 */       songFont.setColor(toColorFromString(fontColor));
///*     */       
///* 335 */       if (!StringUtils.isEmpty(textStyle)) {
///* 336 */         songFont.setStyle(textStyle);
///*     */       }
///* 338 */       char[] ch = text.toCharArray();
///* 339 */       for (int i = 0; i < ch.length; i++) {
///* 340 */         char c = ch[i];
///* 341 */         Chunk chunk = new Chunk();
///* 342 */         chunk.append(String.valueOf(c));
///* 343 */         if (!ToolsUtil.isChinese(c)) {
///* 344 */           chunk.setFont(timesFont);
///*     */         } else {
///* 346 */           chunk.setFont(songFont);
///*     */         } 
///* 348 */         document.add((Element)chunk);
///*     */       } 
///* 350 */       document.close();
/* 351 */     } catch (Exception e) {
/*     */       
/* 353 */       e.printStackTrace();
/* 354 */       log.error("text to rtf", e);
/*     */     } 
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
/*     */   public static void othersText2Rtf(String filePath, String text, String fontSize, String fontFamily, String fontColor, String textStyle) {
/*     */     try {
///* 368 */       Document document = new Document();
///* 369 */       RtfWriter2.getInstance(document, new FileOutputStream(String.valueOf(filePath) + tempName));
///* 370 */       document.open();
///*     */       
///* 372 */       BaseFont bfTimesFont = BaseFont.createFont(String.valueOf(ConfigUtil.getProperties("default.source.path")) + "Fonts" + File.separator + fontFamily + ".ttf", 
///* 373 */           "Identity-H", false);
///*     */       
///* 375 */       Font timesFont = new Font(bfTimesFont, Integer.valueOf(fontSize).intValue());
///* 376 */       timesFont.setColor(toColorFromString(fontColor));
///*     */       
///* 378 */       if (!StringUtils.isEmpty(textStyle)) {
///* 379 */         timesFont.setStyle(textStyle);
///*     */       }
///* 381 */       BaseFont bfSongTiFont = BaseFont.createFont(String.valueOf(ConfigUtil.getProperties("default.source.path")) + "Fonts" + File.separator + "simsun.ttc,0", 
///* 382 */           "Identity-H", false);
///*     */       
///* 384 */       Font songFont = new Font(bfSongTiFont, Integer.valueOf(fontSize).intValue());
///* 385 */       songFont.setColor(toColorFromString(fontColor));
///*     */       
///* 387 */       if (!StringUtils.isEmpty(textStyle)) {
///* 388 */         songFont.setStyle(textStyle);
///*     */       }
///* 390 */       char[] ch = text.toCharArray();
///* 391 */       for (int i = 0; i < ch.length; i++) {
///* 392 */         char c = ch[i];
///* 393 */         Chunk chunk = new Chunk();
///* 394 */         chunk.append(String.valueOf(c));
///* 395 */         if (!ToolsUtil.isChinese(c)) {
///* 396 */           chunk.setFont(timesFont);
///*     */         } else {
///* 398 */           chunk.setFont(songFont);
///*     */         } 
///* 400 */         document.add((Element)chunk);
///*     */       } 
///* 402 */       document.close();
/* 403 */     } catch (Exception e) {
/*     */       
/* 405 */       e.printStackTrace();
/* 406 */       log.error("text to rtf", e);
/*     */     } 
/*     */   }
/*     */   public static Color toColorFromString(String colorStr) {
/* 410 */     colorStr = colorStr.substring(1, 7);
/* 411 */     Color color = new Color(Integer.parseInt(colorStr, 16));
/*     */     
/* 413 */     return color;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\liste\\util\Text2ImagesUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */