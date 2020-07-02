package com.web.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.web.entity.SysUser;

public class VisitorRoleFilter extends ZuulFilter {

	
	public boolean shouldFilter() {
		return true;
	}

	
	public String filterType() {
		return "pre";
	}

	
	public int filterOrder() {
		return 0;
	}
	


	/**
	 * 权限验证+单点登录
	 */
	public Object run() {
		  RequestContext ctx = RequestContext.getCurrentContext();
	        HttpServletRequest request = ctx.getRequest();
	        HttpSession session = request.getSession();
	        SysUser sysUser = (SysUser) session.getAttribute("user");
	        boolean stop = true;
	        if(sysUser!=null && sysUser.getSysMenu()!=null) {
		        List<String> menus = sysUser.getSysMenu();
		        if(menus.contains(request.getServletPath()))
		        	stop=false;
	        }
	        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	            return null;
	        }
	        if (stop) {
	            ctx.setSendZuulResponse(false);
	            ctx.setResponseStatusCode(401);
	            ctx.setResponseBody("{\"status\":401,\"msg\":\"user is not login !\"}");
	            ctx.getResponse().setContentType("text/html;charset=UTF-8");
	        }
	        return null;
	}

}
