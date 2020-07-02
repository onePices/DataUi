package com.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.entity.SysMenu;
import com.web.entity.SysUser;
import com.web.service.SysMenuService;

@RestController
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;
	
	
	/**
	 * 添加菜单
	 * @param session
	 * @param sysMenu
	 * @return
	 */
	@RequestMapping(value="sysmenu/add", method=RequestMethod.POST)
	public boolean add(HttpSession session,  @ModelAttribute("sysMenu") SysMenu sysMenu) {
		SysUser nowUser = (SysUser) session.getAttribute("user");
		sysMenu.setCreateBy(nowUser.getNickName());
		sysMenu.setLastUpdateBy(nowUser.getNickName());
		return sysMenuService.insert(sysMenu);
	}
	
	
	/**
	 * 修改菜单
	 * @param session
	 * @param sysMenu
	 * @return
	 */
	@RequestMapping(value="sysmenu/update", method=RequestMethod.POST)
	public boolean update(HttpSession session, @ModelAttribute("sysMenu") SysMenu sysMenu) {
		SysUser nowUser = (SysUser) session.getAttribute("user");
		sysMenu.setLastUpdateBy(nowUser.getNickName());
		return sysMenuService.updateByPrimaryKeySelective(sysMenu);
	}
	

	/**
	 * 查询所有菜单
	 * @return
	 */
	@RequestMapping(value="sysmenu/getAll", method=RequestMethod.POST)
	public List<SysMenu> getByPage() {
		return sysMenuService.selectAll();
	}
	
	



	
}
