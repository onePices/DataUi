package com.web.service;

import java.util.List;

import com.web.entity.SysRole;



/**
 * 角色管理
 * @author Louis
 * @date Jan 13, 2019
 */
public interface SysRoleService{

	List<SysRole> selectByUserId(Integer userId);
    SysRole selectByPrimaryKey(Integer id);
    List<SysRole> selectRoleByPage(SysRole record);
    boolean deleteByPrimaryKey(Integer id);
    boolean insert(SysRole record);
    boolean updateByPrimaryKeySelective(SysRole record);
    boolean updateByPrimaryKey(SysRole record);

}
