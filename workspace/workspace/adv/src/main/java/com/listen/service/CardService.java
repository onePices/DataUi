package com.listen.service;

import com.listen.model.ResponseModel;
import com.listen.model.ResultBean;
import com.listen.model.TmlDeviceMapImage;
import com.listen.model.query.DeviceQuery;
import com.listen.model.query.LogDeviceAlarmInfo;
import com.listen.model.query.TmlDeviceBasicVo;
import com.listen.model.task.PlayTaskBasicVo;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface CardService {
  void getLogs(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Integer paramInteger);
  
  ResponseModel vertifyNetwork(String paramString1, String paramString2);
  
  ResponseModel getCardProgramInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7);
  
  ResponseModel getCardIndexInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7);
  
  ResponseModel getCardUpdateInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7);
  
  ResponseModel addHeartbeatData(TmlDeviceBasicVo paramTmlDeviceBasicVo);
  
  ResponseModel deviceAlarmCommit(LogDeviceAlarmInfo paramLogDeviceAlarmInfo);
  
  ResponseModel updateTaskStatus(PlayTaskBasicVo paramPlayTaskBasicVo);
  
  ResponseModel updateDownloadSchedule(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);
  
  List<DeviceQuery> getDevList();
  
  List<TmlDeviceMapImage> getMapDevList();
  
  ResultBean getDevList(Integer paramInteger1, Integer paramInteger2, String paramString);
  
  ResultBean getMapDevList(Integer paramInteger1, Integer paramInteger2, String paramString);
  
  ResponseModel getCardInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7);
  
  ResponseModel uploadFile(HttpServletRequest paramHttpServletRequest, String paramString1, @RequestParam("uploadFile") MultipartFile paramMultipartFile, String paramString2, String paramString3) throws ServletException, IOException;
  
  List<DeviceQuery> getDeviceHeartBeatList();
  
  ResultBean getDeviceHeartBeatList(Integer paramInteger1, Integer paramInteger2, String paramString);
  
  List<PlayTaskBasicVo> getDeviceTaskUpdateList();
  
  ResultBean getDeviceTaskUpdateList(Integer paramInteger1, Integer paramInteger2, String paramString);
}


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\service\CardService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */