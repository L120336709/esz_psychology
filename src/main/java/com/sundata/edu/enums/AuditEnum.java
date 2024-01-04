package com.sundata.edu.enums;

/**
 * @Description TODO
 * @Author whj
 * @Date 2018-12-22 11:38
 * @Version 1.0
 */
public enum AuditEnum {
    /**
     * 修改
     */
    UPDATE(1),

    /**
     * 审核成功
     */
    SUCCESS(2),

    /**
     * 审核失败
     */
    FAIL(3);

    private final int code;

    AuditEnum(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
