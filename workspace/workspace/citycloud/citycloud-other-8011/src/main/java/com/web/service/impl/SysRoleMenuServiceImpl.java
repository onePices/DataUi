package com.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.SysRoleMenuMapper;
import com.web.entity.SysRoleMenu;
import com.web.service.SysRoleMenuService;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	@Override
	public void deleteByRoleMene(SysRoleMenu sysRoleMenu) {
		sysRoleMenuMapper.deleteByRoleMene(sysRoleMenu);

	}

	@Override
	public void deleteByRoleId(Integer roleId) {
		sysRoleMenuMapper.deleteByRoleId(roleId);
	}

	@Override
	public boolean insert(SysRoleMenu sysRoleMenu) {
		if(sysRoleMenu.getRoleId()==null)
			return false;
		if(sysRoleMenu.getMenuId()==null)
			return false;
		sysRoleMenu.setCreateTime(new Date().toString());
		sysRoleMenu.setLastUpdateTime(new Date().toString());
		sysRoleMenuMapper.insert(sysRoleMenu);
		return true;
	}

	@Override
	public List<SysRoleMenu> findRoleMenus(Integer roleId) {
		return sysRoleMenuMapper.findRoleMenus(roleId);
	}

}
