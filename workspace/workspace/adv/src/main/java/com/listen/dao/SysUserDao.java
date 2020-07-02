package com.listen.dao;

import com.listen.model.user.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface SysUserDao extends Mapper<SysUser> {
  @Select({"SELECT * FROM sys_user WHERE user_name = #{userName}"})
  SysUser getSysUser(@Param("userName") String paramString);
  
  @Update({"UPDATE sys_user SET password = #{password} WHERE user_id = #{userId}"})
  void updatePassword(@Param("userId") Integer paramInteger, @Param("password") String paramString);
}


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\dao\SysUserDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */