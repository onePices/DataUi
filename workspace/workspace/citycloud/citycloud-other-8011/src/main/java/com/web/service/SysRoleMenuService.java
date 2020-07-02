package com.web.service;

import java.util.List;

import com.web.entity.SysRoleMenu;

public interface SysRoleMenuService {
    void deleteByRoleMene(SysRoleMenu sysRoleMenu);
    void deleteByRoleId(Integer roleId);
    boolean insert(SysRoleMenu sysRoleMenu);
    List<SysRoleMenu> findRoleMenus(Integer roleId);
}
