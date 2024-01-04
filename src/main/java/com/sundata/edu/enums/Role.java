package com.sundata.edu.enums;

/**
 * 用户角色，1：管理员，2：教师
 */
public enum Role {
    /**
     * 管理员角色-1
     */
    ADMIN(1),

    /**
     * 教师角色-2
     */
    TEACHER(2);

    private int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
