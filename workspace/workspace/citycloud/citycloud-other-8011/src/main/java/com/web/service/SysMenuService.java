package com.web.service;

import java.util.List;

import com.web.entity.SysMenu;


public interface SysMenuService{
    SysMenu selectByPrimaryKey(Integer id);
    List<String> selectByRoleId(Integer roleId);
    List<SysMenu> selectAll();
    boolean deleteByPrimaryKey(Integer id);
    boolean insert(SysMenu record);
    boolean updateByPrimaryKeySelective(SysMenu record);
    boolean updateByPrimaryKey(SysMenu record);
}
