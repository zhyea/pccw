package org.chobit.cm.spring.response;

/**
 * @author robin
 */
public enum ApiErrorCode {

    /**
     * 请求错误
     */
    BAD_REQUEST(10000, "请求错误"),
    /**
     * 未授权
     */
    UNAUTHORIZED(10001, "未授权"),
    /**
     * 无效的授权信息
     */
    INVALID_GRANT(10002, "无效的授权信息"),
    /**
     * 缺失必要参数
     */
    MISSING_PARAMETER(10003, "缺失必要参数"),
    /**
     * 内部错误
     */
    INTERNAL_ERROR(50000, "内部错误");

    public final int code;
    public final String message;

    private ApiErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
