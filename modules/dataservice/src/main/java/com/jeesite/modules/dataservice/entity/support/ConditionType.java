package com.jeesite.modules.dataservice.entity.support;

import java.util.Objects;

public enum ConditionType {

    NORMAL("0", "正常"),
    SUSPECTED("5", "可疑"),
    ABNORMAL("6", "异常");

    private final String code;
    private final String description;

    // 构造函数
    ConditionType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    // 获取code值
    public String getCode() {
        return code;
    }

    // 获取描述
    public String getDescription() {
        return description;
    }

    // 根据code获取ConditionType枚举
    public static ConditionType fromCode(String code) {
        for (ConditionType type : ConditionType.values()) {
            if (Objects.equals(type.getCode(), code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unexpected code: " + code);
    }
}

