package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.entity.SysUser;

@Mapper
public interface SysUserMapper {
	SysUser selectByPrimaryKey(Integer id);
	SysUser selectByName(String name);
	List<SysUser> selectUserByPage(SysUser sysuser);
	List<SysUser> findAll();
	void deleteByPrimaryKey(Integer id);
	void insert(SysUser sysuser);
	void updateByPrimaryKeySelective(SysUser sysuser);
	void updateByPrimaryKey(SysUser sysuser);
}