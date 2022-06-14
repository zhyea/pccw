package org.chobit.cm.common.constants;

public enum UserState {

    /**
     * 已创建
     */
    INIT(0),
    /**
     * 已发出通知验证
     */
    INFORMED(1),
    /**
     * 已验证
     */
    VERIFIED(2),
    ;


    public final int code;

    UserState(int code) {
        this.code = code;
    }
}
