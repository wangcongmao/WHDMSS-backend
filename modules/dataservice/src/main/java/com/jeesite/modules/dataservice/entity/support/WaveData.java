package com.jeesite.modules.dataservice.entity.support;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class WaveData {
    @ExcelProperty("Time")
    private String time;

    @ExcelProperty("Hs")
    private Double hs;

    @ExcelProperty("Te")
    private Double te;

    @ExcelProperty("实际功率")
    private Double actualPower;
}