package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.entity.SysUserRole;
@Mapper
public interface SysUserRoleMapper {
	void deleteByUserRole(SysUserRole sysUserRole);
	void deleteByUserId(Integer userId);
    void insert(SysUserRole sysUserRole);
    List<SysUserRole> findUserRole(Integer userId);
}