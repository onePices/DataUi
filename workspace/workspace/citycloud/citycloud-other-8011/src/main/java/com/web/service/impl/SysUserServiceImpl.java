package com.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dao.SysUserMapper;
import com.web.entity.SysUser;
import com.web.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {
    
    @Autowired
    private SysUserMapper sysUserMapper;
    

    public SysUser selectByPrimaryKey(Integer id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}
	
    public SysUser selectByName(String name) {
		return sysUserMapper.selectByName(name);
	}

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		sysUserMapper.deleteByPrimaryKey(id);
		return true;
	}

	@Override
	public boolean insert(SysUser sysuser) {
		if(sysuser.getName()==null || sysuser.getName().trim().equals(""))
			return false;
		if(sysuser.getNickName()==null || sysuser.getNickName().trim().equals(""))
			return false;
		if(sysuser.getMobile()==null || sysuser.getMobile().trim().equals(""))
			return false;
		if(sysuser.getPassword()==null || sysuser.getPassword().trim().equals(""))
			return false;
		sysuser.setSexuality(1);
		sysuser.setCreateTime(new Date().toString());
		sysuser.setLastUpdateTime(new Date().toString());
		sysuser.setDelFlag(0);
		sysUserMapper.insert(sysuser);
		return true;
	}

	@Override
	public boolean updateByPrimaryKeySelective(SysUser sysuser) {
		sysuser.setLastUpdateTime(new Date().toString());
		sysUserMapper.updateByPrimaryKeySelective(sysuser);
		return true;
	}

	@Override
	public boolean updateByPrimaryKey(SysUser sysuser) {
		sysUserMapper.updateByPrimaryKey(sysuser);
		return true;
	}

	@Override
	public List<SysUser> selectUserByPage(SysUser sysuser) {
		sysuser.countFirstResult();
		return sysUserMapper.selectUserByPage(sysuser);
	}
}