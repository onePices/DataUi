package com.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.web.entity.SysRole;
import com.web.entity.SysUser;
import com.web.entity.SysUserRole;
import com.web.service.SysMenuService;
import com.web.service.SysRoleService;
import com.web.service.SysUserRoleService;
import com.web.service.SysUserService;

@RestController
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	
	/**
	 * 添加用户信息
	 * @param sysUser 用户信息
	 * @return
	 */
	@RequestMapping(value="sysuser/add", method=RequestMethod.POST)
	public boolean add(HttpSession session, @ModelAttribute("sysUser") SysUser sysUser) {
		SysUser nowUser = (SysUser) session.getAttribute("user");
		sysUser.setCreateBy(nowUser.getNickName());
		sysUser.setLastUpdateBy(nowUser.getNickName());
		return sysUserService.insert(sysUser);
	}
	
	
	/**
	 * 修改用户信息
	 * @param sysUser 用户信息
	 * @return
	 */
	@RequestMapping(value="sysuser/update", method=RequestMethod.POST)
	public boolean update(HttpSession session, @ModelAttribute("sysUser") SysUser sysUser) {
		SysUser nowUser = (SysUser) session.getAttribute("user");
		sysUser.setLastUpdateBy(nowUser.getNickName());
		return sysUserService.updateByPrimaryKeySelective(sysUser);
	}
	

	
	/**
	 * 根据条件分页查询用户
	 * @param sysUser 查询条件
	 * @return
	 */
	@RequestMapping(value="sysuser/getByPage", method=RequestMethod.GET)
	public List<SysUser> getByPage(@ModelAttribute("sysUser") SysUser sysUser) {
		return sysUserService.selectUserByPage(sysUser);
	}
	
	
	
	/**
	 * 登录
	 * @param sysUser 用户信息
	 * @return
	 */
	@RequestMapping(value="sysuser/login", method=RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "processHystrix_Login")
	public String login(HttpSession session, @ModelAttribute("sysUser") SysUser sysUser) {
		SysUser result = sysUserService.selectByName(sysUser.getName());
		if(result==null) {
			return "登录失败，用户名或密码错误";
		}else {
			if(result.getPassword().equals(sysUser.getPassword())) {
				List<SysRole> roles = sysRoleService.selectByUserId(result.getId());
				List<String> menus = new ArrayList<String>();
				for(int i=0; i<roles.size(); i++)
					menus.addAll(sysMenuService.selectByRoleId(roles.get(i).getId()));
				result.setSysRole(roles);
				result.setSysMenu(menus);
				session.setAttribute("user", result);
				return "登录成功";
			}else {
				return "登录失败，用户名或密码错误";
			}
		}
	}
	
	/**
	 * 登出
	 * @param sysUser 用户信息
	 * @return
	 */
	@RequestMapping(value="sysuser/loginout", method=RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "processHystrix_Login")
	public String loginout(HttpSession session, @ModelAttribute("sysUser") SysUser sysUser) {
		session.removeAttribute("user");
		return "登出成功";
	}
	
	
	/**
	 * 根据用户查询权限
	 * @param sysUser 用户信息
	 * @return
	 */
	@RequestMapping(value="sysuser/findUserRole", method=RequestMethod.GET)
	public List<SysUserRole> findUserRole(@ModelAttribute("sysUserRole") SysUserRole sysUserRole) {
		return sysUserRoleService.findUserRole(sysUserRole.getUserId());
	}
	
	
	
	/**
	 * 根据用户查询权限
	 * @param sysUser 用户信息
	 * @return
	 */
	@RequestMapping(value="sysuser/deleteByUserRole", method=RequestMethod.POST)
	public String deleteByUserRole(@ModelAttribute("sysUserRole") SysUserRole sysUserRole) {
		sysUserRoleService.deleteByUserRole(sysUserRole);
		return "删除成功";
	}

	
	
	/**
	 * 根据用户_权限对应关系 删除用户_权限对应关系表数据
	 * @param sysUser 用户信息
	 * @return
	 */
	@RequestMapping(value="sysuser/deleteByUserId", method=RequestMethod.POST)
	public String deleteByUserId(@ModelAttribute("sysUserRole") SysUserRole sysUserRole) {
		sysUserRoleService.deleteByUserId(sysUserRole.getUserId());
		return "删除成功";
	}

	
	
	/**
	 * 添加用户_权限对应关系数据
	 * @param sysUser 用户信息
	 * @return
	 */
	@RequestMapping(value="sysuser/addUserRole", method=RequestMethod.POST)
	public String addUserRole(HttpSession session, @ModelAttribute("sysUserRole") SysUserRole sysUserRole) {
		SysUser nowUser = (SysUser) session.getAttribute("user");
		sysUserRole.setCreateBy(nowUser.getNickName());
		sysUserRole.setLastUpdateBy(nowUser.getNickName());
		sysUserRoleService.insert(sysUserRole);
		return "删除成功";
	}
	

    public String processHystrix_Login(HttpSession session, @ModelAttribute("sysUser") SysUser sysUser) {
        return "登录组件负载过大，暂停服务";
    }

	
}
