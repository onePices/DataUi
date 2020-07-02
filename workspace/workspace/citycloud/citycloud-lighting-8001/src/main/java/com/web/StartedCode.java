package com.web;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
public class StartedCode implements ServletContextAware {


	public void setServletContext(ServletContext servletContext) {
//		new Thread(new TcpReceiveMsg()).start();
	}

}
