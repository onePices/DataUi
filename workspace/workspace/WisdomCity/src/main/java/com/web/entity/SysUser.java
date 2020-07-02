package com.web.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SysUser extends Page implements Serializable {
    private Integer id;
    private String name;
    private String nickName;
    private String avatar;
    private String password;
    private String email;
    private String mobile;
    private Integer sexuality;
    private String createBy;
    private String createTime;
    private String lastUpdateBy;
    private String lastUpdateTime;
    private Integer delFlag;

    private List<SysRole> sysRole;
    private List<String> sysMenu;
    
    private String createTimeBegin;
    private String createTimeEnd;
    
}