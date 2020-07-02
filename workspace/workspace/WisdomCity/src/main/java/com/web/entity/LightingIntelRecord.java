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
public class LightingIntelRecord extends Page implements Serializable {
    private Integer id;
    private Integer lightid;
    private String voltage;
    private String current;
    private Double power;
    private Double percent;
    private Double energyconsum;
    private String offon;
    private String intelstatus;
    private String status;
    private String createdate;
    
    private String powerBegin;
    private String powerEnd;
    private Double percentBegin;
    private Double percentEnd;
    private String energyBegin;
    private String energyEnd;
}