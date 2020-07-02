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
public class LightingIntel extends Page implements Serializable {
	//主键
    private Integer id;
    //设备类型（监控终端、单灯集中器、单灯控制器等）
    private Integer type;
    //设备名称
    private String name;
    //IP地址或唯一标识
    private String ipdress;
    //设备运转所需的标准电压
    private String voltage;
    //设备运转所需的标准电流
    private String current;
    //设备标准功率
    private Double power;
    //设备品牌
    private String brand;
    //厂家
    private String manufacturer;
    //购入日期
    private String purchasedate;
    //上线运行日期
    private String launchdate;
    //保修期
    private String warrantyperiod;
    //设备位置
    private String position;
    //归哪个用户管
    private Integer inchargeof;
    //所属分组
    private Integer teamid;
    //备注
    private String description;
    
    private String launchBegin;
    private String launchEnd;
    private String dataType;
    
    private List<LightingIntelRecord> lightingIntelRecords;
}