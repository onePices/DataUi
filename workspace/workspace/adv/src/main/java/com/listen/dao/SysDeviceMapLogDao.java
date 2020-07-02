package com.listen.dao;

import com.listen.model.sdkservice.SysDeviceMapLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SysDeviceMapLogDao extends Mapper<SysDeviceMapLog> {
  List<SysDeviceMapLog> getDevMapLogList(@Param("devCode") String paramString);
}


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\dao\SysDeviceMapLogDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */