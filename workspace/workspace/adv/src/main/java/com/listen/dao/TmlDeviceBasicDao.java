package com.listen.dao;

import com.listen.model.sdkservice.TmlDeviceBasic;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface TmlDeviceBasicDao extends Mapper<TmlDeviceBasic> {
  List<TmlDeviceBasic> getTmlDeviceBasic(@Param("devCodeOrMac") String paramString);
  
  @Select({"SELECT * FROM tml_device_basic WHERE dev_mac = #{devMac}"})
  TmlDeviceBasic getDeviceByMac(@Param("devMac") String paramString);
  
  @Select({"SELECT * FROM tml_device_basic WHERE 1=1 ORDER BY dev_id DESC LIMIT 1"})
  TmlDeviceBasic getLastDevice();
}


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\dao\TmlDeviceBasicDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */