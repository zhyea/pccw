package org.chobit.cm.biz.tools;

import org.springframework.context.ApplicationContext;

public final class BeanKit {


    /**
     * 将管理上下文的applicationContext设置成静态变量，供全局调用
     */
    private static ApplicationContext context;


    public static void init(ApplicationContext context) {
        if (null == BeanKit.context) {
            BeanKit.context = context;
        }
    }


    public static <T> T get(Class<T> c) {
        return context.getBean(c);
    }

    private BeanKit() {
    }

}
