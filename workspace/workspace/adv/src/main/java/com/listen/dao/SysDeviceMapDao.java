package com.listen.dao;

import com.listen.model.sdkservice.SysDeviceMap;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface SysDeviceMapDao extends Mapper<SysDeviceMap> {
  @Select({"SELECT * FROM sys_device_map WHERE dev_code_name = #{devCodeName}"})
  SysDeviceMap getMapByDevCode(@Param("devCodeName") String paramString);
  
  @Insert({"INSERT INTO sys_device_map(dev_code_name,image_url) VALUE(#{devCodeName},#{imageUrl})"})
  void insertDeviceMap(@Param("devCodeName") String paramString1, @Param("imageUrl") String paramString2);
  
  @Update({"UPDATE sys_device_map SET image_url = #{imageUrl} WHERE dev_code_name = #{devCodeName}"})
  void updateDeviceMap(@Param("devCodeName") String paramString1, @Param("imageUrl") String paramString2);
}


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\dao\SysDeviceMapDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */