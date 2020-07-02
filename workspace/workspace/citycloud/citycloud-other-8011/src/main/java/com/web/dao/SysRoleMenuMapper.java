package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.entity.SysRoleMenu;
@Mapper
public interface SysRoleMenuMapper {
    void deleteByRoleMene(SysRoleMenu sysRoleMenu);
    void deleteByRoleId(Integer roleId);
    void insert(SysRoleMenu sysRoleMenu);
    List<SysRoleMenu> findRoleMenus(Integer roleId);
}