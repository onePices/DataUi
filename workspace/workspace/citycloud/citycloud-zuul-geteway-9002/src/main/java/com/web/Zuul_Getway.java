package com.web;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.web.filter.VisitorRoleFilter;

@SpringBootApplication
@EnableZuulProxy
public class Zuul_Getway {

	public static void main(String[] args) {
		SpringApplication.run(Zuul_Getway.class,args);
	}
	

	@Bean
	public VisitorRoleFilter visitorRoleFilter() {
	    return new VisitorRoleFilter();
	}
}
