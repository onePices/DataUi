package com.listen.service;

import com.listen.model.ResultBean;
import com.listen.model.sdkservice.ConfigService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface SysUserService {
  ResultBean logIn(HttpSession paramHttpSession, String paramString1, String paramString2);
  
  ResultBean logOut(HttpServletRequest paramHttpServletRequest);
  
  ResultBean updatepassword(HttpServletRequest paramHttpServletRequest, String paramString1, String paramString2);
  
  ResultBean getConfigureList();
  
  ResultBean saveOrUpdateConfigure(ConfigService paramConfigService);
}


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\service\SysUserService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */