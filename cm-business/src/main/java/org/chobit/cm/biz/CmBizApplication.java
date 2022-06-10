package org.chobit.cm.biz;

import org.chobit.cm.biz.tools.BeanKit;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author robin
 */
@EnableCaching
@SpringBootApplication
@ComponentScan({"org.chobit.cm"})
@MapperScan({"org.chobit.cm"})
public class CmBizApplication {


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(CmBizApplication.class, args);
        BeanKit.init(ctx);
        System.out.println("应用启动成功...");
    }

}
