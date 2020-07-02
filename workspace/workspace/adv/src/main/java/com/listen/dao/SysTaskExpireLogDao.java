package com.listen.dao;

import com.listen.model.sdkservice.SysTaskExpireLog;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface SysTaskExpireLogDao extends Mapper<SysTaskExpireLog> {
  List<SysTaskExpireLog> getSysTaskExpireLog(@Param("devCodeName") String paramString, @Param("startTime") long paramLong1, @Param("endTime") long paramLong2);
  
  @Delete({"DELETE FROM sys_task_expire_log WHERE remark = #{strTempRedisKey}"})
  void delSysTaskExpireLogByCode(@Param("strTempRedisKey") String paramString);
  
  @Select({"SELECT * FROM sys_task_expire_log WHERE remark = #{devCodeName}"})
  SysTaskExpireLog getLogByDevCodeName(@Param("devCodeName") String paramString);
}


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\dao\SysTaskExpireLogDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */