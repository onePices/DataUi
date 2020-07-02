package com.listen.service;

import javax.servlet.http.HttpServletRequest;

public interface MapCardService {
  String getPicturePath(String paramString, HttpServletRequest paramHttpServletRequest);
  
  void saveMapImageInfo();
  
  void saveNewMapImageInfo();
}


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\service\MapCardService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */