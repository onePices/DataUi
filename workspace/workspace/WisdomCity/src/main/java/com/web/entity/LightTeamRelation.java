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
public class LightTeamRelation implements Serializable   {
	private Integer id;
	private Integer teamid;
	private Integer lightid;
}
