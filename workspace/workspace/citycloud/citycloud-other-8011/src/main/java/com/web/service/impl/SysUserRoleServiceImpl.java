package com.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.SysUserRoleMapper;
import com.web.entity.SysUserRole;
import com.web.service.SysUserRoleService;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public void deleteByUserRole(SysUserRole sysUserRole) {
		sysUserRoleMapper.deleteByUserRole(sysUserRole);
	}

	@Override
	public void deleteByUserId(Integer userId) {
		sysUserRoleMapper.deleteByUserId(userId);
	}

	@Override
	public boolean insert(SysUserRole sysUserRole) {
		if(sysUserRole.getRoleId()==null)
			return false;
		if(sysUserRole.getUserId()==null)
			return false;
		sysUserRole.setCreateTime(new Date().toString());
		sysUserRole.setLastUpdateTime(new Date().toString());
		sysUserRoleMapper.insert(sysUserRole);
		return true;
	}

	@Override
	public List<SysUserRole> findUserRole(Integer userId) {
		return sysUserRoleMapper.findUserRole(userId);
	}

}
