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
public class SysMenu implements Serializable{
    private Integer id;
    private String name;
    private Integer parentId;
    private String url;
    private String perms;
    private Integer type;
    private String icon;
    private Integer orderNum;
    private String createBy;
    private String createTime;
    private String lastUpdateBy;
    private String lastUpdateTime;
    private Integer delFlag;

}