package com.jeesite.modules.dataservice.entity;

import lombok.Data;

@Data
public class DevicesDataQuality {
    private String name;
    private Integer normalCount;
    private Integer uncertainCount;
    private Integer errorCount;

    public DevicesDataQuality(){
        name = "";
        normalCount = 0;
        uncertainCount = 0;
        errorCount = 0;
    }
    public DevicesDataQuality(DevicesDataQuality devicesDataQuality) {
        setName(devicesDataQuality.getName());
        setNormalCount(devicesDataQuality.getNormalCount());
        setUncertainCount(devicesDataQuality.getUncertainCount());
        setErrorCount(devicesDataQuality.getErrorCount());

    }
}
