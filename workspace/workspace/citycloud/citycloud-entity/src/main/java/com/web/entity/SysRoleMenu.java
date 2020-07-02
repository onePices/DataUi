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
public class SysRoleMenu implements Serializable {
    private Integer id;
    private Integer roleId;
    private Integer menuId;
    private String createBy;
    private String createTime;
    private String lastUpdateBy;
    private String lastUpdateTime;

 
}