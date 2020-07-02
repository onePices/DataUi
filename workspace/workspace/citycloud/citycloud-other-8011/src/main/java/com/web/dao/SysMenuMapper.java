package com.web.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.entity.SysMenu;

@Mapper
public interface SysMenuMapper {
    SysMenu selectByPrimaryKey(Integer id);
    List<String> selectByRoleId(Integer roleId);
    List<SysMenu> selectAll();
    int deleteByPrimaryKey(Integer id);
    int insert(SysMenu record);
    int updateByPrimaryKeySelective(SysMenu record);
    int updateByPrimaryKey(SysMenu record);
    
}