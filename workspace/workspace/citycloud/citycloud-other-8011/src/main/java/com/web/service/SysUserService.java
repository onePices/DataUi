package com.web.service;

import java.util.List;

import com.web.entity.SysUser;

public interface SysUserService {
	SysUser selectByPrimaryKey(Integer id);
	SysUser selectByName(String name);
	List<SysUser> selectUserByPage(SysUser sysuser);
	List<SysUser> findAll();
	boolean deleteByPrimaryKey(Integer id);
	boolean insert(SysUser sysuser);
	boolean updateByPrimaryKeySelective(SysUser sysuser);
	boolean updateByPrimaryKey(SysUser sysuser);
}
