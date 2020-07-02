package com.web.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class LightStrategy extends Page implements Serializable  {
	private Integer id;
	private String name;
	private String begin;
	private String end;
	private Double brightpercent;
	private String createdate;
	private String description;
}
