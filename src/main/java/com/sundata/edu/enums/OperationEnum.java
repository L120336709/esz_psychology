package com.sundata.edu.enums;

/**
 * @Description TODO
 * @Author whj
 * @Date 2018-12-22 11:38
 * @Version 1.0
 */
public enum OperationEnum {
    /**
     * 修改
     */
    UPDATE(1),

    /**
     * 新增
     */
    INSERT(2);

    private final int code;

    OperationEnum(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
