package com.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.SysRoleMapper;
import com.web.entity.SysRole;
import com.web.service.SysRoleService;


@Service
public class SysRoleServiceImpl  implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public List<SysRole> selectByUserId(Integer userId) {
		return sysRoleMapper.selectByUserId(userId);
	}

	@Override
	public SysRole selectByPrimaryKey(Integer id) {
		return sysRoleMapper.selectByPrimaryKey(id);
	}

	public boolean deleteByPrimaryKey(Integer id) {
    	sysRoleMapper.deleteByPrimaryKey(id);
    	return true;
    }
    
	public boolean insert(SysRole record) {
		if(record.getName()==null || record.getName().trim().equals(""))
			return false;
		if(record.getRemark()==null || record.getRemark().trim().equals(""))
			return false;
		record.setCreateTime(new Date().toString());
		record.setLastUpdateTime(new Date().toString());
		record.setDelFlag(0);
		sysRoleMapper.insert(record);
		return true;
    }
    
	public boolean updateByPrimaryKeySelective(SysRole record) {
		record.setLastUpdateTime(new Date().toString());
		sysRoleMapper.updateByPrimaryKeySelective(record);
		return true;
    }
    
	public boolean updateByPrimaryKey(SysRole record) {
		sysRoleMapper.updateByPrimaryKey(record);
		return true;
    }

	@Override
	public List<SysRole> selectRoleByPage(SysRole record) {
		record.countFirstResult();
		return sysRoleMapper.selectRoleByPage(record);
	}

	
}
