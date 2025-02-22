package com.jeesite.modules.dataservice.entity.support;

import lombok.Data;

@Data
public class ParamDataCondition {
    private String paramName;
    private int normalCount;
    private int uncertainCount;
    private int errorCount;

    public ParamDataCondition() {
        paramName = "";
        normalCount = 0;
        uncertainCount = 0;
        errorCount = 0;
    }

    public ParamDataCondition(ParamDataCondition paramDataCondition) {
        setParamName(paramDataCondition.getParamName());
        setNormalCount(paramDataCondition.getNormalCount());
        setUncertainCount(paramDataCondition.getUncertainCount());
        setErrorCount(paramDataCondition.getErrorCount());
    }
}
