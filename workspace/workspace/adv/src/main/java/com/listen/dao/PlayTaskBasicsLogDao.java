package com.listen.dao;

import com.listen.model.sdkservice.PlayTaskBasicsLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface PlayTaskBasicsLogDao extends Mapper<PlayTaskBasicsLog> {
  List<PlayTaskBasicsLog> getPlayTaskBasicsLog();
  
  List<PlayTaskBasicsLog> getPlayTaskBasicsLog2(@Param("devCode") String paramString);
}


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\dao\PlayTaskBasicsLogDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */