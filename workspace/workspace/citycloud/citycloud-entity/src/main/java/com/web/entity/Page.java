package com.web.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Page {
	private Integer pageSize;
	private Integer page;
	private Integer totalPage;
	private Integer firstResult;
	
	public Integer countFirstResult() {
		this.setFirstResult((page-1)*pageSize) ;
		return firstResult;
	}
}
