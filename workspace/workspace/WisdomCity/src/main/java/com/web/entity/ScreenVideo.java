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
public class ScreenVideo extends Page implements Serializable {
	//主键
    private Integer id;
    //ID编号
    private String number;
    //设备名称
    private String name;
    //素材路径
    private String serverurl;
    //创建时间
    private String createdate;
    //最后修改时间
    private String updatedate;
    private String iconurl;

}