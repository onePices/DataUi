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
public class SysRole extends Page implements Serializable{
    private Integer id;
    private String name;
    private String remark;
    private String createBy;
    private String createTime;
    private String lastUpdateBy;
    private String lastUpdateTime;
    private Integer delFlag;
 
    private String createTimeBegin;
    private String createTimeEnd;
}