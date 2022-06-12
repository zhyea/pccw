package org.chobit.cm.spring;

/**
 * @author robin
 */
public class ApiException  extends RuntimeException {

    private final int errorCode;
    private final String message;

    public ApiException(ApiErrorCode errorCode, String message) {
        this.errorCode = errorCode.code;
        if (null != message && !"".equals(message)) {
            this.message = message;
        } else {
            this.message = errorCode.message;
        }

    }

    public ApiException(ApiErrorCode errorCode) {
        this(errorCode, (String)null);
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }
}
