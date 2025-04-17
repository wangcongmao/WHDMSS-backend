package com.jeesite.modules.dataservice.entity.support;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class TidalData {
    @ExcelProperty("Pflux")
    private String Pflux;

    @ExcelProperty("p")
    private Double p;

    @ExcelProperty("v")
    private Double v;

    @ExcelProperty("实际功率")
    private Double actualPower;
}
