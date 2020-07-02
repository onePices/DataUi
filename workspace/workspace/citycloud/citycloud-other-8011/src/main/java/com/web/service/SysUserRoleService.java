package com.web.service;

import java.util.List;

import com.web.entity.SysUserRole;

public interface SysUserRoleService {
	void deleteByUserRole(SysUserRole sysUserRole);
	void deleteByUserId(Integer userId);
    boolean insert(SysUserRole sysUserRole);
    List<SysUserRole> findUserRole(Integer userId);
}
