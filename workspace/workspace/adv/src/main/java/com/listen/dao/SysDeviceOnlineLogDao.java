package com.listen.dao;

import com.listen.model.sdkservice.SysDeviceOnlineLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SysDeviceOnlineLogDao extends Mapper<SysDeviceOnlineLog> {
  List<SysDeviceOnlineLog> getSysDeviceOnlineLog();
  
  List<SysDeviceOnlineLog> getSysDeviceOnlineLog2(@Param("devCodeOrMac") String paramString);
}


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\dao\SysDeviceOnlineLogDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */