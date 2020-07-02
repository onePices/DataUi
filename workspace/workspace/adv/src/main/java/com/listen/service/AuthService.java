package com.listen.service;

import com.listen.model.ResponseModel;

public interface AuthService {
  ResponseModel saveAndSendProgram(String paramString1, String paramString2);
  
  ResponseModel saveAndSendProgramNew(String paramString1, String paramString2);
  
  ResponseModel saveAndSendTask(String paramString1, Integer paramInteger, String paramString2);
  
  ResponseModel updateData(String paramString1, String paramString2, String paramString3, String paramString4);
  
  ResponseModel getDeviceList();
  
  ResponseModel addMapDevice(String paramString1, String paramString2);
  
  ResponseModel getMapDeviceList();
}


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\service\AuthService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */