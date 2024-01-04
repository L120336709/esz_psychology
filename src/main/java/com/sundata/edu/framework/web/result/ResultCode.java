package com.sundata.edu.framework.web.result;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 服务器内部错误
     */
    FAIL(500),
    /**
     * 认证错误
     */
    UNAUTHORIZED(401),
    /**
     * 不存在
     */
    NOT_FOUND(404);

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
