package com.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.entity.SysRole;

@Mapper
public interface SysRoleMapper {

	List<SysRole> selectByUserId(Integer userId);
	List<SysRole> selectRoleByPage(SysRole record);
    SysRole selectByPrimaryKey(Integer id);
    void deleteByPrimaryKey(Integer id);
    void insert(SysRole record);
    void updateByPrimaryKeySelective(SysRole record);
    void updateByPrimaryKey(SysRole record);
    
    List<SysRole> findPage();

	List<SysRole> findAll();
	
	List<SysRole> findPageByName(@Param(value="name") String name);
	
	List<SysRole> findByName(@Param(value="name") String name);
}