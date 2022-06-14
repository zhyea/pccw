package org.chobit.cm.tools;


import org.chobit.cm.spring.response.ApiException;

import static org.chobit.cm.spring.response.ApiErrorCode.BAD_REQUEST;
import static org.chobit.common.utils.StrKit.isBlank;

/**
 * 参数处理工具类
 *
 * @author zhangrui137
 */
public final class Args {

    /**
     * 判断是否需要抛出ApiException
     *
     * @param result 是否需要抛出异常
     * @param errMsg 异常信息
     */
    public static void check(boolean result, String errMsg) {
        if (result) {
            throw new ApiException(BAD_REQUEST, errMsg);
        }
    }

    /**
     * 判断对象为null，如果不为null则抛出异常
     *
     * @param src    要判断的对象
     * @param errMsg 错误信息
     * @param <T>    源对象类型
     */
    public static <T> void checkNull(T src, String errMsg) {
        check(null != src, errMsg);
    }


    /**
     * 判断对象不为null，如果为null则抛出异常
     *
     * @param src    要判断的对象
     * @param errMsg 错误信息
     * @param <T>    源对象类型
     */
    public static <T> void checkNotNull(T src, String errMsg) {
        check(null == src, errMsg);
    }

    /**
     * 判断对象不为空，如果为空则抛出异常
     *
     * @param src    要判断的对象
     * @param errMsg 错误信息
     */
    public static void checkNotBlank(String src, String errMsg) {
        check(isBlank(src), errMsg);
    }

    /**
     * 判断数值大于0，如果不大于0则抛出异常
     *
     * @param src    要判断的对象
     * @param errMsg 错误信息
     */
    public static void checkPositive(double src, String errMsg) {
        check(src <= 0, errMsg);
    }

    /**
     * 判断数值大于0，如果不大于0则抛出异常
     *
     * @param src    要判断的对象
     * @param errMsg 错误信息
     */
    public static void checkPositive(Long src, String errMsg) {
        long v = null == src ? 0 : src;
        check(v <= 0, errMsg);
    }


    /**
     * 判断数值大于0，如果不大于0则抛出异常
     *
     * @param src    要判断的对象
     * @param errMsg 错误信息
     */
    public static void checkNonNegative(double src, String errMsg) {
        check(src < 0, errMsg);
    }


    /**
     * 检查是否是有效邮箱地址
     *
     * @param email 邮箱地址
     */
    public static void checkEmail(String email) {
        check(!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+"), "邮箱格式非法");
    }


    /**
     * 检查是否是备选值
     *
     * @param value      要校验的值
     * @param errMsg     错误提示信息
     * @param candidates 备选值
     */
    public static void checkContains(int value, String errMsg, int... candidates) {
        if (null == candidates || candidates.length == 0) {
            return;
        }
        for (int v : candidates) {
            if (value == v) {
                return;
            }
        }
        throw new ApiException(BAD_REQUEST, errMsg);
    }


    /**
     * 检查是否是备选值
     *
     * @param value      要校验的值
     * @param errMsg     错误提示信息
     * @param candidates 备选值
     */
    public static void checkContains(String value, String errMsg, String... candidates) {
        if (null == candidates || candidates.length == 0) {
            return;
        }
        for (String v : candidates) {
            if (v.equals(value)) {
                return;
            }
        }
        throw new ApiException(BAD_REQUEST, errMsg);
    }


    private Args() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }

}
