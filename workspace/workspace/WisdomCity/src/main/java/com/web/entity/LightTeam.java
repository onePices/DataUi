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
public class LightTeam extends Page implements Serializable   {
	private Integer id;
	private String name;
	private Integer parentid;
	private Integer strategyid;
	private Integer level;
	private String position;
	private Integer inchargeof;
	private String description;

}
