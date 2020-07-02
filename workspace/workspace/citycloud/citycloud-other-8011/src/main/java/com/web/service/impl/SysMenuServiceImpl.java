package com.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.SysMenuMapper;
import com.web.entity.SysMenu;
import com.web.service.SysMenuService;


@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public SysMenu selectByPrimaryKey(Integer id) {
		return sysMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<String> selectByRoleId(Integer roleId) {
		return sysMenuMapper.selectByRoleId(roleId);
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		sysMenuMapper.deleteByPrimaryKey(id);
		return true;
	}

	@Override
	public boolean insert(SysMenu record) {
		if(record.getName()==null || record.getName().trim().equals(""))
			return false;
		if(record.getParentId()==null)
			return false;
		if(record.getUrl()==null || record.getUrl().trim().equals(""))
			return false;
		if(record.getType()==null)
			record.setType(0);
		if(record.getOrderNum()==null)
			record.setOrderNum(0);
		record.setCreateTime(new Date().toString());
		record.setLastUpdateTime(new Date().toString());
		record.setDelFlag(0);
		sysMenuMapper.insert(record);
		return true;
	}

	@Override
	public boolean updateByPrimaryKeySelective(SysMenu record) {
		record.setLastUpdateTime(new Date().toString());
		sysMenuMapper.updateByPrimaryKeySelective(record);
		return true;
	}

	@Override
	public boolean updateByPrimaryKey(SysMenu record) {
		sysMenuMapper.updateByPrimaryKey(record);
		return true;
	}

	@Override
	public List<SysMenu> selectAll() {
		return sysMenuMapper.selectAll();
	}


	
	
}
