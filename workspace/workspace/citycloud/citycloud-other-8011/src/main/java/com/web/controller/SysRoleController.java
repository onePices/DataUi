package com.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.entity.SysRole;
import com.web.entity.SysRoleMenu;
import com.web.entity.SysUser;
import com.web.service.SysRoleMenuService;
import com.web.service.SysRoleService;

@RestController
public class SysRoleController {
	
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	
	/**
	 * 添加权限信息
	 * @param sysUser 权限信息
	 * @return
	 */
	@RequestMapping(value="sysrole/add", method=RequestMethod.POST)
	public boolean add(HttpSession session, @ModelAttribute("sysRole") SysRole sysRole) {
		SysUser nowUser = (SysUser) session.getAttribute("user");
		sysRole.setCreateBy(nowUser.getNickName());
		sysRole.setLastUpdateBy(nowUser.getNickName());
		return sysRoleService.insert(sysRole);
	}
	
	
	/**
	 * 修改权限信息
	 * @param sysUser 权限信息
	 * @return
	 */
	@RequestMapping(value="sysrole/update", method=RequestMethod.POST)
	public boolean update(HttpSession session, @ModelAttribute("sysRole") SysRole sysRole) {
		SysUser nowUser = (SysUser) session.getAttribute("user");
		sysRole.setLastUpdateBy(nowUser.getNickName());
		return sysRoleService.updateByPrimaryKeySelective(sysRole);
	}
	

	
	/**
	 * 分页查询权限信息
	 * @param sysUser 权限信息
	 * @return
	 */
	@RequestMapping(value="sysrole/getByPage", method=RequestMethod.POST)
	public List<SysRole> getByPage(@ModelAttribute("sysRole") SysRole sysRole) {
		return sysRoleService.selectRoleByPage(sysRole);
	}

	
	
	/**
	 * 根据权限查询 权限_菜单对应关系
	 * @param sysUser 权限信息
	 * @return
	 */
	@RequestMapping(value="sysrole/getAllMenu", method=RequestMethod.POST)
	public List<SysRoleMenu> getAllMenu(@ModelAttribute("sysRole") SysRole sysRole) {
		return sysRoleMenuService.findRoleMenus(sysRole.getId());
	}

	
	
	/**
	 * 删除权限_菜单对应关系
	 * @param sysUser 权限信息
	 * @return
	 */
	@RequestMapping(value="sysrole/deleteByRoleMene", method=RequestMethod.POST)
	public String deleteByRoleMene(@ModelAttribute("sysRoleMenu") SysRoleMenu sysRoleMenu) {
		sysRoleMenuService.deleteByRoleMene(sysRoleMenu);
		return "删除成功";
	}

	
	
	/**
	 * 根据权限删除 权限_菜单对应关系
	 * @param sysUser 权限信息
	 * @return
	 */
	@RequestMapping(value="sysrole/deleteMenuByRoleId", method=RequestMethod.POST)
	public String deleteMenuByRoleId(@ModelAttribute("sysRoleMenu") SysRoleMenu sysRoleMenu) {
		sysRoleMenuService.deleteByRoleId(sysRoleMenu.getId());
		return "删除成功";
	}

	
	/**
	 * 添加权限菜单对应关系
	 * @param sysUser 权限信息
	 * @return
	 */
	@RequestMapping(value="sysrole/addRoleMenu", method=RequestMethod.POST)
	public String addRoleMenu(HttpSession session, @ModelAttribute("sysRoleMenu") SysRoleMenu sysRoleMenu) {
		SysUser nowUser = (SysUser) session.getAttribute("user");
		sysRoleMenu.setCreateBy(nowUser.getNickName());
		sysRoleMenu.setLastUpdateBy(nowUser.getNickName());
		sysRoleMenuService.insert(sysRoleMenu);
		return "删除成功";
	}
	

	
}
