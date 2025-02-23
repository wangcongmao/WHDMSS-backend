package com.jeesite.modules.dataservice.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DataserviceDeviceDataEverydayCountsVO {
    private String countsDeviceId;		// 设备id
    private Date countsDate;		// 日期
    private Integer countsDataCount;		// 数据量
}
