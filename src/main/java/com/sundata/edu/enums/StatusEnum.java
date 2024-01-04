package com.sundata.edu.enums;

/**
 * @Description TODO
 * @Author whj
 * @Date 2018-12-22 11:38
 * @Version 1.0
 */
public enum StatusEnum {
    /**
     * 不可用
     */
    DISABLED(0),

    /**
     * 可用
     */
    AVAILABLE(1);

    private final int code;

    StatusEnum(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
